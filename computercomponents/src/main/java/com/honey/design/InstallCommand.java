package com.honey.design;

import com.honey.computer.ComponentHandler;

import java.util.List;

public class InstallCommand implements Command {

    ComponentHandler handler;

    public InstallCommand(ComponentHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(String command, List<String> arguments) {
        System.out.println(command);
        String component = arguments.get(1);
        handler.handleInstall(component);
    }
}
