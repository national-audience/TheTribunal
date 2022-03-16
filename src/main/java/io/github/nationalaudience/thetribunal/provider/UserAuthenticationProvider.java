package io.github.nationalaudience.thetribunal.provider;

import io.github.nationalaudience.thetribunal.constant.Authorities;
import io.github.nationalaudience.thetribunal.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    private static final GrantedAuthority ADMIN = new SimpleGrantedAuthority(Authorities.ADMIN);
    private static final GrantedAuthority USER = new SimpleGrantedAuthority(Authorities.USER);

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserRepository repository;

    public UserAuthenticationProvider(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var user = repository.findByUsername(authentication.getName());
        if (user.isEmpty() || !user.get().getUsername().equals(authentication.getName())) {
            throw new BadCredentialsException("User not found");
        }

        var password = authentication.getCredentials().toString();
        if (!encoder.matches(password, user.get().getPasswordHash())) {
            throw new BadCredentialsException("Wrong password");
        }

        var roles = user.get().isAdmin()
                ? List.of(USER, ADMIN)
                : List.of(USER);
        return new UsernamePasswordAuthenticationToken(user.get().getUsername(), password, roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
