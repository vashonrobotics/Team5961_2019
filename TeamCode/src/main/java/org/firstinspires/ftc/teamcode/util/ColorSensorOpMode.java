package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.ExponentialMovingAverage;

@TeleOp(name = "Color sensor test")
public class ColorSensorOpMode extends LinearOpMode {
    private ColorSensor colorSensor;


    @Override
    public void runOpMode() throws InterruptedException {

        colorSensor = hardwareMap.colorSensor.get("color");
        ExponentialMovingAverage avgr = new ExponentialMovingAverage(0.01);
        ExponentialMovingAverage avgg = new ExponentialMovingAverage(0.01);
        ExponentialMovingAverage avgb = new ExponentialMovingAverage(0.01);
        ExponentialMovingAverage avga = new ExponentialMovingAverage(0.01);

        waitForStart();

        while (!isStopRequested()) {
            int red = colorSensor.red();   // Red channel value
            avgr.update(red);
            int green = colorSensor.green(); // Green channel value
            avgg.update(green);
            int blue = colorSensor.blue();  // Blue channel value
            avgb.update(blue);

            int alpha = colorSensor.alpha(); // Total luminosity
            avga.update(alpha);

            colorSensor.argb();  // Combined color value

            colorSensor.enableLed(true);  // Turn the LED on
            colorSensor.enableLed(false); // Turn the LED off

            telemetry.addData("r, g, b, a",
                    String.format("%f, %f, %f, %f", avgr.getCurrent(),
                            avgg.getCurrent(), avgb.getCurrent(), avga.getCurrent()));
            telemetry.update();
            idle();
        }
    }
}
