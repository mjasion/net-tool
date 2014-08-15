package pl.mjasion.nettool.controllers.admin

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import pl.mjasion.nettool.domain.accesshistory.AccessHistory
import pl.mjasion.nettool.domain.accesshistory.AccessHistoryRepository
import pl.mjasion.nettool.domain.redirect.RedirectHistory
import pl.mjasion.nettool.domain.redirect.RedirectHistoryRepository

import javax.servlet.http.HttpServletRequest

@Slf4j
@Controller
@CompileStatic
@RequestMapping('/admin')
class AdminController {
    @Autowired AccessHistoryRepository accessHistoryRepository
    @Autowired RedirectHistoryRepository redirectHistoryRepository

    @RequestMapping('')
    String admin(HttpServletRequest request) {
        if(!request.getRequestURI().endsWith('/'))
            return "redirect:${request.getRequestURI()}/"
        return 'index'
    }

    @RequestMapping('/partials/{partialName}.html')
    String partialsResolver(@PathVariable('partialName') String partialName) {
        return "partials/$partialName"
    }

    @ResponseBody
    @RequestMapping('/redirect-history')
    Page<RedirectHistory> redirectHistory(
            @PageableDefault(page = 0, size = 50, sort = ["accessDate"], direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return redirectHistoryRepository.findAll(pageable)
    }

    @ResponseBody
    @RequestMapping('/access-history')
    Page<AccessHistory> accessHistoryList(
            @PageableDefault(page = 0, size = 50, sort = ["accessDate"], direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return accessHistoryRepository.findAll(pageable)
    }
}
