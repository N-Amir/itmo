# lab5

Лабораторная работа 5 по программированию.

Консольное приложение для управления коллекцией объектов класса **City**
в интерактивном режиме.

## Требования

- Класс элементов реализует сортировку по умолчанию (`Comparable<City>`: население, затем площадь).
- Коллекция — `java.util.LinkedHashMap<Integer, City>`.
- При запуске коллекция заполняется из XML-файла, имя файла передаётся аргументом командной строки.
- Чтение — `java.io.FileReader`, запись — `java.io.PrintWriter`.
- Все классы документированы в формате javadoc.
- Некорректные данные (в файле, в консоли, в скрипте) обрабатываются без падения программы.

## Команды

- `help` — справка
- `info` — информация о коллекции
- `show` — все элементы коллекции
- `insert key {element}` — добавить элемент с заданным ключом
- `update id {element}` — обновить элемент по id
- `remove_key key` — удалить по ключу
- `clear` — очистить коллекцию
- `save` — сохранить в файл
- `execute_script file_name` — выполнить скрипт
- `exit` — выход без сохранения
- `remove_greater {element}` — удалить элементы больше заданного
- `remove_lower {element}` — удалить элементы меньше заданного
- `remove_greater_key key` — удалить элементы с ключом больше заданного
- `count_greater_than_telephone_code telephoneCode` — количество элементов с телефонным кодом больше заданного
- `print_field_ascending_telephone_code` — телефонные коды по возрастанию
- `print_field_descending_telephone_code` — телефонные коды по убыванию

## Запуск

```bash
mvn clean package
java -jar target/lab5-1.0-SNAPSHOT.jar test.xml
```

Требуется Java 17.

## Формат ввода

- Аргументы простых типов — в одной строке с командой.
- Составные типы (`{element}`) — по одному полю в строку; в скрипте это ровно 12 строк:
  `name, x, y, area, population, metersAboveSeaLevel, populationDensity, telephoneCode, climate,
  governor.age, governor.height, governor.birthday`.
- Для `null` — пустая строка.
- `id` и `creationDate` не вводятся, они генерируются автоматически.
- Климат: `RAIN_FOREST`, `HUMIDSUBTROPICAL`, `OCEANIC`, `DESERT`.

## Формат XML

```xml
<collectionManager>
    <entries>
        <entry key="1"><city>...</city></entry>
    </entries>
    <creation_date_collection>2024-04-27</creation_date_collection>
</collectionManager>
```

Полный пример — `test.xml`, пример скрипта — `script.txt`.
