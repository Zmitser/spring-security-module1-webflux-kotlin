package by.security.spring.course.config.security

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers


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
       return http
                .authorizeExchange()
                .pathMatchers("/login").permitAll()
                .pathMatchers("/favicon.ico", "/css/**", "/webjars/**").permitAll()
                .pathMatchers("/delete/**").hasAuthority("ADMIN")
                .anyExchange().authenticated()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login")
                .and()
//                .logout().logoutUrl("/logout").and().build()
                .logout().logoutUrl("/logout").requiresLogout(ServerWebExchangeMatchers.pathMatchers(GET,"/logout")).and().build()
    }

}