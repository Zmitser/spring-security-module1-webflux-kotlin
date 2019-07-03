package by.security.spring.course.config.security

import by.security.spring.course.domain.repository.UserRepository
import by.security.spring.course.service.security.UserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain


@EnableWebFluxSecurity
class SecurityConfig(val userRepository: UserRepository) {


    @Bean
    fun userDetailsService(): ReactiveUserDetailsService = UserDetailsService(userRepository)

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
                .authorizeExchange()
                .pathMatchers("/favicon.ico", "/css/**", "/webjars/**").permitAll()
                .pathMatchers("/login", "/signup", "/user/register").permitAll()
//                .pathMatchers("/delete/**").hasAuthority("ADMIN")
                .anyExchange().authenticated()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login")
                .and()
                .logout().logoutUrl("/logout").and().build()
//                .logout().logoutUrl("/logout").requiresLogout(ServerWebExchangeMatchers.pathMatchers(GET,"/logout")).and().build()
    }

}