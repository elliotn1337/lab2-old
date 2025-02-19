package lab2_old;
import labb1_objekt.Vehicle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame{
    private static final int X = 800;
    private static final int Y = 800;

    // The controller member
    CarController carC;

    DrawPanel drawPanel = new DrawPanel(X, Y-240);

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JPanel angPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    JSpinner angSpinner = new JSpinner();
    int gasAmount = 0;
    int angAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");
    JLabel angLabel = new JLabel("Preffered anglus");

    JButton turnLeftButton = new JButton("TurnLeft");
    JButton turnRightButton = new JButton("TurnRight");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton scaniaRaiseButton = new JButton("Scania Lift Bed");
    JButton scaniaLowerButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public CarView(String framename, CarController cc){
        this.carC = cc;
        initComponents(framename);
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        SpinnerModel angModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        70, //max
                        1);//step


        angSpinner = new JSpinner(angModel);
        angSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                angAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });
        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        angPanel.add(angLabel, BorderLayout.WEST);
        angPanel.add(angSpinner, BorderLayout.EAST);

        this.add(gasPanel);
        this.add(angPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(scaniaRaiseButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(scaniaLowerButton, 5);
        controlPanel.add(turnLeftButton, 6);
        controlPanel.add(turnRightButton, 7);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary
        gasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carC.gas(gasAmount);
            }
        });
        brakeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carC.brake(gasAmount);
            }
        });
        turnLeftButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                carC.turnLeft();
            }
        });
        turnRightButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                carC.turnRight();
            }
        });
        turboOnButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                carC.turboOn();
            }
        });
        turboOffButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                carC.turboOff();
            }
        });
        startButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){
                carC.startEngine();
            }
        });
        stopButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                carC.stopEngine();
            }
        });
        scaniaRaiseButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {carC.raise(angAmount);}

        });
        scaniaLowerButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {carC.lower(angAmount);}

        });

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}