package ulaval.glo2003.product.api;

import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.QueryParam;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.assembler.ProductAssembler;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductsResponse;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductFilters;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.domain.ProductWithSeller;
import ulaval.glo2003.product.domain.ProductIdFactory;
import ulaval.glo2003.product.service.ProductService;

import java.net.URI;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
  private static final String ENDPOINT = "products";
  private final ProductFactory productFactory;
  private final ProductService productService;
  private final ProductAssembler productAssembler;
  private final ProductRequestValidator productRequestValidator;
  private final ProductIdFactory productIdFactory;
  private final ProductFiltersFactory productFiltersFactory;

  public ProductResource(
          ProductFactory productFactory,
          ProductService productService,
          ProductAssembler productAssembler,
          ProductIdFactory productIdFactory,
          ProductRequestValidator productRequestValidator,
          ProductFiltersFactory productFiltersFactory) {
    this.productFactory = productFactory;
    this.productService = productService;
    this.productAssembler = productAssembler;
    this.productIdFactory = productIdFactory;
    this.productRequestValidator = productRequestValidator;
    this.productFiltersFactory = productFiltersFactory;
  }

  @POST
  public Response createProduct(ProductRequest productRequest, @HeaderParam("X-Seller-Id") String sellerIdString) {
    try {
      this.productRequestValidator.validate(productRequest);

      Product product = this.productFactory.create(productRequest, sellerIdString);

      this.productService.addProduct(product);

      URI uri = URI.create(ENDPOINT + "/" + product.getStringId());
      return Response.created(uri).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getProductById(@PathParam("id") String id) {
    try {
      ProductId productId = this.productIdFactory.create(id);

      ProductWithSeller productWithSeller = this.productService.getProductWithSeller(productId);

      ProductResponse productResponse = this.productAssembler.toResponse(productWithSeller);

      return Response.ok().entity(productResponse).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFilteredProducts(@QueryParam("sellerId") String sellerId,
                                      @QueryParam("title") String title,
                                      @QueryParam("categories") List<String> categories,
                                      @QueryParam("minPrice") Integer minPrice,
                                      @QueryParam("maxPrice") Integer maxPrice) {

    try {
      this.productRequestValidator.validatePrices(minPrice, maxPrice);

      ProductFilters productFilters = this.productFiltersFactory.create(
              sellerId, title, categories, minPrice, maxPrice
      );

      List<ProductWithSeller> products = this.productService.getFilteredProducts(productFilters);

      ProductsResponse productsResponse = this.productAssembler.toProductsResponse(products);

      return Response.ok().entity(productsResponse).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }
}