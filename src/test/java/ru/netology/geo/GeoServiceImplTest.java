package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class GeoServiceImplTest {
    GeoService geoService = new GeoServiceImpl();

    @Test
    void byIp() {
        String rusIp = "172.";
        String usaIp = "96.";
        assertThat(Country.RUSSIA, equalTo(geoService.byIp(rusIp).getCountry()));
        assertThat(Country.USA, equalTo(geoService.byIp(usaIp).getCountry()));
    }

    @Test
    void byCoordinates() {
        double anyLatitude = 12;
        double anyLongitude = 13;
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(anyLatitude, anyLongitude));
    }
}