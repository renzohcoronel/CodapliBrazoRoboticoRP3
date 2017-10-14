package org.codapli.utn.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class ServoController {

	@Autowired
	private ApplicationContext context;

	public ServoCodapli getServoHombro() {
		return (ServoCodapli) context.getBean("servoHombro");

	}

	public ServoCodapli getServoCodo() {
		return (ServoCodapli) context.getBean("servoCodo");
	}

	public ServoCodapli getServoPinza() {
		return (ServoCodapli) context.getBean("servoPinza");
	}

	public ServoCodapli getServoBase() {
		return (ServoCodapli) context.getBean("servoBase");
	}

}
