package com.margub.commands;

import com.margub.OutputPrinter;
import com.margub.service.ParkingLotService;

public class ExitCommandExecutor extends CommandExecutor
{

	public static final String COMMAND_IDENTIFIER = "exit";

	public ExitCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter)
	{
		super(parkingLotService, outputPrinter);
	}

	@Override public boolean validate(Command command)
	{
		return false;
	}

	@Override public void execute(Command command)
	{

	}
}
