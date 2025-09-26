package com.example.demo.DTO;

import java.util.List;

public record UserInput(
    String fullname,
    String email,
    String password,
    String phone,
    List<Long> categoryIds
) {}
