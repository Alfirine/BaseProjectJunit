package project.enums;

/**
 * Перечисление, описывающее возможные окружения для запуска тестов.
 */
public enum Environment {
    DEV("dev"), STAGE("stage");

    private final String environment;

    Environment(String environment) {
        this.environment = environment;
    }

    /**
     * Получение строкового представления.
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * Получение элемента перечисления по его строковому представлению.
     *
     * @param environment строковое представление элемента перечисления.
     * @return элемент перечисления. Если элемент не найден, либо передано не корректное значение,
     * возвращается дефолтное значениe
     */
    public static Environment getValue(String environment) {
        Environment result = Environment.DEV;
        if (environment != null) {
            try {
                result = Environment.valueOf(environment.toUpperCase());
            } catch (IllegalArgumentException e) {
                return result;
            }
        }
        return result;
    }
}
