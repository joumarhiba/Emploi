package com.emploi.security;

import com.emploi.model.Admin;
import com.emploi.model.Company;
import com.emploi.service.AdminService;
import com.emploi.service.CompanyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtHandler jwtHandler;
    private final AdminService adminService;

    private final CompanyService companyService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("AUTHORIZATION");
        final String userRole;
        final String userEmail;
        final String token;
        final boolean isTokenValid;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //extract jwt, username and userrole
        token = authHeader.substring(7);
        userEmail = jwtHandler.extractEmail(token);
        userRole = jwtHandler.extractRole(token);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Admin userDetails = null;
            Company companyDetails = null;
            if ("ADMIN".equals(userRole)) {
                System.out.println("inside jwt filter Admin");
                userDetails = adminService.loadUserByEmail(userEmail);
            } else if ("COMPANY".equals(userRole)) {
                System.out.println("inside jwt filter elseeeee");
                companyDetails = companyService.loadUserByEmail(userEmail);
            }


            if (userDetails != null) {
                isTokenValid = jwtHandler.validateToken(token, userDetails);
                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } else if (companyDetails != null) {
                isTokenValid = jwtHandler.validateTokenCompany(token, companyDetails);
                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(companyDetails, null, companyDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }

            } else {
                System.out.println("something Wrong in JWTAuthFilter");
            }
            filterChain.doFilter(request, response);
        }
    }
}