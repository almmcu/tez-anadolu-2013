package tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.model;

/**
 * User: ali
 * Date: 4/8/13
 * Time: 11:07 AM
 */      ///degisecekk
public class ExamQuestion {
    private String questionId;
    private String examId;
    private String number;

    public ExamQuestion(String questionId, String examId, String number) {
        this.questionId = questionId;
        this.examId = examId;
        this.number = number;
    }

    public ExamQuestion(String questionId, String examId) {
        this.questionId = questionId;
        this.examId = examId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
