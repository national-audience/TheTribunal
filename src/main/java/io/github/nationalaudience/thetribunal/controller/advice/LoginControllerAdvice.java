package io.github.nationalaudience.thetribunal.controller.advice;

import io.github.nationalaudience.thetribunal.entity.User;
import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import static io.github.nationalaudience.thetribunal.constant.LoginStaticValues.CACHE_LOGGED_USER;

@ControllerAdvice
public class LoginControllerAdvice {

    private final UserRepository userRepository;

    public LoginControllerAdvice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute(CACHE_LOGGED_USER)
    private User lastSearchQuery() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            var user = userRepository.findByUsername(authentication.getName());
            return user.orElse(null);
        }
        return null;
    }
}
