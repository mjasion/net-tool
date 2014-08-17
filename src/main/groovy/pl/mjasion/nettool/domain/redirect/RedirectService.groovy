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

    public static final String DEFAULT_REDIRECT_KEY = 'DEFAULT'
    private static final String WILDCARD_PREFIX = '*.'


    String getRedirect(String hostname, String ip) {
        String domain = getDomain(hostname)
        log.info("Finding redirect for: $domain")
        Redirect redirect = findRedirect(domain) ?: redirectRepository.findOne(DEFAULT_REDIRECT_KEY)
        log.info("Redirect for $hostname is: ${redirect.redirectUrl}")
        saveRedirectHistory(hostname, redirect.redirectUrl, ip)
        return redirect.redirectUrl
    }

    private String getDomain(String hostname) {
        def domain = hostname.split(':').first().toLowerCase()
        return isNotIpAddress(domain) ? domain : 'localhost'
    }

    private boolean isNotIpAddress(String domain) {
        return !domain.matches('^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$')
    }

    private Redirect findRedirect(String domain) {
        Redirect redirect = redirectRepository.findOne(domain)
        while (!redirect && canCreateUpperLevelWildcard(domain)) {
            domain = getWildCardDomain(domain)
            redirect = redirectRepository.findOne(domain)
        }
        return redirect ?: redirectRepository.findOne(DEFAULT_REDIRECT_KEY)
    }

    boolean canCreateUpperLevelWildcard(String domain) {
        return !domain.matches('^\\*\\.[a-z]+$') && !domain.matches('\\*')
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
                accessDate: timeService.now()
        ))
    }
}
