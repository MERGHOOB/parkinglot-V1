package com.margub.modes;

import com.margub.OutputPrinter;
import com.margub.commands.Command;
import com.margub.commands.CommandExecutorFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileMode extends Mode
{

	private final String filePath;

	public FileMode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter, String filePath)
	{
		super(commandExecutorFactory, outputPrinter);
		this.filePath = filePath;
	}

	@Override
	public void process() throws IOException
	{
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				Command command = new Command(line);
				processCommand(command);
			}
		}
	}
}
