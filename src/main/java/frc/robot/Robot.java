package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveSubsystem;

public class Robot extends TimedRobot {
    private RobotContainer m_robotContainer;
    private final Joystick joy = new Joystick(0);
    private final DriveSubsystem driveSubsystem = new DriveSubsystem(joy);
    private boolean a, b, x;
    private double fixSpeed;

    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer(driveSubsystem, joy);
    }

    @Override
    public void robotPeriodic() {
        driveSubsystem.SmartDashboard();
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {
        driveSubsystem.setPOV(joy.getPOV());
        driveSubsystem.setMotorSpeeds();
    }
}