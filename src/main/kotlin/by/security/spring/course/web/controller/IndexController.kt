package by.security.spring.course.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.result.view.Rendering

@Controller
class IndexController {


    @RequestMapping("/login")
    fun login():Rendering = Rendering.view("loginForm").build()
}