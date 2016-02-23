package tr.anadolu.edu.ceng.bim444.tez.giris.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.dao.PersonDAO;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.Person;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * User: ali
 * Date: 3/30/13
 * Time: 3:44 PM
 */


@Component
@Path("/signup/post")
public class TezAnadoluPostPerson {


    @POST
    @Path("/{name}")
    @Consumes("application/json")
    public Response postCommentss(@PathParam("name") String name, Person person) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Module.xml");
        PersonDAO customerDAO = (PersonDAO) context.getBean("customerDAO");
        customerDAO.insert(person, name);
        String result = "Person saved : " + name;

        return Response.status(201).entity(result).build();

    }
}
