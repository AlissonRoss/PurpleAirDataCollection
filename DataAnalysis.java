//IMPORTS
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//MAIN
public class DataAnalysis {
    //this ArrayList of Record Class will hold the dataset from each Record
    private static ArrayList<Record> dataset = new ArrayList<Record>();

    public static void main(String[] args) {
        Scanner input = null;
        try {
            input = new Scanner(new File("KMCE.csv")); //looks for file
        } catch (FileNotFoundException e) {
            System.out.println("file not found"); //if file not found then it is caught and 'file not found' prints
            e.printStackTrace();
        }

        //to read in the 8 header lines of the text file
        //we do not need the 8 header lines read
        int count = 0;
        while(count++ < 8) {
            input.nextLine();
        }
        //this gets the input and assigns each record onto the dataset
        while(input.hasNext())  {
            String line = input.nextLine();
            Record record = new Record( line );
            dataset.add( record );
        }

        System.out.println("Database loaded\n");
        //These are function calls. We are sending in
        //the dataset and the desired hour in military time
        calcAndPrintAvg(dataset, 2);
        calcAndPrintAvg(dataset, 8);
        calcAndPrintAvg(dataset, 14);
        calcAndPrintAvg(dataset, 20);
    }//end main

    static void calcAndPrintAvg(ArrayList<Record> dataset, int hourToLookFor) {
        //We initialize and declare the avg of Record Class
        // for the specific hour by passing in
        //the dataset and the hourToLookFor to the getAverage function so it gets
        //the average
        Record avg = getAverage(dataset, hourToLookFor);
        //Ternary Operator: If AM is selected, then true, if not, PM is selected
        String meridian = (hourToLookFor < 12) ? "AM" : "PM";
        hourToLookFor = (hourToLookFor + 11) % 12 + 1; // let 0 map to "12AM"

        //This prints the averages
        System.out.printf("%d%s average:\n", hourToLookFor, meridian);
        System.out.printf("\tTemp       : %.1f*C\n", avg.getTemp());
        System.out.printf("\tHumidity   : %.1f%%\n", avg.getHumidity());
        System.out.printf("\tWind Speed : %.3f m/s\n", avg.getWindSpeed());
        System.out.printf("\tWind Direction : %.2f°\n", avg.getWindDirection());
    }
    //This function gets the averages of the dataset and calculates them for
    //the specific hour that is sent
    static Record getAverage(ArrayList<Record> dataset, int hourToLookFor) {
        //declaring the initial values for the sums of the dataset values
        double tempSum = 0.0;
        double humiditySum = 0.0;
        double windSpeedSum = 0.0;
        double windDirectSum = 0.0;
        int count = 0;
        System.out.println("Records at " + hourToLookFor);
        // for loop that looks at every record in the array
        for (Record record : dataset) {
            //If the current hour iterated equals the hour passed to the function
            if (record.getDate().getHour() == hourToLookFor) {
                //Add/Sum the temperatures, humidity, wind speed, wind direction
                tempSum += record.getTemp();
                humiditySum += record.getHumidity();
                windSpeedSum += record.getWindSpeed();
                windDirectSum += record.getWindDirection();
                //Prints out the record for the specified hourToLookFor
                System.out.println(record.toString());
                ++count;
            }
        }

        if (count == 0) {
            return null;
        }
        //This calculates the average of each
        double tempAvg = tempSum / count;
        double humidityAvg = humiditySum / count;
        double windSpeedAvg = windSpeedSum / count;
        double windDirectAvg = windDirectSum / count;

        //Returns the average temp, avg humidity, avg wind speed
        return new Record(null, null, tempAvg, humidityAvg, windSpeedAvg, windDirectAvg);
    }
}