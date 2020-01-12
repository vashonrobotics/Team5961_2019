package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.util.RobotLog;

import java.util.ArrayList;
import java.util.List;

public class Pass {
    private List<PassSegment> segments;

    public Pass(Point... points) {
        segments = new ArrayList<>();
        Point lastPoint = null;
        for (Point currentPoint : points) {
            if (lastPoint != null) {
//                RobotLog.d(String.format("Added segment start = %s, end = %s", lastPoint, currentPoint));
                segments.add(new PassSegment(lastPoint, currentPoint));
            }

            lastPoint = currentPoint;
        }
    }

    public Integer getMinimum(int x) {
        for (PassSegment segment : segments) {
            int minX = Math.min(segment.getStartX(), segment.getEndX());
            int maxX = Math.max(segment.getStartX(), segment.getEndX());
            if (x >= minX && x < maxX) {
//                RobotLog.d(String.format("Getting minimum from segment startx = %d, endX = %d, min = %d",
//                        minX, maxX, segment.getMinimum(x)));
                return segment.getMinimum(x);
            }

        }
        return null;
    }
}
