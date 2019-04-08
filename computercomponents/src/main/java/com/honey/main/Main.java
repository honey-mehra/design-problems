package com.honey.main;

import com.honey.computer.ComponentHandler;
import com.honey.design.*;

import java.util.*;

public class Main {

    public static Map<String, Command> commands = new HashMap<>();
    static ComponentHandler handler = new ComponentHandler();

    static {
        commands.put("DEPEND", new DependCommand(handler));
        commands.put("INSTALL", new InstallCommand(handler));
        commands.put("REMOVE", new RemoveCommand(handler));
        commands.put("LIST", new ListCommand(handler));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();

            // no action for empty input
            if (line == null || line.length() == 0) {
                continue;
            }

            // the END command to stop the program
            if ("END".equals(line)) {
                System.out.println("END");
                break;
            }

            String[] tokens = line.split(" ");
            String name = tokens[0];

            Command command = commands.get(name);
            if(command == null) {
                System.err.println("INVALID COMMAND PASSED with name: "+name);
                System.exit(1);
            }
            List<String> arguments = Arrays.asList(tokens);
            command.execute(line, arguments);
        }

        scanner.close();
    }
}
