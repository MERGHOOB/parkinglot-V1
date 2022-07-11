package com.margub.commands;

import com.margub.OutputPrinter;
import com.margub.exceptions.InvalidCommandException;
import com.margub.models.Car;
import com.margub.models.Slot;
import com.margub.service.ParkingLotService;

import java.util.List;

public class StatusCommandExecutor extends CommandExecutor
{

	public static final String COMMAND_IDENTIFIER = "status";

	public StatusCommandExecutor(ParkingLotService parkingLotService,
			OutputPrinter outputPrinter)
	{
		super(parkingLotService, outputPrinter);
	}

	@Override public boolean validate(Command command)
	{
		return command.getCommand().equals(COMMAND_IDENTIFIER)
				&& command.getParams().isEmpty();

	}

	@Override public void execute(Command command)
	{
		if(!validate(command)) {
			throw new InvalidCommandException();
		}

		List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
		if(occupiedSlots.isEmpty()) {
			outputPrinter.parkingLotEmpty();
			return;
		}

		outputPrinter.printHeaderStatus();
		for(Slot slot: occupiedSlots) {

			outputPrinter.printWithNewLine(slot.getSlotNumber() + " " + slot.getCar().getRegistrationNumber() + " " + slot.getCar()
					.getColor());
		}

	}
}
