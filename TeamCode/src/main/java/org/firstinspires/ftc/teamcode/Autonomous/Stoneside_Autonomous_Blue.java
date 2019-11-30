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

package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Grabby;
import org.firstinspires.ftc.teamcode.SkystoneTracker;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveREV;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveREVOptimized;


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

@Config
@Autonomous(group = "drive")
public class Stoneside_Autonomous_Blue extends LinearOpMode {
    public static double DISTANCE = 10;
    public static double ANGLE = 90;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private SkystoneTracker tracker = null;
    private Grabby claw;
    private Servo WaveMF;
    private Servo Mine;

    @Override
    public void runOpMode() {
        SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap, false);
        tracker = new SkystoneTracker(0.5);
        tracker.init();
        WaveMF = hardwareMap.get(Servo.class, "WaveMF");
        Mine = hardwareMap.get(Servo.class, "Mine");

        claw = new Grabby(Mine, WaveMF);
        Trajectory trajectory = drive.trajectoryBuilder()
                .forward(DISTANCE)
                .build();

        while(!isStarted()){
            tracker.update();
            if(tracker.isVisible()){
                double xpos = tracker.getX();
                double ypos = tracker.getY();
                telemetry.addData("Stoneside_Autonomous_Red:",xpos);
                telemetry.addData ("Y:", ypos);
            }
            telemetry.update();
            sleep(25);
        }
        drive.setPoseEstimate(new Pose2d(40, 63, Math.PI/2));

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeLeft(48)
                        .build()
        );

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .forward(24)
                        .build()
        );

        drive.turnSync(Math.PI/2);
//        wiggle(drive);
//
//        tracker.update();
//        sleep(1000);
//        tracker.update();
//
//        int count = 0;
//        while(!tracker.isVisible() && count < 2) {
//            drive.followTrajectorySync(
//                    drive.trajectoryBuilder()
//                            .strafeLeft(9)
//                            .build()
//            );
//
//            wiggle(drive);
//            count++;
//        }

        //drive and find skystone SNARFLE!
        //respond to stones


        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeRight(12)
                        .build()
        );


//        drive.followTrajectorySync(
//                drive.trajectoryBuilder()
//                        .back(72 + (count - 2)  * 8)
//                        .build()
//        );
        drive.turnSync(-Math.PI/2);

        //place block

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeRight(48)
                        .build()
        );

//
        if (isStopRequested()) return;

//        drive.followTrajectorySync(trajectory);
//        drive.turnSync(Math.toRadians(ANGLE));
//        drive.turnSync(Math.toRadians(ANGLE));
//        drive.turnSync(Math.toRadians(ANGLE));
//        drive.followTrajectorySync(trajectory);
    }

    private void wiggle(SampleMecanumDriveBase drive) {
//        drive.turnSync(-Math.PI / 24);
//        int sign = 1;
//        for(int i = 0; i < 2; i++) {
//            drive.turnSync(sign * Math.PI / 12);
//            sign *= -1;
//            tracker.update();
//        }
//        drive.turnSync(Math.PI / 24);


    }
}