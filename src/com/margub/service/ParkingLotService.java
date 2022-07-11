package com.margub.service;

import com.margub.exceptions.ParkingLotException;
import com.margub.models.Car;
import com.margub.models.ParkingLot;
import com.margub.models.Slot;
import com.margub.models.parking.strategy.ParkingStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLotService {

	private ParkingLot parkingLot;
	private ParkingStrategy parkingStrategy;
	public void createParkingLot(int capacity, ParkingStrategy strategy)
	{
		if(parkingLot != null) {
			throw new ParkingLotException("Parking lot already exist");
		}

		parkingLot = new ParkingLot(capacity);
		this.parkingStrategy = strategy;
		for(int i= 0; i<capacity; i++) {
			parkingStrategy.addSlot(i+1); // slot starts from 1
		}
	}

	public Integer park(Car car)
	{
		validateParkingLotExists();
		Integer nextSlot = parkingStrategy.getNextSlot();
		parkingLot.park(car, nextSlot);
		parkingStrategy.removeSlot(nextSlot);
		return nextSlot;
	}

	/**
	 * Helper method to validate whether the parking lot exists or not. This is used to validate the
	 * existence of parking lot before doing any operation on it.
	 */
	private void validateParkingLotExists() {
		if (parkingLot == null) {
			throw new ParkingLotException("Parking lot does not exists to park.");
		}
	}

	public void makeSlotFree(int slotNumber)
	{
		validateParkingLotExists();
		parkingLot.makeSlotFree(slotNumber);
		parkingStrategy.addSlot(slotNumber);

	}

	public List<Slot> getOccupiedSlots()
	{
		validateParkingLotExists();

		return parkingLot.getSlots()
				.entrySet()
				.stream()
				.map(entry -> entry.getValue())
				.filter(e -> !e.isFree())
				.collect(Collectors.toList());
	}
}
