package codesample.kotlin.controller

import codesample.kotlin.service.DbUserDetailsService
import codesample.kotlin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class UserController (@Autowired private val userService: UserService){

    @GetMapping("/login")
    fun login() = "login"

    @GetMapping("/logout")
    fun logout() = "logout"

    @GetMapping("/login-error")
    fun loginError(model: Model): String {
        model.addAttribute("loginError", true)
        return "login"
    }

    @GetMapping("/secure/user")
    fun secureUser(): ModelAndView {
        val mav = ModelAndView("hello")

        val user = userService.getCurrentAuthenticatedUser()

        mav.addObject("userName", user.username)
        mav.addObject("userPassword", user.password)
        mav.addObject("userSecondName", user.secondName)

        return mav
    }

    @PostMapping("/secure/user")
    fun createUser(@RequestParam username: String,
                   @RequestParam password: String,
                   @RequestParam secondName: String): ModelAndView {
        val mav = ModelAndView("hello")
        val user = userService.createNewAndSave(username, password, secondName)

        mav.addObject("userName", user.username)
        mav.addObject("userPassword", user.password)

        return mav
    }
}