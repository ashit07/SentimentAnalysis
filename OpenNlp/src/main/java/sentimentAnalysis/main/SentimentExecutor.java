package sentimentAnalysis.main;


import sentimentAnalysis.analyzer.SentimentAnalyzer;

public class SentimentExecutor {

    public static void main(String[] args) {
        SentimentAnalyzer.init();
        CommentsLoader.getInstance().getComments().forEach( comment -> {
       //     SentimentAnalyzer.estimatingSentiment(comment);
        });

    }
}
