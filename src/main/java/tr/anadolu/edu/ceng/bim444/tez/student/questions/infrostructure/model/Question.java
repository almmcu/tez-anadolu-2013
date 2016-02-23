package tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.model;

/**
 * User: ali
 * Date: 4/8/13
 * Time: 10:57 AM
 */               ///degisecekkk
public class Question {


    private String Id;
    private QuestionTypes type;
    private String question;
    private String points;
    private String answer;
    private String image;
    private String qNumber;
    private String time ;

    public Question(String id, QuestionTypes type, String question, String points, String answer, String image, String qNumber) {
        Id = id;
        this.type = type;
        this.question = question;
        this.points = points;
        this.answer = answer;
        this.image = image;
        this.qNumber = qNumber;
    }

    public Question(String id, QuestionTypes type, String question, String points, String answer, String image, String qNumber, String time) {
        Id = id;
        this.type = type;
        this.question = question;
        this.points = points;
        this.answer = answer;
        this.image = image;
        this.qNumber = qNumber;
        this.time = time;
    }

    public String getId() {
        return Id;
    }

    public QuestionTypes getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getPoints() {
        return points;
    }

    public String getAnswer() {
        return answer;
    }

    public String getImage() {
        return image;
    }

    public String getqNumber() {
        return qNumber;
    }

    public void setqNumber(String qNumber) {
        this.qNumber = qNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
