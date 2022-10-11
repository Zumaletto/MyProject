package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("188.243.88.144");
        assertEquals(geoIp, "RUS");
        System.out.println(geoIp);
    }

    @Test
    public void testInvalidIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("188.243.88.xxx");
        assertEquals(geoIp, "RUS");
        System.out.println(geoIp);
    }
}
