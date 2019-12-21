package org.firstinspires.ftc.teamcode.opmode;

import java.util.ArrayList;
import java.util.List;

public class Pass {
    private List<PassSegment> segments;
    public Pass(Point ... points){
        segments = new ArrayList<>();
        Point lastPoint = null;
        for(Point currentPoint : points) {
            if(lastPoint != null) {
                segments.add(new PassSegment(lastPoint, currentPoint));
            }

            lastPoint = currentPoint;
        }
    }
}
