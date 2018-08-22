package codesample.kotlin.sandbox.springboot.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class ThymeleafController {

    @GetMapping("/user")
    fun getUserInfo() : ModelAndView = ModelAndView("user")

    @PostMapping("/data")
    fun loginXss(@RequestParam username: String) : ModelAndView {
        val mav = ModelAndView("userlogin")
        mav.modelMap.addAttribute("username", username)
        return mav
    }
}