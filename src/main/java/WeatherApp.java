import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherApp extends JFrame {
    private WeatherController weatherController;
    private String zipcode;
    private JFrame frame;
    private Font customFont;
    private int FONT_SIZE = 16;
    private JLabel zipCodeLabel;
    private JLabel locationLabel;
    private JLabel temperatureLabel;
    private JLabel conditionLabel;
    private JLabel highLabel;
    private JLabel lowLabel;
    private JLabel iconLabel;
    private JLabel timeLabel;
    private BufferedImage weatherImage;


    public WeatherApp() {
        try {
            File fontFile = new File("src/main/resources/ShareTechMono-Regular.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont((float) FONT_SIZE);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (FontFormatException | IOException e) {
            customFont = new Font("Arial", Font.PLAIN, FONT_SIZE);
        }

        WeatherScraper weatherScraper = new WeatherScraper();
        this.weatherController = new WeatherController(weatherScraper, this);

        frame = new JFrame("Pocket Weather");

        frame.setIconImage(new ImageIcon("src/main/resources/imgs/rain.png").getImage());

        // Exit program on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Customize frame
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setBackground(Color.BLACK);
        frame.setForeground(Color.BLACK);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // North panel
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.BLACK);

        // Location label
        locationLabel = new JLabel("");
        locationLabel.setForeground(Color.WHITE);
        locationLabel.setFont(new Font(customFont.getFontName(), Font.BOLD, 25));

        locationLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 30, 10));

        // Time label
        timeLabel = new JLabel("");
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font(customFont.getFontName(), Font.BOLD, 15));

        // Add components to North label
        northPanel.add(locationLabel);
        northPanel.add(timeLabel);

        // Center panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);

        // Temperature label
        temperatureLabel = new JLabel("");
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setFont(new Font(customFont.getFontName(), Font.BOLD, 100));
        temperatureLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // High label
        highLabel = new JLabel("");
        highLabel.setForeground(Color.WHITE);
        highLabel.setFont(new Font(customFont.getFontName(), Font.BOLD, 20));
        highLabel.setBorder(BorderFactory.createEmptyBorder(2, 8, 0, 0));
        highLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Low label
        lowLabel = new JLabel("");
        lowLabel.setForeground(Color.WHITE);
        lowLabel.setFont(new Font(customFont.getFontName(), Font.BOLD, 20));
        lowLabel.setBorder(BorderFactory.createEmptyBorder(2, 8, 0, 0));
        lowLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Temperature panel
        JPanel temperaturePanel = new JPanel();
        temperaturePanel.setBackground(Color.BLACK);
        temperaturePanel.setLayout(new BoxLayout(temperaturePanel, BoxLayout.Y_AXIS));
        temperaturePanel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
        temperaturePanel.add(temperatureLabel);
        temperaturePanel.add(highLabel);
        temperaturePanel.add(lowLabel);


        iconLabel = new JLabel();
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        // Condition label
        conditionLabel = new JLabel("");
        conditionLabel.setForeground(Color.WHITE);
        conditionLabel.setFont(new Font(customFont.getFontName(), Font.BOLD, 18));
        conditionLabel.setPreferredSize(new Dimension(180, 50));
        conditionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Icon and Condition panel
        JPanel iconPanel = new JPanel();
        iconPanel.setBackground(Color.BLACK);
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.Y_AXIS));
        iconPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        // Add components to Icon panel
        iconPanel.add(iconLabel);
        iconPanel.add(conditionLabel);

        // Add components to Center panel
        centerPanel.add(temperaturePanel, BorderLayout.WEST);
        centerPanel.add(iconPanel, BorderLayout.EAST);

        // South panel
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLACK);

        // Zip Code Label
        zipCodeLabel = new JLabel("Zip Code:");
        zipCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        zipCodeLabel.setForeground(Color.WHITE);
        zipCodeLabel.setFont(customFont);

        // Zip Code Text Field
        JTextField zipCodeField = new JTextField(5);

        // Enter Button
        JButton enterButton = new JButton("Search");
        enterButton.setFont(new Font(customFont.getFontName(), Font.BOLD, 14));
        enterButton.addActionListener(e -> {
            zipcode = zipCodeField.getText();
            try {
                weatherController.getWeather(zipcode);
            } catch (WeatherScraperException ex) {
                // Error dialog if scraping fails
                JOptionPane.showMessageDialog(frame, "Invalid zip code. Please enter a valid zip code.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        enterButton.setBackground(Color.BLACK);
        enterButton.setForeground(Color.WHITE);

        // Add components to South panel
        southPanel.add(zipCodeLabel, BorderLayout.NORTH);
        southPanel.add(zipCodeField, BorderLayout.CENTER);
        southPanel.add(enterButton, BorderLayout.CENTER);

        // Add subpanels to Main panel
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Add panels to frame
        frame.add(mainPanel);

        frame.pack();
        frame.setVisible(true);
    }
    private String getWeatherIconPath(String condition, String time) {
        String imagePath;
        String conditionLower = condition.toLowerCase();

        // Extract hour from time
        int hour = -1;
        try {
            // Split hour from time
            String[] timeParts = time.split(" ");
            String[] hourMinute = timeParts[0].split(":");
            hour = Integer.parseInt(hourMinute[0]);

            // Convert to military time
            if (timeParts[1].equalsIgnoreCase("PM") && hour != 12) {
                hour += 12;
            } else if (timeParts[1].equalsIgnoreCase("AM") && hour == 12) {
                hour = 0; // midnight
            }
        } catch (Exception e) {
            hour = -1;  // default
        }

        // Check if it is currently day or night
        boolean isDay = hour >= 6 && hour < 18;

        // Check condition and time of day for the icon
        if (conditionLower.contains("sunny")) {
            imagePath = "src/main/resources/imgs/sun.png";
        } else if (conditionLower.equals("cloudy") || conditionLower.contains("overcast")) {
            imagePath = "src/main/resources/imgs/cloud.png";
        } else if (conditionLower.contains("cloudy")) {
            imagePath = isDay ? "src/main/resources/imgs/partly-cloudy-sun.png" :
                    "src/main/resources/imgs/partly-cloudy-moon.png";
        } else if (conditionLower.contains("snow")) {
            imagePath = "src/main/resources/imgs/snow.png";
        } else if (conditionLower.contains("rain") || conditionLower.contains("shower") ||
                conditionLower.contains("hail")) {
            imagePath = isDay ? "src/main/resources/imgs/rain.png" : "src/main/resources/imgs/rain-night.png";
        } else if (conditionLower.contains("storm")) {
            imagePath = "src/main/resources/imgs/lightning.png";
        } else if (conditionLower.contains("clear") || conditionLower.contains("fair")) {
            imagePath = "src/main/resources/imgs/clear.png";
        } else if (conditionLower.contains("sleet")) {
            imagePath = "src/main/resources/imgs/sleet.png";
        } else if (conditionLower.contains("fog")) {
            imagePath = "src/main/resources/imgs/fog.png";
        } else if (conditionLower.contains("mist")) {
            imagePath = "src/main/resources/imgs/mist.png";
        } else if (conditionLower.contains("wind")) {
            imagePath = "src/main/resources/imgs/wind.png";
        } else {
            imagePath = "src/main/resources/imgs/sun.png";
        }

        return imagePath;
    }

    public void updateWeatherUI(WeatherData weatherData) {
        locationLabel.setText(weatherData.getLocation());
        temperatureLabel.setText(weatherData.getTemperature());
        conditionLabel.setText(weatherData.getCondition());
        highLabel.setText("High: " + weatherData.getHigh());
        lowLabel.setText("Low: " + weatherData.getLow());
        timeLabel.setText(weatherData.getTime());

        try {
            File iconFile = new File(getWeatherIconPath(weatherData.getCondition(), weatherData.getTime()));
            weatherImage = ImageIO.read(iconFile);

            ImageIcon icon = new ImageIcon(weatherImage);
            iconLabel.setIcon(icon);
            iconLabel.repaint();

        } catch (IOException e) {
            weatherImage = null;
        }

        iconLabel.revalidate();
        iconLabel.repaint();
    }
    public static void main(String[] args) {
        WeatherApp weatherApp = new WeatherApp();
    }
}
