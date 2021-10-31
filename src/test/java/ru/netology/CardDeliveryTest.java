package ru.netology;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldRegister() {
        $(byCssSelector("fieldset div:nth-child(1) input")).setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Иван Иванов");
        $(byName("phone")).setValue("+71234567431");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $(byClassName("notification__title")).shouldHave(exactText("Успешно!"), Duration.ofSeconds(15));
    }

    @Test
    void shouldRegisterWithoutCity(){
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Иван Иванов");
        $(byName("phone")).setValue("+71234567431");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $(byClassName("input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldRegisterWithoutName(){
        $(byCssSelector("fieldset div:nth-child(1) input")).setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("phone")).setValue("+71234567431");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $(byCssSelector("form div:nth-child(3) span span.input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldRegisterWithoutPhone(){
        $(byCssSelector("fieldset div:nth-child(1) input")).setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Иван Иванов");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $(byCssSelector("div:nth-child(4) span span.input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldRegisterWithoutCheckBox(){
        $(byCssSelector("fieldset div:nth-child(1) input")).setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Иван Иванов");
        $(byName("phone")).setValue("+71234567431");
        $("[class='button__text']").click();
        $("[data-test-id=agreement]").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldRegisterWithInvalidCity() {
        $(byCssSelector("fieldset div:nth-child(1) input")).setValue("Moscow");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Иван Иванов");
        $(byName("phone")).setValue("+71234567431");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $(byClassName("input__sub")).shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldRegisterWithInvalidName() {
        $(byCssSelector("fieldset div:nth-child(1) input")).setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Ivan");
        $(byName("phone")).setValue("+71234567431");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $(byCssSelector("fieldset div:nth-child(3) span span.input__sub")).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldRegisterWithInvalidNumber() {
        $(byCssSelector("fieldset div:nth-child(1) input")).setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Иван Иванов");
        $(byName("phone")).setValue("+712345");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $(byCssSelector("fieldset div:nth-child(4) span span.input__sub")).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }




}


