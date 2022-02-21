package com.example.xplore.marsPhotos.Threads;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.xplore.marsPhotos.MarsPhotos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ThreadLastCuriosityPhotos extends Thread
{
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        super.run();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String yesterdayDate = LocalDate.now().minusDays(2).format(formatter);

        try {
            URL link = new URL( "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date="+yesterdayDate+"&api_key=zf7QfMVf4ayz0usZoRPtlQuV9JT5zh0818fygooZ");
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            InputStream dataPage = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(dataPage);
            BufferedReader bff = new BufferedReader(isr);
            String line= "";
            String result = "";

            while((line = bff.readLine()) != null)
            {
                result += line;
            }

            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("photos");

            for (int i = 0; i < 25; i++)
            {
                String src = jsonArray.getJSONObject(i).getString("img_src");
                MarsPhotos.lastCuriosityImages.add(src);
                System.out.println(MarsPhotos.lastCuriosityImages.get(i));
            }


            System.out.println("API RESPONSE CODE" + connection.getResponseCode());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
