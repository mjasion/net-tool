package pl.mjasion.nettool.bootstrap

import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import pl.mjasion.nettool.domain.redirect.Redirect
import pl.mjasion.nettool.domain.redirect.RedirectHistory
import pl.mjasion.nettool.domain.redirect.RedirectHistoryRepository
import pl.mjasion.nettool.domain.redirect.RedirectRepository

import static pl.mjasion.nettool.Profiles.DEVELOP
import static pl.mjasion.nettool.domain.redirect.RedirectService.DEFAULT_REDIRECT_KEY

@Component
@TypeChecked
class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired RedirectRepository redirectRepository

    @Override
    void onApplicationEvent(ContextRefreshedEvent event) {
        if (redirectRepository.findOne(DEFAULT_REDIRECT_KEY) == null) {
            redirectRepository.save(new Redirect(
                    accessUrl: DEFAULT_REDIRECT_KEY,
                    created: new Date(),
                    redirectUrl: 'localhost:8080'
            ))
        }
    }
}

@Component
@Profile(DEVELOP)
@TypeChecked
class DevelopBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired RedirectHistoryRepository redirectHistoryRepository

    private List<String> accessUrls = ['nettool.mjasion.pl', '*.github.io', 'mjasion.pl']
    private List<String> redirectUrls = ['http://nettool.github.com', 'http://nettool.herokuapp.com', 'http://mjasion.github.io']

    @Override
    void onApplicationEvent(ContextRefreshedEvent event) {
        saveRedirectHistory()
    }

    void saveRedirectHistory() {
        if (redirectHistoryRepository.count() < 100) {
            (1..500).each {
                Collections.shuffle(accessUrls)
                Collections.shuffle(redirectUrls)
                redirectHistoryRepository.save(new RedirectHistory(
                        accessDate: new Date(),
                        accessUrl: accessUrls.first(),
                        redirectUrl: redirectUrls.first()
                ))
            }
        }
    }
}
