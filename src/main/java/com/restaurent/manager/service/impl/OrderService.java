package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.order.DishOrderRequest;
import com.restaurent.manager.dto.request.order.OrderRequest;
import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.dto.response.order.OrderResponse;
import com.restaurent.manager.entity.Dish;
import com.restaurent.manager.entity.DishOrder;
import com.restaurent.manager.entity.Order;
import com.restaurent.manager.entity.TableRestaurant;
import com.restaurent.manager.enums.DISH_ORDER_STATE;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.DishOrderMapper;
import com.restaurent.manager.mapper.OrderMapper;
import com.restaurent.manager.repository.DishOrderRepository;
import com.restaurent.manager.repository.OrderRepository;
import com.restaurent.manager.repository.TableRestaurantRepository;
import com.restaurent.manager.service.*;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    OrderRepository orderRepository;
    IEmployeeService employeeService;
    ITableRestaurantService tableRestaurantService;
    IRestaurantService restaurantService;
    TableRestaurantRepository tableRestaurantRepository;
    OrderMapper orderMapper;
    IDishService dishService;
    DishOrderMapper dishOrderMapper;
    DishOrderRepository dishOrderRepository;
    @Override
    public OrderResponse createOrder(OrderRequest request) {
        TableRestaurant tableRestaurant = tableRestaurantService.findById(request.getTableId());
        Order order = new Order();
        order.setRestaurant(restaurantService.getRestaurantById(request.getRestaurantId()));
        order.setTableRestaurant(tableRestaurant);
        order.setEmployee(employeeService.findEmployeeById(request.getEmployeeId()));
        order.setOrderDate(LocalDate.now());
        Order orderSaved = orderRepository.save(order);
        tableRestaurant.setOrderCurrent(orderSaved.getId());
        tableRestaurantRepository.save(tableRestaurant);
        return orderMapper.toOrderResponse(orderSaved);
    }

    @Override
    public List<DishOrderResponse> addDishToOrder(Long orderId, List<DishOrderRequest> requestList) {
        Order order = findOrderById(orderId);
        Set<DishOrder> dishOrders = order.getDishOrders();
        for (DishOrderRequest request : requestList){
                DishOrder dishOrder = dishOrderMapper.toDishOrder(request);
                dishOrder.setDish(dishService.findByDishId(request.getDishId()));
                dishOrder.setOrder(order);
                dishOrder.setStatus(DISH_ORDER_STATE.WAITING);
                dishOrders.add(dishOrderRepository.save(dishOrder));
            }
        order.setDishOrders(dishOrders);
        orderRepository.save(order);
        return dishOrderRepository.findDishOrderByOrder_Id(orderId).stream().map(dishOrderMapper::toDishOrderResponse).toList();
    }

    @Override
    public List<DishOrderResponse> findDishByOrderId(Long orderId) {
        return dishOrderRepository.findDishOrderByOrder_Id(orderId).stream().map(dishOrderMapper::toDishOrderResponse).toList();
    }

    @Override
    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        );
    }
}
