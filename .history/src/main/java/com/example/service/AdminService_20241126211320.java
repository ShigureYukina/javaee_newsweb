package com.example.service;

import com.example.model.Admin;

public interface AdminService {
    Admin login(String username, String password);
} 