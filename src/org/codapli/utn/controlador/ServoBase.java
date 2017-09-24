package org.codapli.utn.controlador;

import org.codapli.utn.config.PropertiesApp;
import org.springframework.stereotype.Component;

import com.diozero.Servo;

@Component
public class ServoBase {

	protected float max;
	protected float min;
	protected Servo servo;

	public ServoBase() {
		PropertiesApp prop = new PropertiesApp();
		this.servo = new Servo(Integer.valueOf(prop.getProperty("servoBase")), 1.5f);
		setMax(Float.valueOf(prop.getProperty("baseAngMax")));
		setMin(Float.valueOf(prop.getProperty("baseAngMin")));

		System.out.println("INIT ---> SERVO BASE GPIO:" + servo.getGpio() + " INICIAL:" + servo.getAngle() + " MAX:"
				+ max + " MIN:" + min);
	}

	public void increase() {
		if (servo.getAngle() < max) {
			servo.setAngle(servo.getAngle() + 1);
			System.out.println("SERVO BASE MAX:" + max + " MIN:" + min + " ACTUAL:" + servo.getAngle());
		} else {
			System.out.println("SERVO BASE --> TOPE EN MAX:" + max + " ACTUAL:" + servo.getAngle());
		}
	}

	public void decrease() {
		if (servo.getAngle() > min) {
			servo.setAngle(servo.getAngle() - 1);
			System.out.println("SERVO BASE MAX:" + max + " MIN:" + min + " ACTUAL:" + servo.getAngle());
		} else {
			System.out.println("SERVO BASE --> TOPE EN MIN:" + min + " ACTUAL:" + servo.getAngle());
		}
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max > 180.0f ? 180.0f : max;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min < 0.1f ? 0.1f : min;
	}

}
