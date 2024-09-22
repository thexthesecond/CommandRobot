package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveSystem;

public class RobotContainer {
    public Joystick joy = new Joystick(0);

    public RobotContainer() {

        configureBindings();
    }

    private void configureBindings() {
    }
}
