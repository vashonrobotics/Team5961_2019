package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Grabby {
    private Servo mine;
    public Grabby(HardwareMap hardwareMap) {
       this.mine = hardwareMap.servo.get("Mine");
    }

    public void SMASH() {  mine.setPosition(0.57);
    }


    public void openGrip() {
        mine.setPosition(0);
    }

}
