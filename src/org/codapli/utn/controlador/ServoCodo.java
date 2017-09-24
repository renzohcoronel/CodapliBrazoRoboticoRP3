package org.codapli.utn.controlador;

import org.codapli.utn.config.PropertiesApp;
import org.springframework.stereotype.Component;

import com.diozero.Servo;
import com.diozero.util.RuntimeIOException;

@Component
public class ServoCodo {

	protected float max;
	protected float min;
	protected Servo servo;

	public ServoCodo() throws RuntimeIOException {
		PropertiesApp prop = new PropertiesApp();
		this.servo = new Servo(Integer.valueOf(prop.getProperty("servoCodo")), 1.5f);
		setMax(Float.valueOf(prop.getProperty("codoAngMax")));
		setMin(Float.valueOf(prop.getProperty("codoAngMin")));

		System.out.println("INIT ---> SERVO CODO GPIO:" + servo.getGpio() + " INICIAL:" + servo.getAngle() + " MAX:"
				+ max + " MIN:" + min);
	}

	public void increase() {
		if (this.servo.getAngle() < max) {
			this.servo.setAngle(this.servo.getAngle() + 1);
			System.out.println("SERVO CODO MAX:" + max + " MIN:" + min + " ACTUAL:" + this.servo.getAngle());
		} else {
			System.out.println("SERVO CODO --> TOPE EN MAX:" + max + " ACTUAL:" + this.servo.getAngle());
		}
	}

	public void decrease() {
		if (this.servo.getAngle() > min) {
			this.servo.setAngle(this.servo.getAngle() - 1);
			System.out.println("SERVO CODO MAX:" + max + " MIN:" + min + " ACTUAL:" + this.servo.getAngle());
		} else {
			System.out.println("SERVO CODO  --> TOPE EN MIN:" + min + " ACTUAL:" + this.servo.getAngle());
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
