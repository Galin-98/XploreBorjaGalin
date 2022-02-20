package com.example.xplore.asteroids;

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

public class AsteroidThread extends Thread
{
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

                    Asteroids.asteroidsName.add(jsonArray.getJSONObject(i).getString("name"));
                }
            }


        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
