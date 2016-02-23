package tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.model;

/**
 * User: ali
 * Date: 4/14/13
 * Time: 5:50 PM
 */
public class AnswerPosted {

    private String cevap;
    private String soruId;

    public AnswerPosted() {
    }

    public AnswerPosted(String cevap, String soruId) {
        this.cevap = cevap;
        this.soruId = soruId;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }

    public String getSoruId() {
        return soruId;
    }

    public void setSoruId(String soruId) {
        this.soruId = soruId;
    }
}
