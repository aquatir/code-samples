package codesample.kotlin.jwtexample.controller

import codesample.kotlin.jwtexample.dto.StringReqResp
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ImportantDataController {

    @GetMapping("/data")
    fun getData() = StringReqResp("data success!")

    @GetMapping("/userData")
    @PreAuthorize("hasRole('USER')")
    fun getUserDate() = StringReqResp("userData success!")

    @GetMapping("/adminData")
    @PreAuthorize("hasRole('ADMIN')")
    fun getAdminDate() = StringReqResp("adminData success!")

    @GetMapping("/userOrAdminData")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getUserOrAdminData() = StringReqResp("userOrAdminData success!")
}