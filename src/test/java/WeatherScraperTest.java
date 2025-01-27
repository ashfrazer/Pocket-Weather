import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherScraperTest {
    @Test
    void testValidZipCode() throws WeatherScraperException {
        WeatherScraper scraper = new WeatherScraper();
        WeatherData data = scraper.scrapeData("72032");

        // Ensure returned data is not null
        assertNotNull(data);

        // Check if the location is valid
        assertEquals("Conway, AR", data.getLocation());

        // Check other weather data vars
        assertNotNull(data.getTemperature());
        assertNotNull(data.getCondition());
        assertNotNull(data.getHigh());
        assertNotNull(data.getLow());
        assertNotNull(data.getTime());
    }

    @Test
    void testInvalidZipCode() throws WeatherScraperException {
        WeatherScraper scraper = new WeatherScraper();
        assertThrows(WeatherScraperException.class, () -> scraper.scrapeData("00000"));
    }
}
