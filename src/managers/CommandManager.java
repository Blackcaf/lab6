package managers;

import commands.*;
import utility.Console;
import utility.ExecutionResponse;
import utility.HumanBeingAsker;

import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер команд.
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final Console console;

    public CommandManager(CollectionManager collectionManager, Console console) {
        this.console = console;
        HumanBeingAsker asker = new HumanBeingAsker(console);
        commands.put("help", new Help(console, this));
        commands.put("info", new Info(console, collectionManager));
        commands.put("show", new Show(console, collectionManager));
        commands.put("add", new Add(console, collectionManager, asker));
        commands.put("update", new Update(console, collectionManager, asker));
        commands.put("remove_by_id", new RemoveById(console, collectionManager));
        commands.put("clear", new Clear(console, collectionManager));
        commands.put("execute_script", new ExecuteScript(console, this));
        commands.put("exit", new Exit(console));
        commands.put("remove_first", new RemoveFirst(console, collectionManager));
        commands.put("remove_head", new RemoveHead(console, collectionManager));
        commands.put("remove_lower", new RemoveLower(console, collectionManager, asker));
        commands.put("count_less_than_impact_speed", new CountLessThanImpactSpeed(console, collectionManager));
        commands.put("filter_starts_with_name", new FilterStartsWithName(console, collectionManager));
        commands.put("print_unique_impact_speed", new PrintUniqueImpactSpeed(console, collectionManager));
    }

    public ExecutionResponse execute(String commandLine) {
        if (commandLine.isEmpty()) return new ExecutionResponse(false, "Команда не введена");
        String[] parts = commandLine.trim().split("\\s+", 2);
        String commandName = parts[0];
        String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        Command command = commands.get(commandName);
        if (command == null) return new ExecutionResponse(false, "Команда '" + commandName + "' не найдена");
        return command.apply(args);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}