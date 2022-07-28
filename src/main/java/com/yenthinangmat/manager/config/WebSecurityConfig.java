package com.yenthinangmat.manager.config;

import com.yenthinangmat.manager.config.jwt.JwtEntryPoint;
import com.yenthinangmat.manager.config.jwt.JwtTokenFilter;
import com.yenthinangmat.manager.config.jwt.MyUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired MyUDService myUDService;
    @Autowired JwtEntryPoint jwtEntryPoint;
    @Bean
    public JwtTokenFilter jwtTokenFilter(){
        return new JwtTokenFilter();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(myUDService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                cors().and().csrf().disable().
//                exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and().
//                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                authorizeRequests().
                antMatchers("/","/signup","/api/user/**","/api/getproduct/**","/api/productsell/**","/api/auth/login").permitAll().
                antMatchers("/employee/**","/api/cart/**","/api/pnkitem","/api/employee/**").hasRole("EMPLOYEE").
                antMatchers(HttpMethod.PUT,"/api/cart/**").hasRole("EMPLOYEE").
                antMatchers(HttpMethod.POST,"/api/pnkitem/**").hasRole("EMPLOYEE").
                antMatchers("/admin/**","/api/combotemp/**","/api/product/**","/api/admin/**").hasRole("ADMIN").
                anyRequest().authenticated().and().
                formLogin().loginPage("/login").defaultSuccessUrl("/employee/sell").permitAll().and().logout().permitAll();
//        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**");
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.
//                cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and().
//                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
//                authorizeHttpRequests().
//                antMatchers("/","/signup","/api/user/**","/api/getproduct/**","/api/productsell/**").permitAll().
//                antMatchers("/employee/**","/api/cart/**","/api/pnkitem","/api/employee/**").hasAnyAuthority("ROLE_EMPLOYEE").
//                antMatchers("/admin/**","/api/combotemp/**","/api/product/**","/api/admin/**").hasAuthority("ROLE_ADMIN")
//                .anyRequest().authenticated().and()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/employee/sell").permitAll().and().logout().permitAll();
//        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.authorizeHttpRequests().anyRequest().permitAll();
//        return http.build();
//    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**");
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new MyUDService();
//
//    }
//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
}
