package org.ai.sentimentAnalysis.main;

import org.ai.sentimentAnalysis.analyzer.OpenAIAnalyzer;
import org.ai.sentimentAnalysis.analyzer.SentimentAnalyzer;

public class SentimentExecutor {

    public static void analyzeSentimentsStandford() {
        SentimentAnalyzer.init();
        CommentsLoader.getInstance().getComments().forEach( comment -> {
            SentimentAnalyzer.estimatingSentiment(comment);
        });
    }

    public static void analyzeSentimentsOpenAI() {
        OpenAIAnalyzer.estimatingSentiment(CommentsLoader.getInstance().getComments());
    }
    public static void main(String[] args) {
        analyzeSentimentsStandford();
//        analyzeSentimentsOpenAI();
    }
}
