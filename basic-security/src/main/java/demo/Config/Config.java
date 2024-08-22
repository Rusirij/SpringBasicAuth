package demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration // Represents configuration class in spring boot
// Extending WebSecurityConfigurerAdapter class allows customizing HTTP security for features, such as endpoints authorization or the authentication manager configuration
public class Config extends WebSecurityConfigurerAdapter {

/*
Overriding configure method of WebSecurityConfigurerAdapter class to provide our own security implementation
Here we chose in-memory auth for simplicity. We could have chosen to get the username and password from database
{noop} represents plain-text password.
*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Administrator").password("{noop}password").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("Rusiri").password("{noop}password2").roles("USER");
    }

    /*
    * We override another method from WebSecurityConfigurerAdapter to provide additional features on http security
    * Disable CSRF token
    * Enable all requests to be fully authenticated. We can add a filter here if we want to filter some specific urls from security
     * Used basic authentication here (username and password)
     *
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

     // We added one public url, which will be accessible to all without security (starting with /rest/public . all other requests will be authenticated
        http.authorizeRequests().antMatchers("/rest/public/**").permitAll().
                anyRequest().authenticated().and().
                httpBasic();
    }


}
