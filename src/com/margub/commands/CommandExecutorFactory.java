package com.margub.commands;

import com.margub.OutputPrinter;
import com.margub.service.ParkingLotService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {

    private final Map<String, CommandExecutor> commands = new HashMap<>();

    public CommandExecutorFactory(ParkingLotService parkingLotService) {
        final OutputPrinter outputPrinter = new OutputPrinter();
        commands.put(
                CreateParkingLotCommand.command_identifier,
                new CreateParkingLotCommand(parkingLotService, outputPrinter));
        commands.put(
                ParkCommandExecutor.command_identifier,
                new ParkCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                LeaveCommandExecutor.COMMAND_IDENTIFIER,
                new LeaveCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                StatusCommandExecutor.COMMAND_IDENTIFIER,
                new StatusCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                RegistrationNumberForCarsWithColorCommandExecutor.COMMAND_IDENTIFIER,
                new RegistrationNumberForCarsWithColorCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                SlotNumbersForCarsWithColour.COMMAND_IDENTIFIER,
                new SlotNumbersForCarsWithColour(parkingLotService, outputPrinter));
        commands.put(
                SlotNumbersForRegistrationNumber.command_identifier,
                new SlotNumbersForRegistrationNumber(parkingLotService, outputPrinter));
        commands.put(
                ExitCommandExecutor.COMMAND_IDENTIFIER,
                new ExitCommandExecutor(parkingLotService, outputPrinter));
    }

    public CommandExecutor getCommandExecutor(Command command)
    {
        return commands.get(command.getCommand());
    }
}
