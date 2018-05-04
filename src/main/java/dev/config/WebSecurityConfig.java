/**
 * 
 */
package dev.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.model.ViewCollaborateur;
import dev.service.CollaborateurService;

/**
 * @author Alexis Darcy
 *
 */
@Configuration
@CrossOrigin
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CollaborateurService collaborateurService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder)
				.usersByUsernameQuery("select MATRICULE, MOT_DE_PASSE, EST_ACTIF from COLLABORATEUR where MATRICULE=?")
				.authoritiesByUsernameQuery("select MATRICULE,ROLE from COLLABORATEUR where MATRICULE = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll()
				.successHandler(successHandler()).failureHandler(failureHandler()).and().logout()
				.logoutSuccessHandler(onLogoutSuccess()).permitAll().and().csrf().disable().cors();
	}


	/**
	 * @return
	 */
	private LogoutSuccessHandler onLogoutSuccess() {
		return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			String message;
			if (SecurityContextHolder.getContext().getAuthentication() == null) {
				message = "success";
			} else {
				message = "error";
			}

			String json = mapper
					.writeValueAsString(message);
			response.getWriter().write(json);
		};
	}

	/**
	 * @return
	 */
	private AuthenticationFailureHandler failureHandler() {
		return (HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) -> {
				response.setContentType("text/html;charset=UTF-8");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"Authentication Failed. Wrong username or password or both");

		};
	}

	/**
	 * @return
	 */
	private AuthenticationSuccessHandler successHandler() {
		return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
			response.setContentType("application/json");
			ObjectMapper mapper = new ObjectMapper();
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			ViewCollaborateur viewCollaborateur = collaborateurService.collaborateurIdentifier(name);
			String json = mapper
					.writeValueAsString(viewCollaborateur);
			response.getWriter().write(json);
		};
	}
}