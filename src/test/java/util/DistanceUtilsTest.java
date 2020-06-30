package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceUtilsTest {

    /**
     * Test if isWithinRadius returns true for latitude and longitude within 100km radius of Intercom Dublin Office
     */
    @Test
    void testIsWithinRadiusCase1()
    {
        DistanceUtils distanceUtils = new DistanceUtils();
        assertTrue(distanceUtils.isWithinRadius(53.2451022,-6.238335));
    }

    /**
     * Test if isWithinRadius returns false for latitude and longitude outside 100km radius of Intercom Dublin Office
     */
    @Test
    void testIsWithinRadiusCase2()
    {
        DistanceUtils distanceUtils = new DistanceUtils();
        assertFalse(distanceUtils.isWithinRadius(51.92893,-10.27699));
    }
}