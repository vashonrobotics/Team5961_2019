package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;

public class LiftyBoi {
    public static final int D_MIN = 17;
    private static final int UPPER_FRO = 2000;
    private static final int LOWER_FRO = 0;
    private static final int UPPER_UP = 2000;
    private static final int LOWER_UP = 0;
    public static final int D_MAX = 26;
    public static final int MIN_SAFE_UPPER = 18;
    private OpMode opMode;
    private final DcMotor ascension;
    private final DcMotor reach;
    private static final int SLOP = 20;

    public LiftyBoi(HardwareMap hardwareMap) {
        this.ascension = hardwareMap.dcMotor.get("LiftUp");
        this.reach = hardwareMap.dcMotor.get("LiftOut");

        this.ascension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.reach.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.ascension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.reach.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.ascension.setPower(0.0);
        this.reach.setPower(0.0);
    }

    public void setFroVelocity(double velocity) {
        velocity = Math.min(1.0, Math.max(velocity, -1.0));
        int currentReach = reach.getCurrentPosition();
        if(currentReach >= UPPER_FRO) {
            velocity = Math.min(velocity, 0);
        }

        if(currentReach <= LOWER_FRO) {
            velocity = Math.max(velocity, 0);
        }

        reach.setPower(velocity);
    }

    public void setUpVelocity(double velocity) {
        velocity = Math.min(1.0, Math.max(velocity, -1.0));
        int currentAscension = ascension.getCurrentPosition();
        if(currentAscension >= UPPER_UP) {
            velocity = Math.min(velocity, 0);
        }

        if(currentAscension <= LOWER_UP) {
            velocity = Math.max(velocity, 0);
        }

        ascension.setPower(velocity);
    }

    private int toTicks(int upper, int lower, double position) {
        return (int) ((upper - lower) * position + lower);
    }

    private int constrainUpper(int reachTicks, int ascensionTicks) {
        if (reachTicks > D_MIN && reachTicks < D_MAX) {
            return Math.min(ascensionTicks, MIN_SAFE_UPPER);
        }
        return ascensionTicks;
    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
