package com.margub.modes;

import com.margub.OutputPrinter;
import com.margub.commands.Command;
import com.margub.commands.CommandExecutor;
import com.margub.commands.CommandExecutorFactory;

import java.io.IOException;

public abstract class Mode {
   protected CommandExecutorFactory commandExecutorFactory;
   protected OutputPrinter outputPrinter;

   public Mode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter)
   {
      this.commandExecutorFactory = commandExecutorFactory;
      this.outputPrinter = outputPrinter;
   }

   public abstract void process() throws IOException;

   public void processCommand(Command command) {
      CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
      commandExecutor.execute(command);
   }
}
