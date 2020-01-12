package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    public static final double POWER = .75;
    private final DcMotor leftMotor;
    private final DcMotor rightMotor;
    public Intake(HardwareMap hardwareMap) {
        this.leftMotor = hardwareMap.dcMotor.get("intakeLeft");
        this.rightMotor = hardwareMap.dcMotor.get("intakeRight");
    }

    public void in() {
        leftMotor.setPower(-POWER);
        rightMotor.setPower(-POWER);
    }
    public void out() {
        leftMotor.setPower(POWER);
        rightMotor.setPower(POWER);
    }
    public void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
