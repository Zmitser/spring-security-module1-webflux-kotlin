package by.security.spring.course.config.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain


@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("pass")
                .roles("USER")
                .build()
        return MapReactiveUserDetailsService(user)
    }

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
                .authorizeExchange()
                .pathMatchers("/delete/**")
                .hasAuthority("ADMIN")
                .anyExchange().authenticated()
                .and()
                .csrf().disable()
                .httpBasic().and()
                .formLogin()
        return http.build()
    }
}