/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package ps1.test.twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.*;

import org.junit.Test;
import ps1.src.twitter.SocialNetwork;
import ps1.src.twitter.Tweet;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */

    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Tweet tweet1 = new Tweet(1, "Alice", "Hello @Bob! How are you?", d1);
    private static final Tweet tweet2 = new Tweet(2, "Bob", "Hi @Alice, I'm fine. What about @Charlie?", d2);
    private static final Tweet tweet3 = new Tweet(3, "Charlie", "@Bob @Alice @Li Good to hear from both of you!", d3);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }

    @Test
    public void testGuessFollowsGraph() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(List.of(tweet1, tweet2, tweet3));
        assertEquals(4, followsGraph.size());
        Set<String> A_followers = new HashSet<>();
        Set<String> B_followers = new HashSet<>();
        Set<String> C_followers = new HashSet<>();
        A_followers.add("bob");
        B_followers.add("alice");
        B_followers.add("charlie");
        C_followers.add("alice");
        C_followers.add("bob");
        C_followers.add("li");
        assertEquals(A_followers, followsGraph.get("Alice".toLowerCase()));
        assertEquals(B_followers, followsGraph.get("Bob".toLowerCase()));
        assertEquals(C_followers, followsGraph.get("Charlie".toLowerCase()));
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }

    @Test
    public void testInfluencers() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(List.of(tweet1, tweet2, tweet3));
        List<String> influencers= SocialNetwork.influencers(followsGraph);

        assertEquals(4, influencers.size());
        assertEquals("bob", influencers.get(1));
        assertEquals("alice", influencers.get(2));
        assertEquals("charlie", influencers.get(0));
        assertEquals("li", influencers.get(3));
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
