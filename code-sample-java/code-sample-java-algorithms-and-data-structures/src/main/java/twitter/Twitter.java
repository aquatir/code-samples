package twitter;

import java.util.List;

/*
Implement the Twitter class
Methods:
void postTweet(int userId, int tweetId) (1)
void follow(int followerId, int followeeId) ( is following "twice" a problem? Solvable by "query -> insert" )

// follower — me, followeedId — who I follow
void unfollow(int followerId, int followeeId) (delete or mark as deleted)

List<Integer> getNewsFeed(int userId)

For getNewsFeed:
Retrieves the 10 most recent tweet IDs in the user's news feed.
Each item in the news feed must be posted by users who the user followed or by the user themself.
Tweets must be ordered from most recent to least recent.
 */
public class Twitter {

    private final Database database;

    public Twitter(Database database) {
        this.database = database;
    }

    void postTweet(int userId, int tweetId) {
        database.insertTweet(userId, tweetId);
    }

    void follow(int followerId, int followeeId) {
        database.insertFollow(followerId, followeeId);
    }

    void unfollow(int followerId, int followeeId) {
        database.unfollow(followerId, followeeId);
    }

    List<Integer> getNewsFeed(int userId) {
        return database.selectFeed(userId);
    }


    /*
    Data model:
    - users:
        - userId
    - tweets
        - tweetId
        - userId
        - updated_at: timestamp
    - followers
        - userId <- Me
        - follower_userIid <- People who I follow
    - [feed] <- we will only need when users left join follers left join tweets become too slow
     */

    public static void main(String[] args) {
//        Twitter twitter = new Twitter(new Database());
//        twitter.postTweet(1, 5);
//        System.out.println(twitter.getNewsFeed(1));
//        twitter.follow(1, 2);
//        twitter.postTweet(2, 6);
//        System.out.println(twitter.getNewsFeed(1));
//        twitter.unfollow(1, 2);
//        System.out.println(twitter.getNewsFeed(1));

        /*
        3[a] => aaa
2[cd]ef => cdcdef
3[a2[c]] => accaccacc
         */


        System.out.println(unrollowString("3[a]"));
        System.out.println(unrollowString("10[ab]"));
        System.out.println(unrollowString("3[a2[c]]"));
        System.out.println(unrollowString("3[4[a]]"));
    }

    private static String unrollowString(String str) {
        // 3[a2[c]] => accaccacc
        // 1. 3[acc]
        // 1.1 30[acc] <- valid
        // 2. accaccacc

        // 3[a2[c]]
        // 4[b]3[aaa] ->
        // [4[3[abc]] -> no number [ number

        var leftBracketIndex = -1;
        var rightBrackerIndex = -1;

        var shouldContinue = true;

        while (shouldContinue) {
            for (int i = 0; i < str.length(); i++) {
                shouldContinue = false;
                var ch = str.charAt(i);
                if (ch == '[') {
                    leftBracketIndex = i;
                }
                if (ch == ']') {
                    rightBrackerIndex = i;

                    str = replaceUnrolled(str, leftBracketIndex, rightBrackerIndex);
                    shouldContinue = true;
                    break;
                }
            }
        }

        return str;
    }

    private static String replaceUnrolled(String str, int leftBracketIndex, int rightBrackerIndex) {
        var startIndexOfNumberPart = leftBracketIndex - 1;

        // hope this doesn't run out of string; TODO: test
        if (startIndexOfNumberPart > 0 && Character.isDigit(str.charAt(startIndexOfNumberPart - 1))) {
            startIndexOfNumberPart--;
        }
        var numberOfRepeats = Integer.parseInt(str.substring(startIndexOfNumberPart, leftBracketIndex));
        var repeatedInput = str.substring(leftBracketIndex + 1, rightBrackerIndex);

        var afterRepeat = repeatedInput.repeat(numberOfRepeats);

        var beforeLeftBracker = str.substring(0, startIndexOfNumberPart);
        var rightOfRightBracker = str.substring(rightBrackerIndex + 1);

        return beforeLeftBracker + afterRepeat + rightOfRightBracker;
    }
}

