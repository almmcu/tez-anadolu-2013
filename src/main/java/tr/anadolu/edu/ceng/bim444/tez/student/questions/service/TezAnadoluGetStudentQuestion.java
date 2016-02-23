package tr.anadolu.edu.ceng.bim444.tez.student.questions.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.dao.QuestionDAO;
import tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.model.Question;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * User: ali
 * Date: 3/30/13
 * Time: 3:44 PM
 */

@Component
@Path("/student/question")
public class TezAnadoluGetStudentQuestion {


    @GET
    @Path("/get")
    @Produces("application/json")
    public List<Question> searchPerson(@QueryParam("id") String id) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Module.xml");
        QuestionDAO questionDAO = (QuestionDAO) context.getBean("questionDAO");
        List<Question> result;
        result = questionDAO.searchQuestion(id);

        return result;
    }
}
