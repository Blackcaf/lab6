package commands;

import managers.CommandManager;
import utility.Console;
import utility.ExecutionResponse;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Команда для выполнения скрипта из файла
 */
public class ExecuteScript extends Command {
    private final Console console;
    private final CommandManager commandManager;
    private final Set<String> executedFiles;

    public ExecuteScript(Console console, CommandManager commandManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.console = console;
        this.commandManager = commandManager;
        this.executedFiles = new HashSet<>();
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length != 1) return new ExecutionResponse(false, "Команда требует один аргумент: имя файла");

        File file = new File(arguments[0]);
        String absolutePath;
        try {
            absolutePath = file.getCanonicalPath();
        } catch (Exception e) {
            return new ExecutionResponse(false, "Ошибка при получении пути файла: " + e.getMessage());
        }

        if (executedFiles.contains(absolutePath)) {
            return new ExecutionResponse(false, "Обнаружена рекурсия: файл '" + absolutePath + "' уже выполняется");
        }

        if (!file.exists() || !file.canRead()) {
            return new ExecutionResponse(false, "Файл не существует или недоступен для чтения");
        }

        executedFiles.add(absolutePath);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    ExecutionResponse response = commandManager.execute(line);
                    console.println(response.getMessage());
                    if (!response.isSuccess() && line.equals("exit")) break;
                }
            }
            executedFiles.remove(absolutePath);
            return new ExecutionResponse(true, "Скрипт успешно выполнен");
        } catch (Exception e) {
            executedFiles.remove(absolutePath);
            return new ExecutionResponse(false, "Ошибка при выполнении скрипта: " + e.getMessage());
        }
    }
}