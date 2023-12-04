package com.example.startrek_triviagame;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * This is an Item file for the StarTrekGameListAdapter.
 *
 * @author Nicole Al-Sabah
 * Date: December 02, 2023
 */
public class StarTrekGameItem {

    public StarTrekGameItem(String stringExtra) {
        this.itemType = ItemType.USER;
        this.user = null;
        this.triviaQuestions = null;
        this.score = null;
        this.itemList = null;
        this.itemId = generateUniqueId();
    }

    public enum ItemType {
        USER,
        TRIVIA_QUESTIONS,
        SCORE
    }

    private final ItemType itemType;
    private final User user;
    private final TriviaQuestions triviaQuestions;
    private final ScoreHistory score;
    private final List<StarTrekGameItem> itemList;
    private final long itemId;

    // Constructors, getters, and setters...

    public StarTrekGameItem(ItemType itemType, User user, TriviaQuestions triviaQuestions, ScoreHistory score, List<StarTrekGameItem> itemList) {
        this.itemType = itemType;
        this.user = user;
        this.triviaQuestions = triviaQuestions;
        this.score = score;
        this.itemList = itemList;
        this.itemId = generateUniqueId();
    }

    public static StarTrekGameItem createUserItem(User user, List<StarTrekGameItem> itemList) {
        return new StarTrekGameItem(ItemType.USER, user, null, null, itemList);
    }

    public static StarTrekGameItem createTriviaQuestionItem(TriviaQuestions triviaQuestions, List<StarTrekGameItem> itemList) {
        return new StarTrekGameItem(ItemType.TRIVIA_QUESTIONS, null, triviaQuestions, null, itemList);
    }

    public static StarTrekGameItem createScoreItem(ScoreHistory score, List<StarTrekGameItem> itemList) {
        return new StarTrekGameItem(ItemType.SCORE, null, null, score, itemList);
    }

    // Getter for the item type
    public ItemType getItemType() {
        return itemType;
    }

    // Getters for specific entity types...

    public User getUser() {
        return user;
    }

    public TriviaQuestions getTriviaQuestion() {
        return triviaQuestions;
    }

    public ScoreHistory getScore() {
        return score;
    }

    public List<StarTrekGameItem> getItemList() {
        return itemList;
    }

    public long getItemId() {
        return itemId;
    }

    private static long generateUniqueId() {
        // Generate unique ID here
        return new Random().nextLong();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarTrekGameItem that = (StarTrekGameItem) o;
        return getItemId() == that.getItemId() && getItemType() == that.getItemType() && Objects.equals(getUser(), that.getUser()) && Objects.equals(getTriviaQuestion(), that.getTriviaQuestion()) && Objects.equals(getScore(), that.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemType(), getUser(), getTriviaQuestion(), getScore(), getItemId());
    }
}
