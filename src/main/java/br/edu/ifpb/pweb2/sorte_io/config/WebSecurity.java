package br.edu.ifpb.pweb2.sorte_io.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/apostadores/form", "/controladores/form", "/apostadores", "/controladores",  
                                        "/css/**", "/images/**", "/videos/**").permitAll()
            .antMatchers("/sorteios/**").hasRole("CONTROLLER")
            .antMatchers("/apostas/**").hasRole("USER")
            .anyRequest()
            .authenticated()
            .and()
            .formLogin( 
                form -> form.loginPage("/login")
                            .defaultSuccessUrl("/home", true)
                            .permitAll()
            )
            .logout(
                logout -> logout.logoutUrl("/logout")
            );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(encoder);
    }

}
