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

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Grabby;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveREV;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "Basic: Linear OpMode", group = "Linear Opmode")

public class Teleop_OpMode extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private SampleMecanumDriveBase drive = null;
    private DcMotor reach;
    private DcMotor ascension;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        drive = new SampleMecanumDriveREV(hardwareMap, true);
        Intake intake = new Intake(hardwareMap);
        LiftyBoi liftyBoi = new LiftyBoi(hardwareMap);
        Grabby grabby = new Grabby(hardwareMap);
        BaseGrabber baseGrabber = new BaseGrabber(hardwareMap);
        double currentFro = 0;
        double currentUp = 0;
        boolean isBaseGrabbing = false;
        boolean isBaseReleased = true;
        boolean isGripping = true;
        ascension = hardwareMap.dcMotor.get("LiftUp");
        reach = hardwareMap.dcMotor.get("LiftOut");


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        while (!isStopRequested()) {
            long start = System.currentTimeMillis();
            drive.setDrivePower(new Pose2d(
                    -1.0 * gamepad1.left_stick_x,
                    1.0 * gamepad1.left_stick_y,
                    -0.06 * gamepad1.right_stick_x
            ));

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());

            if (gamepad2.dpad_down) {
                intake.in();
            } else if (gamepad2.dpad_up) {
                intake.out();
            } else {
                intake.stop();
            }

            liftyBoi.setVelocity(-gamepad2.left_stick_x, -gamepad2.left_stick_y);

            int ascensionpos = ascension.getCurrentPosition();
            int reacpos = reach.getCurrentPosition();

            telemetry.addData("positions", "asc = %d, rea = %d", ascensionpos, reacpos);

            boolean gripperPressed = gamepad2.right_trigger > .5;
            if (gripperPressed) {
                isGripping = !isGripping;
            }

            if (isGripping) {
                grabby.SMASH();
            } else {
                grabby.openGrip();
            }

            if (gamepad2.a && isBaseReleased) {
                isBaseGrabbing = !isBaseGrabbing;
                isBaseReleased = false;
            }

            if (!gamepad2.a) {
                isBaseReleased = true;
            }

            if (isBaseGrabbing) {
                baseGrabber.grab();
            } else {
                baseGrabber.release();
            }

            long end = System.currentTimeMillis();
            String msg = String.format("Loop took %dms", end - start);

            telemetry.addLine(msg);
            telemetry.update();
        }
    }

}

