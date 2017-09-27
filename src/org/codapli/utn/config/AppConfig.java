package org.codapli.utn.config;

import org.codapli.utn.controlador.ServoCodapli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Autowired
	PropertiesApp propertiesApp;

	@Bean(name = "servoHombro")
	public ServoCodapli getServoHombro() {
		return new ServoCodapli("HOMBRO", Integer.valueOf(propertiesApp.getProperty("servoHombro")),
				Float.valueOf(propertiesApp.getProperty("homboAngMax")),
				Float.valueOf(propertiesApp.getProperty("homboAngMin")));
	}

	@Bean(name = "servoCodo")
	public ServoCodapli getServoCodo() {
		return new ServoCodapli("CODO", Integer.valueOf(propertiesApp.getProperty("servoCodo")),
				Float.valueOf(propertiesApp.getProperty("codoAngMax")),
				Float.valueOf(propertiesApp.getProperty("codoAngMin")));
	}

	@Bean(name = "servoBase")
	public ServoCodapli getServoBase() {
		return new ServoCodapli("BASE", Integer.valueOf(propertiesApp.getProperty("servoBase")),
				Float.valueOf(propertiesApp.getProperty("baseAngMax")),
				Float.valueOf(propertiesApp.getProperty("baseAngMin")));
	}

	@Bean(name = "servoPinza")
	public ServoCodapli getServoPinza() {
		return new ServoCodapli("PINZA", Integer.valueOf(propertiesApp.getProperty("servoPinza")),
				Float.valueOf(propertiesApp.getProperty("pinzaAngMax")),
				Float.valueOf(propertiesApp.getProperty("pinzaAngMin")));
	}

}
