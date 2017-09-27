package org.codapli.utn.controlador;

import org.codapli.utn.modelo.Posicion;
import org.codapli.utn.modelo.PosicionesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.diozero.Servo;
import com.diozero.util.RuntimeIOException;

@Component
public class ServoCodapli {

	protected float max;
	protected float min;
	protected Servo servo;
	protected String servoNombre;

	@Autowired
	PosicionesDAO posicionesDAO;

	private ServoCodapli() {

	}

	public ServoCodapli(String nombre, Integer gpio, float max, float min) throws RuntimeIOException {
		this();
		this.servoNombre = nombre;
		// this.servo = new Servo(gpio, 1.5f);
		setMax(max);
		setMin(min);

		// System.out.println("INIT ---> SERVO " + servoNombre + " GPIO:" +
		// servo.getGpio() + " INICIAL:"
		// + servo.getAngle() + " MAX:" + max + " MIN:" + min);
	}

	public void increase() {
		if (servo.getAngle() < max) {
			servo.setAngle(servo.getAngle() + 1);
			// System.out.println("SERVO " + servoNombre + " MAX:" + max + "
			// MIN:" + min + " ACTUAL:" + servo.getAngle());
			posicionesDAO.save(new Posicion(servoNombre));
		} else {
			// System.out.println("SERVO " + servoNombre + " --> TOPE EN MAX:" +
			// max + " ACTUAL:" + servo.getAngle());
		}
	}

	public void decrease() {
		if (servo.getAngle() > min) {
			servo.setAngle(servo.getAngle() - 1);
			// System.out.println("SERVO " + servoNombre + " MAX:" + max +
			// "MIN:" + min + " ACTUAL:" + servo.getAngle());
			posicionesDAO.save(new Posicion(servoNombre));
		} else {
			// System.out.println("SERVO " + servoNombre + " --> TOPE EN MIN:" +
			// min + " ACTUAL:" + servo.getAngle());
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
