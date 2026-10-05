package command;

import collection.CollectionManager;
import collection.Parser;

/**
 * save : сохранить коллекцию в файл.
 */
public class SaveCommand extends BaseCommand {
    private final CollectionManager collection;

    public SaveCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            Parser.saveToXml(collection);
        }
    }

    @Override
    public void getDescription() {
        System.out.println("save : сохранить коллекцию в файл");
    }
}
