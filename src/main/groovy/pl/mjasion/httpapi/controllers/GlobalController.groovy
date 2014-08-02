package pl.mjasion.httpapi.controllers

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import pl.mjasion.httpapi.domain.AccessHistory
import pl.mjasion.httpapi.domain.AccessHistoryRepository

import javax.servlet.http.HttpServletRequest

import static pl.mjasion.httpapi.domain.Site.GLOBAL

@CompileStatic
@Controller
class GlobalController {

    @Autowired AccessHistoryRepository accessHistoryRepository

    @RequestMapping('/**')
    String root(HttpServletRequest request) {
        def ip = request.getRemoteAddr()
        accessHistoryRepository.save(new AccessHistory(ip: ip, accessDate: new Date(), site: GLOBAL))
        return 'redirect:https://github.com/mjasion/http-api'
    }
}
