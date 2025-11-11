package org.zabalburu.recyclon.CDI;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;

@Data
@RequestScoped
@Named
public class MensajeCDI {
	//Mensaje de alerta que modificaremos dependiendo de las necesidades en el controller por ejemplo
	//		mensajeCDI.setRole("alert alert-info");
	//		mensajeCDI.setMessage("Cita eliminada con ÉXITO!");
	
	private String role="ALERT_INFO";
	private String message="";
	public MensajeCDI() {
		// TODO Auto-generated constructor stub
	}

}
