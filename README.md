# astro-mathematics
This is a small repo for demonstrating how to convert celestial coordinates into cartesian.
I derived all the math from a combination of the following resources:

* [The book "Astronomical Algorithms"](https://amzn.com/0943396611)
* [[1]Website "Converting RA and DEC to ALT and AZ"](http://www.stargazing.net/kepler/altaz.html)
* [[2] Navy website that shows local apparent sidereal time](http://tycho.usno.navy.mil/sidereal.html)

[2] note: this website doesn't always load. Try again a few times.

### Project structure
| File        | Defintion  |
|:-------------|:-------------|
| /src/AstroMath.java      | The java file that contains all of the actual mathematics in the project. |

### vocabulary
You can research these terms on their own for more information. But here's a quick guide. NOTE: most celestial bodies are stored in polar coordinates with an RA/Dec at an epoch (Currently set at January 1st, 2000). This is updated every 45 or 50 years to account for drift and other variables.

 * RA - right ascension. One of the coordinates used for celestial bodies.
 * Dec - declination. Another coordinate used for celestial bodies.
 * Altitude - the cartesian coordinate representing the Y axis in degrees.
 * Azimuth - the caretesian coordinate representing the X axis in degrees.
 * Sidereal time - the official definition is dizzying. Basically sidereal time is a clock that does not have 24 hours and is used in astronomy. It accounts for lots of other variables that affect visible stars. (That's what I understand it to be anyway).
