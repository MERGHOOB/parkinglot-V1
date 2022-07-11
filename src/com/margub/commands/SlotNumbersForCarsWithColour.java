package com.margub.commands;

import com.margub.OutputPrinter;
import com.margub.exceptions.InvalidCommandException;
import com.margub.models.Slot;
import com.margub.service.ParkingLotService;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class SlotNumbersForCarsWithColour extends CommandExecutor
{

	public static final String COMMAND_IDENTIFIER = "slot_numbers_for_cars_with_colour";

	public SlotNumbersForCarsWithColour(ParkingLotService parkingLotService,
			OutputPrinter outputPrinter)
	{
		super(parkingLotService, outputPrinter);
	}

	@Override public boolean validate(Command command)
	{
		return command.getCommand().equals(COMMAND_IDENTIFIER) && command.getParams().size() == 1;
	}

	@Override public void execute(Command command)
	{
		if (!validate(command)) {
			throw new InvalidCommandException();
		}

		StringJoiner stringJoiner = new StringJoiner(",");
		List<Integer> collect = parkingLotService.getOccupiedSlots().stream()
				.filter(slot -> slot.getCar().getColor().equals(command.getParams().get(0)))
				.map(Slot::getSlotNumber)
				.collect(Collectors.toList());
		collect.stream().map(String::valueOf).forEach(stringJoiner::add);
		outputPrinter.printWithNewLine(stringJoiner.toString());
	}
}
