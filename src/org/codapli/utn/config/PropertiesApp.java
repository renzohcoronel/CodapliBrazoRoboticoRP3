package org.codapli.utn.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.codapli.utn.MainBrazoRobot;

public class PropertiesApp {

	private String filename = "/resources/config.properties";
	private Properties prop;
	private InputStream input = null;

	public PropertiesApp() {
		System.out.println("inicializo las propiedades");
		prop = new Properties();
		input = MainBrazoRobot.class.getResourceAsStream(filename);

		try {

			if (input == null) {
				throw new RuntimeException("No se pudo cargar las propiedades del sistema");
			}

			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getProperty(String name) {
		return prop.getProperty(name);
	}

}
