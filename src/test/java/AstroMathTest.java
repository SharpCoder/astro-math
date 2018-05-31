import com.pisolaris.AstroMath;
import org.junit.Assert;
import org.junit.Test;

public class AstroMathTest {

    @Test
    public void testStarFinding() {

        double rightAscension = AstroMath.degToRad(AstroMath.rightAscensionToDegrees(16, 41.7, 0));
        double declination = AstroMath.degToRad(AstroMath.declinationToDegrees(36, 28, 0));
        double latitude = AstroMath.degToRad(AstroMath.declinationToDegrees(52, 30, 0));
        double longitude = AstroMath.degToRad(AstroMath.declinationToDegrees(1, 55, 0) * -1);

        int year = 1998;
        int month = 8;
        int day = 10;
        int hour = 23;
        int minute = 10;
        int seconds = 0;

        double LST = AstroMath.degToRad(AstroMath.getLocalSiderealTime(year, month, day, hour, minute, seconds, AstroMath.radToDeg(longitude)));
        double HA = AstroMath.degToRad(AstroMath.getHourAngle(year, month, day, hour, minute, seconds, AstroMath.radToDeg(longitude), AstroMath.radToDeg(rightAscension)));

        double sinAlt = Math.sin(declination) * Math.sin(latitude) + Math.cos(declination) * Math.cos(latitude) * Math.cos(HA);
        double altitude = Math.asin(sinAlt);

        double cosA =
                (Math.sin(declination) - (sinAlt * Math.sin(latitude)))
                        /
                        (Math.cos(altitude) * Math.cos(latitude));

        double AZ = Math.acos(cosA);
        if (Math.sin(HA) >= 0 )
            AZ = (2 * Math.PI) - AZ;

        Assert.assertTrue(round(AstroMath.radToDeg(HA)) == 54.38);
        Assert.assertTrue(round(AstroMath.radToDeg(LST)) == 304.8);
        Assert.assertTrue(round(AstroMath.radToDeg(altitude)) == 49.16);
        Assert.assertTrue(round(AstroMath.radToDeg(AZ)) == 269.14);
    }

    private double round(double v) {
        return Math.floor(v * 100d) / 100d;
    }

}
