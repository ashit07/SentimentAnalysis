package org.ai.sentimentAnalysis.model;


public class Sentiment {
    private String sentimentInt;
    private String sentimentName;
    private String comment;
    public Sentiment(String sentimentInt, String sentimentName, String comment) {
        this.sentimentInt = sentimentInt;
        this.sentimentName = sentimentName;
        this.comment = comment;
    }

    public void setSentimentInt(String sentimentInt) {
        this.sentimentInt = sentimentInt;
    }

    public void setSentimentName(String sentimentName) {
        this.sentimentName = sentimentName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSentimentInt() {
        return sentimentInt;
    }

    public String getSentimentName() {
        return sentimentName;
    }

    public String getComment() {
        return comment;
    }
}
