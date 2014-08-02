package pl.mjasion.httpapi.controllers

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import pl.mjasion.httpapi.domain.AccessHistory
import pl.mjasion.httpapi.domain.AccessHistoryRepository

import javax.servlet.http.HttpServletRequest

import static pl.mjasion.httpapi.domain.Site.PARAMS

@CompileStatic
@RestController
class ParamsController {

    @Autowired AccessHistoryRepository ipUserRepository

    @RequestMapping(value = '/params', method = RequestMethod.GET)
    Map get(HttpServletRequest request) {
        return [
                method: 'GET',
                params: convertParams(request)
        ]
    }

    @RequestMapping(value = '/params', method = RequestMethod.POST)
    Map post(HttpServletRequest request) {
        return [
                method: 'POST',
                params: convertParams(request)
        ]
    }

    private List<Map> convertParams(HttpServletRequest request) {
        ipUserRepository.save(new AccessHistory(ip: request.getRemoteAddr(), accessDate: new Date(), site: PARAMS))
        request.getParameterMap().collect { String key, String[] value ->
            Map params = [
                    key  : key,
                    value: value
            ]
            if (value.length == 1)
                params.value = value.first()
            return params
        }
    }
}
