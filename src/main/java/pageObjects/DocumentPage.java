package pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;
import java.time.LocalTime;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DocumentPage {

    public SelenideElement textAreaWrapper = $(".CodeMirror div");
    public SelenideElement additionalKeyWordsTextArea = textAreaWrapper.$("textArea");
    public ElementsCollection foundResults = $$(".snippetItem__content");
    public ElementsCollection highlightedArticleTexts = $$(".x-grid3-row-blue");
    public SelenideElement articleIframe = $("#content-1");


    @Step("Open document page")
    public DocumentPage open() {
        Selenide.open("doc/PR-386ea743f2a90399fb0e4300ddf37d0697abc743");
        return this;
    }

    @Step("Make Additional KeyWords TextArea Visible")
    public DocumentPage makeAdditionalKeyWordsTextAreaVisible() {
        Selenide.executeJavaScript("arguments[0].style=''", textAreaWrapper);
        return this;
    }

    @Step("Set Value To Additional KeyWords TextArea")
    public DocumentPage setValueToAdditionalKeyWordsTextArea(String value) {
        makeAdditionalKeyWordsTextAreaVisible();
        additionalKeyWordsTextArea.setValue(value);
        return this;
    }

    @Step("Search By Additional KeyWords TextArea")
    public DocumentPage searchByAdditionalKeyWordsTextArea() {
        makeAdditionalKeyWordsTextAreaVisible();
        additionalKeyWordsTextArea.pressEnter();
        return this;
    }

    @Step("Get Last Found Result Text")
    public String getLastFoundResultText() {
        return foundResults.last().getText();
    }

    @Step("Scroll To Last Found Result")
    public DocumentPage scrollToLastFoundResult() {
        int countBeforeScroll, countAfterScroll;
        LocalTime startTime = LocalTime.now();

        do {
            countBeforeScroll = foundResults.size();
            foundResults.last().scrollIntoView(true);
            countAfterScroll = foundResults.size();
        } while (countBeforeScroll != countAfterScroll &&
                LocalTime.now().isBefore(startTime.plus(Duration.ofSeconds(20))));

        return this;
    }

    @Step("Click Last Found Result")
    public DocumentPage clickLastFoundResult() {
        foundResults.last().click();
        return this;
    }

    @Step("Get Highlighted Article Texts")
    public String getHighlightedArticleTexts() {
        Selenide.switchTo().frame(articleIframe);
        highlightedArticleTexts.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return String.join(" ", highlightedArticleTexts.texts());
    }
}
