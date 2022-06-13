package ru.netology.sender;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class MessageSenderImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"rusIp", "usaIp"})
    void send(String ip) {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(
                        Objects.equals(ip, "rusIp")
                        ? new Location("Moscow", Country.RUSSIA, null, 0)
                        : new Location("New York", Country.USA, null, 0)
                );
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        assertThat(Objects.equals(ip, "rusIp") ? "Добро пожаловать" : "Welcome", equalTo(messageSender.send(headers)));
    }
}