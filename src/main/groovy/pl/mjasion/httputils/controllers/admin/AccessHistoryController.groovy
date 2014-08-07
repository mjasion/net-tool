package pl.mjasion.httputils.controllers.admin

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.mjasion.httputils.domain.accesshistory.AccessHistory
import pl.mjasion.httputils.domain.accesshistory.AccessHistoryRepository

import static org.springframework.data.domain.Sort.Direction.DESC

@CompileStatic
@RestController
class AccessHistoryController {
    @Autowired AccessHistoryRepository accessHistoryRepository

    @RequestMapping('/access-history')
    Page<AccessHistory> accessHistoryList() {
        def pageRequest = new PageRequest(0, 5000, new Sort(new Sort.Order(DESC, 'accessDate')))
        return accessHistoryRepository.findAll(pageRequest)
    }
}
