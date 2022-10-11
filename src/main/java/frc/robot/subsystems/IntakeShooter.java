package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeShooter extends SubsystemBase{

    // CANSparkMax can = new CANSparkMax(6, MotorType.kBrushless);  
    TalonSRX can = new TalonSRX(Constants.intakeShooterID);

    DoubleSolenoid ds = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    Compressor comp = new Compressor(PneumaticsModuleType.CTREPCM);


    public IntakeShooter()
    {
        ds.set(Value.kForward); //enables toggling
    }
    
    public void intakeInward(){
        
        can.set(ControlMode.PercentOutput, 1); 
    }

    public void intakeStop(){
        can.set(ControlMode.PercentOutput, 0); 
    }
    
    public void intakeOutwards() {
        can.set(ControlMode.PercentOutput, -1); 
    }

    @Override
    public void periodic()
    {
        SmartDashboard.putBoolean("Pressure Switch", comp.getPressureSwitchValue());
        SmartDashboard.putBoolean("Intake is UP", ds.get() != DoubleSolenoid.Value.kForward);
    }

    public void togglePneumatics(){
        ds.toggle();
    }
}
