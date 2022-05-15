package pl.kozubek.apigamereviewapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.kozubek.apigamereviewapp.service.AuthenticationService;
import pl.kozubek.apigamereviewapp.service.dto.AuthenticationJwtToken;
import pl.kozubek.apigamereviewapp.service.dto.UserDetailsDto;
import pl.kozubek.apigamereviewapp.service.dto.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserDetailsServiceImpl userDetailsService) {
        this.authenticationService = authenticationService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/token")
    public AuthenticationJwtToken getAuthenticationToken(@RequestBody UserDetailsDto userDetailsDto) {
        return authenticationService.createAuthenticationToken(userDetailsDto);
    }

    @PostMapping("/register")
    public Long setUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
        return userDetailsService.saveUser(userDetailsDto);
    }

}
