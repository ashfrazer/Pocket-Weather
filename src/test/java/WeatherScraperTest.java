import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        WeatherData data = scraper.scrapeData("00000");

        // Ensure that proper string is returned upon invalid entries
        assertNotNull(data);
        assertEquals("UNKNOWN", data.getLocation());
        assertEquals("No data found.", data.getTemperature());
        assertEquals("No data found.", data.getCondition());
        assertEquals("No data found.", data.getHigh());
        assertEquals("No data found.", data.getLow());
        assertEquals("No data found.", data.getTime());
    }
}
