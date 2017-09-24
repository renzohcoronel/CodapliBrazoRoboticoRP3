package org.codapli.utn.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControladorServos {

	@Autowired
	ServoHombro servoHombro;
	@Autowired
	ServoCodo servoCodo;
	@Autowired
	ServoPinza servoPinza;
	@Autowired
	ServoBase servoBase;

	public ServoHombro getServoHombro() {
		return servoHombro;
	}

	public ServoCodo getServoCodo() {
		return servoCodo;
	}

	public ServoPinza getServoPinza() {
		return servoPinza;
	}

	public ServoBase getServoBase() {
		return servoBase;
	}

}
