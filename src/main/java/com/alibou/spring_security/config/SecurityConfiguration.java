package com.alibou.spring_security.config;

import com.alibou.spring_security.modules.user.entities.Post;
import com.alibou.spring_security.modules.user.entities.User;
import com.alibou.spring_security.modules.user.enums.Role;
import com.alibou.spring_security.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
//
//    @Autowired
//    private final ApplicationConfig applicationConfig;
//    @Autowired
//    private final UserRepository userRepository;


//    @Bean
//    @Transactional
//    public String generateUserRoles() {
//        System.out.println("Generating user roles");
        // Có thể trả về một giá trị nếu cần
//        User admin = new User("admin", "admin", "admin@gmail.com", applicationConfig.passwordEncoder().encode("123"), Role.ADMIN);
//        User user = new User("user", "user", "user@gmail.com", applicationConfig.passwordEncoder().encode("123"), Role.USER);
//        userRepository.save(admin);
//        userRepository.save(user);
//        admin.addPost(new Post("Admin never tweets"));
//        user.addPost(new Post("User never tweets"));
//        return "User roles generated";
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // hàm này khởi tạo khi chạy project và khi gửi request sẽ đi qua đây trước dự vào các request được setup ở dươới
        System.out.println("6");
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler) // được implement và check lại trong LogoutService
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        );
        return http.build();
    }
}
