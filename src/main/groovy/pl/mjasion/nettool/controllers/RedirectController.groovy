package pl.mjasion.nettool.controllers

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import pl.mjasion.nettool.domain.redirect.RedirectService

import javax.servlet.http.HttpServletRequest

import static pl.mjasion.nettool.Profiles.PRODUCTION

@Slf4j
@Controller
@CompileStatic
@Profile(PRODUCTION)
class RedirectController {

    @Autowired RedirectService redirectService

    @RequestMapping('/**')
    String redirect(HttpServletRequest request) {
        def ip = request.getRemoteAddr()
        def hostname = request.getHeader('host')
        return redirectService.getRedirect(hostname, ip)
    }
}
