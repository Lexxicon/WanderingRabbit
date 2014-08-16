/**
 * 
 */
package com.biotech.toys.bees.systems;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.biotech.toys.bees.MathUtil;
import com.biotech.toys.bees.components.Target;
import com.biotech.toys.bees.components.Wings;
import com.biotech.toys.bees.components.materials.EnergyTank;
import com.biotech.toys.bees.components.physical.Location;
import com.biotech.toys.bees.components.physical.Velocity;

/**
 * Created: Aug 9, 2014
 * 
 * @author Brian Holman
 *
 */
public class WingMovementSystem extends EntitySystem {

	private static transient final Logger LOGGER = LoggerFactory.getLogger(WingMovementSystem.class);
	@Mapper
	ComponentMapper<Wings> wingsMap;
	@Mapper
	ComponentMapper<EnergyTank> tankMap;
	@Mapper
	ComponentMapper<Target> targetMap;
	@Mapper
	ComponentMapper<Velocity> velocityMap;
	@Mapper
	ComponentMapper<Location> locationMap;

	final private Set<Entity> releventEntities;

	/**
	 * @param aspect
	 */
	@SuppressWarnings("unchecked")
	public WingMovementSystem(Aspect aspect) {
		super(Aspect.getAspectForAll(Wings.class, EnergyTank.class, Target.class, Velocity.class, Location.class));
		releventEntities = new CopyOnWriteArraySet<Entity>();
	}

	/*
	 * @see
	 * com.artemis.EntitySystem#processEntities(com.artemis.utils.ImmutableBag)
	 */
	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {

		for (Entity e : releventEntities) {
			Wings wings = wingsMap.get(e);
			EnergyTank tank = tankMap.get(e);
			Target target = targetMap.get(e);
			Location location = locationMap.get(e);
			Velocity velocity = velocityMap.get(e);

			if (tank.energy >= wings.fuelNeed && target.location != null) {
				tank.energy -= wings.fuelNeed;
				double targetAngle = Math.toRadians(MathUtil.calcRotationAngleInDegrees(location, target.location));
				velocity.x = (float) Math.cos(targetAngle) * wings.distance;
				velocity.y = (float) Math.sin(targetAngle) * wings.distance;
			}
		}

	}

	/*
	 * @see com.artemis.EntitySystem#checkProcessing()
	 */
	@Override
	protected boolean checkProcessing() {
		return true;
	}

	/*
	 * @see com.artemis.EntitySystem#inserted(com.artemis.Entity)
	 */
	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		releventEntities.add(e);
	}

	/*
	 * @see com.artemis.EntitySystem#removed(com.artemis.Entity)
	 */
	@Override
	protected void removed(Entity e) {
		super.removed(e);
		releventEntities.add(e);
	}
}
