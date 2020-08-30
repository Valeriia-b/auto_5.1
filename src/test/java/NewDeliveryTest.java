import data.DataHelper;
import data.RegistrationInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.Registration.DeliveryDay;
import static data.DataHelper.Registration.getCity;

public class NewDeliveryTest {
    private RegistrationInfo fakeInfo = DataHelper.Registration.generateByCard("ru");
    private String date = DeliveryDay(4);
    private String nextdate = DeliveryDay(10);


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitIfAllCorrect(){
        $("[placeholder='Город']").setValue(getCity());
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL+"a"+Keys.BACK_SPACE);
        $("[placeholder='Дата встречи'").setValue(date);
        $("[name='name']").setValue(fakeInfo.getName());
        $("[name='phone']").setValue(fakeInfo.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $(".notification__content").waitUntil(visible, 15000).shouldHave(text(date));

        $("[data-test-id='success-notification'] .icon-button__text").click();
        $("[placeholder='Дата встречи']").sendKeys(Keys.CONTROL+"a"+Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(nextdate);
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification']").waitUntil(visible, 15000).shouldHave(text("Перепланировать?"));
        $(".notification__content .button__text").click();
        $(".notification__content").waitUntil(visible, 15000).shouldHave(text(nextdate));

    }
}
