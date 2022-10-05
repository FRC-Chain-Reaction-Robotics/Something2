package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {
  public static final double stickDeadband = 0.1;

  public static final class Swerve {
    public static final boolean invertGyro = true; // Always ensure Gyro is CCW+ CW-

    /* Drivetrain Constants */
    public static final double trackWidth = Units.inchesToMeters(21.5);
    public static final double wheelBase = Units.inchesToMeters(23.25);
    public static final double wheelDiameter = Units.inchesToMeters(4.0);
    public static final double wheelCircumference = wheelDiameter * Math.PI;

    public static final double driveGearRatio = (6.75 / 1.0); // MK4 L2
    public static final double angleGearRatio = (12.8 / 1.0); // 12.8:1

    public static final SwerveDriveKinematics swerveKinematics =
        new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

    /* Swerve Current Limiting */
    public static final int angleContinuousCurrentLimit = 25;
    public static final int driveContinuousCurrentLimit = 35;

    /* Angle Motor PID Values */
    public static final double angleKP = 0.99;  //  Let's try leaving the PID and FF tuning alone for today. It might even work as-is.
    public static final double angleKI = 0.0;
    public static final double angleKD = 0.0;

    /* Drive Motor PID Values */
    public static final double driveKP = 0.10;
    public static final double driveKI = 0.0;
    public static final double driveKD = 0.0;

    /* Drive Motor Characterization Values */
    public static final double driveKS = 0.667;
    public static final double driveKV = 2.44;
    public static final double driveKA = 0.27;

    /* Swerve Profiling Values */
    public static final double maxSpeed = 4.5; // meters per second
    public static final double maxAngularVelocity = 11.5;

    /* Neutral Modes */
    public static final IdleMode angleNeutralMode = IdleMode.kBrake;
    public static final IdleMode driveNeutralMode = IdleMode.kBrake;

    /* Motor Inverts */
    public static final boolean driveInvert = false;
    public static final boolean angleInvert = false;

    /* Angle Encoder Invert */
    public static final boolean canCoderInvert = false;

    /* Module Specific Constants */
    /* Front Left Module - Module 0 */
    public static final class Mod0 {
<<<<<<< HEAD
      public static final int driveMotorID = 5;
      public static final int angleMotorID = 8;
      public static final int canCoderID = 1;
      public static final double angleOffset = 144.22;
=======
      public static final int driveMotorID = 8; //  TODO: verify ID that the drive and angle motors are correct; Left is drive and Right is angle
      public static final int angleMotorID = 5; //  TODO: verify ID
      public static final int canCoderID = 1; //  TODO: verify CANCoder ID (May have to wait for Josh)
      public static final double angleOffset = 144.22;  //  TODO: change offset (Wait for Josh)
>>>>>>> f7d9028c4200a47c7934074d631da96a90d0d20a
      public static final SwerveModuleConstants constants =
          new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
    }

    /* Front Right Module - Module 1 */
    public static final class Mod1 {
<<<<<<< HEAD
      public static final int driveMotorID = 2;
      public static final int angleMotorID = 4;
      public static final int canCoderID = 2;
      public static final double angleOffset = 109.07;
=======
      public static final int driveMotorID = 4; //  TODO: verify ID
      public static final int angleMotorID = 2; //  TODO: verify ID
      public static final int canCoderID = 2; //  TODO: verify CANCoder ID (May have to wait for Josh)
      public static final double angleOffset = 109.07;  //  TODO: change offset (Wait for Josh)
>>>>>>> f7d9028c4200a47c7934074d631da96a90d0d20a
      public static final SwerveModuleConstants constants =
          new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
    }

    /* Back Left Module - Module 2 */
    public static final class Mod2 {
<<<<<<< HEAD
      public static final int driveMotorID = 1;
      public static final int angleMotorID = 10;
      public static final int canCoderID = 3;
      public static final double angleOffset = 326.07;
=======
      public static final int driveMotorID = 10;  //  TODO: verify ID
      public static final int angleMotorID = 1; //  TODO: verify ID
      public static final int canCoderID = 3; //  TODO: verify CANCoder ID (May have to wait for Josh)
      public static final double angleOffset = 326.07;  //  TODO: change offset (Wait for Josh)
>>>>>>> f7d9028c4200a47c7934074d631da96a90d0d20a
      public static final SwerveModuleConstants constants =
          new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
    }

    /* Back Right Module - Module 3 */
    public static final class Mod3 {
<<<<<<< HEAD
      public static final int driveMotorID = 3;
      public static final int angleMotorID = 7;
      public static final int canCoderID = 4;
      public static final double angleOffset = 157.76;
=======
      public static final int driveMotorID = 7; //  TODO: verify ID
      public static final int angleMotorID = 3; //  TODO: verify ID
      public static final int canCoderID = 4; //  TODO: verify CANCoder ID (May have to wait for Josh)
      public static final double angleOffset = 157.76;  //  TODO: change offset (Wait for Josh)
>>>>>>> f7d9028c4200a47c7934074d631da96a90d0d20a
      public static final SwerveModuleConstants constants =
          new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
    }
  }

  // Not going to use Auto unless we have more time. :(
  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 1;

    // Constraint for the motion profilied robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
        new TrapezoidProfile.Constraints(
            kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }
}

//  TODO: If you complete everything in this file,
//  Check electrical issues
//  Set the CANCoder IDs (google how to do that; it's very similar to setting ID for a TalonSRX (can google that too))
  //  It would be in the Phoenix Tuner app.
//  And then follow https://github.com/Team364/BaseFalconSwerve#setting-constants
//  This will tell you how to do the stuff marked as "wait for Josh"
//  Such as setting the CANCoder offset, PID tuning, etc.
//  Also verify that any unmarked constants such as driveInvert, angleInvert, and canCoderInvert are correct
  //  by changing them one by one to true and seeing if that fixes anything.
//  I *might* be available on Discord, but trying asking the FRC discord for technical questions https://discord.com/invite/frc
// Even Teleop should not work until you fix at least the CANCoder offsets, if not more.