package codesample.kotlin.jwtexample.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ImportantDataController {

    @GetMapping("/data")
    fun getData() = "data"
}