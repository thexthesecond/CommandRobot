package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private RobotContainer RoboCont = new RobotContainer();
    public Joystick joy = RoboCont.joy;
    private DriveSystem driveSystem = new DriveSystem();

    public int POV;
    public double Jx, Jy, Jx2, Jy2, TriggerValue;
    public boolean a, b, x;
    public double velocity = 0.5;

    @Override
    public void robotInit() {
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();

        SmartDashboard.putNumber("Magnitude", driveSystem.magnitude);
        SmartDashboard.putNumber("Vel esq.", driveSystem.Lspeed);
        SmartDashboard.putNumber("Vel dir.", driveSystem.Rspeed);
        SmartDashboard.putNumber("Quad.", driveSystem.getQuad(Jx, Jy));
        SmartDashboard.putNumber("POV.", POV);
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        TriggerValue = joy.getRawAxis(2) - joy.getRawAxis(3);
        Jx = driveSystem.Deadzone(joy.getRawAxis(0));
        Jy = driveSystem.Deadzone(-joy.getRawAxis(1));
        Jx2 = driveSystem.Deadzone(-joy.getRawAxis(4));
        Jy2 = driveSystem.Deadzone(joy.getRawAxis(5));

        a = joy.getRawButton(1);
        b = joy.getRawButton(2);
        x = joy.getRawButton(3);

        if (a) velocity = 0.5;
        if (b) velocity = 0.25;
        if (x) velocity = 1;

        POV = joy.getPOV();

        int quad = driveSystem.getQuad(Jx, Jy);
        driveSystem.POV(POV);

        if (POV == -1) {
            driveSystem.JoySpeed(Jx, Jy, quad, TriggerValue);
        }

        if ((Jx != 0 || Jx2 != 0 ) && (Jx == 0 && Jy == 0)) {
            driveSystem.JoySpeed(Jx2, Jy2, quad, TriggerValue);
        }

        driveSystem.set(driveSystem.R_motor1, driveSystem.L_motor1, velocity);
    }
}