package com.example.xplore.asteroids.Threads;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.xplore.asteroids.Asteroids;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class AsteroidThread extends Thread
{
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        super.run();


        try
        {
            URL link = null;
            link = new URL("https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=qJ0zfwEXsZf7WJ231fFgaTPIdeAbnO4mmV81g6ta");
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setRequestMethod("GET");

            if( connection.getResponseCode() == 200)
            {
                InputStream dataApi = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(dataApi);
                BufferedReader bf = new BufferedReader(isr);
                String line = "";
                String result = "";

                //Put Api respond in to String
                while((line = bf.readLine()) != null)
                {
                    result += line;
                }


                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("near_earth_objects");

                for (int i = 0; i < jsonArray.length(); i++)
                {

                    Asteroids.asteroidsMatrix.add(new ArrayList<>());
                    Asteroids.asteroidsMatrix.get(i).add(jsonArray.getJSONObject(i).getString("name"));
                    Asteroids.asteroidsMatrix.get(i).add(jsonArray.getJSONObject(i).getJSONObject("estimated_diameter").getJSONObject("kilometers").getString("estimated_diameter_max"));
                    Asteroids.asteroidsMatrix.get(i).add(jsonArray.getJSONObject(i).getString("is_potentially_hazardous_asteroid"));


                    String todayD = LocalDate.now().toString();
                    SimpleDateFormat format  = new SimpleDateFormat("yyyy-mm-dd");
                    Date today = format.parse(todayD);
                    Date lastDate = format.parse("0000-00-00");
                    Date nextDate;
                    boolean check = false;
                    int count = 0;


                    while( check == false)
                    {
                        String dateAux = jsonArray.getJSONObject(i).getJSONArray("close_approach_data").getJSONObject(count).getString("close_approach_date");
                        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateAux);


                        if (date1.before(today) && date1.after(lastDate))
                        {
                            System.out.println("CAMBIO FECHA");
                            lastDate = date1;
                            count++;
                        }

                        else if (date1.after(today))
                        {
                            System.out.println("FECHA GUARDADA");
                            Asteroids.asteroidsMatrix.get(i).add(lastDate.toString());
                            String kmh = jsonArray.getJSONObject(i).getJSONArray("close_approach_data").getJSONObject(count-1).getJSONObject("relative_velocity").getString("kilometers_per_hour");
                            Asteroids.asteroidsMatrix.get(i).add(kmh);
                            String distance = jsonArray.getJSONObject(i).getJSONArray("close_approach_data").getJSONObject(count-1).getJSONObject("miss_distance").getString("kilometers");
                            Asteroids.asteroidsMatrix.get(i).add(distance);

                            nextDate = date1;
                            Asteroids.asteroidsMatrix.get(i).add(nextDate.toString());
                            String kmh2 = jsonArray.getJSONObject(i).getJSONArray("close_approach_data").getJSONObject(count).getJSONObject("relative_velocity").getString("kilometers_per_hour");
                            Asteroids.asteroidsMatrix.get(i).add(kmh2);
                            String distance2 = jsonArray.getJSONObject(i).getJSONArray("close_approach_data").getJSONObject(count).getJSONObject("miss_distance").getString("kilometers");
                            Asteroids.asteroidsMatrix.get(i).add(distance2);


                            check = true;
                        }

                    }


                }
            }


        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
