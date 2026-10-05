package command;

import client.ClientManager;
import collection.CollectionManager;
import data.City;

/**
 * remove_greater {element} : удалить из коллекции все элементы, превышающие заданный.
 */
public class RemoveGreaterCommand extends BaseCommand {
    private final CollectionManager collection;

    public RemoveGreaterCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
            return;
        }
        City city = ExecuteScriptCommand.getFlag()
                ? ClientManager.createCityFromScript(ExecuteScriptCommand.getCityList())
                : clientManager.getCity();
        collection.removeGreater(city);
    }

    @Override
    public void getDescription() {
        System.out.println("remove_greater {element} : удалить из коллекции все элементы, превышающие заданный");
    }
}
