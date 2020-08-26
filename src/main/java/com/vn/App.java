package com.vn;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.DeserializationFeature;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App
{
    private static final String GET_URL = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&site=stackoverflow";

    /**
     * Function to format url with the query user has entered as input.
     * @param strQuery : User entered input query
     * @return : Returns the formatted string url.
     */
    private static String FormatUrl(String strQuery)
    {
        String strUrl = "";

        try
        {
            strUrl = GET_URL + "&intitle=" + URLEncoder.encode(strQuery, StandardCharsets.UTF_8.toString());
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return strUrl;
    }//FormatUrl

    /**
     * Function to send request to api.stackexchange.com and reads response of the request.
     * Takes formatted url string as input and returns the response string.
     * @param strUrl : Formatted string url.
     * @return : Returns the response as string.
     */
    private static String SendRequest(String strUrl)
    {
        String strResponse = "";

        try
        {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(strUrl).build();
            Response response = client.newCall(request).execute();
            strResponse = response.body().string();

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return strResponse;

    }//SendRequest

    /**
     * Function to parse the json formatted string response to java object.
     * @param strResponse : json formatted string response
     * @return : Returns Record object
     */
    public static Record ParseResponse(String strResponse)
    {
        Record mRecord = new Record();

        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mRecord = objectMapper.readerFor(Record.class).readValue(strResponse);
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return mRecord;
    }//ParseResponse

    /**
     * Function to display records. Top 5 records will be displayed.
     * @param mRecord : Object that contains the list of records
     * @param strQuery : The user entered query as input. This is to display as heading for results.
     */
    public static void DisplayRecords(Record mRecord, String strQuery)
    {
        int dwNoOfRecordsReq = Math.min(mRecord.getItems().size(), 5);
        if(dwNoOfRecordsReq > 0) {
            Stream<Item> mStream = mRecord.getItems().stream().limit(dwNoOfRecordsReq);
            System.out.println("Here are the top-" + dwNoOfRecordsReq + " results for your search '" + strQuery + "': ");
            mStream.forEach(item -> DisplayItem(item));
        }
        else
            System.out.println("No results found.");

    }//DisplayRecords

    /**
     * Function to display each item as record.
     * @param mitem : Item object
     */
    public static void DisplayItem(Item mitem)
    {
        System.out.println("Title: " + mitem.getTitle());
        System.out.println("URL: " + mitem.getLink());
        System.out.println("Author Display Name: " + mitem.getOwner().getDisplay_name() + "\n");
    }//DisplayItem

    /**
     * Function to get and display results for user entered query.
     * @param strQuery : User entered query
     * @return : Returns true on success and false on error.
     */
    private static boolean getRecords(String strQuery)
    {
        try
        {
            // Format Url
            final String strUrl = FormatUrl(strQuery);

            // Send request
            String strResponse = SendRequest(strUrl);

            // Parse response
            Record mRecord = ParseResponse(strResponse);

            // Display records
            DisplayRecords(mRecord, strQuery);

        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;

    }//getRecords

    public static void main(String[] args) {

        Scanner mScanner = new Scanner(System.in);

        while(true)
        {
            System.out.print("\nPlease enter your search query: ");
            String strQuery = mScanner.nextLine();
            if(strQuery.trim().length() > 0)
            {
                if(!getRecords(strQuery))
                    break;
            }

        }//while

        mScanner.close();

    }//main
}

class Owner{
    int reputation;
    long user_id;
    String user_type;
    String profile_image;
    String display_name;
    String link;

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}//Owner

class Item {
    private List<String> tags;
    private Owner owner;
    private boolean is_answered;
    private int view_count;
    private long accepted_answer_id;
    private int answer_count;
    private int score;
    private long last_activity_date;
    private long creation_date;
    private long question_id;
    private String content_license;
    private String title;
    private String link;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isIs_answered() {
        return is_answered;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public long getAccepted_answer_id() {
        return accepted_answer_id;
    }

    public void setAccepted_answer_id(long accepted_answer_id) {
        this.accepted_answer_id = accepted_answer_id;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getLast_activity_date() {
        return last_activity_date;
    }

    public void setLast_activity_date(long last_activity_date) {
        this.last_activity_date = last_activity_date;
    }

    public long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(long creation_date) {
        this.creation_date = creation_date;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getContent_license() {
        return content_license;
    }

    public void setContent_license(String content_license) {
        this.content_license = content_license;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}//Item

class Record {
    private List<Item>  items;
    private int         quota_max;
    private int         quota_remaining;
    private boolean     has_more;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getQuota_max() {
        return quota_max;
    }

    public void setQuota_max(int quota_max) {
        this.quota_max = quota_max;
    }

    public int getQuota_remaining() {
        return quota_remaining;
    }

    public void setQuota_remaining(int quota_remaining) {
        this.quota_remaining = quota_remaining;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

}//Record