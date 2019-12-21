package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.SkystoneTrackerVuforia;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveBase;

public class SidewaysBot implements VirtualDriveInterface {
    private final StraightBot inner;

    public SidewaysBot(SampleMecanumDriveBase drive, SkystoneTrackerVuforia tracker) {
        this.inner = new StraightBot(drive, tracker);
    }

    @Override
    public void strafeLeft(int distance) {
        inner.driveBack(distance);
    }

    @Override
    public void strafeRight(int distance) {
        inner.driveForward(distance);

    }

    @Override
    public void driveBack(int distance) {
        inner.strafeRight(distance);

    }

    @Override
    public void driveForward(int distance) {
        inner.strafeLeft(distance);

    }

    @Override
    public void wiggle() {

    }
}
