package util;

import constants.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistanceUtils {

    private static final Logger logger = LoggerFactory.getLogger(DistanceUtils.class);

    /**
     * Checks if the given latitude and longitude is present within 100km radius of Intercom Dublin Office
     *
     * @param latitude  : Latitude of the user
     * @param longitude : Longitude of the user
     * @return true if the given latitude and longitude is present within 100km radius of Intercom Dublin Office
     * else false
     */
    public boolean isWithinRadius(double latitude, double longitude) {
        logger.info("Testing log");
        double distance = 0.0;
        try {
            double deltaLongitude = Math.toRadians(AppConstants.INTERCOM_OFFICE_LONGITUDE - longitude);
            double centralangle = Math.acos(Math.sin(Math.toRadians(AppConstants.INTERCOM_OFFICE_LATITUDE)) *
                    Math.sin(Math.toRadians(latitude)) +
                    (Math.cos(Math.toRadians(AppConstants.INTERCOM_OFFICE_LATITUDE)) *
                            Math.cos(Math.toRadians(latitude)) *
                            Math.cos(deltaLongitude)));
            distance = AppConstants.EARTH_RADIUS * centralangle;
        } catch (RuntimeException e) {
            logger.error("Error occurred in isWithinRadius " + latitude + " " + longitude + ": " + e.getMessage());
            return false;
        }
        return distance <= 100.0;
    }
}
