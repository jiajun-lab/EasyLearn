/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package ps1.src.twitter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        if (tweets == null || tweets.isEmpty()) {
            return null;
        }

        Instant start = tweets.get(0).getTimestamp();
        Instant end = tweets.get(0).getTimestamp();

        for (Tweet tweet : tweets) {
            if (tweet.getTimestamp().isBefore(start)){
                start = tweet.getTimestamp();
            }
            if (tweet.getTimestamp().isAfter(end)){
                end = tweet.getTimestamp();
            }
        }

        return new Timespan(start,end);

    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
//        Set<String> mentionedUsers = new HashSet<>();
//        String userName = "";
//        for (Tweet tweet : tweets) {
//            String[] strings = tweet.getText().split("@");
//            if(!strings[0].endsWith(" ") && strings.length > 1) {
//                 userName = strings[1].split(" ")[0].toLowerCase();
//            }
//            if(!userName.isEmpty()){
//                mentionedUsers.add(userName);
//            }
//        }
//        return mentionedUsers;
        Set<String> mentionedUsers = new HashSet<>();
        Pattern pattern = Pattern.compile("(?<![A-Za-z0-9_])@([A-Za-z0-9_]{1,15})(?![A-Za-z0-9_])", Pattern.CASE_INSENSITIVE);

        for (Tweet tweet : tweets) {
            Matcher matcher = pattern.matcher(tweet.getText());
            while (matcher.find()) {
                String username = matcher.group(1).toLowerCase();
                mentionedUsers.add(username);
            }
        }
        return mentionedUsers;
    }

}
