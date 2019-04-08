package com.honey.design;

import com.honey.computer.ComponentHandler;

import java.util.Arrays;
import java.util.List;

public class DependCommand implements  Command {

    ComponentHandler handler;

    public DependCommand(ComponentHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(String command, List<String> arguments) {
        System.out.println(command);
        String component = arguments.get(0);
        String[] args = arguments.stream().toArray(String[]::new);
        String[] dependencies = Arrays.copyOfRange(args, 1, args.length);
        handler.handleDepend(component, dependencies);
    }
}
