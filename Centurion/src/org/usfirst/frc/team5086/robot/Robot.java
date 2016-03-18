
package org.usfirst.frc.team5086.robot;


import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team5086.robot.commands.BucketDownAndRun;
import org.usfirst.frc.team5086.robot.commands.ExampleCommand;
import org.usfirst.frc.team5086.robot.commands.RobotDance;
import org.usfirst.frc.team5086.robot.commands.SmoothSailing;
import org.usfirst.frc.team5086.robot.commands.Test;
import org.usfirst.frc.team5086.robot.subsystems.ExampleSubsystem;


import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final org.usfirst.frc.team5086.robot.subsystems.DriveSystem DriveSystem = new org.usfirst.frc.team5086.robot.subsystems.DriveSystem();
	public static final org.usfirst.frc.team5086.robot.subsystems.ArmSubsystem ArmSubsystem = new org.usfirst.frc.team5086.robot.subsystems.ArmSubsystem();
	public static OI oi;
	//public static CameraServer server;
    Compressor c = new Compressor(1);
    //public static boolean change;
    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
    	c.setClosedLoopControl(true);
		//server = CameraServer.getInstance();
        //server.setQuality(100);
        //the camera name (ex "cam0") can be found through the roborio web interface
        //server.startAutomaticCapture("cam0");
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
        chooser.addObject("Robot Dance Party", new RobotDance());
        chooser.addObject("Forward with Arm up", new SmoothSailing());
        chooser.addObject("Forward w/ arm down", new BucketDownAndRun());
        chooser.addObject("Test", new Test());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putNumber("Load Speed", RobotMap.loadSpeed);
        SmartDashboard.putNumber("Trigger Requirement", RobotMap.triggerRequirement);
        SmartDashboard.putNumber("Controller Dead Zone", RobotMap.controllerDeadZoneArea);
        SmartDashboard.putNumber("Turn Reduction", RobotMap.turnReduction);
        SmartDashboard.putNumber("Maximum Drifting", RobotMap.maximumDrifting);
        //SmartDashboard.putBoolean("Cam Number", change);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	c.setClosedLoopControl(false);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //This Code will make us irWIN.
        
        double leftTrigger = Robot.oi.JoySpecial.getRawAxis(2);
        double rightTrigger = Robot.oi.JoySpecial.getRawAxis(3);
        double leftMoveY = Robot.oi.JoyDrive.getRawAxis(1);
        double rightMoveX = Robot.oi.JoyDrive.getRawAxis(4);
        double deadZone = RobotMap.controllerDeadZoneArea;
        double bumperMin = RobotMap.triggerRequirement;
        double loadSpeed = RobotMap.loadSpeed;
        
        /*changCams = changCams+1;
        if (changCams < 10) {
        	server.startAutomaticCapture("cam0");
        }
        else {
        	server.startAutomaticCapture("cam1");
        }
        
        /*if (change=true) {
            server.startAutomaticCapture("cam1");
        }
        else {
            server.startAutomaticCapture("cam0");
        }*/
        
        //c.clearAllPCMStickyFaults();
        c.setClosedLoopControl(true);
        //boolean enabled = c.enabled();
        //boolean pressureSwitch = c.getPressureSwitchValue();
        //float current = c.getCompressorCurrent();
        /*if (current < 120) {
        	c.start();
        }
        else {
        	c.stop();
        }*/
        
    	//SmartDashboard.putBoolean("pressure", pressureSwitch);
        /* OLDER THAN MONTY PYTHON
        if (Math.abs(altitudeAxis) > deadZone) {
        	Robot.ArmSubsystem.armHeightChange(altitudeAxis);
        }
        else {
        	Robot.ArmSubsystem.armHeightChange(0);
        }*/
    	
        
        // Arm Intake and Firing
        if (leftTrigger > bumperMin) {
        	Robot.ArmSubsystem.setArmSpeeds(loadSpeed);
        }
        if (rightTrigger > bumperMin) {
        	Robot.ArmSubsystem.setArmSpeeds(-1);
        }
        if ((leftTrigger < bumperMin) && (rightTrigger < bumperMin)) {
        	Robot.ArmSubsystem.setArmSpeeds(0);
        }
        
        //Drive
        if (Math.abs(leftMoveY) > deadZone) {
        	Robot.DriveSystem.mainDrive(leftMoveY, rightMoveX);
        }
        if ((Math.abs(leftMoveY) < deadZone) && (Math.abs(rightMoveX) > deadZone)) {
        	Robot.DriveSystem.hardTurn(rightMoveX);
        }
        if ((Math.abs(leftMoveY) < deadZone) && (Math.abs(rightMoveX) < deadZone)) {
        	Robot.DriveSystem.stop();
        }
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
