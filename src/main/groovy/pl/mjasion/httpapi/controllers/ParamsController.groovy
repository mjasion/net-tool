package pl.mjasion.httpapi.controllers

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

@CompileStatic
@RestController
class ParamsController {

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
