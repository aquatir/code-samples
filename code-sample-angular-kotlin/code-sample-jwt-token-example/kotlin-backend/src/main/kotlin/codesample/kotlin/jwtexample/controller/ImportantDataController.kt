package codesample.kotlin.jwtexample.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ImportantDataController {

    @GetMapping("/data")
    fun getData() = "data"

    @GetMapping("/userData")
    @PreAuthorize("hasRole('USER')")
    fun getUserDate() = "userData"

    @GetMapping("/adminData")
    @PreAuthorize("hasRole('ADMIN')")
    fun getAdminDate() = "adminData"

    @GetMapping("/userOrAdminData")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getUserOrAdminData() = "userOrAdminData"
}