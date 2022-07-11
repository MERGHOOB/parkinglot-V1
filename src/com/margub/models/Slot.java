package com.margub.models;

public class Slot
{
	private Integer slotNumber;
	private Car car;

	public Slot(Integer slotNumber)
	{
		this.slotNumber = slotNumber;
		this.car = null;
	}

	public boolean isFree()
	{
		return car == null;
	}


	public void assignCar(Car car)
	{
		this.car = car;
	}

	public void releaseCar()
	{
		car = null;
	}

	public Integer getSlotNumber()
	{
		return slotNumber;
	}

	public Car getCar()
	{
		return car;
	}
}
