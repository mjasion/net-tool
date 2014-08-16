package pl.mjasion.nettool.controllers.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping('/admin/login')
class LoginController {

    @RequestMapping('/')
    String loginPage() {
        // TODO use viewresolver :)
        return 'login'
    }
}
