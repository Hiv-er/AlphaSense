import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DocumentPageUITests extends TestBase {

    @BeforeEach
    void openDocumentPage() {
        documentPage.open();
    }

    @Test
    void checkHighlightedArticleTextAfterSearchingForAdditionalKeywords() {
        documentPage
                .setValueToAdditionalKeyWordsTextArea("AlphaSense")
                .searchByAdditionalKeyWordsTextArea()
                .scrollToLastFoundResult()
                .clickLastFoundResult();

        String lastFoundResultText = documentPage.getLastFoundResultText();
        String highlightedArticleText = documentPage.getHighlightedArticleTexts();

        Assertions.assertEquals(lastFoundResultText, highlightedArticleText);
    }

}
