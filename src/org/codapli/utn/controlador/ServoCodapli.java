package org.codapli.utn.controlador;

import org.springframework.stereotype.Component;

import com.diozero.Servo;
import com.diozero.util.RuntimeIOException;

@Component
public class ServoCodapli {

	protected float max;
	protected float min;
	protected Servo servo;
	protected String servoName;
	protected int time;

	private ServoCodapli() {

	}

	public ServoCodapli(String nombre, Integer gpio, float max, float min, int time) throws RuntimeIOException {
		this();
		this.servoName = nombre;
		// this.servo = new Servo(gpio, 1.5f);
		setMax(max);
		setMin(min);
		setTime(time);
		System.out.println(toString());

	}

	public float increase() {
		if (servo.getAngle() < max) {
			servo.setAngle(servo.getAngle() + 1);
			return servo.getAngle();

		} else {
			return servo.getAngle();
		}
	}

	public float decrease() {
		if (servo.getAngle() > min) {
			servo.setAngle(servo.getAngle() - 1);
			return servo.getAngle();
		} else {
			return servo.getAngle();
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

	public String getServoNombre() {
		return servoName;
	}

	public void setServoNombre(String servoNombre) {
		this.servoName = servoNombre;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setAngle(float ang) {
		this.servo.setAngle(ang);
	}

	public void goToPosition(float ang) throws InterruptedException {

		while (this.servo.getAngle() <= ang || this.servo.getAngle() >= ang) {
			if (this.servo.getAngle() <= ang)
				increase();
			if (this.servo.getAngle() >= ang)
				decrease();

			Thread.sleep(this.time);
		}
	}

	@Override
	public String toString() {
		return "ServoCodapli [max=" + max + ", min=" + min + ", servoName=" + servoName + ", time=" + time + "ANG:"
				+ "]";
	}

}
