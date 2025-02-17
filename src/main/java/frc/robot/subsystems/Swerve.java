package frc.robot.subsystems;

import com.ctre.phoenix.sensors.Pigeon2;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.math.Conversions;
import frc.robot.Constants;

public class Swerve extends SubsystemBase {
	public SwerveDriveOdometry swerveOdometry;
	public SwerveModule[] mSwerveMods;
	public Pigeon2 gyro;
	// public AHRS gyro = new AHRS(SerialPort.Port.kMXP);
	private final Field2d m_field2d = new Field2d();

	public Swerve() {
		gyro = new Pigeon2(Constants.Swerve.pigeonID);
		gyro.configFactoryDefault();

		SmartDashboard.putData("Field", m_field2d);
		
		// gyro.calibrate();
			
		zeroGyro();

		swerveOdometry = new SwerveDriveOdometry(Constants.Swerve.swerveKinematics, getYaw());

		mSwerveMods =
				new SwerveModule[] {
					new SwerveModule(0, Constants.Swerve.Mod0.constants),
					new SwerveModule(1, Constants.Swerve.Mod1.constants),
					new SwerveModule(2, Constants.Swerve.Mod2.constants),
					new SwerveModule(3, Constants.Swerve.Mod3.constants)
				};

		// Constants.Swerve.Mod0.angleOffset = mSwerveMods[0].angleEncoder.getAbsolutePosition();
		// Constants.Swerve.Mod1.angleOffset = mSwerveMods[1].angleEncoder.getAbsolutePosition();
		// Constants.Swerve.Mod2.angleOffset = mSwerveMods[2].angleEncoder.getAbsolutePosition();
		// Constants.Swerve.Mod3.angleOffset = mSwerveMods[3].angleEncoder.getAbsolutePosition() + 45;
	}

	public void drive(
			Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop, boolean slowMode) {
		// if (!fakeTank)
		// {
			var x = translation.getX();
			var y = translation.getY();
			var theta = rotation;
	
			if (slowMode)
			{
				x *= 0.25;
				y *= 0.25;
				theta *= 0.25;
			}
			
			
			SwerveModuleState[] swerveModuleStates =
				Constants.Swerve.swerveKinematics.toSwerveModuleStates(
						fieldRelative
							? ChassisSpeeds.fromFieldRelativeSpeeds(
										x, y, theta, getYaw())
								: new ChassisSpeeds(x, y, theta));
			
			setModuleStates(swerveModuleStates, isOpenLoop);
		// }
		// else
		// {

		// }
			
	}

	/* Used by SwerveControllerCommand in Auto */
	public void setModuleStates(SwerveModuleState[] desiredStates, boolean isOpenLoop) {
		SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.Swerve.maxSpeed);
		
		for (SwerveModule mod : mSwerveMods) {
			SwerveModuleState.optimize(desiredStates[mod.moduleNumber], mod.getState().angle);
			mod.setDesiredState(desiredStates[mod.moduleNumber], isOpenLoop);
		};
	}

	public Pose2d getPose() {
		return swerveOdometry.getPoseMeters();
	}

	public void resetOdometry(Pose2d pose) {
		swerveOdometry.resetPosition(pose, getYaw());
	}

	public SwerveModuleState[] getStates() {
		SwerveModuleState[] states = new SwerveModuleState[4];
		for (SwerveModule mod : mSwerveMods) {
			states[mod.moduleNumber] = mod.getState();
		}
		return states;
	}

	public void zeroGyro() {
		gyro.setYaw(0);
		// gyro.reset();
	}

	public Rotation2d getYaw() {
		return (Constants.Swerve.invertGyro)
				? Rotation2d.fromDegrees(360 - gyro.getYaw())
				: Rotation2d.fromDegrees(gyro.getYaw());
	}

	@Override
	public void periodic() {
		swerveOdometry.update(getYaw(), getStates());
		m_field2d.setRobotPose(getPose());
		
		// SmartDashboard.putNumber("Match Time", DriverStation.getMatchTime());

		for (SwerveModule mod : mSwerveMods) {
			SmartDashboard.putNumber(
					"Mod " + mod.moduleNumber + " Cancoder", mod.getCanCoder().getDegrees() % 180);
			SmartDashboard.putNumber(
					"Mod " + mod.moduleNumber + " Integrated", mod.getState().angle.getDegrees());
			SmartDashboard.putNumber(
					"Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);
			SmartDashboard.putData("Drive PID Controller", SwerveModule.mDrivePID);
			SmartDashboard.putData("Angle PID Controller", SwerveModule.mAnglePID);
		}
	}

	public void resetEncoders()
	{
		mSwerveMods[0].mAngleEncoder.setPosition(Conversions.degreesToNeo(Constants.Swerve.Mod0.angleOffset, Constants.Swerve.angleGearRatio));
		mSwerveMods[1].mAngleEncoder.setPosition(Conversions.degreesToNeo(Constants.Swerve.Mod1.angleOffset, Constants.Swerve.angleGearRatio));
		mSwerveMods[2].mAngleEncoder.setPosition(Conversions.degreesToNeo(Constants.Swerve.Mod2.angleOffset, Constants.Swerve.angleGearRatio));
		mSwerveMods[3].mAngleEncoder.setPosition(Conversions.degreesToNeo(Constants.Swerve.Mod3.angleOffset, Constants.Swerve.angleGearRatio));
		
	}
}
