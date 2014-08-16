package pl.mjasion.nettool.conf

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value('${user.admin.password}') String password

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(password).roles("ADMIN") // still in develop :)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers('/admin/**', '/css/**', '/js/**', '/**.html').hasRole('ADMIN')
                .and()
                .requiresChannel().antMatchers('/admin/**', '/css/**', '/js/**', '/**.html').requiresSecure()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
    }
}
