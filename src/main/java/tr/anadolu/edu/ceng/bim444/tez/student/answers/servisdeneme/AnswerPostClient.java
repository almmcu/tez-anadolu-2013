package tr.anadolu.edu.ceng.bim444.tez.student.answers.servisdeneme;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * User: ali
 * Date: 4/14/13
 * Time: 6:11 PM
 */
public class AnswerPostClient {

/*    [
    {
        "cevap": "ali ",
            "soruId": "veli "
    },
    {
        "cevap": "beliii ",
            "soruId": "deli  "
    },
    {
        "cevap": "celii  ",
            "soruId": "geliii  "
    }
    ]*/

    public static void main(String[] args) {

        try {
            //{"comment":{"comment2":null,"comment1":null},"student":{"student1":null,"student2":null},"books":{"book2":null,"book1":null}}

            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://localhost:9090/jaxrs/students/answer/post");

            String input;


            input = "{\n" +
                    "    \"answers\": [\n" +
                    "        {\n" +
                    "            \"cevap\": \"asasd\",\n" +
                    "            \"soruId\": \"1\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"cevap\": \"as\",\n" +
                    "            \"soruId\": \"2\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"cevap\": \"asdfra\",\n" +
                    "            \"soruId\": \"4\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"cevap\": \"1\",\n" +
                    "            \"soruId\": \"2\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, input);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Output from Server .... \n");
            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {
            System.out.print("There is an error  = " + e);

            e.printStackTrace();

        }

    }
}
