package com.example.xplore.pictureOfTheDay.threads;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.xplore.pictureOfTheDay.PictureOfTheDay7List;
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

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
            {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                yesterdayDate = LocalDate.now().minusDays(1).format(formatter);
                last7Date = LocalDate.now().minusDays(8).format(formatter);

                System.out.println(yesterdayDate);
                System.out.println(last7Date);
            }


            URL link = new URL("https://api.nasa.gov/planetary/apod?api_key=qJ0zfwEXsZf7WJ231fFgaTPIdeAbnO4mmV81g6ta&start_date=" + last7Date + "&end_date=" + yesterdayDate);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200)
            {
                System.out.println("Work");
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

                    if(!(PictureOfTheDay7List.names.contains(title)))
                    {
                        PictureOfTheDay7List.names.add(title);
                    }

                    if (!(PictureOfTheDay7List.dates.contains(date)))
                    {
                        //Formato inicial.
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String dateNoParse  = date;
                        Date dateAux = format.parse(dateNoParse);

                        //Aplica formato requerido.
                        format.applyPattern("dd-MM-yyyy");
                        String dateParsed = format.format(dateAux);
                        PictureOfTheDay7List.dates.add(dateParsed);
                    }

                    if (!(PictureOfTheDay7List.photosLink.contains(url)))
                    {
                        PictureOfTheDay7List.photosLink.add(url);
                    }

                    if (!(PictureOfTheDayFromList.descriptions.contains(description)))
                    {
                        PictureOfTheDayFromList.descriptions.add(description);
                    }

                }


            }

            else
            {
                System.out.println("DONT WORK");
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
