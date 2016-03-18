package org.usfirst.frc.team5086.robot.commands.actions;

import org.usfirst.frc.team5086.robot.Robot;
import org.usfirst.frc.team5086.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootMe extends Command {


	boolean yes;
    
    public ShootMe(boolean state) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	yes = state;
    	requires(Robot.ArmSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ArmSubsystem.shootBall(yes);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
