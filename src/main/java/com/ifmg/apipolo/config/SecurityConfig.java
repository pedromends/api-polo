package com.ifmg.apipolo.config;

import com.ifmg.apipolo.error.CustomAccessDeniedHandler;
import com.ifmg.apipolo.login.JwtAuthenticationFilter;
import com.ifmg.apipolo.service.CustomUserDetailsService;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService myCustomUserDetailService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/user/register","/user/login","/user/hello",
                    "/main-new/list","/presenter-card/list", "/new/list",
                    "/who-we-are/get", "/project/list","/new/get-three",
                    "/image/get-one/{id}", "/news-card/list", "/event/list",
                    "/capacitation/list", "/advantages/list", "/talent/list",
                    "/researcher/list","/open-new/{id}", "/new/show-one/{id}",
                    "img/{id}","/campus/list", "/contact/create",
                    "/researcher/get-by-email", "/edicts/list", "/docs/list", "/FAQ/list",
                    "/docs/download/{filename}", "/edicts/download/{filename}", "/new/search",
                    "/capacitation/get-text", "/user/handshake"
                ).permitAll()
                .requestMatchers("/user/change-permissions", "/user/list")
                .hasAnyRole("CODEMASTER","ADMIN").anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

        .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(myCustomUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setProperty("relaxedQueryChars", "|{}[]");
            }
        });
        return factory;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return myCustomUserDetailService;
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
