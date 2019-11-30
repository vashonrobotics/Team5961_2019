package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveREV;

@Autonomous(group = "auto")
public class OptimalStonesideRed extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap, false);
        // TODO: if you haven't already, set the localizer to something that doesn't depend on
        // drive encoders for computing the heading

        telemetry.addLine("Press play to begin the track width tuner routine");
        telemetry.addLine("Make sure your robot has enough clearance to turn smoothly");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeLeft(35)
                        .build()
        );

        //look for skystone

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .forward(55)
                        .build()
        );

        //grab skystone

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .back(55)
                        .build()
        );

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeRight(144)
                        .build()
        );

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .forward(35)
                        .build()
        );

        //place block
        //grab foundation
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .back(35)
                        .build()
        );

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeLeft(72)
                        .build()
        );

        while (!isStopRequested()) {
            idle();
        }
    }
}

