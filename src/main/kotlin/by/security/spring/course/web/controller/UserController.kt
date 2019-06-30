package by.security.spring.course.web.controller

import by.security.spring.course.domain.model.User
import by.security.spring.course.domain.repository.UserRepository
import by.security.spring.course.web.model.Message
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.result.view.Rendering
import reactor.core.publisher.toMono
import javax.validation.Valid


@Controller
@RequestMapping("/")
class UserController(private val userRepository: UserRepository) {

    @RequestMapping(method = [RequestMethod.GET])
    fun list(): Rendering = Rendering.view("/users/list").modelAttribute("users", userRepository.findAll()).build()


    @RequestMapping("{id}")
    fun view(@PathVariable("id") id: Long): Rendering {
        userRepository.findById(id).let {
            return Rendering.view("users/view")
                    .modelAttribute("user", it)
                    .build()
        }
    }

    @RequestMapping(params = ["form"], method = [RequestMethod.GET])
    fun createForm(@ModelAttribute user: User): Rendering = Rendering.view("users/form").build()


    @RequestMapping(method = [RequestMethod.POST])
    fun create(@Valid user: User, result: BindingResult): Rendering? {
        if (result.hasErrors()) {
            return Rendering.view("users/form").modelAttribute("formErrors", result.allErrors).build()
        }
        this.userRepository.save(user).let {
          return  Rendering.redirectTo("").modelAttribute("globalMessage","Successfully created a new user").build()
        }
    }

    @RequestMapping(value = ["delete/{id}"])
    fun delete(@PathVariable("id") id: Long): Rendering? {
        return userRepository.deleteById(id).let {
            Rendering.redirectTo("/").build()
        }
    }

    @RequestMapping(value = ["modify/{id}"], method = [RequestMethod.GET])
    fun modifyForm(@PathVariable("id") id: Long): Rendering {
        userRepository.findById(id).let {
            return Rendering.view("users/form").modelAttribute("user", it).build()
        }
    }
}
