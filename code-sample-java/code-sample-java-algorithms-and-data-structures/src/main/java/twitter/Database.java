package twitter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Database {

    private final Map<Integer, UserEntity> users = new HashMap<>();

    // from userId to a set of tweets
    private final Map<Integer, List<Integer>> tweets = new HashMap<>();
    private final Map<Integer, TweetEntity> tweetEntities = new HashMap<>();

    // from userId to a set of userIds
    private final Map<Integer, Set<Integer>> followees = new HashMap<>();

    public void insertTweet(int userId, int tweetId) {
        var curTweets = tweets.getOrDefault(userId, new ArrayList<>());
        curTweets.add(tweetId);
        tweets.put(userId, curTweets);
        tweetEntities.put(tweetId, new TweetEntity(tweetId, userId, OffsetDateTime.now()));
    }

    /**
     *
     */
    public void insertFollow(int followerId, int followeeId) {
        var curFollowees = followees.getOrDefault(followerId, new HashSet<>());
        curFollowees.add(followeeId);
        followees.put(followerId, curFollowees);
    }

    public void unfollow(int followerId, int followeeId) {
        var curFollowees = followees.getOrDefault(followerId, new HashSet<>());
        curFollowees.remove(followeeId);
        followees.put(followerId, curFollowees);
    }

    public List<Integer> selectFeed(int userId) {
        var curFollowees = followees.getOrDefault(userId, new HashSet<>());
        curFollowees.add(userId);

        var listOfFoloweesTweets = curFollowees.stream()
            .map(theUserIdentifier -> selectAllTweetIds(theUserIdentifier))
            .flatMap(Collection::stream)
            .map(tweetId -> tweetEntities.get(tweetId))
            .sorted(Comparator.comparing(it -> it.updatedAt)) // TODO: reversed?
            .toList();

        var lastIndex = Math.min(10, listOfFoloweesTweets.size());
        return listOfFoloweesTweets.subList(0, lastIndex).stream().map(it -> it.tweetId).toList();
    }

    private List<Integer> selectAllTweetIds(Integer userId) {
        return tweets.get(userId);
    }

    record UserEntity(Integer userId) {
    }

    record TweetEntity(
        Integer tweetId,
        Integer userId,
        OffsetDateTime updatedAt
    ) {
    }

    ;
}
