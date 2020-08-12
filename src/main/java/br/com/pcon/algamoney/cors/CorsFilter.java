package br.com.pcon.algamoney.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.pcon.algamoney.config.property.AlgamoneyApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	@Autowired
	private AlgamoneyApiProperty algamoneyApiProperty;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		resp.setHeader("Access-Controll-Allow-Origin", algamoneyApiProperty.getOrigemPermitida());
		resp.setHeader("Access-Controll-Allow-Credentials", "true");	

		if("OPTIONS".equals(req.getMethod()) && algamoneyApiProperty.getOrigemPermitida().equals(req.getHeader("Origin"))) {
			
			resp.setHeader("Access-Controll-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
			resp.setHeader("Access-Controll-Allow-Headers", "Authorization, Content-Type, Accept");
			resp.setHeader("Access-Controll-Allow-Max-Age", "3600");
			
			resp.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(request, response);
		}
		
	}
}
