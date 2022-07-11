package com.margub.models.parking.strategy;

import com.margub.exceptions.NoFreeSlotAvailableException;

import java.util.TreeSet;

/**
 * Parking strategy in which the natural ordering numbers are used for deciding the slot numbers.
 * For example, 1st car will be parked in slot 1, then next in slot 2, then in slot 3, and so on.
 */
public class NaturalOrderingParkingStrategy implements ParkingStrategy
{
	TreeSet<Integer> slotTreeSet = new TreeSet<>();

	@Override public void addSlot(Integer slotNumber)
	{
		slotTreeSet.add(slotNumber);

	}

	@Override public void removeSlot(Integer slotNumber)
	{
			slotTreeSet.remove(slotNumber);
	}

	@Override public Integer getNextSlot() throws NoFreeSlotAvailableException
	{
		if(slotTreeSet.isEmpty()) {
			throw new NoFreeSlotAvailableException();
		}
		return slotTreeSet.first();
	}
}
