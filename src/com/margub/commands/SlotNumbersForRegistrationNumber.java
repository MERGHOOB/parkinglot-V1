package com.margub.commands;

import com.margub.OutputPrinter;
import com.margub.exceptions.InvalidCommandException;
import com.margub.models.Slot;
import com.margub.service.ParkingLotService;

import java.util.Optional;

public class SlotNumbersForRegistrationNumber extends CommandExecutor
{

	public static final String command_identifier = "slot_number_for_registration_number";
	public SlotNumbersForRegistrationNumber(ParkingLotService parkingLotService,
			OutputPrinter outputPrinter)
	{
		super(parkingLotService, outputPrinter);
	}

	@Override public boolean validate(Command command)
	{
		return command.getCommand().equals(command_identifier) && command.getParams().size() == 1;
	}

	@Override public void execute(Command command)
	{
		if(!validate(command)) {
			throw new InvalidCommandException();
		}

		final String registrationNumber = command.getParams().get(0);
		Optional<Slot> first = parkingLotService.getOccupiedSlots().stream()
				.filter(slot -> slot.getCar().getRegistrationNumber().equals(registrationNumber))
				.findFirst();
		if(first.isEmpty()) {
			outputPrinter.printWithNewLine("Not found");
		}
		else {
			outputPrinter.printWithNewLine(String.valueOf(first.get().getSlotNumber()));
		}

	}
}
