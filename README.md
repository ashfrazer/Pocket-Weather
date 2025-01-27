## Welcome to Pocket Weather!
(Submission for **CSCI-3355: Software Design and Construction**)

This project was created with the goal of exploring **Maven** and **web scraping**, two areas I've been interested in for some time.
To achieve this goal, I explored various libraries and ultimately decided to learn [jsoup](https://jsoup.org/)-- a Java library that parses HTML pages.

Pocket Weather generates a small, sleek window that contains basic weather information regarding a location of your choice. This information is scraped
from https://weather.com/.

I created a dozen icons that correspond with the current weather condition of your specified location.

<p align="center">
  <img src="https://raw.githubusercontent.com/ashfrazer/Pocket-Weather/master/src/main/resources/imgs/lightning.png" alt="Lightning" />
</p>

## How to Run Pocket Weather
Once you clone this repository, navigate to the **WeatherApp** class. This class is the executable portion of the program. Once you run **WeatherApp*,
the user interface should generate. 

Proceed to enter in a zip code of your choice, and then enjoy the results!

## How to Test Pocket Weather (Maven)
The class **WeatherScraperTest** is a test class that ensures that any potential errors found in scraping weather dad are caught. To run a test
in Maven (assuming you have Maven installed), enter `mvn test` into the IDE's terminal. This will execute **WeatherScraperTest**.
