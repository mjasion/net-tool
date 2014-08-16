package pl.mjasion.nettool.service

import groovy.transform.CompileStatic
import org.springframework.stereotype.Service

@Service
@CompileStatic
class TimeService {

    Date now() {
        return new Date()
    }
}
