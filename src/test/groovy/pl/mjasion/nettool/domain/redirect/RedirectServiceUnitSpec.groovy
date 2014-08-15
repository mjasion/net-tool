package pl.mjasion.nettool.domain.redirect

import pl.mjasion.nettool.service.TimeService
import spock.lang.Specification
import spock.lang.Unroll

class RedirectServiceUnitSpec extends Specification {
    Date now = new Date()
    String hostname = 'google.com'
    String redirectUrl = 'www.google.com'
    String ip = '1.2.3.4'
    String defaultUrl = 'www.default.pl'
    Redirect defaultRedirect = new Redirect(accessUrl: RedirectService.DEFAULT_REDIRECT_KEY, redirectUrl: defaultUrl)

    TimeService timeService = Stub(TimeService) { now() >> now }
    RedirectRepository redirectRepository = Mock(RedirectRepository) {
        findOne(hostname) >> new Redirect()
        findOne(RedirectService.DEFAULT_REDIRECT_KEY) >> defaultRedirect
    }
    RedirectHistoryRepository redirectHistoryRepository = Mock(RedirectHistoryRepository)
    RedirectService redirectService = new RedirectService(
            redirectHistoryRepository: redirectHistoryRepository,
            redirectRepository: redirectRepository,
            timeService: timeService
    )

    def 'should return redirect url for given hostname'() {
        given:
        String hostname = 'mjasion.pl'
        redirectRepository.findOne(hostname) >> new Redirect(accessUrl: hostname, redirectUrl: redirectUrl)

        when:
        String redirect = redirectService.getRedirect(hostname, ip)

        then:
        redirect == "redirect:$redirectUrl"
    }

    @Unroll
    def 'should return redirect url when accessing with #accessUrl and global domain wildcard exist'() {
        given:
        redirectRepository.findOne(wildCard) >> new Redirect(accessUrl: wildCard, redirectUrl: redirectUrl)

        when:
        String redirect = redirectService.getRedirect(accessUrl, ip)

        then:
        redirect == "redirect:$redirectUrl"

        where:
        accessUrl                | wildCard
        'nettool.mjasion.pl'     | '*.mjasion.pl'
        'www.nettool.mjasion.pl' | '*.mjasion.pl'
        'www.nettool.mjasion.pl' | '*.nettool.mjasion.pl'
    }

    def 'should save redirect history'() {
        given:
        redirectRepository.findOne(hostname) >> new Redirect(accessUrl: hostname, redirectUrl: redirectUrl)

        when:
        String redirect = redirectService.getRedirect(hostname, ip)

        then:
        1 * redirectHistoryRepository.save(_)
    }


    def 'should return default redirect when hostname is top level domain'() {
        when:
        String redirect = redirectService.getRedirect('*.com', ip)

        then:
        redirect == "redirect:$defaultUrl"
    }

    def 'should return default redirect when cannot match access url'() {
        when:
        String redirect = redirectService.getRedirect('donotfindme.com', ip)

        then:
        redirect == "redirect:$defaultUrl"
    }
}
