package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;
import utility.ExecutionResponse;
import utility.HumanBeingAsker;

public class Add extends Command {
    private final CollectionManager collectionManager;

    public Add(Console console, CollectionManager collectionManager, HumanBeingAsker asker) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        HumanBeing human = collectionManager.getTempHuman();
        if (human == null)
            return new ExecutionResponse(false, "Элемент не передан от клиента");

        collectionManager.add(human);
        return new ExecutionResponse(true, "Элемент успешно добавлен");
    }
}
