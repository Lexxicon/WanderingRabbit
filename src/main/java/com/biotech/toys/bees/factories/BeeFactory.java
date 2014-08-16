/**
 * 
 */
package com.biotech.toys.bees.factories;

import com.artemis.Entity;
import com.artemis.World;
import com.biotech.toys.bees.components.Home;
import com.biotech.toys.bees.components.Target;
import com.biotech.toys.bees.components.Wings;
import com.biotech.toys.bees.components.materials.EnergyTank;
import com.biotech.toys.bees.components.materials.PollenTank;
import com.biotech.toys.bees.components.physical.Location;
import com.biotech.toys.bees.components.physical.Velocity;

/**
 * Created: Aug 9, 2014
 * 
 * @author Brian Holman
 *
 */
public class BeeFactory {
	private final World world;

	/**
	 * 
	 */
	public BeeFactory(World world) {
		this.world = world;
	}

	public Entity createBee(Location homeLocation) {
		Entity e = world.createEntity();

		Location location = new Location();
		Velocity velocity = new Velocity();
		Target target = new Target();
		Home home = new Home();
		PollenTank pollenTank = new PollenTank();
		EnergyTank energyTank = new EnergyTank();
		Wings wings = new Wings();

		home.location = homeLocation;

		location.x = homeLocation.x;
		location.y = homeLocation.y;

		wings.distance = 1.0f;
		wings.fuelNeed = 1.0f;

		pollenTank.max = 1000;
		energyTank.max = 1000;

		e.addComponent(location);
		e.addComponent(velocity);
		e.addComponent(target);
		e.addComponent(home);
		e.addComponent(pollenTank);
		e.addComponent(energyTank);
		e.addComponent(wings);

		return e;
	}
}
