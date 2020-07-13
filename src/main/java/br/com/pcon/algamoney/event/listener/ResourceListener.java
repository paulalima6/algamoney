package br.com.pcon.algamoney.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pcon.algamoney.event.ResourceEvent;

@Component
public class ResourceListener implements ApplicationListener<ResourceEvent>{

	@Override
	public void onApplicationEvent(ResourceEvent resourceEvent) {
		HttpServletResponse response = resourceEvent.getresponse();
		Long codigo = resourceEvent.getCodigo();
		
		addHeaderLocation(response, codigo);
	}

	private void addHeaderLocation(HttpServletResponse response, Long codigo) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{/codigo}")
				.buildAndExpand(codigo).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
