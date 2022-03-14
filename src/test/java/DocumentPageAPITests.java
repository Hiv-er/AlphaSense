import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestConfig;

import static io.restassured.RestAssured.given;


public class DocumentPageAPITests {

    @BeforeEach
    void prepareRestAssured() {
        TestConfig testConfig = new TestConfig();
        RestAssured.baseURI = testConfig.getSiteUrl() + "services/i/public-document-data/document" +
                "/PR-386ea743f2a90399fb0e4300ddf37d0697abc743/";
    }

    @Test
    void checkOriginalStatementCountForKeywordSearch() {
        given()
                .filter(new AllureRestAssured())
                .param("keyword", "AlphaSense")
                .param("slop", "15")
                .param("released", "1633003200000")
                .log().all()
                .when()
                .get("keyword-search/")
                .then()
                .assertThat()
                .body("searchResults.originalStatementCount", CoreMatchers.equalTo(17))
                .statusCode(200);
    }

    @Test
    void checkTopicsAreNotEmptyForKeywordSearch() {
        given()
                .filter(new AllureRestAssured())
                .param("keyword", "AlphaSense")
                .param("slop", "15")
                .param("released", "1633003200000")
                .log().all()
                .when()
                .get("keyword-search/")
                .then()
                .assertThat()
                .body("topics", Matchers.hasSize(Matchers.greaterThan(0)))
                .statusCode(200);
    }
}
