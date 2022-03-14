import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import pageObjects.DocumentPage;
import utils.TestConfig;

public class TestBase {
    public static TestConfig testConfig = new TestConfig();
    public DocumentPage documentPage = new DocumentPage();

    @BeforeAll
    static void openSite() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.browserSize = "1920x1070";
        Configuration.pageLoadTimeout = 90000;
        Configuration.baseUrl = testConfig.getSiteUrl();
    }
}
