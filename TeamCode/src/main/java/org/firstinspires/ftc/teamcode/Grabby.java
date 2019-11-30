package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class Grabby {
    private Servo Mine;
    private Servo WaveMF;

    public Grabby(Servo mine, Servo waveMF) {
        Mine = mine;
        WaveMF = waveMF;
    }

    public void SMASH() {  Mine.setPosition(0.57);
    }


    public void openGrip() {
        Mine.setPosition(0);
    }

    public void razeClaw() {
        WaveMF.setPosition(.9 );
    }

    public void dorpClaw() {
        WaveMF.setPosition(.1);
    }
}
