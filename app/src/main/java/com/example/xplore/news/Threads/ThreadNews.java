package com.example.xplore.news.Threads;

import com.example.xplore.news.NewObject;
import com.example.xplore.news.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ThreadNews extends Thread
{

    URL link;
    public ThreadNews(URL link)
    {
        this.link = link;
    }

    @Override
    public void run()
    {
        super.run();

        try {

            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200)
            {
                System.out.println("Good");
                InputStream data = connection.getInputStream();
                parseXml(data);

            }

            else
            {
                System.out.println("BAD");
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

private void parseXml(InputStream data)
{
    try {
        XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = parserFactory.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
        parser.setInput(data, null);
        processParsing(parser);

    } catch (XmlPullParserException e) {
        e.printStackTrace();
    }
}

private void processParsing(XmlPullParser parser)
{
    ArrayList<NewObject> news = new ArrayList<>();

    try {
        int eventType = parser.getEventType();
        NewObject newObject = null;

        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            String eltName = null;
            switch (eventType)
            {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();

                    if ("item".equals(eltName))
                    {
                        newObject = new NewObject();
                       News.news.add(newObject);

                    }

                    else if (newObject != null)
                    {
                        if("title".equals(eltName))
                        {
                            newObject.setTitle(parser.nextText().replace("<i>" , "").replace("</i>" , ""));
                        }

                        else if("link".equals(eltName))
                        {
                            newObject.setLink(parser.nextText());
                        }

                        else if ("content".equals(eltName))
                        {
                            newObject.setImage(parser.nextText());
                        }

                        else if("enclosure".equals(eltName))
                        {
                            newObject.setImage(parser.getAttributeValue(0));
                        }

                        else if ("description".equals(eltName))
                        {
                            newObject.setDescription(parser.nextText());
                        }

                        else if("pubDate".equals(eltName))
                        {
                            newObject.setDate(parser.nextText());
                        }
                    }
                break;


                    }

            eventType = parser.next();

            }


        } catch (XmlPullParserException xmlPullParserException) {
        xmlPullParserException.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}



    }




