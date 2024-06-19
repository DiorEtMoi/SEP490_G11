package com.restaurent.manager.mapper;

import com.restaurent.manager.dto.request.dish.DishCategoryRequest;
import com.restaurent.manager.dto.response.DishCategoryResponse;
import com.restaurent.manager.entity.DishCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishCategoryMapper {

    DishCategory toDishCategory(DishCategoryRequest req);
    DishCategoryResponse toDishCategoryResponse(DishCategory dishCategory);
}
