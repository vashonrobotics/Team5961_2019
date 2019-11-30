package org.firstinspires.ftc.teamcode.Autonomous;

import android.view.animation.LinearInterpolator;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveREV;

@Autonomous(group = "auto")
public class OptimalFoundationBlue extends LinearOpMode {

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
                        .forward(20)
                        .build()
        );

        //look for skystone
        if (isStopRequested()) return;
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeRight(24)
                        .build()
        );

        if (isStopRequested()) return;
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .forward(20)
                        .build()
        );

        if (isStopRequested()) return;
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .back(15)
                        .build()
        );
        //grab skystone
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeLeft(80)
                        .build()
        );

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .forward (30)
                        .build()
        );
        //place block

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .back(20)
                        .build()
        );
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeRight(50)
                        .build()
        );
    }
}
