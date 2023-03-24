package sentimentAnalysis.analyzer;



import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.*;

import java.io.*;
import java.util.Properties;

public class SentimentAnalyzer {
    DoccatModel model;
    public void init()
    {
        try {
            ClassLoader classLoader = getClass().getClassLoader();

            File f = new File("tweets.txt");
            InputStreamFactory factory = new MarkableFileInputStreamFactory(f);
            ObjectStream lineStream = new PlainTextByLineStream(factory, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);
            // Specifies the minimum number of times a feature must be seen
            int cutoff = 2;
            int trainingIterations = 30;
            TrainingParameters parameters = new TrainingParameters();

            model = DocumentCategorizerME.train("en", sampleStream, cutoff,
                    trainingIterations);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

}
