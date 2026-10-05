package command;

import collection.CollectionManager;

/**
 * remove_key key : удалить элемент из коллекции по его ключу.
 */
public class RemoveKeyCommand extends BaseCommand {
    private final CollectionManager collection;

    public RemoveKeyCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            try {
                Integer value = Integer.parseInt(args[1]);
                collection.removeKey(value);
            } catch (NumberFormatException e) {
                System.out.println("Введён некорректный ключ");
            }
        }
    }

    @Override
    public void getDescription() {
        System.out.println("remove_key key : удалить элемент из коллекции по его ключу");
    }
}
