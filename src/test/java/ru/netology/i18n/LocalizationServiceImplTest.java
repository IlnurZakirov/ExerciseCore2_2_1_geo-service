package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class LocalizationServiceImplTest {

    @Test
    void locale() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        assertThat("Добро пожаловать", equalTo(localizationService.locale(Country.RUSSIA)));
        assertThat("Welcome", equalTo(localizationService.locale(Country.GERMANY)));
    }
}