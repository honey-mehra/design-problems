package com.honey.design;

import com.honey.computer.ComponentHandler;

import java.util.List;

public class RemoveCommand implements Command {

    ComponentHandler handler;

    public RemoveCommand(ComponentHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(String command, List<String> arguments) {
        System.out.println(command);
        String component = arguments.get(1);
        boolean removed = handler.handleRemove(component);
        if (!removed) {
            System.out.println(component + " is still needed");
        }
    }
}
