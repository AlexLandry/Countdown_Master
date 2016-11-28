package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="CapWack", group="Exercises")

/**
 * Created by Mark on 11/17/2016.
 */

public class DriveStright extends LinearOpMode
{
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor shooterMotor, intakeMotor;
    Servo beaconServo, doorServo;
    ColorSensor beaconColor;
    String programVersion;
    double beaconPosition, doorPosition;


    @Override
    public void runOpMode() throws InterruptedException
    {

        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        shooterMotor = hardwareMap.dcMotor.get("shooter_motor");
        intakeMotor = hardwareMap.dcMotor.get("intake_motor");
        beaconServo = hardwareMap.servo.get("beacon_servo");
        doorServo = hardwareMap.servo.get("door_servo");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        programVersion = "MRB_11_17_16_3";

        // reset encoder count kept by motor.
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motor to run to target encoder position and stop with brakes on.
        // RUN_WITH_ENCODER will stop with coast.
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        //Drive to shooting position

        leftMotor.setTargetPosition(-1400);
        rightMotor.setTargetPosition(-1400);

        // set both motors to 25% power. Movement will start.

        leftMotor.setPower(-0.25);
        rightMotor.setPower(-0.25);


        while (opModeIsActive() && leftMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())
        {
            telemetry.addLine(programVersion);
            telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
            telemetry.update();
            idle();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
// shoot
        doorServo.setPosition(0.1);
        doorPosition = 0.1;
        sleep(700);
        doorServo.setPosition(0.5);
        doorPosition = 0.5;
        sleep(200);
        shooterMotor.setPower(1);
        sleep(800);
        shooterMotor.setPower(0);
        intakeMotor.setPower(1);
        doorServo.setPosition(0.1);
        doorPosition = 0.1;
        sleep(700);
        intakeMotor.setPower(0);
        doorServo.setPosition(0.5);
        doorPosition = 0.5;
        sleep(200);
        shooterMotor.setPower(1);
        sleep(800);
        shooterMotor.setPower(0);
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        // whack the ball
        leftMotor.setTargetPosition(-3000);
        rightMotor.setTargetPosition(-3000);
        leftMotor.setPower(-1);
        rightMotor.setPower(-1);

        while (opModeIsActive() && leftMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())
        {
            telemetry.addLine(programVersion);
            telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
            telemetry.update();
            idle();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        while (opModeIsActive())
        {
            telemetry.addData("encoder-end", leftMotor.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }
}

