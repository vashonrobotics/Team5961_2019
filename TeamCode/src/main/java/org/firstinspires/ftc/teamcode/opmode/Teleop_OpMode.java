/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Grabby;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveREV;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")

public class Teleop_OpMode extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private SampleMecanumDriveBase drive = null;
    Servo   WaveMF;
    Servo   Mine;
    DcMotor Theta;
    DcMotor Reach;
    Grabby claw;
    CRServo   LongSmush;

    static final double INCREMENT   = 0.01;
    static final int    CYCLE_MS    =   50;
    static final double MAX_POS     =  1.0;
    static final double MIN_POS     =  0.0;
    private static int MAX_THETA_TICKS = 143;
    private static int MIN_THETA_TICKS = -352;
    private static int MAX_REACH_TICKS = 2538;
    private static int MIN_REACH_TICKS = 0;
    private double Theta_Proportion = 0.0;
    private double Reach_Proportion = 0.0;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        drive = new SampleMecanumDriveREV(hardwareMap, true);
        WaveMF = hardwareMap.get(Servo.class, "WaveMF");
        Mine = hardwareMap.get(Servo.class, "Mine");
        LongSmush = hardwareMap.get(CRServo.class, "LongSmush");
        Theta = hardwareMap.get(DcMotor.class, "Theta");
        Reach = hardwareMap.get(DcMotor.class, "Reach");
        Theta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Reach.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Theta.setTargetPosition(0);
        Reach.setTargetPosition(0);
        Theta.setPower(1.0);
        Reach.setPower(1.0);
        Theta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Reach.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        claw = new Grabby(Mine, WaveMF);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
          while (!isStopRequested()) {
            drive.setDrivePower(new Pose2d(
                    -1.0* gamepad1.left_stick_y,
                    -1.0* gamepad1.left_stick_x,
                    -0.06  * gamepad1.right_stick_x
            ));



            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());


            if(gamepad2.a){
                claw.dorpClaw();
            }else {
                claw.razeClaw();
            }

            if(gamepad2.x){
                claw.openGrip();
            }else {
                claw.SMASH();
            }

            if(gamepad2.b){
                LongSmush.setPower(0.5);
            }else {
                LongSmush.setPower(-0.5 );
            }

            Theta_Proportion += -0.01 * gamepad2.left_stick_y;
            Reach_Proportion += 0.0025 * gamepad2.left_stick_x;

            Reach_Proportion = Math.max(0, Math.min(Reach_Proportion,1.0));
            Theta_Proportion = Math.max(0, Math.min(Theta_Proportion,1.0));

            int Theta_Target = (int)(MIN_THETA_TICKS + Theta_Proportion * (MAX_THETA_TICKS - MIN_THETA_TICKS));
            int Reach_Target = (int)(MIN_REACH_TICKS + Reach_Proportion * (MAX_REACH_TICKS - MIN_REACH_TICKS));

            Theta.setTargetPosition(Theta_Target);
            Reach.setTargetPosition(Reach_Target);

            telemetry.addData("Theta target:", Theta_Target);
            telemetry.addData("Reach Target", Reach_Target);

            telemetry.update();

        }
    }

}
