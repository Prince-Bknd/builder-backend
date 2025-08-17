package com.project.builder.service;

import com.project.builder.dto.AdminDTO;
import com.project.builder.dto.LoginDTO;
import com.project.builder.dto.UserDTO;
import com.project.builder.response.CustomApiResponse;

public interface AuthenticationService {
    
    CustomApiResponse<?> registerAdmin(AdminDTO adminDTO);
    
    CustomApiResponse<?> registerUser(UserDTO userDTO);
    
    CustomApiResponse<?> login(LoginDTO loginDTO);
    
    CustomApiResponse<?> validateToken(String token);
}
