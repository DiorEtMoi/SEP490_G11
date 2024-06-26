package com.restaurent.manager.controller;

import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.enums.DISH_ORDER_STATE;
import com.restaurent.manager.service.IDishOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RequestMapping(value = "/api/dish-order")
@SecurityRequirement(name = "bearerAuth")
public class DishOrderController {
    IDishOrderService dishOrderService;
    @PutMapping(value = "/{dishOrderId}")
    public ApiResponse<DishOrderResponse> updateStatusDishOrderById(@PathVariable Long dishOrderId,@RequestBody DISH_ORDER_STATE status){
        return ApiResponse.<DishOrderResponse>builder()
                .result(dishOrderService.changeStatusDishOrderById(dishOrderId,status))
                .build();
    }
}
