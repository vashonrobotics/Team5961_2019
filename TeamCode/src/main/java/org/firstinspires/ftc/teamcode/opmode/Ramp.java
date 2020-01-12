package org.firstinspires.ftc.teamcode.opmode;
/*
 *    UPPER_UP ---------------------------------
 *
 *      <<---- OUTBOARD | INBOARD ------>>
 *
 *
 *
 *                     INNER_RAMP      (Below this line is the danger zone)
 *                        |             |
 *                        |             |
 *                        v             v
 *                        /--------------------  PLATEAU_UP
 *                       / O                  ^
 *                      /   \                 |
 *                     /     \                |
 * LOWER_UP ----------/      Axle            LOWER_FRO
 *          ^          ^
 *          |          |
 *      UPPER_FRO      |
 *                 OUTER_RAMP
 *
 */

public class Ramp {
    private static final int FRO_OFFSET = 1461;
    private static final int UP_OFFSET = -200;
    public static final int UPPER_FRO = 1461 - FRO_OFFSET;
    public static final int LOWER_FRO = -553 - FRO_OFFSET;
    public static final int UPPER_UP = 1420 - UP_OFFSET;
    public static final int LOWER_UP = -200 - UP_OFFSET;
    public static final int END_FRO = -475 - FRO_OFFSET;
    public static final int END_UP = -39 - UP_OFFSET;
    public static final int OUTER_RAMP = 1119 - FRO_OFFSET;
    public static final int INNER_RAMP = 1038 - FRO_OFFSET;
    public static final int PLATEAU_UP = 265 - UP_OFFSET;
    public static Pass lowerLimitPass = new Pass(
            new Point(-2000, END_UP),
            new Point(END_FRO, END_UP),
            new Point(END_FRO, PLATEAU_UP),
            new Point(LOWER_FRO, PLATEAU_UP),
            new Point(INNER_RAMP, PLATEAU_UP),
            new Point(OUTER_RAMP, LOWER_UP),
            new Point(UPPER_FRO, LOWER_UP));

    public Integer getMinimum(int reach) {
        return lowerLimitPass.getMinimum(reach);
    }
}
