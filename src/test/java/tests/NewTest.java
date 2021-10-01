package tests;

import framework.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import steps.MainSteps;

public class NewTest extends BaseTest {
    MainSteps mainSteps = new MainSteps();

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void test() {
        mainSteps.mainStep();
    }
}
