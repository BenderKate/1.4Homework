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
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Иван Иванов");
        $(byName("phone")).setValue("+71234567431");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").shouldHave(exactText("Успешно! Встреча успешно забронирована на " + day), Duration.ofSeconds(15));
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
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("phone")).setValue("+71234567431");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldRegisterWithoutPhone(){
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Иван Иванов");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldRegisterWithoutCheckBox(){
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
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
        $("[data-test-id=city] input").setValue("Moscow");
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
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Ivan");
        $(byName("phone")).setValue("+71234567431");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='name'] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldRegisterWithInvalidNumber() {
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String day = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(day);
        $(byName("name")).setValue("Иван Иванов");
        $(byName("phone")).setValue("+712345");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }




}


