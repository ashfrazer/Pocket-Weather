public class WeatherData {
    private String location;
    private String temperature;
    private String condition;
    private String high;
    private String low;
    private String time;

    public WeatherData(String location, String temperature, String condition, String high, String low, String time) {
        this.location = location;
        this.temperature = temperature;
        this.condition = condition;
        this.high = high;
        this.low = low;
        this.time = time;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getTemperature() {
        return temperature;
    }
    public void setCondition(String condition) {
        // Shorten text
        if (condition.equals("Showers in the Vicinity")) {
            condition = "Rain Coming";
        }
        this.condition = condition;
    }
    public String getCondition() {
        return condition;
    }
    public void setHigh(String high) {
        this.high = high;
    }
    public String getHigh() {
        return high;
    }
    public void setLow(String low) {
        this.low = low;
    }
    public String getLow() {
        return low;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    @Override
    public String toString() {
        return String.format("""
                Location: %s
                Temperature: %s
                Condition: %s
                High: %s
                Low: %s
                Time: %s
                """,
                location, temperature, condition, high, low, time);
    }
}
