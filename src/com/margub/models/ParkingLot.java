package com.margub.models;

import com.margub.exceptions.InvalidSlotException;
import com.margub.exceptions.ParkingLotException;
import com.margub.exceptions.SlotAlreadyOccupiedException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot
{

	private static final int MAX_CAPACITY = 100000;
	private final int capacity;
	private final Map<Integer, Slot> slots ;

	public ParkingLot(int capacity)
	{
		if (capacity > MAX_CAPACITY || capacity <= 0) {
			throw new ParkingLotException("Invalid capacity given for parking lot.");
		}
		this.capacity = capacity;
		this.slots = new HashMap<>();
	}

	public int getCapacity()
	{
		return capacity;
	}

	public Map<Integer, Slot> getSlots()
	{
		return slots;
	}

	public Slot park(Car car, Integer slotNumber)
	{
		Slot slot = getSlot(slotNumber);
		if(!slot.isFree()) {
			throw new SlotAlreadyOccupiedException();
		}
		slot.assignCar(car);
		return slot;

	}

	private Slot getSlot(Integer slotNumber)
	{
		if(slotNumber > getCapacity() || slotNumber <= 0) {
			throw new InvalidSlotException();
		}
		slots.putIfAbsent(slotNumber, new Slot(slotNumber));
		return slots.get(slotNumber);

	}

	public Slot makeSlotFree(int slotNumber)
	{
		Slot slot = getSlot(slotNumber);
		slot.releaseCar();
		return slot;
	}
}
