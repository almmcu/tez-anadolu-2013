package tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.model;

import java.util.ArrayList;

/**
 * User: Ali
 * Date: 14.04.2013
 * Time: 23:22
 */
public class Answers {

    ArrayList<Answer> answers = new ArrayList<Answer>();

    public Answers() {
    }

    public Answers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
