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
    private ImageIcon weatherIcon;

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

        this.zipcode = "72032";

        // TO-DO: GUI LOGIC
        frame = new JFrame("Weather Scraper");

        // TO-DO: Set Icon?
        // frame.setIconImage(new ImageIcon(imgURL).getImage());

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

        locationLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 30, 5));

        // Add components to North label
        northPanel.add(locationLabel);

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

        // Icon (COME BACK TO THIS LATER)
        BufferedImage placeholderImage = new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = placeholderImage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 150, 150);
        g2d.dispose();

        weatherIcon = new ImageIcon(placeholderImage);
        JLabel iconLabel = new JLabel(weatherIcon);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        // Condition label
        conditionLabel = new JLabel("");
        conditionLabel.setForeground(Color.WHITE);
        conditionLabel.setFont(new Font(customFont.getFontName(), Font.BOLD, 18));

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
    public void updateWeatherUI(WeatherData weatherData) {
        // TO-DO: UPDATE GUI LOGIC
        locationLabel.setText(weatherData.getLocation());
        temperatureLabel.setText(weatherData.getTemperature());
        conditionLabel.setText(weatherData.getCondition());
        highLabel.setText("High: " + weatherData.getHigh());
        lowLabel.setText("Low: " + weatherData.getLow());
    }
    public static void main(String[] args) {
        WeatherApp weatherApp = new WeatherApp();
    }
}
