/**
 * 
 */
package com.biotech.toys.bees.factories;

import com.artemis.Entity;
import com.artemis.World;
import com.biotech.toys.bees.components.ScentRange;
import com.biotech.toys.bees.components.materials.PollenGenerator;
import com.biotech.toys.bees.components.materials.PollenTank;
import com.biotech.toys.bees.components.physical.Location;
import com.biotech.toys.bees.components.physical.LandingZone;

/**
 * Created: Aug 9, 2014
 * 
 * @author Brian Holman
 *
 */
public class FlowerFactory {

	private final World world;

	/**
	 * 
	 */
	public FlowerFactory(World world) {
		this.world = world;
	}

	public Entity createFlower(float x, float y) {
		Entity e = world.createEntity();

		Location location = new Location();
		LandingZone squareCollider = new LandingZone();
		PollenTank pollenTank = new PollenTank();
		PollenGenerator generator = new PollenGenerator();
		ScentRange scentRange = new ScentRange();

		pollenTank.max = 1000;
		generator.rate = .5f;
		location.x = x;
		location.y = y;
		squareCollider.w = 15;
		squareCollider.h = 15;
		scentRange.range = 500;

		e.addComponent(location);
		e.addComponent(squareCollider);
		e.addComponent(pollenTank);
		e.addComponent(generator);
		e.addComponent(scentRange);

		return e;
	}
}
