// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.IntakeShooter;
import frc.robot.subsystems.Swerve;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  /* Controllers */
  // private final Joystick driver = new Joystick(0);

  // /* Drive Controls */
  // private final int translationAxis = XboxController.Axis.kLeftY.value;
  // private final int strafeAxis = XboxController.Axis.kLeftX.value;
  // private final int rotationAxis = XboxController.Axis.kRightX.value;

  // /* Driver Buttons */
  XboxController driver = new XboxController(0);

  private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
  XboxController operator = new XboxController(1);
  //private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);


  /* Subsystems */
  public Swerve s_Swerve = new Swerve();
  IntakeShooter intakeShooter = new IntakeShooter();

  // private TankDrive dt = new TankDrive();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    s_Swerve.setDefaultCommand(
      new TeleopSwerve(
      s_Swerve,
      () -> -driver.getLeftY(),
      () -> -driver.getLeftX(),
      () -> -driver.getRightX(),  
      driver::getRightBumper,
      () -> true));

    // dt.setDefaultCommand(
    //     new RunCommand(() -> dt.arcadeDrive(-driver.getLeftY(), driver.getRightX()), dt));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /* Driver Buttons */
    zeroGyro.whenPressed(new InstantCommand(() -> s_Swerve.zeroGyro()));

    var togglePnuematics = new JoystickButton(operator, XboxController.Button.kA.value);
    var in = new JoystickButton(operator, XboxController.Button.kLeftBumper.value);
    var out = new JoystickButton(operator, XboxController.Button.kRightBumper.value);

    togglePnuematics.whenPressed(new InstantCommand(intakeShooter::togglePneumatics, intakeShooter));
    in.whenPressed(new RunCommand(intakeShooter::intakeInward, intakeShooter))
      .whenReleased(new RunCommand(intakeShooter::intakeStop, intakeShooter));
    out.whenPressed(new RunCommand(intakeShooter::intakeOutwards, intakeShooter))
      .whenReleased(new RunCommand(intakeShooter::intakeStop, intakeShooter));


    var resetEncButton = new JoystickButton(driver, XboxController.Button.kX.value);
    
    resetEncButton.whenPressed(new InstantCommand(s_Swerve::resetEncoders, s_Swerve));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return new exampleAuto(s_Swerve);
    // return new TeleopSwerve(
    //   s_Swerve,
    //   () -> 0.2,
    //   () -> 0,
    //   () -> 0,
    //   () -> false,
    //   () -> false)
    // .withTimeout(7)
    // .andThen(new PrintCommand("Auto Complete :)"));
    // return new PrintCommand("Hello World!");
    return new PrintCommand("We do be codin from Replit doe");
  }
}
