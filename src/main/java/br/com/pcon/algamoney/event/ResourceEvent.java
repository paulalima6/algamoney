package br.com.pcon.algamoney.event;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class ResourceEvent extends ApplicationEvent implements Serializable {

	/**	 serialVersionUID	 */
	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;

	private Long codigo;

	public ResourceEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
	}

	public HttpServletResponse getresponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}
}
