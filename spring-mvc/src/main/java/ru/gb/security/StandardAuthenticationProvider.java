package ru.gb.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Slf4j
@RequiredArgsConstructor
@Component
public class StandardAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final UserDetailsService userDetailsService;
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (!Objects.equals(userDetails.getPassword()/*этот нужно заэнкодить*/, authentication.getCredentials())){
            log.error("Bad credentials for {}", userDetails.getUsername());
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return userDetailsService.loadUserByUsername(username);
    }

/*    @Bean
    public BCryptPasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }*/
}
