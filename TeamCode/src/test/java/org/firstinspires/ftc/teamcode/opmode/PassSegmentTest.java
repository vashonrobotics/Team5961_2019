package org.firstinspires.ftc.teamcode.opmode;

import org.junit.Test;

import static org.junit.Assert.*;

public class PassSegmentTest {

    @Test
    public void getMinimum() {
        PassSegment segment = new PassSegment(new Point(100, 100), new Point(200, 200));
        assertEquals((int) segment.getMinimum(100), 100);
        assertEquals((int) segment.getMinimum(199), 199);

        assertNull(segment.getMinimum(0));
        assertNull(segment.getMinimum(200));
        assertNotNull(segment.getMinimum(100));

        assertEquals((int)segment.getMinimum(150), 150);
    }
}