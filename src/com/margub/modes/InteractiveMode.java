package com.margub.modes;

import com.margub.OutputPrinter;
import com.margub.commands.Command;
import com.margub.commands.CommandExecutorFactory;
import com.margub.commands.ExitCommandExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveMode extends Mode
{

	public InteractiveMode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter)
	{
		super(commandExecutorFactory, outputPrinter);
	}

	@Override
	public void process() throws IOException
	{
		outputPrinter.welcome();
		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			final String input = bufferedReader.readLine();
			final Command command = new Command(input);
			if (command.getCommand().equals(ExitCommandExecutor.COMMAND_IDENTIFIER)) {
				break;
			}
			processCommand(command);
		}
	}
}
