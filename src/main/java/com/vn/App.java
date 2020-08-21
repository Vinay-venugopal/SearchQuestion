package com.vn;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Scanner;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.zip.GZIPInputStream;

public class App
{
    private static final String GET_URL = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&site=stackoverflow";

    private static int getResults(String strQuery)
    {
        int                 dwResult                =   0;
        int                 dwRespCode              =   0;
        URL                 mUrl                    =   null;
        HttpURLConnection   mHttpURLConnection      =   null;
        String              strUrl                  =   "";

        try
        {
			//Format URL.
			strUrl = GET_URL + "&intitle=" + URLEncoder.encode(strQuery, StandardCharsets.UTF_8.toString());

            mUrl = new URL(strUrl);

            mHttpURLConnection = (HttpURLConnection)mUrl.openConnection();
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.setConnectTimeout(5000);
            mHttpURLConnection.setReadTimeout(5000);

			mHttpURLConnection.connect();

            dwRespCode = mHttpURLConnection.getResponseCode();
            if(dwRespCode == HttpURLConnection.HTTP_OK)
            {
                //Success
				InputStream mInputStream = null;
				mInputStream = new GZIPInputStream(mHttpURLConnection.getInputStream());
				
				StringBuffer mStringBuffer = new StringBuffer();
				if (mInputStream != null) {
					InputStreamReader inputStreamReader = new InputStreamReader(mInputStream);
					BufferedReader reader = new BufferedReader(inputStreamReader);
					String line = reader.readLine();
					while (line != null) {
						mStringBuffer.append(line);
						line = reader.readLine();
					}
					reader.close();
				}
				String jsonResponse = mStringBuffer.toString();

				JSONObject mJSONObject = new JSONObject(jsonResponse);
				JSONArray mJSONArray = mJSONObject.getJSONArray("items");

				if(mJSONArray != null)
				{
					if(mJSONArray.length() > 5)
						System.out.println("Here are the top-5 results for your search '"+ strQuery +"': ");
					else if(mJSONArray.length() > 0)
						System.out.println("Here are the results for your search '"+ strQuery +"': ");
					else
						System.out.println("No records found.");

					int dwMaxRecords = 5;
					for(int i = 0; i < mJSONArray.length(); i++)
					{
						if(i < dwMaxRecords)
						{
							JSONObject mItemJO = mJSONArray.getJSONObject(i);
							JSONObject mOwnerJO = mItemJO.getJSONObject("owner");
							System.out.println("Title: " + mItemJO.getString("title"));
							System.out.println("URL: " + mItemJO.getString("link"));
							System.out.println("Author Display Name: " + mOwnerJO.getString("display_name") + "\n");
						}
						else
						{
							break;
						}
					}
				}
				else
				{
					System.out.println("No records found.");
				}
            }
            else
            {
                //Failed
                dwResult = -1;
            }

        }catch(Exception e)
        {
            e.printStackTrace();
            dwResult = -1;
        }
        finally
        {
            if(mHttpURLConnection != null)
                mHttpURLConnection.disconnect();
        }

        return dwResult;

    }//getResults

    public static void main(String[] args) {

        int dwResult = 0;
        String strQuery = "";
        Scanner mScanner = new Scanner(System.in);

        while(true)
        {
            System.out.print("\nPlease enter your search query: ");
            strQuery = mScanner.nextLine();
            if(strQuery.length() > 0)
            {
                dwResult = getResults(strQuery);
                if(dwResult != 0)
                    break;
            }

        }//while

        mScanner.close();

    }//main

}//App
