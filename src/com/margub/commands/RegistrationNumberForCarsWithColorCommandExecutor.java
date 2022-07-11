package com.margub.commands;

import com.margub.OutputPrinter;
import com.margub.exceptions.InvalidCommandException;
import com.margub.models.Car;
import com.margub.models.Slot;
import com.margub.service.ParkingLotService;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class RegistrationNumberForCarsWithColorCommandExecutor extends CommandExecutor
{

	public static final String COMMAND_IDENTIFIER = "registration_numbers_for_cars_with_colour";

	public RegistrationNumberForCarsWithColorCommandExecutor(ParkingLotService parkingLotService,
			OutputPrinter outputPrinter)
	{
		super(parkingLotService, outputPrinter);
	}

	@Override public boolean validate(Command command)
	{
		return command.getCommand().equals(COMMAND_IDENTIFIER) && command.getParams().size()==1;
	}

	@Override public void execute(Command command)
	{
		if(!validate(command)) {
			throw new InvalidCommandException();
		}

		String color = command.getParams().get(0);
		List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
		List<String> collect = occupiedSlots.stream().map(Slot::getCar).filter(car -> car.getColor().equals(color)).map(
				Car::getRegistrationNumber).collect(Collectors.toList());
		StringJoiner stringJoiner = new StringJoiner(",");
		collect.forEach(stringJoiner::add);
		outputPrinter.printWithNewLine(stringJoiner.toString());
	}
}
