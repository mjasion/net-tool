package pl.mjasion.httpapi.controllers

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

@CompileStatic
@RestController
class HeadersController {

    @RequestMapping('/headers')
    Map headers(HttpServletRequest request) {
        Map headers = [:]
        Enumeration<String> headerNames = request.getHeaderNames()
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headers.put(key, value);
        }
        return headers
    }

    @RequestMapping('/user-agent')
    Map userAgent(HttpServletRequest request) {
        return ['user-agent': request.getHeader('user-agent')]
    }


}
