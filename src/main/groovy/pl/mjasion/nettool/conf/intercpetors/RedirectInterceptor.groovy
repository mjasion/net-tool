package pl.mjasion.nettool.conf.intercpetors

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import pl.mjasion.nettool.domain.redirect.RedirectService

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@CompileStatic
class RedirectInterceptor extends HandlerInterceptorAdapter {
    @Value('${nettool.admin.host}') String adminHost
    @Autowired RedirectService redirectService

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        def ip = request.getRemoteAddr()
        def hostname = request.getHeader('host')
        if (hostname.contains(adminHost)) {
            super.preHandle(request, response, handler)
            return true
        } else {
            response.sendRedirect(redirectService.getRedirect(hostname, ip))
            return false
        }
    }
}
