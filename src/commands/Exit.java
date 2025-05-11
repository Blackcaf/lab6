package commands;

import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда для завершения работы программы без автоматического сохранения коллекции.
 */
public class Exit extends Command {
    private final Console console;

    /**
     * Создаёт команду Exit с указанной консолью.
     *
     * @param console консоль для вывода сообщений
     */
    public Exit(Console console) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.console = console;
    }

    /**
     * Выполняет команду, завершая программу.
     *
     * @param arguments аргументы команды (не используются)
     * @return результат выполнения команды с сообщением о завершении
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length > 0) return new ExecutionResponse(false, "Команда 'exit' не принимает аргументы");
        return new ExecutionResponse(false, "Программа завершена");
    }
}