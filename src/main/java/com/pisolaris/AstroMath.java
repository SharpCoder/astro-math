package com.pisolaris;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class AstroMath {

    public static double radToDeg(double rad) {
        return (rad * 180) / Math.PI;
    }

    public static double degToRad(double deg) {
        return (deg / 180) * Math.PI;
    }

    public static double timeToDec(double hours, double minutes, double seconds) {
        return hours + (minutes / 60) + (seconds / 3600);
    }

    public static double rightAscensionToDegrees(double hours, double minutes, double seconds) {
        // 15 degrees per hour
        return timeToDec(hours, minutes, seconds) * 15;
    }

    public static double declinationToDegrees(double degrees, double minutes, double seconds) {
        double result = Math.abs(degrees) + timeToDec(0, minutes, seconds);
        if (degrees < 0) {
            return -1 * result;
        } else {
            return result;
        }
    }

    public static double coordToDec(double degrees, double minutes) {
        return degrees + timeToDec(0, minutes, 0);
    }

    public static double getDaysJulian(double y, double m, double d, double hour, double minute, double seconds) {
        // Add the hour decimal.
        d += timeToDec(hour, minute, seconds) / 24d;

        // If we are calculating for january or february, consider it the "13th and 14th"
        // months.
        if (m == 1d || m == 2d) {
            y = y - 1d;
            m = m + 12d;
        }

        double A = (int)(y / 100d);
        double B = 2d - A + (int)(A / 4d);
        return (int)(365.25d * (y + 4716d)) + (int)(30.6001d * (m + 1d)) + d + B - 1524.5d;
    }

    public static double getLocalSiderealTime(int utYear, int utMonth, int utDay, int utHour, int utMinute, int utSeconds, double longitude) {
        double JD = getDaysJulian(utYear, utMonth, utDay, utHour, utMinute, utSeconds);
        double T = (JD - 2451545.0d) / 36525d;

        // Mean sidereal time (in degrees) at greenwich for an instant.
        double LMST = 280.46061837d + 360.98564736629d * (JD - 2451545.0d)
                + 0.000387933d * Math.pow(T, 2) - (Math.pow(T, 3) / 38710000d);

        // Adjust the location (which is already represented in a degree decimal).
        LMST += longitude;

        // Bring it within range.
        double range = 360d;
        while (LMST < 0d || LMST > range) {
            if ( LMST > range) LMST -= range;
            else LMST += range;
        }

        // Now we have the LMST
        return LMST;
    }

    public static double getHourAngle(int year, int month, int day, int hour, int minute, int seconds, double longitude, double rightAscension) {
        double result = getLocalSiderealTime(year, month, day, hour, minute, seconds, longitude) - rightAscension;
        if (result < 0) {
            result += 360;
        }

        return result;
    }

    public static double getCurrentSiderealTime(double longitude) {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        return getLocalSiderealTime(utc.getYear(), utc.getMonthValue(), utc.getDayOfMonth(), utc.getHour(), utc.getMinute(), utc.getSecond(), longitude);
    }
}
