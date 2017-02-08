package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="RedFish", group="Exercises")
//@Disabled
public class FishRed extends LinearOpMode
{
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor intakeMotor1, intakeMotor2, shooterMotor;
    Servo doorServo, beaconServo, liftServo;
    int loopCount, newLoopCount;
    String programVersion;

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        intakeMotor1 = hardwareMap.dcMotor.get("intake_motor1");
        intakeMotor2 = hardwareMap.dcMotor.get("intake_motor2");
        shooterMotor = hardwareMap.dcMotor.get("shooter_motor");
        doorServo = hardwareMap.servo.get("door_servo");
        beaconServo = hardwareMap.servo.get("beacon_servo");
        liftServo = hardwareMap.servo.get("lift_servo");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        programVersion = "MRB_2_1_17_1";
        // reset encoder count kept by motor.
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motor to run to target encoder position and stop with brakes on.
        // RUN_WITH_ENCODER will stop with coast.
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        beaconServo.setPosition(0.9);
        doorServo.setPosition(0.9);
        liftServo.setPosition(-0.3);
        loopCount = 0;
        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Mode", "running");
            telemetry.addLine(programVersion);
            telemetry.update();

            // set left motor to run for 1000 encoder counts.
            leftMotor.setPower(0.4);
            rightMotor.setPower(0.4);
            leftMotor.setTargetPosition(900);
            rightMotor.setTargetPosition(900);

            // set both motors to 25% power. Movement will start.


            // wait while opmode is active and left motor is busy running to position.

            while (opModeIsActive() && leftMotor.getCurrentPosition() >= 850)   //.getCurrentPosition() > leftMotor.getTargetPosition())
            {
                telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
                telemetry.addData("MotorPower: ", "leftMotor: " + leftMotor.getPower(), "rightMotor" + rightMotor.getPower());
                telemetry.addLine(programVersion);
                telemetry.update();
                idle();
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);


            // set motor power to zero to stop motors.
            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);
            doorServo.setPosition(0.3);
            intakeMotor1.setPower(1);
            intakeMotor2.setPower(1);
            doorServo.setPosition(0.9);
            sleep(700);
            doorServo.setPosition(0.3);
            sleep(200);
            shooterMotor.setPower(1);
            sleep(1000);
            shooterMotor.setPower(0);
            doorServo.setPosition(0.9);
            sleep(700);
            doorServo.setPosition(0.3);
            sleep(200);
            shooterMotor.setPower(1);
            sleep(1000);
            shooterMotor.setPower(0);
            intakeMotor1.setPower(0);
            intakeMotor2.setPower(0);
            doorServo.setPosition(0.9);
            newLoopCount = loopCount++;
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            sleep(200);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(200);
            leftMotor.setTargetPosition(3000);
            rightMotor.setTargetPosition(3000);
            sleep(1000);
            leftMotor.setPower(-0.4);
            rightMotor.setPower(0);
            while (opModeIsActive() && leftMotor.getCurrentPosition() >= 2700)
            {
                telemetry.addData("encoder-wait", leftMotor.getCurrentPosition());
                telemetry.update();
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            sleep(200);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftMotor.setTargetPosition(2000);
            rightMotor.setTargetPosition(2000);
            leftMotor.setPower(0.8);
            rightMotor.setPower(0.8);
            while (opModeIsActive() && leftMotor.getCurrentPosition() >= -2000) {
                telemetry.addData("encoder-wait", leftMotor.getCurrentPosition());
                telemetry.addData("doorServo", doorServo.getPosition() + " beaconServo", beaconServo.getPosition());
                telemetry.addData("loop", loopCount + ", ", newLoopCount);
                telemetry.addLine(programVersion);
                telemetry.update();
                idle();
            }

            leftMotor.setPower(0);
            rightMotor.setPower(0);
            while (opModeIsActive()) {
                telemetry.addData("encoder-wait", leftMotor.getCurrentPosition());
                telemetry.addData("doorServo", doorServo.getPosition() + " beaconServo", beaconServo.getPosition());
                telemetry.addData("loop", loopCount + ", ", newLoopCount);
                telemetry.addLine(programVersion);
                telemetry.update();
                idle();
            }
        }
    }
}

