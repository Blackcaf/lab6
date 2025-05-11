package commands;

import managers.CommandManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для вывода справки по доступным командам
 */
public class Help extends Command {
    private final Console console;
    private final CommandManager commandManager;

    public Help(Console console, CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.console = console;
        this.commandManager = commandManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length > 0) return new ExecutionResponse(false, "Команда 'help' не принимает аргументы");
        StringBuilder sb = new StringBuilder();
        commandManager.getCommands().forEach((name, command) ->
                sb.append(String.format("%s: %s%n", name, command.getDescription())));
        return new ExecutionResponse(true, sb.toString());
    }
}