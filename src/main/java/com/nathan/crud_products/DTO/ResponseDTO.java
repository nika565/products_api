package com.nathan.crud_products.DTO;

import com.nathan.crud_products.domain.user.User;

public record ResponseDTO(User user, String token) {
}
