package com.margub.commands;

import com.margub.OutputPrinter;
import com.margub.models.parking.strategy.NaturalOrderingParkingStrategy;
import com.margub.service.ParkingLotService;
import com.margub.validators.IntegerValidator;

public class CreateParkingLotCommand extends CommandExecutor
{

	public static final String command_identifier = "create_parking_lot";

	public CreateParkingLotCommand(ParkingLotService parkingLotService,
			OutputPrinter outputPrinter)
	{
		super(parkingLotService, outputPrinter);
	}

	@Override public boolean validate(Command command)
	{
		return command_identifier.equals(command.getCommand())
				&& command.getParams().size() == 1
				&& IntegerValidator.isInteger(command.getParams().get(0));
	}

	@Override public void execute(Command command)
	{
		int capacity = Integer.parseInt(command.getParams().get(0));

		parkingLotService.createParkingLot(capacity, new NaturalOrderingParkingStrategy());
		outputPrinter.printWithNewLine("Created a parking lot with " + capacity + "Slots");
	}
}
