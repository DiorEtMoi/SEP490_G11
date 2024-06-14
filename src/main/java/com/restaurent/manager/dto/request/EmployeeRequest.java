package com.restaurent.manager.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {
    String username;
    String password;
    String employeeName;
    String email;
    String phoneNumber;
    Long restaurantId;
    Long roleId;
}