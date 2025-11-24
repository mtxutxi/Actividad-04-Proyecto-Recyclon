package org.zabalburu.recyclon.cdi;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;

@Data
@RequestScoped
@Named
public class MensajeCDI {
	private String role="ALERT_INFO";
	private String message="";
	public MensajeCDI() {
		// TODO Auto-generated constructor stub
	}
}
