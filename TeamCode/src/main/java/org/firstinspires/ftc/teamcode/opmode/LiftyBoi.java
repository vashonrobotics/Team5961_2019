package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop  .opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;


public class LiftyBoi {
    public static final int D_MIN = 17;

    private final DcMotor ascension;
    private final DcMotor reach;
    private final Ramp ramp;


    public LiftyBoi(HardwareMap hardwareMap) {
        this.ascension = hardwareMap.dcMotor.get("LiftUp");
        this.reach = hardwareMap.dcMotor.get("LiftOut");
        this.reach.setDirection(DcMotorSimple.Direction.REVERSE );

        this.ascension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.reach.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.ascension.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.reach.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.ascension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.reach.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.ascension.setPower(0.0);
        this.reach.setPower(0.0);

        this.ramp = new Ramp();
    }

    public void setVelocity(double froVelocity, double upVelocity) {

        froVelocity = clipVelocity(froVelocity);
        upVelocity = clipVelocity(upVelocity);

        int currentReach = reach.getCurrentPosition();
        int currentUp = ascension.getCurrentPosition();

        Integer lowerLimit = ramp.getMinimum(currentReach);
        if(lowerLimit != null) {
            double restorationVelocity =  clipVelocity(0.5 * (lowerLimit - currentUp));
            if(restorationVelocity > 0) {
                upVelocity = Math.max(upVelocity, restorationVelocity);
                froVelocity = Math.max(Math.min(0.5, froVelocity), -0.5);
            }
        }

        if (currentReach >= Ramp.UPPER_FRO) {
            froVelocity = Math.min(froVelocity, 0);
        }

        if (currentReach <= Ramp.LOWER_FRO) {
            froVelocity = Math.max(froVelocity, 0);
        }

        if (currentUp >= Ramp.UPPER_UP) {
            upVelocity = Math.min(upVelocity, 0);
        }

        if (currentUp <= Ramp.LOWER_UP) {
            upVelocity = Math.max(upVelocity, 0);
        }

        String messasge = String.format("reach = %d, asc = %d, reach' = %f, asc' = %f",
                currentReach, currentUp, froVelocity, upVelocity);

        RobotLog.d(messasge);

        reach.setPower(froVelocity);
        ascension.setPower(upVelocity);
    }

    private double clipVelocity(double motorVelocity) {
        return Math.min(1.0, Math.max(motorVelocity, -1.0));
    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
