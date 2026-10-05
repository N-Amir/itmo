package command;

import client.ClientManager;
import collection.CollectionManager;
import data.City;

/**
 * insert key {element} : добавить новый элемент с заданным ключом.
 */
public class InsertCommand extends BaseCommand {
    private final CollectionManager collection;

    public InsertCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
            return;
        }
        try {
            Integer key = Integer.parseInt(args[1]);
            City city = ExecuteScriptCommand.getFlag()
                    ? ClientManager.createCityFromScript(ExecuteScriptCommand.getCityList())
                    : clientManager.getCity();
            collection.insert(key, city);
        } catch (NumberFormatException e) {
            System.out.println("Введён некорректный ключ");
        }
    }

    @Override
    public void getDescription() {
        System.out.println("insert key {element} : добавить новый элемент с заданным ключом");
    }
}
