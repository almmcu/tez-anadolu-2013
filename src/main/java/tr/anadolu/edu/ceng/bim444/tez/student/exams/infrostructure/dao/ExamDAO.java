package tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.dao;


import tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.model.Exam;
import tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.model.Status;

import java.util.List;

/**
 * User: ali
 * Date: 4/8/13
 * Time: 11:00 AM
 */
public interface ExamDAO {

    public Exam findByExamId(String examId, String studentId);

    public Status findStatusByExamId(String examId);

    public List<Exam> searchExam(String studentId);


}
