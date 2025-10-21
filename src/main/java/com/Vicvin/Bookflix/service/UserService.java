package com.Vicvin.Bookflix.service;

import com.Vicvin.Bookflix.dto.LoginRequest;
import com.Vicvin.Bookflix.dto.SignupRequest;

public interface UserService {

    String signup(SignupRequest request);
    String login(LoginRequest request);
    String logout(String token);
}
