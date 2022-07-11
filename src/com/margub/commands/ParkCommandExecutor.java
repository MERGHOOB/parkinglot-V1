package com.margub.commands;

import com.margub.OutputPrinter;
import com.margub.exceptions.NoFreeSlotAvailableException;
import com.margub.models.Car;
import com.margub.service.ParkingLotService;

/*
park KA-01-HH-1234 White
 */
public class ParkCommandExecutor extends CommandExecutor
{

	public static final String command_identifier = "park";

	public ParkCommandExecutor(ParkingLotService parkingLotService,
			OutputPrinter outputPrinter)
	{
		super(parkingLotService, outputPrinter);
	}

	@Override public boolean validate(Command command)
	{
		return command.getCommand().equals(command_identifier)
				&& command.getParams().size() == 2;
	}

	@Override public void execute(Command command)
	{

		// it will create a Car object;
		final Car car = new Car(command.getParams().get(0), command.getParams().get(1));
		final Integer slot;
		try {
			slot = parkingLotService.park(car);
			outputPrinter.printWithNewLine("Allocated slot number: " + slot);
		} catch (NoFreeSlotAvailableException e) {
			outputPrinter.parkingLotFull();
		}
	}
}
