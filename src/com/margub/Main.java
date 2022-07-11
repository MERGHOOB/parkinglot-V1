package com.margub;

import com.margub.commands.CommandExecutorFactory;
import com.margub.exceptions.InvalidModeException;
import com.margub.modes.FileMode;
import com.margub.modes.InteractiveMode;
import com.margub.service.ParkingLotService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InvalidModeException, IOException
    {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService);

        if(isInteractiveMode(args)) {
            new InteractiveMode(commandExecutorFactory, outputPrinter).process();
        } else if(isFileInputMode(args)) {
            new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();
        } else {
            throw new InvalidModeException();
        }
    }

    private static boolean isFileInputMode(String[] args) {
        return args.length==1;
    }

    private static boolean isInteractiveMode(String[] args) {
        return args.length==0;
    }
}
