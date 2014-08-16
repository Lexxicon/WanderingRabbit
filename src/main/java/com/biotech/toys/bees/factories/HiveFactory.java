/**
 * 
 */
package com.biotech.toys.bees.factories;

import com.artemis.Entity;
import com.artemis.World;
import com.biotech.toys.bees.components.materials.EnergyConversionRate;
import com.biotech.toys.bees.components.materials.EnergyTank;
import com.biotech.toys.bees.components.materials.PollenTank;
import com.biotech.toys.bees.components.physical.LandingZone;
import com.biotech.toys.bees.components.physical.Location;

/**
 * Created: Aug 9, 2014
 * 
 * @author Brian Holman
 *
 */
public class HiveFactory {

	private final World world;

	/**
	 * 
	 */
	public HiveFactory(World world) {
		this.world = world;
	}

	public Entity createHive(float x, float y) {
		Entity e = world.createEntity();

		EnergyTank energyTank = new EnergyTank();
		PollenTank pollenTank = new PollenTank();
		EnergyConversionRate conversionRate = new EnergyConversionRate();
		Location location = new Location();
		LandingZone collider = new LandingZone();

		energyTank.max = 10_000;
		energyTank.energy = 10_000;

		pollenTank.max = 10_000;

		conversionRate.energyOutput = 1;
		conversionRate.pollenInput = 1;

		location.x = x;
		location.y = y;

		collider.h = 50;
		collider.w = 50;
		collider.x = -25;
		collider.y = -25;

		e.addComponent(energyTank);
		e.addComponent(pollenTank);
		e.addComponent(conversionRate);
		e.addComponent(location);
		e.addComponent(collider);

		return e;
	}
}
