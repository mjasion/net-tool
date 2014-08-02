package pl.mjasion.httpapi.controllers

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mjasion.httpapi.domain.AccessHistory
import pl.mjasion.httpapi.domain.AccessHistoryRepository
import pl.mjasion.httpapi.domain.Site

import javax.servlet.http.HttpServletRequest

import static Site.IP

@CompileStatic
@RestController
class IpController {

    @Autowired AccessHistoryRepository accessHistoryRepository

    @RequestMapping('/ip')
    String ip(HttpServletRequest request) {
        return getIp(request)
    }

    @RequestMapping('/rest/ip')
    Map restIp(HttpServletRequest request) {
        return [ip: getIp(request)]
    }

    private String getIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr()
        accessHistoryRepository.save(new AccessHistory(ip: ip, accessDate: new Date(), site: IP))
        return ip
    }
}
