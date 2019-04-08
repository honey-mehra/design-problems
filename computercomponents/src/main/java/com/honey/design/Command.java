package com.honey.design;

import com.honey.computer.ComponentHandler;

import java.util.List;

public interface Command {

    public void execute(String command, List<String> arguments);

}
