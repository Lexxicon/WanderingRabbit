/**
 * 
 */
package com.biotech.toys.bees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biotech.toys.bees.components.physical.Location;

/**
 * Created: Aug 9, 2014
 * 
 * @author Brian Holman
 *
 */
public class MathUtil {

	private static transient final Logger LOGGER = LoggerFactory.getLogger(MathUtil.class);

	/**
	 * Calculates the angle from centerPt to targetPt in degrees. The return
	 * should range from [0,360), rotating CLOCKWISE, 0 and 360 degrees
	 * represents NORTH, 90 degrees represents EAST, etc...
	 *
	 * Assumes all points are in the same coordinate space. If they are not, you
	 * will need to call SwingUtilities.convertPointToScreen or equivalent on
	 * all arguments before passing them to this function.
	 *
	 * @param centerPt
	 *            Point we are rotating around.
	 * @param targetPt
	 *            Point we want to calcuate the angle to.
	 * @return angle in degrees. This is the angle from centerPt to targetPt.
	 */
	public static float calcRotationAngleInDegrees(Location centerPt, Location targetPt) {
		// calculate the angle theta from the deltaY and deltaX values
		// (atan2 returns radians values from [-PI,PI])
		// 0 currently points EAST.
		// NOTE: By preserving Y and X param order to atan2, we are expecting
		// a CLOCKWISE angle direction.
		float theta = (float) Math.atan2(targetPt.y - centerPt.y, targetPt.x - centerPt.x);
		return theta;
	}
}
