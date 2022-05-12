package pl.kozubek.apigamereviewapp.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.kozubek.apigamereviewapp.service.JWTService;
import pl.kozubek.apigamereviewapp.service.dto.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Service
public class JwtRequestConfigurator extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JWTService jwtService;

    public JwtRequestConfigurator(UserDetailsServiceImpl userDetailsService, JWTService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;


        var authPrefix = "Bearer ";
        if (Objects.nonNull(authHeader) && authHeader.startsWith(authPrefix)) {
            jwt = authHeader.substring(authPrefix.length());
            username = jwtService.extractUserName(jwt);
        }
        if (Objects.nonNull(username)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(jwt, userDetails)) {
                var userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                userPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
