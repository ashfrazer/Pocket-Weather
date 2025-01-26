public class WeatherController {
    private WeatherScraper weatherScraper;

    public WeatherController(WeatherScraper weatherScraper, WeatherApp weatherApp) {
        this.weatherScraper = weatherScraper;
    }

    public void getWeather(String zipcode) {
        // Create weatherData object that contains scraped data
        WeatherData weatherData = weatherScraper.scrapeData(zipcode);

        // TO-DO: Call GUI update method
    }
}
