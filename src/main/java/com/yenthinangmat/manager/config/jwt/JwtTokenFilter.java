package com.yenthinangmat.manager.config.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtTokenFilter extends OncePerRequestFilter {
    static final Logger logger= LoggerFactory.getLogger(JwtTokenFilter.class);
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    MyUDService myUDService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token=getJwt(request);
            if(token!=null&& jwtTokenProvider.validateToken(token)){
                String username=jwtTokenProvider.getUserNameFromJWT(token);
                UserDetails userDetails= myUDService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails,
                        null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        catch (Exception e){
            logger.error("Hello world!");
        }
        filterChain.doFilter(request,response);
    }
    private String getJwt(HttpServletRequest request){
        String token=null;
        String rawCookie=request.getHeader("Cookie");
        String[] rawCookieParams=rawCookie.split(";");
        for(String cookie:rawCookieParams){
            if(cookie.trim().startsWith("jwt")){
                token=cookie.trim().replace("jwt=","");

            }
        }
        if(token!=null&&token.startsWith("Bearer")){
            return token.replace("Bearer","");
        }
        return null;
    }
}
