package tr.anadolu.edu.ceng.bim444.tez.client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oda114 on 23.2.2016.
 */
public class Words {

   List<Word> answers = new ArrayList<Word>();

    public Words() {
    }

    public List<Word> getWords() {
        return answers;
    }

    public void setWords(ArrayList<Word> words) {
        this.answers = words;
    }
}
