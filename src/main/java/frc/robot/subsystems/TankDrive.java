package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankDrive extends SubsystemBase
{
    CANSparkMax lf = new CANSparkMax(Constants.Swerve.Mod0.driveMotorID, MotorType.kBrushless);
    CANSparkMax lb = new CANSparkMax(Constants.Swerve.Mod2.driveMotorID, MotorType.kBrushless);
    CANSparkMax rf = new CANSparkMax(Constants.Swerve.Mod1.driveMotorID, MotorType.kBrushless);
    CANSparkMax rb = new CANSparkMax(Constants.Swerve.Mod3.driveMotorID, MotorType.kBrushless);

    MotorControllerGroup left = new MotorControllerGroup(lf, lb);
    MotorControllerGroup right = new MotorControllerGroup(rf, rb);

    DifferentialDrive dt = new DifferentialDrive(left, right);

    CANSparkMax angle0 = new CANSparkMax(Constants.Swerve.Mod0.angleMotorID, MotorType.kBrushless);
    CANSparkMax angle1 = new CANSparkMax(Constants.Swerve.Mod1.angleMotorID, MotorType.kBrushless);
    CANSparkMax angle2 = new CANSparkMax(Constants.Swerve.Mod2.angleMotorID, MotorType.kBrushless);
    CANSparkMax angle3 = new CANSparkMax(Constants.Swerve.Mod3.angleMotorID, MotorType.kBrushless);
    
    public TankDrive()
    {
        angle0.setIdleMode(IdleMode.kBrake);
        angle1.setIdleMode(IdleMode.kBrake);
        angle2.setIdleMode(IdleMode.kBrake);
        angle3.setIdleMode(IdleMode.kBrake);

        lf.setSmartCurrentLimit(35, 40);
        lb.setSmartCurrentLimit(35, 40);
        rf.setSmartCurrentLimit(35, 40);
        rb.setSmartCurrentLimit(35, 40);

        angle0.setSmartCurrentLimit(10);
        angle1.setSmartCurrentLimit(10);
        angle2.setSmartCurrentLimit(10);
        angle3.setSmartCurrentLimit(10);
    }

    public void arcadeDrive(double xSpeed, double zRotation)
    {
        dt.arcadeDrive(xSpeed, zRotation);
    }

    @Override
    public void periodic() {
        // angle0.set(0.3);
        // angle1.set(0.3);
        // angle2.set(0.3);
        // angle3.set(0.3);
    }
}
