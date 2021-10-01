package framework.base;


import com.codeborne.selenide.logevents.SelenideLogger;
import framework.Localization;
import framework.driver.DriverContainer;
import framework.utils.Logger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.util.Date;

import static java.lang.String.format;

/**
 * Базовый класс, описывающий любой тест.
 */
public abstract class BaseTest {

    protected static final Logger LOG = Logger.getInstance();

    /**
     * Метод, выполняющийся один раз перед всеми тестами в сьюте.
     * Устанавливает локализацию.
     */
    @BeforeAll
    public static void beforeSuite() {
        Localization.getInstance().setLocale(Localization.Locale.RU);
    }

    /**
     * Метод, выполняюшщийся перед каждый тестом (@Test) в сьюте.
     * Инициализирует веб-драйвер, добавляет слушатель AllureSelenide с целью логирования действий Selenide'а, логирует имя теста.
     */
    @BeforeEach
    public void beforeTest(TestInfo testInfo) {
        DriverContainer.setDrivers();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Date date = new Date();
        System.out.println("Дата и время запуска: " + date);
        LOG.info(format("Test '%s' started", testInfo.getDisplayName()));
    }

    /**
     * Метод, выполняющийся после каждого теста (@Test) в сьюте.
     * Закрывает браузер, логирует имя выполнившегося теста.
     */
    @AfterEach()
    public void afterTest(TestInfo testInfo) {
        DriverContainer.quit();
        LOG.info(format("Test '%s' finished", testInfo.getDisplayName()));
    }
}
