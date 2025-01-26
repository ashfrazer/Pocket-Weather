import java.util.Scanner;

public class WeatherApp {
    // FRONT-END
    // TO-DO:
    //  - Create GUI interface
    private WeatherController weatherController;

    public WeatherApp() {
        WeatherScraper weatherScraper = new WeatherScraper();
        this.weatherController = new WeatherController(weatherScraper, this);

        // TO-DO: GUI LOGIC
    }
    public void updateWeatherUI(WeatherData weatherData) {
        // TO-DO: UPDATE GUI LOGIC
    }
    public static void main(String[] args) {
        new WeatherApp();
    }
}
