package com.fastFood.config;

import com.fastFood.repositories.ClientRepository;
import com.fastFood.services.authentications.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

   @Autowired
   public TokenService tokenService;

   @Autowired
   public ClientRepository clientRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);
        if(token != null)
        {
            var login = tokenService.validateToken(token);
            UserDetails client = clientRepository.findUserByEmail(login);

            var authentication = new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {

        var authHeader = request.getHeader("Authorization");
        if(authHeader == null)
            return null;

        return authHeader.replace("Bearer ", "");
    }


}
