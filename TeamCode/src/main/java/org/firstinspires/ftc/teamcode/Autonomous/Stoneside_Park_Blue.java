package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Grabby;
import org.firstinspires.ftc.teamcode.SkystoneTrackerVuforia;
import org.firstinspires.ftc.teamcode.drive.SidewaysBot;
import org.firstinspires.ftc.teamcode.drive.VirtualDriveInterface;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveREV;
import org.firstinspires.ftc.teamcode.opmode.AutoLiftyBoi;
import org.firstinspires.ftc.teamcode.opmode.Intake;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Config
@Autonomous(group = "drive")
public class Stoneside_Park_Blue extends LinearOpMode {
    public static double DISTANCE = 10;
    public static double ANGLE = 90;
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private SkystoneTrackerVuforia tracker = null;

    @Override
    public void runOpMode() {
        SampleMecanumDriveBase drive = new SampleMecanumDriveREV(hardwareMap, false);
        tracker = new SkystoneTrackerVuforia(0.5);
        tracker.init();
        VirtualDriveInterface virtualDrive = new SidewaysBot(drive, tracker);
        Intake intake = new Intake(hardwareMap);
        Grabby grabby = new Grabby(hardwareMap);
        AutoLiftyBoi autoliftyboi = new AutoLiftyBoi(hardwareMap);


        while (!isStarted()) {
            tracker.update();
            if (tracker.isVisible()) {
                double xpos = tracker.getX();
                double ypos = tracker.getY();
                telemetry.addData("Stoneside_Blue_Park:", xpos);
                telemetry.addData("Y:", ypos);
            }
            telemetry.update();
            sleep(25);
        }
        drive.setPoseEstimate(new Pose2d(40, 63, 90));

        autoliftyboi.fromStartingToExtended();

        virtualDrive.driveForward(1);
        virtualDrive.strafeLeft(36);

        if (isStopRequested()) return;

//        drive.followTrajectorySync(trajectory);
//        drive.turnSync(Math.toRadians(ANGLE));
//        drive.turnSync(Math.toRadians(ANGLE));
//        drive.turnSync(Math.toRadians(ANGLE));
//        drive.followTrajectorySync(trajectory);
    }
}
