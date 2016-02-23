package tr.anadolu.edu.ceng.bim444.tez.student.answers.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.dao.AnswerDAO;
import tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.model.AnswerPosted;
import tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.model.Answers;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * User: ali
 * Date: 3/30/13
 * Time: 3:44 PM
 */

@Component
@Path("/students/answer")
public class TezAnadoluPostStudentAnswers {


    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response postAnswers(Answers answers) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Module.xml");
        AnswerDAO answerDAO = (AnswerDAO) context.getBean("answerDAO");
        int size = answers.getAnswers().size();
        size = size - 1;
        /*for (int i = 0; i < answers.getAnswers().size(); i++) {
            System.out.print("answer " + i + " = " + answers.getAnswers().get(i).getCevap());
            System.out.println("    id  = " + answers.getAnswers().get(i).getSoruId());
        }
        answerDAO.insert(answers, answers.getAnswers().get(size).getCevap(), answers.getAnswers().get(size).getSoruId());*/
        String result = "Answers saved : \n\n\n\n\n\n";
        try {
            FileWriter writer = new FileWriter("MyFile.txt", true);
            writer.write(answers.getAnswers().get(1).getErisim());
            writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.status(201).entity(result).build();

    }




}
