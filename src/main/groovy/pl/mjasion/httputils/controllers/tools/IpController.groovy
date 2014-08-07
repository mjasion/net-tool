package pl.mjasion.httputils.controllers.tools

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mjasion.httputils.domain.accesshistory.AccessHistory
import pl.mjasion.httputils.domain.accesshistory.AccessHistoryRepository

import javax.servlet.http.HttpServletRequest

import static pl.mjasion.httputils.domain.accesshistory.Site.IP

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
