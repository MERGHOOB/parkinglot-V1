package com.margub.commands;

import com.margub.exceptions.CommandIdentifierException;
import com.margub.exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private static final String SPACE = " ";
    private final String command;
    private final List<String> params;

    public String getCommand() {
        return command;
    }

    public List<String> getParams() {
        return params;
    }

    public Command(final String inputLine) throws InvalidCommandException {
        List<String> tokenList = Arrays.stream(inputLine.split(SPACE)).collect(Collectors.toList());
        if(tokenList.isEmpty()) {
            throw new InvalidCommandException();
        }
        command = tokenList.get(0).toLowerCase();
        tokenList.remove(0);
        params = tokenList;
    }

}
