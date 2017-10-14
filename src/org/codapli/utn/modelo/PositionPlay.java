package org.codapli.utn.modelo;

import java.util.List;

import org.codapli.utn.controlador.ServoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PositionPlay extends Thread {

	@Autowired
	ServoController servoController;

	public void runMotion(List<Position> motion) {
		if (motion.size() > 1) {
			try {
				servoController.getServoHombro()
						.goToPosition(getFirstPosition(servoController.getServoHombro().getServoNombre(), motion));
				servoController.getServoCodo()
						.goToPosition(getFirstPosition(servoController.getServoCodo().getServoNombre(), motion));
				servoController.getServoPinza()
						.goToPosition(getFirstPosition(servoController.getServoPinza().getServoNombre(), motion));
				servoController.getServoBase()
						.goToPosition(getFirstPosition(servoController.getServoBase().getServoNombre(), motion));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (Position p : motion) {
				switch (p.getNombre()) {
				case "HOMBRO":
					servoController.getServoHombro().setAngle(p.getAngule());
					break;
				case "CODO":
					servoController.getServoCodo().setAngle(p.getAngule());
					break;
				case "PINZA":
					servoController.getServoPinza().setAngle(p.getAngule());
					break;
				case "BASE":
					servoController.getServoBase().setAngle(p.getAngule());
					break;

				default:
					break;
				}
				try {
					Thread.sleep(servoController.getServoHombro().getTime());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private float getFirstPosition(String servoName, List<Position> motion) {
		float angInit = 90;
		for (Position position : motion) {
			if (position.getNombre().equals(servoName)) {
				angInit = position.getAngule();
				break;
			}
		}
		return angInit;
	}

}
