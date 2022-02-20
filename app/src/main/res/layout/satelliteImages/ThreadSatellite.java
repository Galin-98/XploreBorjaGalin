package com.example.xplore.satelliteImages;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ThreadSatellite extends Thread
{

    int countImages = 0;
    int countDate = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run()
    {
        super.run();

        try
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while(countImages < 3)
            {

                countDate++ ;

                String date = LocalDate.now().minusYears(countDate).format(formatter);

                URL url = new URL("https://api.nasa.gov/planetary/earth/imagery?lon=" + SatelliteImages.longitude + "&lat=" + SatelliteImages.latitude + "&date=" + date + "&api_key=qJ0zfwEXsZf7WJ231fFgaTPIdeAbnO4mmV81g6ta&dim=0.1");

                System.out.println(url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == 200) {
                    System.out.println("INSIDE");

                    SatelliteImageResult.matrix.add(new ArrayList<String>());
                    SatelliteImageResult.matrix.get(countImages).add(date.substring(0, 4));
                    SatelliteImageResult.matrix.get(countImages).add(String.valueOf(url));
                    countImages++;
                }

                else
                {
                    break;
                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }


    }
}
