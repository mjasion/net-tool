package pl.mjasion.httputils.controllers

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import pl.mjasion.httputils.domain.accesshistory.AccessHistory
import pl.mjasion.httputils.domain.accesshistory.AccessHistoryRepository
import pl.mjasion.httputils.domain.redirect.RedirectRepository

import javax.servlet.http.HttpServletRequest

import static pl.mjasion.httputils.domain.accesshistory.Site.REDIRECT

@Slf4j
@Controller
@CompileStatic
class RedirectController {

    @Autowired AccessHistoryRepository accessHistoryRepository
    @Autowired RedirectRepository redirectRepository

    @RequestMapping('/**')
    String redirect(HttpServletRequest request) {
        def ip = request.getRemoteAddr()
        def hostname = request.getHeader('host')
        log.info(hostname)
        accessHistoryRepository.save(new AccessHistory(ip: ip, accessDate: new Date(), site: REDIRECT))
        return 'redirect:https://github.com/mjasion/http-api'
    }
}
