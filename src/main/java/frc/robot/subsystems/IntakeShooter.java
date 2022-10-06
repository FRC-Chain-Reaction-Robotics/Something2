package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
//import frc.robot.constants;

public class IntakeShooter extends SubsystemBase{

    CANSparkMax can = new CANSparkMax(6, MotorType.kBrushless);  


    DoubleSolenoid ds = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    Compressor comp = new Compressor(PneumaticsModuleType.CTREPCM);


    public IntakeShooter()
    {
        ds.set(Value.kForward); //enables toggling
    }
    
    public void intakeInward(){
        can.set(1);

    }

    public void intakeStop(){
        can.set(0);
    }
    
    public void intakeOutwards() {
       can.set(-1); 
    }

    public void periodic(){
        SmartDashboard.putBoolean("Pressure", comp.getPressureSwitchValue());
    
    }

    public void togglePneumatics(){
        ds.toggle();
    }
}
