package tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.dao;


import tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.model.Question;
import tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.model.QuestionTypes;

import java.util.List;

/**
 * User: ali
 * Date: 4/8/13
 * Time: 11:00 AM
 */
public interface QuestionDAO {

    public String findTimeDifference (String examId) ;

    public List<Question> searchQuestion(String examId);

    public QuestionTypes findByQuestionTypeId(String qTypeId);


}
