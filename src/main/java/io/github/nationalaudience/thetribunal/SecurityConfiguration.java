package io.github.nationalaudience.thetribunal;

import io.github.nationalaudience.thetribunal.constant.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel().anyRequest().requiresSecure();

        http.authorizeRequests().antMatchers(StudioDataStaticValues.END_POINT_NEW_STUDIO_TO_DB)
                .hasAnyAuthority(Authorities.ADMIN);
        http.authorizeRequests().antMatchers(StudioDataStaticValues.END_POINT_POST_NEW_STUDIO_TO_DB)
                .hasAnyAuthority(Authorities.ADMIN);
        http.authorizeRequests().antMatchers(StudioDataStaticValues.END_POINT_DELETE_STUDIO_DATA + "/*")
                .hasAnyAuthority(Authorities.ADMIN);
        http.authorizeRequests().antMatchers(GameDataStaticValues.END_POINT_NEW_GAME_TO_DB)
                .hasAnyAuthority(Authorities.ADMIN);
        http.authorizeRequests().antMatchers(GameDataStaticValues.END_POINT_POST_NEW_GAME_TO_DB)
                .hasAnyAuthority(Authorities.ADMIN);
        http.authorizeRequests().antMatchers(GameDataStaticValues.END_POINT_DELETE_GAME_DATA + "/*")
                .hasAnyAuthority(Authorities.ADMIN);

        http.authorizeRequests().antMatchers(UserDataStaticValues.END_POINT_DELETE_USER_DATA + "/*")
                .hasAnyAuthority(Authorities.ADMIN);

        http.authorizeRequests().antMatchers(ReviewsStaticValues.END_POINT_NEW_REVIEW_TO_DB)
                .authenticated();
        http.authorizeRequests().antMatchers(ReviewsStaticValues.END_POINT_POST_NEW_REVIEW_TO_DB)
                .authenticated();
        http.authorizeRequests().antMatchers(UserDataStaticValues.END_POINT_FOLLOW_USER_DATA + "/*")
                .authenticated();
        http.authorizeRequests().antMatchers(UserDataStaticValues.END_POINT_UNFOLLOW_USER_DATA + "/*")
                .authenticated();
        http.authorizeRequests().antMatchers(StudioDataStaticValues.END_POINT_FOLLOW_STUDIO_DATA + "/*")
                .authenticated();
        http.authorizeRequests().antMatchers(StudioDataStaticValues.END_POINT_UNFOLLOW_STUDIO_DATA + "/*")
                .authenticated();
        http.authorizeRequests().antMatchers(ReviewsStaticValues.END_POINT_DELETE_REVIEW_TO_DB)
                .authenticated();

        http.authorizeRequests().anyRequest().permitAll();

        http.formLogin().loginPage(LoginStaticValues.END_POINT_LOGIN);
        http.formLogin().usernameParameter(LoginStaticValues.PARAMETER_USER);
        http.formLogin().passwordParameter(LoginStaticValues.PARAMETER_PASSWORD);
        http.formLogin().failureUrl(LoginStaticValues.END_POINT_ERROR_LOGIN);

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher(LoginStaticValues.END_POINT_LOGOUT))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true);

        //http.csrf().disable();
    }
}
