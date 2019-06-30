package by.security.spring.course.web.controller

import by.security.spring.course.domain.model.User
import by.security.spring.course.domain.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.result.view.Rendering
import javax.validation.Valid

@Controller
class AccountController(val userRepository: UserRepository){


    @RequestMapping("/login")
    fun login(): Rendering = Rendering.view("loginForm").build()

    @RequestMapping("/signup")
    fun register(): Rendering = Rendering.view("registrationPage").modelAttribute("user", User()).build()


    @RequestMapping(method = [RequestMethod.POST])
    fun create(@Valid user: User, result: BindingResult): Rendering? {
        if (result.hasErrors()) {
            return Rendering.view("registrationPage").modelAttribute("user", user).build()
        }
        userRepository.save(user).let {
            return  Rendering.redirectTo("/login").build()
        }
    }
}