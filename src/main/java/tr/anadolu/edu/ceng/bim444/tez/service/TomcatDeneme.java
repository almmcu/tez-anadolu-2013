package tr.anadolu.edu.ceng.bim444.tez.service;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: ali
 * Date: 3/27/13
 * Time: 11:15 PM
 */

@Component
@Path("/tom")
public class TomcatDeneme {

    @GET
    @Path("/locations")
    @Produces("application/json")
    public List getLocationsss(@QueryParam("kampus") String kampus) throws SQLException, UnsupportedEncodingException {

        List<HashMap<String, String>> locationList = new ArrayList<HashMap<String, String>>();
        HashMap a = new HashMap();
        a.put("jh", ";kj");
        locationList.add(a);


        return locationList;

    }
}
