package client;

import console.ConsoleManager;
import data.Climate;
import data.Human;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Класс для чтения значений полей с валидацией.
 */
public class ReadManager {
    private final ConsoleManager consoleManager = new ConsoleManager();

    /** Читает название города (не пустое). */
    public String readName() {
        System.out.println("Введите название города:");
        while (true) {
            String name = consoleManager.readLine();
            if (!name.isBlank()) {
                return name;
            }
            System.out.println("Название не может быть пустой строкой, введите название:");
        }
    }

    /** Читает координату X (&gt; -132, не null). */
    public Float readCoordinateX() {
        System.out.println("Введите координату X (должна быть больше -132):");
        while (true) {
            try {
                float x = Float.parseFloat(consoleManager.readLine());
                if (x > -132) {
                    return x;
                }
                System.out.println("Значение должно быть больше -132, попробуйте снова:");
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно, попробуйте снова:");
            }
        }
    }

    /** Читает координату Y (&gt; -486). */
    public int readCoordinateY() {
        System.out.println("Введите координату Y (должна быть больше -486):");
        while (true) {
            try {
                int y = Integer.parseInt(consoleManager.readLine());
                if (y > -486) {
                    return y;
                }
                System.out.println("Значение должно быть больше -486, попробуйте снова:");
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно, попробуйте снова:");
            }
        }
    }

    /** Читает площадь (&gt; 0, не null). */
    public Long readArea() {
        System.out.println("Введите площадь города (больше 0):");
        while (true) {
            try {
                long area = Long.parseLong(consoleManager.readLine());
                if (area > 0) {
                    return area;
                }
                System.out.println("Значение должно быть больше 0, попробуйте снова:");
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно, попробуйте снова:");
            }
        }
    }

    /** Читает население (&gt; 0, не null). */
    public Integer readPopulation() {
        System.out.println("Введите население города (больше 0):");
        while (true) {
            try {
                int population = Integer.parseInt(consoleManager.readLine());
                if (population > 0) {
                    return population;
                }
                System.out.println("Значение должно быть больше 0, попробуйте снова:");
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно, попробуйте снова:");
            }
        }
    }

    /** Читает высоту над уровнем моря (пустая строка — null). */
    public Double readMetersAboveSeaLevel() {
        System.out.println("Введите высоту над уровнем моря (или пустую строку для null):");
        while (true) {
            String input = consoleManager.readLine();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно, попробуйте снова:");
            }
        }
    }

    /** Читает плотность населения (&gt; 0). */
    public int readPopulationDensity() {
        System.out.println("Введите плотность населения (больше 0):");
        while (true) {
            try {
                int density = Integer.parseInt(consoleManager.readLine());
                if (density > 0) {
                    return density;
                }
                System.out.println("Значение должно быть больше 0, попробуйте снова:");
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно, попробуйте снова:");
            }
        }
    }

    /** Читает телефонный код (не null, &gt; 0, ≤ 100000). */
    public Integer readTelephoneCode() {
        System.out.println("Введите телефонный код (1-100000):");
        while (true) {
            try {
                int code = Integer.parseInt(consoleManager.readLine());
                if (code > 0 && code <= 100000) {
                    return code;
                }
                System.out.println("Значение должно быть в диапазоне (0; 100000], попробуйте снова:");
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно, попробуйте снова:");
            }
        }
    }

    /** Читает климат (enum, не null). */
    public Climate readClimate() {
        System.out.println("Доступные климатические зоны: " + Arrays.toString(Climate.values()));
        System.out.println("Введите климат:");
        while (true) {
            String input = consoleManager.readLine().toUpperCase();
            try {
                return Climate.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Такого климата не существует, попробуйте снова:");
            }
        }
    }

    /** Читает правителя (может быть null). */
    public HumanReaderResult readGovernor() {
        System.out.println("Хотите добавить правителя? (Yes/No)");
        while (true) {
            String answer = consoleManager.readLine().toLowerCase();
            if (answer.equals("No")) {
                return new HumanReaderResult(null);
            }
            if (answer.equals("Yes")) {
                break;
            }
            System.out.println("Введите «да» или «нет»:");
        }
        int age;
        System.out.println("Введите возраст правителя (больше 0):");
        while (true) {
            try {
                age = Integer.parseInt(consoleManager.readLine());
                if (age > 0) {
                    break;
                }
                System.out.println("Возраст должен быть больше 0, попробуйте снова:");
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно, попробуйте снова:");
            }
        }
        float height;
        System.out.println("Введите рост правителя (больше 0):");
        while (true) {
            try {
                height = Float.parseFloat(consoleManager.readLine());
                if (height > 0) {
                    break;
                }
                System.out.println("Рост должен быть больше 0, попробуйте снова:");
            } catch (NumberFormatException e) {
                System.out.println("Число введено неверно, попробуйте снова:");
            }
        }
        System.out.println("Введите день рождения правителя (yyyy-MM-dd) или пустую строку для null:");
        LocalDateTime birthday = null;
        while (true) {
            String input = consoleManager.readLine();
            if (input.isEmpty()) {
                break;
            }
            try {
                birthday = LocalDate.parse(input).atStartOfDay();
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты, попробуйте снова (yyyy-MM-dd):");
            }
        }
        return new HumanReaderResult(new Human(age, height, birthday));
    }

    /** Вспомогательный класс для результата чтения правителя. */
    public static class HumanReaderResult {
        private final Human human;

        public HumanReaderResult(Human human) {
            this.human = human;
        }

        public Human getHuman() {
            return human;
        }
    }
}
