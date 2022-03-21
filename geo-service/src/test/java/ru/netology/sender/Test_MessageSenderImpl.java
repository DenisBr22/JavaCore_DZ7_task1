package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

class Test_MessageSenderImpl {

    @Test
    void send_Test_if_RUSSIA() {
        LocalizationService loc = Mockito.spy(LocalizationServiceImpl.class);
        Assertions.assertEquals("Добро пожаловать", loc.locale(Country.RUSSIA));
    }

    @Test
    void send_localeTest_if_USA() {
        LocalizationService loc = Mockito.spy(LocalizationServiceImpl.class);
        Assertions.assertEquals("Welcome", loc.locale(Country.USA));
    }

    @Test
    void send_localeTest_if_BRAZIL() {
        LocalizationService loc = Mockito.spy(LocalizationServiceImpl.class);
        Assertions.assertEquals("Welcome", loc.locale(Country.BRAZIL));
    }

    @Test
    public void send_Test_Language_if_RUSSIA_IP() {
        String ipAddress = "172.0.32.11";
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ipAddress))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> header = new HashMap<>();
        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        String expected = "Добро пожаловать";
        String actual = messageSender.send(header);

        Assertions.assertEquals(expected, actual);
    }
}