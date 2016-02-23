package tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.model;

/**
 * User: ali
 * Date: 4/11/13
 */
public class QuestionTypes {

    private String id;
    private String type;

    public QuestionTypes(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
