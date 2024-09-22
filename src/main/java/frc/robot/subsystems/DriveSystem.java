package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSystem extends SubsystemBase {
    // Sistema de locomoção do robô

    public VictorSPX L_motor1 = new VictorSPX(0);
    public VictorSPX L_motor2 = new VictorSPX(0);
    public VictorSPX R_motor1 = new VictorSPX(0);
    public VictorSPX R_motor2 = new VictorSPX(0);

    public double Lspeed, Rspeed, rad, magnitude;

    public double Deadzone(double val) {
        if (Math.abs(val) > 0.04) return val;
        else return 0;
    }
    
    public DriveSystem() {
        R_motor1.setInverted(true);
        L_motor1.setInverted(false);
        R_motor2.follow(R_motor1);
        L_motor2.follow(L_motor1);

        R_motor2.setInverted(InvertType.FollowMaster);
    }

    double CalcDiff(double rad) {
        return Math.pow(Math.sin(rad), 2);
    }

    public void POV(int POV) {
        switch (POV) {
            case 0: Lspeed = Rspeed = 1;
                break;
            case 45: Lspeed = 1; Rspeed = 0;
                break;
            case 90: Lspeed = 1; Rspeed = -1;
                break;
            case 135: Lspeed = -1; Rspeed = 0;
                break;
            case 180: Lspeed = Rspeed = -1;
                break;
            case 225: Lspeed = 0; Rspeed = -1;
                break;
            case 270: Lspeed = -1; Rspeed = 1;
                break;
            case 315: Lspeed = 0; Rspeed = 1;
                break;
            default: Lspeed = Rspeed = 0;
                break;
        }
    }

    public int getQuad(double x, double y) {
        if (x > 0 && y > 0) return 1;
        if (x < 0 && y > 0) return 2;
        if (x < 0 && y < 0) return 3;
        if (x > 0 && y < 0) return 4;
        else return 0;
    }

    public void JoySpeed(double x, double y, int quad, double TriggerValue) {
        rad = Math.atan2(y, x);
        double diff = CalcDiff(rad);
        magnitude = Math.hypot(x, y);

        switch (quad) {
            case 1: Lspeed = magnitude + diff; Rspeed = diff; break;
            case 2: Rspeed = magnitude + diff; Lspeed = diff; break;
            case 3: Rspeed = (magnitude + diff) * -1; Lspeed = -diff; break;
            case 4: Lspeed = (magnitude + diff) * -1;Rspeed = -diff; break;
            case 0: Lspeed = Rspeed = 0; break;
        }

        if (x > 0 && y == 0) {
            Lspeed = 1; Rspeed = -1;
        }
        if (x < 0 && y == 0) {
            Lspeed = -1; Rspeed = 1;
        }

        if (x == 0 && y > 0) Lspeed = Rspeed =  1;
        if (x == 0 && y < 0) Lspeed = Rspeed = -1;

        if (TriggerValue !=0 && (x != 0 && y != 0)) {
            Lspeed = Rspeed *= TriggerValue;
        }

        if (TriggerValue !=0 && (x == 0 && y == 0)) {
            Lspeed = Rspeed += TriggerValue;
        }
    }

    public void set(VictorSPX Rmotor, VictorSPX Lmotor, double velocity) {

        Rspeed = Math.max(-1, Math.min(1, Rspeed)) * velocity;
        Lspeed = Math.max(-1, Math.min(1, Lspeed)) * velocity;

        R_motor1.set(ControlMode.PercentOutput, Rspeed);
        L_motor1.set(ControlMode.PercentOutput, Lspeed);
    }

}
