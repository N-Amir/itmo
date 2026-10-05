package command;

import collection.CollectionManager;

/**
 * count_greater_than_telephone_code telephoneCode : вывести количество элементов, значение поля telephoneCode которых больше заданного.
 */
public class CountGreaterThanTelephoneCodeCommand extends BaseCommand {
    private final CollectionManager collection;

    public CountGreaterThanTelephoneCodeCommand(CollectionManager collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            try {
                Integer value = Integer.parseInt(args[1]);
                collection.countGreaterThanTelephoneCode(value);
            } catch (NumberFormatException e) {
                System.out.println("Введён некорректный телефонный код");
            }
        }
    }

    @Override
    public void getDescription() {
        System.out.println("count_greater_than_telephone_code telephoneCode : вывести количество элементов, значение поля telephoneCode которых больше заданного");
    }
}
