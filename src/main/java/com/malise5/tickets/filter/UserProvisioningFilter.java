package com.malise5.tickets.filter;

import java.io.IOException;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.malise5.tickets.entity.User;
import com.malise5.tickets.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserProvisioningFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Retrieve the current user's authentication details from the Spring Security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //validation to ensure the authentication is not null and is an instance of UserDetails/ jwt
        // If the user is authenticated and the principal is a Jwt, you can proceed with your logic
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Jwt jwt) {

            UUID keyCloakId = UUID.fromString(jwt.getSubject());

            if (!userRepository.existsById(keyCloakId)){
                // If the user does not exist in the database, you can create a new user
                // Here you would typically extract user details from the JWT and save them to your database
                User user = new User();
                user.setId(keyCloakId);
                user.setName(jwt.getClaimAsString("preferred_username")); // Assuming 'name' is a claim in your JWT
                user.setEmail(jwt.getClaimAsString("email")); // Assuming 'email' is a claim in your JWT
                userRepository.save(user);
            }

            filterChain.doFilter(request, response);

        }
    }

}
