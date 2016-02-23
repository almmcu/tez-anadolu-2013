package tr.anadolu.edu.ceng.bim444.tez.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CommmentClientPost {

    public static void main(String[] args) {

        try {
            //{"comment":{"comment2":null,"comment1":null},"student":{"student1":null,"student2":null},"books":{"book2":null,"book1":null}}

            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://192.168.1.10:8080/jaxrs/signup/post/student");

            String input;


            input = "{\"comment\":{" +
                    "\"comments\":\"ders zor  filan degil  \"}," + //tmm
                    "\"books\":{\"book_code\":\"İŞL221\"}," +
                    "\"student\":{\"student_id\":\"21527663771\",\"student_name\":\"Jack\",\"student_surname\":\"Kuyumcu\"" +
                    "}}";

            input = "{\n" +
                    "    \"id\": \"21527663776\",\n" +
                    "    \"username\": \"alim\",\n" +
                    "    \"password\": \"123456\",\n" +
                    "    \"name\": \"Ali\",\n" +
                    "    \"surnmae\": \"Mumcu\",\n" +
                    "    \"email\": \"alim@anadolu.edu.tr\"\n" +
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