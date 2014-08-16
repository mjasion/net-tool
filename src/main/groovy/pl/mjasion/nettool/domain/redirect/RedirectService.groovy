package pl.mjasion.nettool.domain.redirect

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.mjasion.nettool.service.TimeService

@Slf4j
@Service
@CompileStatic
class RedirectService {
    @Autowired RedirectRepository redirectRepository
    @Autowired RedirectHistoryRepository redirectHistoryRepository
    @Autowired TimeService timeService

    public static final String DEFAULT_REDIRECT_KEY = 'default'
    private static final String WILDCARD_PREFIX = '*.'


    String getRedirect(String hostname, String ip) {
        log.info("Finding redirect for: $hostname")
        Redirect redirect = findRedirect(hostname) ?: redirectRepository.findOne(DEFAULT_REDIRECT_KEY)
        log.info("Redirect for $hostname is: ${redirect.redirectUrl}")
        saveRedirectHistory(hostname, redirect.redirectUrl, ip)
        return "redirect:$redirect.redirectUrl"
    }

    private Redirect findRedirect(String hostname) {
        String domain = hostname.toLowerCase()
        Redirect redirect = redirectRepository.findOne(domain)
        while (!redirect && canCreateUpperLevelWildcard(domain)) {
            domain = getWildCardDomain(domain)
            redirect = redirectRepository.findOne(domain)
        }
        return redirect ?: redirectRepository.findOne(DEFAULT_REDIRECT_KEY)
    }

    boolean canCreateUpperLevelWildcard(String domain) {
        return !domain.matches('^\\*\\.[a-z]+$')
    }

    private String getWildCardDomain(String domain) {
        return domain.startsWith(WILDCARD_PREFIX) ? getUpperLevelWildcard(domain) : createWildCard(domain)
    }

    private String getUpperLevelWildcard(String domain) {
        return domain.replaceFirst('^\\*\\.[a-z0-9-_]+\\.', WILDCARD_PREFIX)
    }

    private String createWildCard(String domain) {
        return domain.replaceFirst('^[a-z0-9-_]+', '*')
    }

    private void saveRedirectHistory(String hostname, String redirectUrl, String ip) {
        redirectHistoryRepository.save(new RedirectHistory(
                accessUrl: hostname,
                redirectUrl: redirectUrl,
                ip: ip,
                accessDate: timeService.now()
        ))
    }
}
