package pl.kozubek.apigamereviewapp.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.kozubek.apigamereviewapp.exception.InvalidNickOrPassword;
import pl.kozubek.apigamereviewapp.service.dto.AuthenticationJwtToken;
import pl.kozubek.apigamereviewapp.service.dto.UserDetailsDto;
import pl.kozubek.apigamereviewapp.service.dto.UserDetailsServiceImpl;

@Service
public class AuthenticationService {
    private final UserDetailsService userDetailsService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserDetailsServiceImpl userDetailsService, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationJwtToken createAuthenticationToken(UserDetailsDto userDetailsDto) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDetailsDto.getUsername(), userDetailsDto.getPassword()
            ));
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            throw new InvalidNickOrPassword();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(userDetailsDto.getUsername());
        String jwtToken = jwtService.generateJWTToken(userDetails);

        return new AuthenticationJwtToken(jwtToken);
    }
}
