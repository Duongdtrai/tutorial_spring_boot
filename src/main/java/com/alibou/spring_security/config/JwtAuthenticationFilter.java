package com.alibou.spring_security.config;

import com.alibou.spring_security.modules.auth.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // chua hieu spring boot sẽ tự động hiểu cái này nhu nào
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
            System.out.println("5");
         final String authHeader = request.getHeader("Authorization");
         final String jwt;
         final String userEmail;
         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
             filterChain.doFilter(request, response);
             return;
         }
        System.out.println("authHeader: "+ authHeader);
         jwt = authHeader.substring(7);

         // giai nen jwt
        userEmail =  jwtService.extractUsername(jwt);// to extract the userEmail from JWT Token
        System.out.println("userEmail: "+ userEmail);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            System.out.println("userDetails: " + userDetails);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // chua hieu doan nay
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                logger.error("getAuthorities: " + userDetails.getAuthorities());
                logger.error("authToken 1" + authToken);
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails((request))
                );
                logger.error("authToken 2" + authToken);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        System.out.println("filter chain do filter");
        filterChain.doFilter(request, response);
    }
}
