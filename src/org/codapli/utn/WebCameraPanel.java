package org.codapli.utn;

import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamLock;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class WebCameraPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	private Webcam webcam;
	private WebcamPanel webcamPanel;

	public WebCameraPanel() {
		webcam = Webcam.getDefault();
		WebcamLock lock = webcam.getLock();
		lock.unlock();
		if (webcam != null) {
			webcam.setViewSize(WebcamResolution.VGA.getSize());

			webcamPanel = new WebcamPanel(webcam);
			webcamPanel.setFPSDisplayed(true);
			webcamPanel.setDisplayDebugInfo(true);
			webcamPanel.setImageSizeDisplayed(true);
			webcamPanel.setMirrored(true);
			add(webcamPanel);

		} else {
			System.out.println("No webcam detected");
		}

	}

	@Override
	public void run() {
		Thread t = new Thread() {

			@Override
			public void run() {
				webcamPanel.start();
			}
		};
		t.setName("example-starter");
		t.setDaemon(true);
		t.start();

	}
}