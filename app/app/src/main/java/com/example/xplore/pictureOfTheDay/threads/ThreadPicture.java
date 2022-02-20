package com.example.xplore.pictureOfTheDay.threads;

import com.example.xplore.pictureOfTheDay.PictureOfTheDay;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ThreadPicture extends Thread {
    @Override
    public void run() {
        super.run();

        try
        {
            URL link = new URL("https://api.nasa.gov/planetary/apod?api_key=qJ0zfwEXsZf7WJ231fFgaTPIdeAbnO4mmV81g6ta&thumbs=true");
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setRequestMethod("GET");

            //If API send good response
            if (connection.getResponseCode() == 200)
            {
                InputStream DataPagina = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(DataPagina);
                BufferedReader bf = new BufferedReader(isr);
                String line = "";
                String result = "";

                //Put Api respond in to String
                while((line = bf.readLine()) != null)
                {
                    result += line;
                }

                //Convert String in to JSON Object
                JSONObject jsonObject = new JSONObject(result);

                //The Api response whit a video
                if(jsonObject.getString("media_type").equals("video"))
                {
                   PictureOfTheDay.url = jsonObject.getString("thumbnail_url");
                   String title = jsonObject.getString("title");
                   title = title.replace("Video:" , "");
                   PictureOfTheDay.titleMain = title;
                   PictureOfTheDay.descriptionMain = jsonObject.getString("explanation");

                }

                //if is a photo
                else
                {
                    PictureOfTheDay.url = jsonObject.getString("hdurl");
                    PictureOfTheDay.titleMain = jsonObject.getString("title");
                    PictureOfTheDay.descriptionMain = jsonObject.getString("explanation");

                }

            }


            else
            {

                System.out.printf("Wrong api code response");

            }


        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

