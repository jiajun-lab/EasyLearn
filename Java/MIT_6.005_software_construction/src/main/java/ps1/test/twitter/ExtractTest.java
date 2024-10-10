/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package ps1.test.twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import ps1.src.twitter.*;


import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */

    // 定义私有静态的 Instant 变量
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T13:00:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T13:00:00.000000001Z");
    private static final Instant d6 = Instant.parse("2016-02-17T13:00:00.000000002Z");
    private static final Instant d7 = Instant.parse("2016-02-28T23:59:59Z"); // 2月的最后一秒
    private static final Instant d8 = Instant.parse("2016-02-29T00:00:00Z"); // 闰年2月29日
    private static final Instant d9 = Instant.parse("2016-03-01T00:00:00Z"); // 3月的开始
    private static final Instant d10 = Instant.parse("1970-01-01T00:00:00Z"); // Unix纪元开始时间
    private static final Instant d11 = Instant.parse("9999-12-31T23:59:59Z"); // 最大可能日期时间

    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "author3", "Hello @User1, have you met @user2 and @User_3?", d3);
    private static final Tweet tweet4 = new Tweet(4, "author4", "Hello @User1, have you met @user2?", d4);
    private static final Tweet tweet5 = new Tweet(5, "author5", "Hello @User1.", d5);
    private static final Tweet tweet6 = new Tweet(6, "author6", "Check out @user! Also, email me at example@domain.com.", d6);
    private static final Tweet tweet7 = new Tweet(7, "author7", "Contact us at support@example.com for assistance.", d7);
    private static final Tweet tweet8 = new Tweet(8, "author8", "This is a test@case where @ is in the middle of a word.", d8);
    private static final Tweet tweet9 = new Tweet(9, "author9", "Hello @User1, have you met @user2 and @User_3?", d9);
    private static final Tweet tweet10 = new Tweet(10, "author10", "Hello @User1, have you met @user2 and @User_3?", d10);
    private static final Tweet tweet11 = new Tweet(11, "author11", "Hello @User1, have you met @user2 and @User_3?", d11);


    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }

    @Test
    public void testGetTimespanOneTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1));

        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d1, timespan.getEnd());
    }

    @Test
    public void testGetTimespanTweetsInOrder() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4));

        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d4, timespan.getEnd());
    }

    @Test
    public void testGetTimespanTweetsInRandom() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet4, tweet2, tweet3));

        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d4, timespan.getEnd());
    }

    @Test
    public void testNanosecondDifferenceTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet5, tweet6));

        assertEquals("expected start", d5, timespan.getStart());
        assertEquals("expected end", d6, timespan.getEnd());
    }

    @Test
    public void testSpecialDatesTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet7, tweet8, tweet9));

        assertEquals("expected start", d7, timespan.getStart());
        assertEquals("expected end", d9, timespan.getEnd());
    }


    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    @Test
    public void testGetMentionedUsersMentionedOnce() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet5));
        Set<String> expected = new HashSet<>(Arrays.asList("user1"));

        assertEquals("Expected set with one username user1",expected, mentionedUsers);
    }

    @Test
    public void testGetMentionedUsersMentionedMoreThanOnce() {
        Set<String> mentionedTwoUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
        Set<String> expected2 = new HashSet<>(Arrays.asList("user1","user2"));
        assertEquals("Expected set with two username",expected2, mentionedTwoUsers);

        Set<String> mentionedThreeUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
        Set<String> expected3 = new HashSet<>(Arrays.asList("user1","user2","user_3"));
        assertEquals("Expected set with three username",expected3, mentionedThreeUsers);
    }

    @Test
    public void testGetMentionedUser_Invalid(){
        Set<String> mentionedUsers1 = Extract.getMentionedUsers(Arrays.asList(tweet6, tweet7, tweet8));
        Set<String> expected = new HashSet<>(Arrays.asList("user"));
        System.out.println(mentionedUsers1);
        assertEquals("expected set with only one name", expected,mentionedUsers1);
    }



    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
