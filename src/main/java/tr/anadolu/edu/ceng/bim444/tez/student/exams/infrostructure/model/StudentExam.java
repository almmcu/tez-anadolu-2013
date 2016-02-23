package tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.model;

/**
 * User: ali
 * Date: 4/8/13
 * Time: 11:07 AM
 */
public class StudentExam {
    private String studentId;
    private String examId;

    public StudentExam(String studentId, String examId) {
        this.studentId = studentId;
        this.examId = examId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
}
