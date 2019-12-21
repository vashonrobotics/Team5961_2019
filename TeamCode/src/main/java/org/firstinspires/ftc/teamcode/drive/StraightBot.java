package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.SkystoneTrackerVuforia;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveBase;

public class StraightBot implements VirtualDriveInterface {
    private final SampleMecanumDriveBase drive;
    private final SkystoneTrackerVuforia tracker;

    public StraightBot(SampleMecanumDriveBase drive, SkystoneTrackerVuforia tracker) {
        this.drive = drive;
        this.tracker = tracker;
    }

    @Override
    public void strafeLeft(int distance) {
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeLeft(distance)
                        .build()
        );
    }

    @Override
    public void strafeRight(int distance) {
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeRight(distance)
                        .build()
        );
    }

    @Override
    public void driveBack(int distance) {
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .back(distance)
                        .build()
        );
    }

    @Override
    public void driveForward(int distance) {
        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .forward(distance)
                        .build()
        );
    }

    @Override
    public void wiggle() {
        drive.turnSync(-Math.PI / 24);
        int sign = 1;
        for(int i = 0; i < 2; i++) {
            drive.turnSync(sign * Math.PI / 12);
            sign *= -1;
            tracker.update();
        }
        drive.turnSync(Math.PI / 24);

    }
}
