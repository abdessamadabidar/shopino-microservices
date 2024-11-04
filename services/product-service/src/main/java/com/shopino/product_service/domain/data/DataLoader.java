package com.shopino.product_service.domain.data;

import com.shopino.product_service.application.ports.input.CreateCategoriesUseCase;
import com.shopino.product_service.application.ports.input.CreateProductsUseCase;
import com.shopino.product_service.application.ports.input.FindAllCategoriesUseCase;
import com.shopino.product_service.domain.dto.request.CategoryRequest;
import com.shopino.product_service.domain.dto.request.ProductRequest;
import com.shopino.product_service.domain.model.Category;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CreateProductsUseCase createProductsUseCase;
    private final CreateCategoriesUseCase createCategoriesUseCase;
    private final FindAllCategoriesUseCase findAllCategoriesUseCase;
    private Faker faker;

    public DataLoader(CreateProductsUseCase createProductsUseCase, CreateCategoriesUseCase createCategoriesUseCase, FindAllCategoriesUseCase findAllCategoriesUseCase) {
        this.createProductsUseCase = createProductsUseCase;
        this.createCategoriesUseCase = createCategoriesUseCase;
        this.findAllCategoriesUseCase = findAllCategoriesUseCase;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        /*
         *   Generate data for category table
         */

        List<CategoryRequest> categoryRequestsList = new ArrayList<>();
        List<String> categoryNames = List.of(
                "Electronics & Gadgets",
                "Home Appliances",
                "Beauty & Personal Care",
                "Sports & Outdoors",
                "Fashion & Apparel",
                "Health & Wellness",
                "Automotive Accessories",
                "Books & Stationery",
                "Toys & Games",
                "Food & Beverages",
                "Furniture & Decor",
                "Pet Supplies",
                "Baby & Maternity",
                "Jewelry & Watches",
                "Tools & Hardware",
                "Garden & Outdoor Living",
                "Musical Instruments",
                "Office Supplies",
                "Travel & Luggage",
                "Fitness & Training Equipment"
        );

        categoryNames
                .forEach(cName -> {
                    CategoryRequest category = CategoryRequest
                            .builder()
                            .name(cName)
                            .description(faker.lorem().characters(20, 50))
                            .build();

                    categoryRequestsList.add(category);
                });

        createCategoriesUseCase.createCategories(categoryRequestsList);



        /*
         *   Generate data for product table
         */

        // Load product categories
        List<Category> categories = findAllCategoriesUseCase.findAllCategories();
        List<ProductRequest> productRequestList = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            ProductRequest request = ProductRequest
                    .builder()
                    .name(faker.commerce().productName())
                    .description(faker.lorem().sentence(10))
                    .imageUrl(
                            String.format(
                                    "https://prd.place/320?id=%s?padding=100",
                                    faker.random().nextInt(1, 45)
                            )
                    )
                    .price(Double.parseDouble(faker.commerce().price(20, 200)))
                    .availableQuantity(faker.random().nextInt(10, 99))
                    .categoryId(
                            categories
                                    .get(faker.random().nextInt(categories.size() - 1))
                                    .getId()
                    )
                    .build();
            productRequestList.add(request);

        }
        createProductsUseCase.createProducts(productRequestList);
    }
}
