import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class WeatherScraper {

    public WeatherData scrapeData(String zipcode) {
        // Create the URL
        String url = "https://weather.com/weather/today/l/" + zipcode;

        // Initialize weather variables (in case scraping is unsuccessful)
        String location = "UNKNOWN";
        String temperature = "No data found.";
        String condition = "No data found.";
        String high = "No data found.";
        String low = "No data found.";

        Document doc = null;

        try {
            // Access the HTML page
            doc = Jsoup.connect(url).get();

            // Scrape the location (based on zipcode)
            location = getElementText(doc.select("h1.CurrentConditions--location--yub4l").first());

            // Scrape the current temperature
            temperature = getElementText(doc.select("span.CurrentConditions--tempValue--zUBSz").first());

            // Scrape the current conditions
            condition = getElementText(doc.select("div.CurrentConditions--phraseValue---VS-k").first());

            // Scrape the high temperature
            high = getElementText(doc.select(
                            "div.CurrentConditions--tempHiLoValue--Og9IG span[data-testid='TemperatureValue']")
                    .first());

            // Scrape the low temperature
            low = getElementText(doc.select(
                            "div.CurrentConditions--tempHiLoValue--Og9IG span[data-testid='TemperatureValue']")
                    .last());

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create WeatherData object containing weather info
        WeatherData weatherData = new WeatherData(location, temperature, condition, high, low);

        // Return complete WeatherData object
        return weatherData;
    }
    private String getElementText(Element element) {
        if (element != null) {
            return element.text();
        }
        else {
            return "No data found.";
        }
    }
}