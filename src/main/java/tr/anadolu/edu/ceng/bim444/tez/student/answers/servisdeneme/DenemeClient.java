package tr.anadolu.edu.ceng.bim444.tez.student.answers.servisdeneme;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * User: ali
 * Date: 4/16/13
 * Time: 9:31 AM
 */
public class DenemeClient {

    public static void main(String[] args) {

        try {
            //{"comment":{"comment2":null,"comment1":null},"student":{"student1":null,"student2":null},"books":{"book2":null,"book1":null}}

            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://localhost:9090/jaxrs/students/answer/post");

            String input;

            // File txt = new File("C:\\Users\\Onur\\Desktop\\sınav.txt");
            File txt = new File("C:\\Users\\ali\\Desktop\\json.txt");
            input = readText(txt);


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

    public static String readText(File f) {
        String text = "";
        int read, N = 1024 * 1024;
        char[] buffer = new char[N];

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while (true) {
                read = br.read(buffer, 0, N);
                text += new String(buffer, 0, read);

                if (read < N) {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return text;
    }

}
