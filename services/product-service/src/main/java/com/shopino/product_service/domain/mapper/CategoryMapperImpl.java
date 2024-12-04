package com.shopino.product_service.domain.mapper;

import com.shopino.product_service.domain.dto.request.CategoryRequest;
import com.shopino.product_service.domain.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapperImpl implements ICategoryMapper {

    @Override
    public Category toCategory(CategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( request.getName() );
        category.description( request.getDescription() );

        return category.build();
    }

    @Override
    public List<Category> toCategories(List<CategoryRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( requests.size() );
        for ( CategoryRequest categoryRequest : requests ) {
            list.add( toCategory( categoryRequest ) );
        }

        return list;
    }
}