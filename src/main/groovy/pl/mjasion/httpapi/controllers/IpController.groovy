package pl.mjasion.httpapi.controllers

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

@CompileStatic
@RestController
class IpController {

    @RequestMapping('/ip')
    Map ip(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return [ip: ipAddress]
    }
}
