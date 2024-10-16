/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package ps1.src.twitter;

import java.time.Instant;
import java.util.*;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (Tweet tweet : tweets) {
            // 获得当前作者
            String author = tweet.getAuthor().toLowerCase();
            //如果作者不在图中将其加入graph
            if(!graph.containsKey(author)) {
                graph.put(author, new HashSet<>());
            }
            // 获得当前tweet提到的用户
            Set<String> mentionedUsers = Extract.getMentionedUsers(tweet);

            // 将被提到的用户加入当前用户
            for (String mentionedUser : mentionedUsers) {
                // 如果当前图中不存在该用户，加入
                if(!graph.containsKey(mentionedUser)) {
                    graph.put(mentionedUser, new HashSet<>());
                }
                graph.get(author).add(mentionedUser);
            }

        }

        return graph;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        List<Map.Entry<String, Set<String>>> entryList = new ArrayList<>(followsGraph.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<String, Set<String>>>() {
            @Override
            public int compare(Map.Entry<String, Set<String>> t1, Map.Entry<String, Set<String>> t2) {
                return -Integer.compare(t1.getValue().size(), t2.getValue().size());
            }
        });

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : entryList) {
            result.add(entry.getKey());
        }

        return result;
    }


    public static void main(String[] args) {
        Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
        Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
        Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
        Tweet tweet1 = new Tweet(1, "Alice", "Hello @Bob! How are you?", d1);
        Tweet tweet2 = new Tweet(2, "Bob", "Hi @Alice, I'm fine. What about @Charlie?", d2);
        Tweet tweet3 = new Tweet(3, "Charlie", "@Bob @Alice @Li Good to hear from both of you!", d3);

        Map<String, Set<String>> socialGraph = guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3));
        System.out.println(socialGraph);
        // 输出: {Alice=[Bob], Bob=[Alice, Charlie], Charlie=[Bob, Alice]}
        List<String> influencers = influencers(socialGraph);
        System.out.println(influencers);
    }

}
