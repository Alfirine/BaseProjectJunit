package framework.base;

import static com.codeborne.selenide.Condition.visible;

/**
 * Базовый класс для описания страницы приложения.
 */
public abstract class BasePage extends PageObject {

    /**
     * Проверка по элементу, открылась ли страница.
     *
     * @return true - если найден элемент, false - в противном случае.
     */
    public boolean isOpened() {
        return getMainElement().is(visible);
    }

}
