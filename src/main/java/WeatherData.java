public class WeatherData {
    private String location;
    private String temperature;
    private String condition;
    private String high;
    private String low;

    public WeatherData(String location, String temperature, String condition, String high, String low) {
        this.location = location;
        this.temperature = temperature;
        this.condition = condition;
        this.high = high;
        this.low = low;
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
    @Override
    public String toString() {
        return String.format("""
                Location: %s
                Temperature: %s
                Condition: %s
                High: %s
                Low: %s
                """,
                location, temperature, condition, high, low);
    }
}
