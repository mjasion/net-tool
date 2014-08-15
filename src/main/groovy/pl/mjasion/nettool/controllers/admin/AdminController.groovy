package pl.mjasion.nettool.controllers.admin

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import pl.mjasion.nettool.domain.accesshistory.AccessHistory
import pl.mjasion.nettool.domain.accesshistory.AccessHistoryRepository

import static org.springframework.data.domain.Sort.Direction.DESC

@Slf4j
@Controller
@CompileStatic
@RequestMapping('/admin')
class AdminController {
    @Autowired AccessHistoryRepository accessHistoryRepository

    @RequestMapping('/')
    String admin() {
        return 'index'
    }

    @RequestMapping('/partials/{partialName}.html')
    String partialsResolver(@PathVariable('partialName') String partialName) {
        return "partials/$partialName"
    }

    @ResponseBody
    @RequestMapping('/access-history')
    Page<AccessHistory> accessHistoryList() {
        def pageRequest = new PageRequest(0, 50, new Sort(new Sort.Order(DESC, 'accessDate')))
        return accessHistoryRepository.findAll(pageRequest)
    }
}
