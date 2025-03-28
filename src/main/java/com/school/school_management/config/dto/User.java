package com.school.school_management.config.dto;

import java.util.List;

public class User {
    private String name;
    private String email;
    private String hashedPassword;
    private List<Role> roles;
    private boolean isEmailVerified;
}
