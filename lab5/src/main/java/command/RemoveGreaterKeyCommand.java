package command;

import collection.CollectionManager;

/**
 * remove_greater_key key : удалить из коллекции все элементы, ключ которых превышает заданный.
 */
public class RemoveGreaterKeyCommand extends BaseCommand {
    private final CollectionManager collection;

    public RemoveGreaterKeyCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            try {
                Integer value = Integer.parseInt(args[1]);
                collection.removeGreaterKey(value);
            } catch (NumberFormatException e) {
                System.out.println("Введён некорректный ключ");
            }
        }
    }

    @Override
    public void getDescription() {
        System.out.println("remove_greater_key key : удалить из коллекции все элементы, ключ которых превышает заданный");
    }
}
