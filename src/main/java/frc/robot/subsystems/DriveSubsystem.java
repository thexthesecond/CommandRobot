package frc.robot.subsystems;

import org.w3c.dom.ls.LSException;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    private final VictorSPX l_motor;
    private final VictorSPX r_motor;
    private final VictorSPX l_motor2;
    private final VictorSPX r_motor2;
    private final Joystick joystick;

    private double Lspeed;
    private double Rspeed;

    public DriveSubsystem(Joystick joystick) {
        this.joystick = joystick;

        r_motor = new VictorSPX(Constants.Ports.R_MOTOR_PORT);
        l_motor2 = new VictorSPX(Constants.Ports.L_MOTOR_PORT2);
        r_motor2 = new VictorSPX(Constants.Ports.R_MOTOR_PORT2);
        l_motor = new VictorSPX(Constants.Ports.L_MOTOR_PORT);

        r_motor.setInverted(true);
        r_motor2.follow(r_motor);

        l_motor2.follow(l_motor);
    }

    public void setPOV(int POV) {

        switch (POV) {
            case 0:
                Lspeed = Rspeed = 1.0;
                break;
            default:
                Lspeed = 0.0;
                Rspeed = 0.0;
                break;
        }
    }

    public void setMotorSpeeds() {
        l_motor.set(ControlMode.PercentOutput, Lspeed);
        r_motor.set(ControlMode.PercentOutput, Rspeed);
    }

    public void SmartDashboard() {
        SmartDashboard.putNumber("Motor Esq", l_motor.getMotorOutputPercent());
        SmartDashboard.putNumber("Motor Dir", r_motor.getMotorOutputPercent());
        SmartDashboard.putNumber("Suposta Vel Esq.", Lspeed);
        SmartDashboard.putNumber("Suposta Vel Dir.", Rspeed);
    }
}
