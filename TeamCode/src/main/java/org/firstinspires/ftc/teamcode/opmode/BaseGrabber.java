package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;

public class BaseGrabber {
    private final Servo leftbase;
    private final Servo rightbase;
    public BaseGrabber(HardwareMap hardwareMap) {
        this.leftbase = hardwareMap.servo.get("baseGrabLeft");
        this.rightbase = hardwareMap.servo.get("baseGrabRight");

    }

    public void grab (){
        leftbase.setPosition(0.23);
        rightbase.setPosition(0.75);
    }
    public void release (){
        leftbase.setPosition(.75);
        rightbase.setPosition(.01);
    }

}
