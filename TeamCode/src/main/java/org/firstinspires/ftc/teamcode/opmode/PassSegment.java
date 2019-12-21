package org.firstinspires.ftc.teamcode.opmode;

public class PassSegment {
    private final Point start;
    private final Point end;

    public PassSegment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Integer getMinimum(int x) {
        if (x < start.getX() || x >= end.getX()) {
            return null;
        }
        double slope = ((double) end.getY() - start.getY()) / (end.getX() - start.getX());
        double y = slope * (x - start.getX()) + start.getY();
        return (int)Math.round(y);
    }
}
