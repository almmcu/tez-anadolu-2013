package tr.anadolu.edu.ceng.bim444.tez;


import org.apache.commons.codec.binary.Base64;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Date serverDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String formattedDate = df.format(serverDate);
        System.out.println(); formattedDate = "04/25/2013 12:00:00";
        System.out.println("alinan zaman =  " + formattedDate);

        formattedDate = df.format(serverDate);
        long serverTimestamp =   timeConversion(formattedDate) ;
        System.out.println("time conversion sonrasi ");
        System.out.println();
        System.out.println(serverTimestamp);

        System.out.println();
        System.out.println("server time ");
        serverDate = new java.util.Date(serverTimestamp * 1000);
        System.out.println(df.format(serverDate));


        System.out.println();
        System.out.println("bakalim ne cikacak ");
        serverDate = new java.util.Date(1366880930 * 1000);
        System.out.println(df.format(serverDate));





        try {
            String clearText = "Hello world";
            clearText = "rlane#k0n4QasH";
            String encodedText;

            // Base64
            encodedText = new String(Base64.encodeBase64(clearText.getBytes()));
            System.out.println("Encoded: " + encodedText);
            System.out.println("Decoded:"
                    + new String(Base64.decodeBase64(encodedText.getBytes())));
            //
            // output :
            //   Encoded: SGVsbG8gd29ybGQ=
            //   Decoded:Hello world
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static long timeConversion(String time)
    {
        long unixtime  = 0 ;
        DateFormat dfm = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        dfm.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));//Specify your timezone
        try
        {

            unixtime = dfm.parse(time).getTime();

            unixtime=unixtime/1000;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        unixtime += 10800 - 60*30 ;
        return unixtime;
    }

}
