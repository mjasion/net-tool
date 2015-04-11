package pl.mjasion.nettool.cron

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import pl.mjasion.nettool.domain.watchers.WatchedPage
import pl.mjasion.nettool.domain.watchers.WatchedPageRepository

@Service
@Slf4j
@CompileStatic
class PageWatcher {

    @Autowired WatchedPageRepository watchedUrlRepository

    @Scheduled(cron = '${nettool.cron.heroku}')
    void execute() {
        log.info('Executing page watcher')
        List<WatchedPage> watchedPages = watchedUrlRepository.findAll()
        log.info("Pages to watch: ${watchedPages*.url}")
        watchedPages*.url.each { String url ->
            url.toURL().text
        }
        log.info('Page watcher finished')
    }

}
