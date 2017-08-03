package rs5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import rs5.persistence.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String REALM="TEST_REALM";

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(getUserDetailsService())
                .passwordEncoder(getPasswordEncoder());
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(getAuthEntryPoint())
                .and()
                .csrf()
                .disable()
                .authorizeRequests().antMatchers("/resources/**").permitAll()
                .and();
//                .authorizeRequests().antMatchers("/rest/**").hasRole("ADMIN")
//                .and()
//                .addFilter(getAuthFilter())
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/**").permitAll();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/static/j_spring_security_check")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/Hello/admin")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);

    }
    @Bean
    public BasicAuthenticationEntryPoint getAuthEntryPoint(){
        BasicAuthenticationEntryPoint ep = new BasicAuthenticationEntryPoint();
        ep.setRealmName(REALM);
        return ep;
    }
//    @Bean
//    public DigestAuthenticationEntryPoint getAuthEntryPoint(){
//        DigestAuthenticationEntryPoint ep = new DigestAuthenticationEntryPoint();
//        ep.setRealmName(REALM);
//        ep.setKey("key");
//        ep.setNonceValiditySeconds(1000);
//        return ep;
//    }
//
//    @Bean
//    public DigestAuthenticationFilter getAuthFilter(){
//        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
//        filter.setUserDetailsService(getUserDetailsService());
//        filter.setAuthenticationEntryPoint(getAuthEntryPoint());
//        return filter;
//    }


    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }
}
