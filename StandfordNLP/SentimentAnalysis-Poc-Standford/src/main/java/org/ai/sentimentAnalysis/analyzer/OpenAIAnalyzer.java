package org.ai.sentimentAnalysis.analyzer;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;

import java.util.List;

public class OpenAIAnalyzer {

    public static void estimatingSentiment(List<String> text) {
        String token = System.getenv("OPENAI_TOKEN");
        OpenAiService service = new OpenAiService(token);

        System.out.println("\nCreating completion...");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("Classify the sentiment in these tweets:\n\n1. \"I can't stand homework\"\n2. \"This sucks. I'm bored 😠\"\n3. \"I can't wait for Halloween!!!\"\n4. \"My cat is adorable ❤️❤️\"\n5. \"I hate chocolate\"\n\nTweet sentiment ratings:")
                .echo(true)
                .temperature(0.75)
                .maxTokens(60)
                .topP(1.0)
                .frequencyPenalty(0.0)
                .presencePenalty(0.0)
                .user("testing")
                .n(3)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);

//        System.out.println("\nCreating Image...");
//        CreateImageRequest request = CreateImageRequest.builder()
//                .prompt("A cow breakdancing with a turtle")
//                .build();
//
//        System.out.println("\nImage is located at:");
//        System.out.println(service.createImage(request).getData().get(0).getUrl());
    }
}
