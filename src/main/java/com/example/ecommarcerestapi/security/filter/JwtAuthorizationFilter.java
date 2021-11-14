package com.example.ecommarcerestapi.security.filter;

import com.example.ecommarcerestapi.security.service.CustomUserDetailService;
import com.example.ecommarcerestapi.security.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final CustomUserDetailService userDetailService;
    private final JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("processing authentication for '{}'", request.getRequestURL());


        String requestHeader = request.getHeader(this.tokenHeader);

        String userName = null;
        String authToken = null;
        if (Objects.nonNull(requestHeader) && requestHeader.startsWith("Bearer ")) {
            try {
                userName = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                log.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                log.warn("the token is expired and not valid anymore", e);
            }
        } else {
            log.warn("couldn't find bearer string , will ignore the header");
        }

        log.debug("checking authentication for user '{}'", userName);

        if (Objects.nonNull(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailService.loadUserByUsername(userName);
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("authorized user '{}', setting security context", userName);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
    }

