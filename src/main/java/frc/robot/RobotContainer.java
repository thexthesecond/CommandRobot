package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class RobotContainer {
    public Joystick joy = new Joystick(0);

    public RobotContainer() {

        configureBindings();
    }

    private void configureBindings() {
    }
}
