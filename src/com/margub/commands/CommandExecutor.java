package com.margub.commands;

import com.margub.OutputPrinter;
import com.margub.exceptions.CommandIdentifierException;
import com.margub.service.ParkingLotService;

public abstract class CommandExecutor
{
	protected ParkingLotService parkingLotService;
	protected OutputPrinter outputPrinter;

	public CommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter)
	{
		this.parkingLotService = parkingLotService;
		this.outputPrinter = outputPrinter;
	}




	public abstract boolean validate(Command command);

	public abstract void execute(final Command command);
}
