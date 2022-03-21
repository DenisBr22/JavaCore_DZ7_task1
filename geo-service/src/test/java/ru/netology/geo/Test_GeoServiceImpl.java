package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class Test_GeoServiceImpl {

    @Test
    void test_byIp_null() {
        GeoService geo = Mockito.spy(GeoServiceImpl.class);
        Assertions.assertEquals(null, geo.byIp("95"));
    }

    @Test
    void test_byIp_Moscow() {
        GeoService geo = Mockito.spy(GeoServiceImpl.class);
        Assertions.assertEquals(new Location("Moscow", Country.RUSSIA, "Lenina", 15), geo.byIp("172.0.32.11"));
    }

    @Test
    void test_byIp_USA() {
        GeoService geo = Mockito.spy(GeoServiceImpl.class);
        Assertions.assertEquals(new Location("New York", Country.USA, " 10th Avenue", 32), geo.byIp("96.44.183.149"));
    }
}