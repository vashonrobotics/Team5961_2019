package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.detectors.skystone.SkystoneDetector;

import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

public class SkystoneTrackerDogeCV implements SkystoneTracker {
    private SkystoneDetector detector;
    private OpenCvInternalCamera phoneCam;

    @Override
    public void init() {
        phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK);
        phoneCam.openCameraDevice();
        detector = new SkystoneDetector();
        phoneCam.setPipeline(detector);

        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
