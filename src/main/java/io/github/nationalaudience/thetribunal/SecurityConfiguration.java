package io.github.nationalaudience.thetribunal;

import io.github.nationalaudience.thetribunal.constant.Authorities;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/newStudioToDb").hasAnyAuthority(Authorities.ADMIN);
        http.authorizeRequests().antMatchers("/newGameToDb").hasAnyAuthority(Authorities.ADMIN);
        http.authorizeRequests().anyRequest().permitAll();

        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("user");
        http.formLogin().passwordParameter("password");
        http.formLogin().failureUrl("/errorLogin");
        http.formLogin().failureUrl("/errorLogin");

        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

        http.csrf().disable();
    }
}
