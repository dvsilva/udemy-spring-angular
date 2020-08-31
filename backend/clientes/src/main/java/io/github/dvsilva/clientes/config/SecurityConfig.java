package io.github.dvsilva.clientes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.dvsilva.clientes.service.UsuarioService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// criar referencias em memoria
		// auth.inMemoryAuthentication()
		//	.withUser("fulano").password("123").roles("USER");
		
		// buscar no banco de dados
		auth.userDetailsService(usuarioService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // desabilita o csrf usando na seguranca de apps web
			.cors() // habilitar o cors
		.and()
			.sessionManagement() // forma de gerenciamento de sessao
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // nao vai manter sessao, vai ser via token em toda a requisicao
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
