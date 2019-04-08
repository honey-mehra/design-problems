package com.honey.design;

import com.honey.computer.ComponentHandler;

import java.util.List;

public class ListCommand implements Command {

    ComponentHandler handler;

    public ListCommand(ComponentHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(String command, List<String> arguments) {
        System.out.println("LIST");
        handler.handleList();
    }
}
