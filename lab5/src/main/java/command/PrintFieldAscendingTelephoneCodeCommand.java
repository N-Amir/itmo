package command;

import collection.CollectionManager;

/**
 * print_field_ascending_telephone_code : вывести значения поля telephoneCode всех элементов в порядке возрастания.
 */
public class PrintFieldAscendingTelephoneCodeCommand extends BaseCommand {
    private final CollectionManager collection;

    public PrintFieldAscendingTelephoneCodeCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Вы неправильно ввели команду");
        } else if (collection.getCollection().isEmpty()) {
            System.out.println("В коллекции нет элементов");
        } else {
            collection.printFieldAscendingTelephoneCode();
        }
    }

    @Override
    public void getDescription() {
        System.out.println("print_field_ascending_telephone_code : вывести значения поля telephoneCode всех элементов в порядке возрастания");
    }
}
