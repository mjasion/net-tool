package pl.mjasion.httpapi.controllers

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@CompileStatic
@Controller
class GlobalController {

    @RequestMapping('/*')
    String root() {
        return 'redirect:https://github.com/mjasion/http-api'
    }
}
