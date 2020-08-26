package com.vn;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void TestRespJsonToJavaObjs() {


        String strResponse = "{\"items\":[{\"tags\":[\"php\",\"symfony\",\"routing\",\"routes\"],\"owner\":{\"reputation\":487,\"user_id\":1014637,\"user_type\":\"registered\",\"accept_rate\":38,\"profile_image\":\"https://www.gravatar.com/avatar/e3fe8b038c62c0eafdac1fbefefb6fcd?s=128&d=identicon&r=PG\",\"display_name\":\"Daniel\",\"link\":\"https://stackoverflow.com/users/1014637/daniel\"},\"is_answered\":true,\"view_count\":88,\"accepted_answer_id\":43807493,\"answer_count\":4,\"score\":1,\"last_activity_date\":1493995234,\"creation_date\":1493993063,\"last_edit_date\":1493993400,\"question_id\":43807055,\"content_license\":\"CC BY-SA 3.0\",\"link\":\"https://stackoverflow.com/questions/43807055/symfony-routing-optional-pagination-test-1-vs-slug-test-abc\",\"title\":\"Symfony routing optional pagination (/test/[1]) vs. slug (/test/abc)\"},{\"tags\":[\"java\",\"regex\"],\"owner\":{\"reputation\":3,\"user_id\":6468412,\"user_type\":\"registered\",\"profile_image\":\"https://lh5.googleusercontent.com/-Csuoji4jVu8/AAAAAAAAAAI/AAAAAAAAACg/4HmZyAWTFFQ/photo.jpg?sz=128\",\"display_name\":\"sakeena banu syed\",\"link\":\"https://stackoverflow.com/users/6468412/sakeena-banu-syed\"},\"is_answered\":true,\"view_count\":39,\"accepted_answer_id\":37830145,\"answer_count\":1,\"score\":-4,\"last_activity_date\":1465981246,\"creation_date\":1465979175,\"last_edit_date\":1465981246,\"question_id\":37829961,\"content_license\":\"CC BY-SA 3.0\",\"link\":\"https://stackoverflow.com/questions/37829961/what-could-be-the-regular-expression-for-the-text-192-168-71-1-get-http-1-0-te\",\"title\":\"What could be the regular expression for the text &quot;192.168.71.1 GET HTTP/1.0 /test/abc&quot;?\"}],\"has_more\":false,\"quota_max\":300,\"quota_remaining\":284}";

        Record mRecord = App.ParseResponse(strResponse);

        App.DisplayRecords(mRecord, "test abc");

    }
}
