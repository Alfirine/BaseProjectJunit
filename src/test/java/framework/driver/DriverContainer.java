package framework.driver;

import com.codeborne.selenide.WebDriverRunner;
import framework.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

/**
 * Класс-контейнер, содержащий экземпляры веб-драйвера, готовые к использованию.
 */
public class DriverContainer {

    private final static ThreadLocal<HashMap<Instance, RemoteWebDriver>> drivers = new InheritableThreadLocal<HashMap<Instance, RemoteWebDriver>>() {
        @Override
        protected HashMap<Instance, RemoteWebDriver> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     * Инициализация основного экземпляра драйвера.
     */
    public static void setDrivers() {
        createDriver(Instance.FIRST);
        switchToFirst();
    }

    /**
     * Создание драйвера и помещение его в контейнер.
     *
     * @param instanceKey ключ, относящийся к определенному экземпляру драйвера.
     */
    private static void createDriver(Instance instanceKey) {
        IDriverFactory driverFactory = new framework.driver.DriverFactory();
        RemoteWebDriver driver = driverFactory.getDriver();
        Logger.getInstance().info("Browser size is " + driver.manage().window().getSize().toString());
        drivers.get().put(instanceKey, driver);
    }

    /**
     * Переключение на основной экземпляр драйвера.
     */
    public static void switchToFirst() {
        switchDriver(Instance.FIRST);
    }

    /**
     * Закрытие основного экземпляра драйвера.
     */
    public static void closeFirst() {
        closeDriver(Instance.FIRST);
    }

    /**
     * Переключение на второй экземпляр драйвера.
     */
    public static void switchToSecond() {
        switchDriver(Instance.SECOND);
    }

    /**
     * Закрытие второго экземпляра драйвера.
     */
    public static void closeSecond() {
        closeDriver(Instance.SECOND);
    }

    /**
     * Переключение на третий экземпляр драйвера.
     */
    public static void switchToThird() {
        switchDriver(Instance.THIRD);
    }

    /**
     * Закрытие третьего экземпляра драйвера.
     */
    public static void closeThird() {
        closeDriver(Instance.THIRD);
    }

    /**
     * Переключение на четвертый экземпляр драйвера.
     */
    public static void switchToFourth() {
        switchDriver(Instance.FOURTH);
    }

    /**
     * Закрытие четвертого экземпляра драйвера.
     */
    public static void closeFourth() {
        closeDriver(Instance.FOURTH);
    }

    /**
     * Закрытие определенного экземпляра драйвера
     */
    private static void closeDriver(Instance instanceKey) {
        drivers.get().get(instanceKey).close();
        drivers.get().remove(instanceKey);
    }

    /**
     * Переключение на определенный экземпляр драйвера.
     * Если драйвер с заданным ключом отсутствует в контейнере, то происходит его создание.
     *
     * @param instanceKey ключ, относящийся к определенному экземпляру драйвера.
     */
    private static void switchDriver(Instance instanceKey) {
        if (drivers.get().get(instanceKey) == null) {
            createDriver(instanceKey);
        }
        WebDriverRunner.setWebDriver(drivers.get().get(instanceKey));
    }

    /**
     * Уничтожение всех созданных экземпляров драйверов и очищение контейнера.
     */
    public static void quit() {
        for (Instance instanceKey : drivers.get().keySet()) {
            drivers.get().get(instanceKey).quit();
        }
        drivers.get().clear();
    }

    /**
     * Ключи, идентифицирующие экземпляры драйверов.
     */
    public enum Instance {
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }

    /**
     * Получаем текущий запущенный драйвер
     *
     * @return WebDriver возвращаем текущий драйвер
     */
    public static WebDriver getCurrentDriver() {
        return WebDriverRunner.getWebDriver();
    }


    /**
     * Получаем текущий URL
     */
    public static String getCurrentUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    /**
     * Закрываем все окна браузера и открываем новое
     */
    public static void closeAllDriverAndOpenNew() {
        DriverContainer.quit();
        DriverContainer.setDrivers();
    }
}
