import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Date;

public class Record {
    // 09/27/2020 19:45 PDT
    private DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm z", Locale.US);

    //data variable
    private String station;
    private LocalDateTime date;
    private double temp;
    private double humidity;
    private double windSpeed;
    private double windDirection;

    public Record(String station, LocalDateTime date, double temp, double humidity, double windSpeed, double windDirection) {
        super();
        this.station = station;
        this.date = date;
        this.temp = temp;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public Record(String record) {
        // KMER,09/27/2020 19:45 PDT,26.0,47.61,0.0,0.0
        String[] fields = record.split(",");

        this.station = fields[0];
        this.date = LocalDateTime.parse(fields[1], dataFormat);
        this.temp = Double.parseDouble(fields[2]);
        this.humidity = Double.parseDouble(fields[3]);
        this.windSpeed = Double.parseDouble(fields[4]);
        this.windDirection = Double.parseDouble(fields[5]);
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDateTime.parse(date, dataFormat);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    @Override
    public String toString() {
        return "Record [station=" + station + ", date=" + date + ", temp=" + temp + ", humidity=" + humidity
                + ", windSpeed=" + windSpeed + ", windDirection=" + windDirection + "]";
    }
}
