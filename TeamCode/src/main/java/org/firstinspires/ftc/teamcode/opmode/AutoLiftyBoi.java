package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;

/*
 *    UPPER_UP ---------------------------------
 *
 *      <<---- OUTBOARD | INBOARD ------>>
 *
 *
 *
 *                     INNER_RAMP      (Below this line is the danger zone)
 *                        |             |
 *                        |             |
 *                        v             v
 *                        /--------------------  PLATEAU_UP
 *                       / O                  ^
 *                      /   \                 |
 *                     /     \                |
 * LOWER_UP ----------/      Axle            LOWER_FRO
 *          ^          ^
 *          |          |
 *      UPPER_FRO      |
 *                 OUTER_RAMP
 *
 */

public class AutoLiftyBoi {
    public static final int D_MIN = 17;
    private static final int UPPER_FRO = 1461;
    private static final int LOWER_FRO = -448;
    private static final int UPPER_UP = 1420;
    private static final int LOWER_UP = -200;
    private static final int END_FRO = -505;
    private static final int END_UP = -39;
    public static final double ACCEPTABLE_ERROR = 10;
    private final DcMotor ascension;
    private final DcMotor reach;

    public static final int OUTER_RAMP = 1119;

    public static final int INNER_RAMP = 1038;
    public static final int PLATEAU_UP = 265;
    private static Pass lowerLimitPass = new Pass(
            new Point(-2000, END_UP),
            new Point(END_FRO, PLATEAU_UP),
            new Point(LOWER_FRO, PLATEAU_UP),
            new Point(INNER_RAMP, PLATEAU_UP),
            new Point(OUTER_RAMP, LOWER_UP),
            new Point(UPPER_FRO, LOWER_UP));


    public AutoLiftyBoi(HardwareMap hardwareMap) {
        this.ascension = hardwareMap.dcMotor.get("LiftUp");
        this.reach = hardwareMap.dcMotor.get("LiftOut");
        this.reach.setDirection(DcMotorSimple.Direction.REVERSE);

        this.ascension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.reach.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.ascension.setTargetPosition(0);
        this.reach.setTargetPosition(0);

        this.ascension.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.reach.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.ascension.setPower(1.0);
        this.reach.setPower(1.0);
    }

    public void gotoPlateauUpUpperFro() {
        this.ascension.setTargetPosition(PLATEAU_UP);
        this.reach.setTargetPosition(UPPER_FRO);
        waitForMotorbs(PLATEAU_UP, UPPER_FRO);
    }

    public void gotoLowerUpUpperFro() {
        this.ascension.setTargetPosition(LOWER_UP);
        this.reach.setTargetPosition(UPPER_FRO);
        waitForMotorbs(LOWER_UP, UPPER_FRO);
    }


    private void waitForMotorbs(int ascensionPosition, int reachPostition) {
        while (Math.abs(ascensionPosition - this.ascension.getCurrentPosition()) > ACCEPTABLE_ERROR &&
                Math.abs(reachPostition - this.reach.getCurrentPosition()) > ACCEPTABLE_ERROR) {
            sleep(5);
        }
    }

    public void fromStartingToExtended(){
        gotoPlateauUpUpperFro();
        gotoLowerUpUpperFro();
    }

    public void fromExtendedtoUp(){
        gotoLowerUpUpperFro();
        gotoPlateauUpUpperFro();
    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
