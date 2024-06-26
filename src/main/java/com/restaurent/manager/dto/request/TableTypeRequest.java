package com.restaurent.manager.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableTypeRequest {
    @NotNull(message = "FIELD_EMPTY")
    String name;
    String imageUrl;
    @Min(value = 4,message = "NUMBER_CHAIRS_TOO_FEW")
    int numberChairs;
}
