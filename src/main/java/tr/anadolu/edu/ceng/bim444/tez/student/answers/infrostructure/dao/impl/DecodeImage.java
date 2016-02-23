package tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.dao.impl;

import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: ali
 * Date: 4/16/13
 * Time: 10:34 AM
 */
public class DecodeImage {

    private String encodedImage;
    private String name;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DecodeImage.class);

    public DecodeImage(String encodedImage, String name) {
        this.encodedImage = encodedImage;
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public String getEncodedImage() {
        return encodedImage;
    }

    public String decodeImage() {
        String url = null;
        String[] splits = getEncodedImage().split(":-:image:-:");

        logger.debug("decode image methodu icinde ");
        if (splits.length == 0) {

            return "";
        }
       logger.debug(":-:image:-: gore pars edildi ve ilk kisim -->  " + splits[0] + " ikinci kisim -->" + splits[1]);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodedBytes = new byte[0];
        try {
            decodedBytes = decoder.decodeBuffer(splits[1]);
        } catch (Exception e) {
               logger.error("Hata decodeImage methodu icinde \n" +
                       "  base 64 deecode yapilirken bir hta olustu \n ---> "+ e );
            e.printStackTrace();
        }
        InputStream in = new ByteArrayInputStream(decodedBytes);
        BufferedImage bImageFromConvert = null;
        try {

             logger.debug("Buffered image elde ediliyor ");
            bImageFromConvert = ImageIO.read(in);
            url = "C:\\xampp\\htdocs\\images\\uImages\\" + getName() + ".jpg";
            ImageIO.write(bImageFromConvert, "jpg", new File(url));

        } catch (IOException e) {
            logger.error("Bufeered image cevrimi yapilirken bir hata var --  >" + e);
            e.printStackTrace();
        }
        url =  "images/uImages/" + getName() + ".jpg";
        return url;
    }

}
