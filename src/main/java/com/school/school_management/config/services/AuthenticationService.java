//package com.school.school_management.config.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class AuthenticationService {
//
//
//        private RestTemplate restTemplate;
//
//    public AuthenticationService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public boolean authenticate(String token) {
//        ResponseEntity<User> userResponseEntity = restTemplate.postForEntity(("http://localhost:8081/users/validate/" + token),
//                null, User.class);
//        if (userResponseEntity.getBody() != null) {
//            return true;
//        }
//        return false;
//    }
//
//}


//working authenticationService
package com.school.school_management.config.services;

import com.school.school_management.config.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    private final RestTemplate restTemplate;

    @Autowired
    public AuthenticationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public boolean authenticate(String token) {
        try {
            ResponseEntity<User> response = restTemplate.postForEntity(
                    "http://localhost:8081/users/validate/" + token,
                    null,
                    User.class
            );

            System.out.println("Auth API Status: " + response.getStatusCode());
            System.out.println("Auth API Response: " + response.getBody());

            return response.getBody() != null; // Only return true if response contains user data
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return false;
        }
    }

}
