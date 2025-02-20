package lab2_old;
import labb1_objekt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController{
    // member fields:
    boolean running = false;
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();
    Garage<Volvo240> garage;

        //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Saab95(0, 100));
        cc.cars.add(new Scania(0, 200));
        cc.cars.add(new Volvo240(0,0));
        cc.garage = new Garage<Volvo240>();


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();

    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {

                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                System.out.printf("%s\t%s\n", car.getClass(), car.getCurrentSpeed());
                frame.drawPanel.moveit(car, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                if (car.getX() < 0 || car.getX() > 800 || car.getY() < 0 || car.getY() > 800) {
                    //if (car instanceof Volvo240 && car.getX()
                    car.turnLeft();
                    car.turnLeft();
                    //car.stopEngine();
                }

                if (275.0 < car.getX() && car.getX() < 325.0 && 275 < car.getY() && car.getY() < 325){
                    if (car instanceof Volvo240 v) {
                        if (!car.getIsLoaded()){
                            garage.loadVehicle(v);
                            ((Volvo240) car).setX(300);
                            car.setY(300);
                            v.stopEngine();

                        }
                    }

                }
            }
        }
    }
    // Calls the gas method for each car once
    void gas(int amount) {
        if (running){
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }}
    }
    void brake(int amount) {
        if (running){
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars
        ) {
            car.brake(brake);
        }}
    }
    void turnLeft() {for (Vehicle car : cars) {car.turnLeft();}}
    void turnRight() {for (Vehicle car : cars) {car.turnRight();}}
    void turboOn() {for (Vehicle car : cars) {
        if (car instanceof Saab95){
            ((Saab95) car).setTurboOn();}}}

    void turboOff() {for (Vehicle car : cars) {
        if (car instanceof Saab95){
            ((Saab95) car).setTurboOff();}}}

    void startEngine(){
        for (Vehicle car : cars) {
            if (!(running)){car.startEngine(); running = true;}}}
    void stopEngine(){for (Vehicle car : cars) { car.stopEngine(); running = false;}}
    void raise(int amount) {
        for (Vehicle car : cars) {
            if (car instanceof Scania scania) {
                scania.raise(amount);
            }
        }
    }
    void lower(int amount){
        for (Vehicle car: cars){
            if (car instanceof Scania scania){
                scania.lower(amount);
            }
        }
    }

}
