package com.margub.commands;

import com.margub.OutputPrinter;
import com.margub.exceptions.InvalidCommandException;
import com.margub.service.ParkingLotService;

public class LeaveCommandExecutor extends CommandExecutor
{

	public static final String COMMAND_IDENTIFIER = "leave";

	public LeaveCommandExecutor(ParkingLotService parkingLotService,
			OutputPrinter outputPrinter)
	{
		super(parkingLotService, outputPrinter);
	}

	@Override public boolean validate(Command command)
	{
	 	return command.getCommand().equals(COMMAND_IDENTIFIER)
				&& command.getParams().size() ==1;
	}

	@Override public void execute(Command command)
	{
		if(!validate(command)) {
			throw new InvalidCommandException();
		}

		final int slotNumber = Integer.parseInt(command.getParams().get(0));
		parkingLotService.makeSlotFree(slotNumber);
		outputPrinter.printWithNewLine("Slot number " + slotNumber + " is Free");
	}
}
