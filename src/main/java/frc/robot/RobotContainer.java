package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
    private final DriveSubsystem driveSubsystem;
    private final Joystick joystick;

    public RobotContainer(DriveSubsystem driveSubsystem, Joystick joystick) {
        this.driveSubsystem = driveSubsystem;
        this.joystick = joystick;

        configureBindings();
    }

    private void configureBindings() {}

    public DriveSubsystem getDriveSubsystem() {
        return driveSubsystem;
    }

    public Joystick getJoystick() {
        return joystick;
    }
}
