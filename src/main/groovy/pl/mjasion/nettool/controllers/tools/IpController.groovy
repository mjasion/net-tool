package pl.mjasion.nettool.controllers.tools

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

@CompileStatic
@RestController
class IpController {

    @RequestMapping('/ip')
    String ip(HttpServletRequest request) {
        return getIp(request)
    }

    @RequestMapping('/rest/ip')
    Map restIp(HttpServletRequest request) {
        return [ip: getIp(request)]
    }

    private String getIp(HttpServletRequest request) {
        return request.getHeader('x-forwarded-for')
    }
}
