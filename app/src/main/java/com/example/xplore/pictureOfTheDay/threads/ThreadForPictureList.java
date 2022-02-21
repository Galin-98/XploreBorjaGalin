package com.example.xplore.pictureOfTheDay.threads;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.xplore.pictureOfTheDay.PictureOfTheDayFromList;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ThreadForPictureList extends Thread
{
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        super.run();

        try
        {
            DateTimeFormatter formatter = null;
            String yesterdayDate ="";
            String last7Date = "";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                yesterdayDate = LocalDate.now().minusDays(1).format(formatter);
                last7Date = LocalDate.now().minusDays(8).format(formatter);

            }


            URL link = new URL("https://api.nasa.gov/planetary/apod?api_key=zf7QfMVf4ayz0usZoRPtlQuV9JT5zh0818fygooZ&start_date=" + last7Date + "&end_date=" + yesterdayDate);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200)
            {
                InputStream dataPagina = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(dataPagina);
                BufferedReader bf = new BufferedReader(isr);
                String linea = "";
                String resultado = "";
                while((linea = bf.readLine()) != null)
                {
                    resultado += linea;
                }

                JSONArray array = new JSONArray(resultado);

                for (int i = 0; i < array.length(); i++)
                {
                    String title = array.getJSONObject(i).getString("title");
                    String date = array.getJSONObject(i).getString("date");
                    String url = array.getJSONObject(i).getString("url");
                    String description = array.getJSONObject(i).getString("explanation");
                    PictureOfTheDayFromList.names.add(title);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String dateNoParse  = date;
                    Date dateAux = format.parse(dateNoParse);
                    format.applyPattern("dd-MM-yyyy");
                    String dateParsed = format.format(dateAux);
                    PictureOfTheDayFromList.dates.add(dateParsed);
                    PictureOfTheDayFromList.photosLink.add(url);
                    PictureOfTheDayFromList.descriptions.add(description);

                }
            }

            else
            {
                System.out.println("API BAD RESPONSE");
            }



        }

        catch (MalformedURLException e)
        {
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
