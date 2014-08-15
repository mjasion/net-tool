package pl.mjasion.nettool.controllers

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import pl.mjasion.nettool.domain.accesshistory.AccessHistory
import pl.mjasion.nettool.domain.accesshistory.AccessHistoryRepository
import pl.mjasion.nettool.domain.redirect.RedirectRepository

import javax.servlet.http.HttpServletRequest

import static pl.mjasion.nettool.Profiles.PRODUCTION
import static pl.mjasion.nettool.domain.accesshistory.Site.REDIRECT

@Slf4j
@Controller
@CompileStatic
@Profile(PRODUCTION)
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
