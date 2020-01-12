package org.firstinspires.ftc.teamcode.opmode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RampTest {

    @Test
    public void getMinimum() {
        Ramp ramp = new Ramp();
        Integer m = ramp.getMinimum(-501);
        Assert.assertNotNull("-501 should not be null", m);
        Assert.assertEquals("Value for -501", Ramp.END_UP, m.intValue());

        m = ramp.getMinimum(Ramp.INNER_RAMP - 1);
        Assert.assertNotNull(String.format("%d  should not be null", Ramp.INNER_RAMP - 1), m);
        Assert.assertEquals("Value for -501", Ramp.PLATEAU_UP, m.intValue());
    }
}