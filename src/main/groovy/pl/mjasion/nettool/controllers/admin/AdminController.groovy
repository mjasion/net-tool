package pl.mjasion.nettool.controllers.admin

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import pl.mjasion.nettool.domain.redirect.Redirect
import pl.mjasion.nettool.domain.redirect.RedirectHistory
import pl.mjasion.nettool.domain.redirect.RedirectHistoryRepository
import pl.mjasion.nettool.domain.redirect.RedirectRepository
import pl.mjasion.nettool.domain.watchers.WatchedPage
import pl.mjasion.nettool.domain.watchers.WatchedPageRepository
import pl.mjasion.nettool.service.TimeService

import javax.servlet.http.HttpServletRequest

import static org.springframework.data.domain.Sort.Direction.DESC
import static pl.mjasion.nettool.domain.redirect.RedirectService.DEFAULT_REDIRECT_KEY

@Slf4j
@Controller
@RequestMapping('/admin')
class AdminController {
    @Autowired RedirectHistoryRepository redirectHistoryRepository
    @Autowired RedirectRepository redirectRepository
    @Autowired TimeService timeService
    @Autowired WatchedPageRepository watchedUrlRepository

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
            @PageableDefault(page = 0, size = 50, sort = ["accessDate"], direction = DESC) Pageable pageable
    ) {
        return redirectHistoryRepository.findAll(pageable)
    }

    @ResponseBody
    @RequestMapping('/redirects')
    List redirects() {
        return redirectRepository.findAll(new Sort(new Sort.Order(DESC, 'created')))
    }

    @ResponseBody
    @RequestMapping(value = '/redirect', method = RequestMethod.PUT)
    void putRedirect(@RequestParam String accessUrl, @RequestParam String redirectUrl) {
        log.info("Creating redirect: $accessUrl -> $redirectUrl")
        redirectRepository.save(new Redirect(redirectUrl: redirectUrl, accessUrl: accessUrl, created: timeService.now()))
    }

    @ResponseBody
    @RequestMapping(value = '/redirect', method = RequestMethod.DELETE)
    void deleteRedirect(@RequestParam String accessUrl) {
        assert !accessUrl.equals(DEFAULT_REDIRECT_KEY)
        log.info("Removing redirect: $accessUrl")
        redirectRepository.delete(accessUrl)
    }

    @ResponseBody
    @RequestMapping('/watchedPages')
    List watchedPages() {
        return watchedUrlRepository.findAll(new Sort(new Sort.Order(DESC, 'created')))
    }

    @ResponseBody
    @RequestMapping(value = '/watchedPage', method = RequestMethod.PUT)
    void putWatchedPage(@RequestParam String url) {
        log.info("Creating watched page: $url")
        watchedUrlRepository.save(new WatchedPage(url: url, created: timeService.now()))
    }

    @ResponseBody
    @RequestMapping(value = '/watchedPage', method = RequestMethod.DELETE)
    void deleteWatchedPage(@RequestParam String url) {
        log.info("Removing watched page: $url")
        watchedUrlRepository.delete(url)
    }
}
