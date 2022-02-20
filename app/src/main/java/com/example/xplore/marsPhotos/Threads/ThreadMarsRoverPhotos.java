package com.example.xplore.marsPhotos.Threads;

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
import java.util.Random;

public class ThreadMarsRoverPhotos extends Thread
{


    public static int white_Box_test = 500;

    @Override
    public void run()
    {
        super.run();



        Random rdm = new Random();
        //Max sol 2208
        if (MarsPhotos.radioButtonSpirit.isChecked())
        {
            MarsPhotos.spiritImages.clear();
            try {



                    while(MarsPhotos.spiritImages.size() < white_Box_test)
                    {
                        String sol = Integer.toString(rdm.nextInt(2208)+ 1);
                        URL link = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/spirit/photos?sol=" + sol + "&api_key=qJ0zfwEXsZf7WJ231fFgaTPIdeAbnO4mmV81g6ta");
                        HttpURLConnection connection = (HttpURLConnection) link.openConnection();
                        connection.setRequestMethod("GET");

                        if(connection.getResponseCode() == 200)
                        {
                            InputStream dataPage = connection.getInputStream();
                            InputStreamReader isr = new InputStreamReader(dataPage);
                            BufferedReader bf = new BufferedReader(isr);
                            String line="";
                            String result= "";

                            while((line = bf.readLine())  != null)
                            {
                                result += line;

                            }

                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray jsonArray = jsonObject.getJSONArray("photos");


                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                if (MarsPhotos.spiritImages.size() >= white_Box_test)
                                {
                                    break;
                                }
                                String src_image = jsonArray.getJSONObject(i).getString("img_src").replace("http" , "https");
                                MarsPhotos.spiritImages.add(src_image);

                            }



                        }

                    else if (connection.getResponseCode() != 200)
                        {
                            System.out.println("API PROBLEM");
                        }


                }




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }//1IF END spirit

        else if(MarsPhotos.radioButtonOpportunity.isChecked())
        {

            MarsPhotos.opportunityImages.clear();
            //Max Sol = 5111
            try {

                while(MarsPhotos.opportunityImages.size() <= white_Box_test)
                {
                    String sol = Integer.toString(rdm.nextInt(5111)+ 1);
                    URL link = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/opportunity/photos?sol=" + sol + "&api_key=qJ0zfwEXsZf7WJ231fFgaTPIdeAbnO4mmV81g6ta");
                    HttpURLConnection connection = (HttpURLConnection) link.openConnection();
                    connection.setRequestMethod("GET");

                    if(connection.getResponseCode() == 200)
                    {
                        InputStream dataPage = connection.getInputStream();
                        InputStreamReader isr = new InputStreamReader(dataPage);
                        BufferedReader bf = new BufferedReader(isr);
                        String line="";
                        String result= "";

                        while((line = bf.readLine())  != null)
                        {
                            result += line;

                        }


                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("photos");


                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            if (MarsPhotos.opportunityImages.size() >= white_Box_test)
                            {
                                break;
                            }
                            String src_image = jsonArray.getJSONObject(i).getString("img_src").replace("http" , "https");

                            MarsPhotos.opportunityImages.add(src_image);

                        }



                    }

                    else if (connection.getResponseCode() != 200)
                    {
                        System.out.println("API PROBLEM");
                    }


                }




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }//END 2if Opportnity

        else if(MarsPhotos.radioButtonCuriosity.isChecked())
        {
            //Max Sol = 3383
            try {

                while(MarsPhotos.curiosityImages.size() <= white_Box_test)
                {
                    String sol = Integer.toString(rdm.nextInt(5111)+ 1);
                    URL link = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=" + sol + "&api_key=qJ0zfwEXsZf7WJ231fFgaTPIdeAbnO4mmV81g6ta");
                    HttpURLConnection connection = (HttpURLConnection) link.openConnection();
                    connection.setRequestMethod("GET");

                    if(connection.getResponseCode() == 200)
                    {
                        InputStream dataPage = connection.getInputStream();
                        InputStreamReader isr = new InputStreamReader(dataPage);
                        BufferedReader bf = new BufferedReader(isr);
                        String line="";
                        String result= "";

                        while((line = bf.readLine())  != null)
                        {
                            result += line;

                        }


                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("photos");


                        for (int i = 0; i < jsonArray.length(); i++)
                        {

                            if (MarsPhotos.curiosityImages.size() >= white_Box_test)
                            {
                                break;
                            }
                            String src_image = jsonArray.getJSONObject(i).getString("img_src").replace("http" , "https");

                            MarsPhotos.curiosityImages.add(src_image);

                        }



                    }

                    else if (connection.getResponseCode() != 200)
                    {
                        System.out.println("API PROBLEM");
                    }


                }




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }//END 3if Curiosity

    }
}
