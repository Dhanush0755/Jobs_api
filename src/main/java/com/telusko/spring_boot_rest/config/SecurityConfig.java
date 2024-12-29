package com.telusko.spring_boot_rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {    // if we use this it won't check application.properties
//
//        UserDetails user = User
//                            .withDefaultPasswordEncoder()
//                            .username("navin")
//                            .password("n@123")
//                            .roles("USER")
//                            .build();
//
//        UserDetails admin = User
//                .withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin@789")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
}


/*
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
     // http.formLogin(Customizer.withDefaults());    //because we use stateless
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
*/

/*

   => Let’s break down each line of this Spring Security configuration code:

1. http.csrf(customizer -> customizer.disable());
        Purpose: Disables Cross-Site Request Forgery (CSRF) protection.
        Explanation: CSRF protection helps prevent unauthorized actions from being performed on behalf of authenticated users.
                     Disabling CSRF might be appropriate in stateless applications, such as those using APIs, where CSRF protection is not necessary because authentication is typically handled via tokens or other mechanisms.
                     In the code, customizer -> customizer.disable() is a lambda function that disables CSRF protection by setting the CSRF configuration to disabled.

2. http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        Purpose: Configures authorization for HTTP requests.
        Explanation: This line specifies that every HTTP request must be authenticated.
                     The request.anyRequest().authenticated() method means that no matter what the URL or endpoint is, the user must be authenticated to access it.
                     This provides a basic level of security where all endpoints require the user to be logged in.

3. // http.formLogin(Customizer.withDefaults()); //because we use stateless
        Purpose: Commented out code that configures form-based login.
        Explanation: http.formLogin(Customizer.withDefaults()); would enable form-based login, where users submit a username and password via an HTML form.
                     This is commented out because the application is configured to be stateless, which means it does not maintain a session between requests. In stateless applications, authentication typically uses token-based mechanisms (like JWT) instead of traditional form-based login.
                     Stateless applications do not use sessions to track authenticated users, which is why form-based login is not appropriate here.

4. http.httpBasic(Customizer.withDefaults());
        Purpose: Configures HTTP Basic Authentication.
        Explanation: HTTP Basic Authentication is a simple authentication scheme where the client sends the username and password in the Authorization header of the HTTP request.
                     http.httpBasic(Customizer.withDefaults()); enables this authentication scheme and is suitable for stateless applications, especially APIs where the client sends credentials in each request.
                     Customizer.withDefaults() sets up the default configuration for HTTP Basic Authentication.

5. http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        Purpose: Configures the session management policy.
        Explanation: session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) specifies that the application does not create or use HTTP sessions.
                     SessionCreationPolicy.STATELESS ensures that the server does not store session data and each request must contain all necessary authentication information (like a token).
                     This is a typical configuration for REST APIs where maintaining session state is not needed, and each request is independently authenticated.

Summary =>
        CSRF Protection: Disabled for stateless applications where tokens are used for authentication instead.
        Authorization: All requests must be authenticated.
        Form-Based Login: Commented out because the application is stateless.
        HTTP Basic Authentication: Enabled for simple authentication where credentials are sent with each request.
        Session Management: Configured to be stateless, meaning no server-side session state is maintained.

This configuration is typical for a stateless API or service where authentication is managed using tokens or headers rather than traditional sessions.

 */




/*

UserDetailsService userDetailsService() Method:

This method creates and configures an instance of UserDetailsService, which is used by Spring Security to retrieve user-related data.
It provides a way for Spring Security to perform authentication by loading user details from an in-memory store (in this case, hardcoded values).


1. Purpose: Provides an in-memory user details service for authentication in a Spring Security setup.
2. In-Memory Storage: Hardcoded user credentials are used, suitable for development and testing.
3. Default Password Encoding: Uses default password encoding which is basic and should not be used in production.
4. Static Setup: By using this method, user details are not loaded from external sources like application.properties, making it suitable for environments where persistent or dynamic configuration is not required.

This approach is straightforward and convenient for development purposes but should be replaced with a more secure and flexible authentication mechanism for production environments.

*/



/*
Purpose: Configures an AuthenticationProvider for Spring Security to handle user authentication.

Key Components:

1. Dependency Injection: Injects a UserDetailsService that provides user details for authentication.
2. DaoAuthenticationProvider: Uses this class to authenticate users by retrieving user details from the UserDetailsService.
3. Password Encoder: Sets NoOpPasswordEncoder (which means no password encoding is performed, suitable only for development).
4. Outcome: Sets up an authentication provider that checks user credentials against an in-memory or database-based user store, using plain text passwords. For production, a more secure password encoder should be used.

Summary
=> Inbuilt Behavior: If you use UserDetailsService without explicitly configuring an AuthenticationProvider, Spring Security uses an internal DaoAuthenticationProvider that works with your UserDetailsService to handle authentication.
=> Custom Configuration: Explicitly defining an AuthenticationProvider allows for custom settings and behavior, providing more flexibility in handling authentication.
In summary, UserDetailsService is automatically integrated with a default AuthenticationProvider (typically DaoAuthenticationProvider), but you can explicitly configure or customize the AuthenticationProvider if needed for more complex scenarios.
*/



//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());  // mentioning no encoder is being used