package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class T2testt2 {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }


    @DisplayName("Checksim")
    @ParameterizedTest(name = "Checksim {0}, ожидаем результат: {1}")
    @CsvSource({"1, Скидка 0%",
            "2, Скидка 0%",
            "3, Скидка 5%",
            "4, Скидка 10%",
            "5, Скидка 15%"})
    void simSellTest(String testData, String expectedResult) {
        Selenide.open("https://karelia.tele2.ru/");
        Selenide.zoom(0.75);
        $(".tariff-cards-container__sim-number-selection").$(byText(testData)).click();
        $$(".tariff-cards-container__sim-number-selection")
                .find(text(expectedResult));


    }

    ;

    @ParameterizedTest
    @ValueSource(strings = {
            "Алтайский край",
            "Ростовская область"
    })
    void simSellTest(String testData) {
        Selenide.open("https://karelia.tele2.ru/");
        Selenide.zoom(0.75);
        $("#regionSearchOpener").click();
        $(".text-field").setValue(testData);
        $(".region-results").find(byText(testData)).click();
        $("#regionSearchOpener").shouldHave(text(testData));
    }
;

    static Stream<Arguments>methodSourceTest() {
        return Stream.of(
                Arguments.of("Алтайский край"),
                Arguments.of("Ростовская область")
        );
    }
    @MethodSource("methodSourceTest")
    @ParameterizedTest
    void methodSourceTest(String testData) {
        Selenide.open("https://karelia.tele2.ru/");
        Selenide.zoom(0.75);
                $("#regionSearchOpener").click();
                $(".text-field").setValue(testData);
                $(".region-results").find(byText(testData)).click();
                $("#regionSearchOpener").shouldHave(text(testData));
    }
}






