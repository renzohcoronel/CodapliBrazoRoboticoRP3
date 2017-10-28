package org.codapli.utn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.codapli.utn.controlador.ServoController;
import org.codapli.utn.modelo.PositionPlay;
import org.codapli.utn.modelo.PositionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "org.codapli")
public class MainBrazoRobot extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton bHombroUp;
	private JButton bCodoUp;
	private JButton bHombroDown;
	private JButton bCodoDown;
	private JButton bPinzaOpen;
	private JButton bPinzaClose;
	private JButton bRotarLeft;
	private JButton bRotarRight;

	@Autowired
	ServoController controladorServos;
	@Autowired
	PositionRecord positionRecord;
	@Autowired
	PositionPlay positionPlay;

	private JPanel Header;
	private JPanel Control;
	private JPanel pWebCam;
	private JPanel panel_2;
	private JButton bRec;
	private JLabelBlinking jLabelBlink;
	private WebCameraPanel webCamPanel;

	private Thread cameraThread;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MainBrazoRobot.class).headless(false)
				.web(false).run(args);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainBrazoRobot ex = ctx.getBean(MainBrazoRobot.class);
					ex.setMouseListener();
					ex.positionRecord.start();
					ex.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainBrazoRobot() {
		getContentPane().setBackground(Color.BLACK);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setTitle("CODAPLI - Brazo Robotico");
		setBounds(100, 100, 898, 603);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setBounds(512, 72, 0, 0);
		panel.add(horizontalGlue);

		Header = new JPanel();
		Header.setBackground(Color.BLACK);
		Header.setBounds(0, 0, 896, 63);
		panel.add(Header);
		Header.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		ImagePanel jpanelImage = new ImagePanel("/resources/customLogo.jpg");
		Header.add(jpanelImage);
		GridBagLayout gbl_jpanelImage = new GridBagLayout();
		gbl_jpanelImage.columnWidths = new int[] { 0 };
		gbl_jpanelImage.rowHeights = new int[] { 0 };
		gbl_jpanelImage.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_jpanelImage.rowWeights = new double[] { Double.MIN_VALUE };
		jpanelImage.setLayout(gbl_jpanelImage);

		Control = new JPanel();
		Control.setBackground(new Color(0, 102, 153));
		Control.setBounds(27, 72, 192, 306);
		panel.add(Control);
		GridBagLayout gbl_Control = new GridBagLayout();
		gbl_Control.columnWidths = new int[] { 92, 92, 0 };
		gbl_Control.rowHeights = new int[] { 0, 39, 39, 39, 39, 39, 39, 39, 0 };
		gbl_Control.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_Control.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		Control.setLayout(gbl_Control);

		JLabel lblNewLabel_5 = new JLabel("Control Del Brazo");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridwidth = 2;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 0;
		Control.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JLabel lblNewLabel_3 = new JLabel("Hombro");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		Control.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Codo");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 1;
		Control.add(lblNewLabel_4, gbc_lblNewLabel_4);

		bHombroUp = new JButton("Up");
		GridBagConstraints gbc_bHombroUp = new GridBagConstraints();
		gbc_bHombroUp.fill = GridBagConstraints.VERTICAL;
		gbc_bHombroUp.insets = new Insets(0, 0, 5, 5);
		gbc_bHombroUp.gridx = 0;
		gbc_bHombroUp.gridy = 2;
		Control.add(bHombroUp, gbc_bHombroUp);

		bCodoUp = new JButton("Up");
		GridBagConstraints gbc_bCodoUp = new GridBagConstraints();
		gbc_bCodoUp.fill = GridBagConstraints.VERTICAL;
		gbc_bCodoUp.insets = new Insets(0, 0, 5, 0);
		gbc_bCodoUp.gridx = 1;
		gbc_bCodoUp.gridy = 2;
		Control.add(bCodoUp, gbc_bCodoUp);

		bHombroDown = new JButton("Down");
		GridBagConstraints gbc_bHombroDown = new GridBagConstraints();
		gbc_bHombroDown.fill = GridBagConstraints.VERTICAL;
		gbc_bHombroDown.insets = new Insets(0, 0, 5, 5);
		gbc_bHombroDown.gridx = 0;
		gbc_bHombroDown.gridy = 3;
		Control.add(bHombroDown, gbc_bHombroDown);

		bCodoDown = new JButton("Down");
		GridBagConstraints gbc_bCodoDown = new GridBagConstraints();
		gbc_bCodoDown.fill = GridBagConstraints.VERTICAL;
		gbc_bCodoDown.insets = new Insets(0, 0, 5, 0);
		gbc_bCodoDown.gridx = 1;
		gbc_bCodoDown.gridy = 3;
		Control.add(bCodoDown, gbc_bCodoDown);

		JLabel lblNewLabel_1 = new JLabel("Rotar");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 4;
		Control.add(lblNewLabel_1, gbc_lblNewLabel_1);

		bRotarLeft = new JButton("Left");
		GridBagConstraints gbc_bRotarLeft = new GridBagConstraints();
		gbc_bRotarLeft.fill = GridBagConstraints.VERTICAL;
		gbc_bRotarLeft.insets = new Insets(0, 0, 5, 5);
		gbc_bRotarLeft.gridx = 0;
		gbc_bRotarLeft.gridy = 5;
		Control.add(bRotarLeft, gbc_bRotarLeft);

		bRotarRight = new JButton("Right");
		GridBagConstraints gbc_bRotarRight = new GridBagConstraints();
		gbc_bRotarRight.fill = GridBagConstraints.VERTICAL;
		gbc_bRotarRight.insets = new Insets(0, 0, 5, 0);
		gbc_bRotarRight.gridx = 1;
		gbc_bRotarRight.gridy = 5;
		Control.add(bRotarRight, gbc_bRotarRight);

		JLabel lblNewLabel = new JLabel("Pinza");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 6;
		Control.add(lblNewLabel, gbc_lblNewLabel);

		bPinzaOpen = new JButton("Open");
		GridBagConstraints gbc_bPinzaOpen = new GridBagConstraints();
		gbc_bPinzaOpen.fill = GridBagConstraints.VERTICAL;
		gbc_bPinzaOpen.insets = new Insets(0, 0, 0, 5);
		gbc_bPinzaOpen.gridx = 0;
		gbc_bPinzaOpen.gridy = 7;
		Control.add(bPinzaOpen, gbc_bPinzaOpen);

		bPinzaClose = new JButton("Close");
		GridBagConstraints gbc_bPinzaClose = new GridBagConstraints();
		gbc_bPinzaClose.fill = GridBagConstraints.VERTICAL;
		gbc_bPinzaClose.gridx = 1;
		gbc_bPinzaClose.gridy = 7;
		Control.add(bPinzaClose, gbc_bPinzaClose);

		pWebCam = new JPanel();
		pWebCam.setBackground(Color.BLACK);
		pWebCam.setBounds(231, 72, 640, 480);
		panel.add(pWebCam);
		pWebCam.setLayout(null);

		webCamPanel = new WebCameraPanel();
		webCamPanel.setBounds(0, 0, 640, 480);
		cameraThread = new Thread(webCamPanel);
		cameraThread.start();
		pWebCam.add(webCamPanel);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 102, 153));
		panel_2.setBounds(27, 401, 192, 113);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JButton bPlay = new JButton("Play");
		bPlay.setIcon(null);
		bPlay.addActionListener(new ActionListener() {
			private Boolean isPlay = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isPlay) {
					System.out.println("Playing");
					isPlay = false;
					bPlay.setText("Stop");
					bRec.setEnabled(false);
					stateButton(false);
					jLabelBlink.setText("Playing!");
					jLabelBlink.setBackground(Color.GREEN);
					jLabelBlink.setVisible(true);
					jLabelBlink.startBlink();
					positionPlay.start();
					positionPlay.runMotion(positionRecord.getRecord());

				} else {

					System.out.println("Not Playing");
					bPlay.setText("Play");
					isPlay = true;
					bRec.setEnabled(true);
					stateButton(true);
					jLabelBlink.setVisible(false);
					jLabelBlink.stopBlink();
					positionPlay.interrupt();

				}
			}
		});
		bPlay.setBounds(12, 42, 68, 32);
		panel_2.add(bPlay);

		bRec = new JButton("Rec");
		bRec.addActionListener(new ActionListener() {
			private Boolean isRecord = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isRecord) {
					bPlay.setEnabled(false);
					System.out.println("Recording");
					// positionRecord.playRecord();
					isRecord = false;
					bRec.setText("Stop");
					jLabelBlink.setText("Recording!");
					jLabelBlink.setBackground(Color.RED);
					jLabelBlink.setVisible(true);
					jLabelBlink.startBlink();
				} else {
					// positionRecord.stopRecord();
					System.out.println("Not Recording");
					bRec.setText("Rec");
					isRecord = true;
					bPlay.setEnabled(true);
					jLabelBlink.setVisible(false);
					jLabelBlink.stopBlink();
				}
			}
		});
		bRec.setBounds(92, 42, 88, 32);
		panel_2.add(bRec);

		JLabel lblGrabacin = new JLabel("Grabación");
		lblGrabacin.setForeground(Color.WHITE);
		lblGrabacin.setFont(new Font("Dialog", Font.BOLD, 15));
		lblGrabacin.setBounds(50, 15, 88, 15);
		panel_2.add(lblGrabacin);

		jLabelBlink = new JLabelBlinking("Recording");
		jLabelBlink.setForeground(Color.BLUE);
		jLabelBlink.setBackground(Color.RED);
		jLabelBlink.setBounds(50, 86, 78, 15);
		jLabelBlink.setVisible(false);
		panel_2.add(jLabelBlink);

	}

	public void setMouseListener() {

		bCodoUp.addMouseListener(new MouseAdapterButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				positionRecord.saveNewPosition(controladorServos.getServoCodo().getServoNombre(),
						controladorServos.getServoCodo().increase());

			}

		}, controladorServos.getServoCodo().getTime()));

		bHombroUp.addMouseListener(new MouseAdapterButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				positionRecord.saveNewPosition(controladorServos.getServoHombro().getServoNombre(),
						controladorServos.getServoHombro().increase());

			}

		}, controladorServos.getServoHombro().getTime()));
		bHombroDown.addMouseListener(new MouseAdapterButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				positionRecord.saveNewPosition(controladorServos.getServoHombro().getServoNombre(),
						controladorServos.getServoHombro().decrease());

			}

		}, controladorServos.getServoHombro().getTime()));
		bCodoDown.addMouseListener(new MouseAdapterButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				positionRecord.saveNewPosition(controladorServos.getServoCodo().getServoNombre(),
						controladorServos.getServoCodo().decrease());

			}

		}, controladorServos.getServoCodo().getTime()));
		bPinzaOpen.addMouseListener(new MouseAdapterButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				positionRecord.saveNewPosition(controladorServos.getServoPinza().getServoNombre(),
						controladorServos.getServoPinza().decrease());

			}

		}, controladorServos.getServoPinza().getTime()));
		bPinzaClose.addMouseListener(new MouseAdapterButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				positionRecord.saveNewPosition(controladorServos.getServoPinza().getServoNombre(),
						controladorServos.getServoPinza().increase());

			}

		}, controladorServos.getServoPinza().getTime()));
		bRotarLeft.addMouseListener(new MouseAdapterButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				positionRecord.saveNewPosition(controladorServos.getServoBase().getServoNombre(),
						controladorServos.getServoBase().decrease());

			}

		}, controladorServos.getServoBase().getTime()));
		bRotarRight.addMouseListener(new MouseAdapterButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				positionRecord.saveNewPosition(controladorServos.getServoBase().getServoNombre(),
						controladorServos.getServoBase().increase());

			}

		}, controladorServos.getServoBase().getTime()));
	}

	public void stateButton(Boolean state) {
		this.bHombroUp.setEnabled(state);
		this.bHombroDown.setEnabled(state);
		this.bCodoUp.setEnabled(state);
		this.bCodoDown.setEnabled(state);
		this.bPinzaOpen.setEnabled(state);
		this.bPinzaClose.setEnabled(state);
		this.bRotarLeft.setEnabled(state);
		this.bRotarRight.setEnabled(state);
	}
}
