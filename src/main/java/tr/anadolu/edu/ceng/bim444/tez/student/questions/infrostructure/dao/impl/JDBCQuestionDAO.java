package tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.dao.impl;

import org.slf4j.LoggerFactory;
import tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.dao.QuestionDAO;
import tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.model.Question;
import tr.anadolu.edu.ceng.bim444.tez.student.questions.infrostructure.model.QuestionTypes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * User: ali
 * Date: 4/8/13
 * Time: 10:56 AM
 */

public class JDBCQuestionDAO implements QuestionDAO {
    private DataSource dataSource;
    private List questionList;
    private String time ;
    private String question ;
    public static final String IP  = "http://95.9.51.254/";

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JDBCQuestionDAO.class);
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
    public List<Question> searchQuestion(String examId) {
        String sql;
        questionList = new ArrayList<Question>();
        sql = "SELECT q.* ,  qe.question_number\n" +
                "FROM onlinesinav.questions AS q\n" +
                "INNER JOIN onlinesinav.question_exam AS qe USING(q_id ) where qe.e_id = ? order by question_number+0 asc ";
         logger.debug("searchQuestion icinde ... ");

        Connection conn = null;
       setTime(findTimeDifference(examId) ) ;


        try {
            conn = getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Question question = null;
            ps.setString(1, examId);
            ResultSet rs = ps.executeQuery();
            logger.debug("Connection acildi execute yapildi ");
            while (rs.next()) {
                setQuestion(rs.getString(4));
                String image =  rs.getString(7);
                logger.debug("----------- > " +  image  + "qwqeqwe " + rs.getString(7) );
                if (image.equals("")) {

                    image = "-";
                }
                else {

                    image = IP.concat(rs.getString(7));
                }
                logger.debug("question nesnesi olusturuluyor  ");
                question = new Question(
                        rs.getString(1), findByQuestionTypeId(rs.getString(3)), getQuestion(),
                        rs.getString(5), rs.getString(6), image,rs.getString(8), getTime());
                questionList.add(question);
            }
            rs.close();
            ps.close();
            return questionList;
        } catch (SQLException e) {
            logger.debug("Hata var in  searchQuestion method  ve o hsta == > " + e );
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public QuestionTypes findByQuestionTypeId(String qTypeId) {
        String sql;

        sql = "SELECT   \n" +
                "*\n" +
                "FROM onlinesinav.question_types WHERE type_id = ?";

        Connection conn = null;

        try {
            conn = getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            QuestionTypes questionTypes = null;
            ps.setString(1, qTypeId);
            ResultSet rs = ps.executeQuery();
             String question ;
            while (rs.next()) {

                if (rs.getString(1).equals("2")){

                    System.out.println();
                    System.out.println();
                    System.out.println( "iffffffffffffffififif icide ");



                    String images [] =  getQuestion().split(":-:") ;
                    question =    images[0] ;
                    for (int i = 1 ; i < images.length ; i++) {
                             images[i] =":-:" + IP.concat(images[i]);
                        System.out.println( images [i]);
                       question =  question.concat(images[i]);
                        System.out.println( question);
                    }
                     setQuestion(question);

                }


                questionTypes = new QuestionTypes(
                        rs.getString(1), rs.getString(2)

                );
            }
            rs.close();
            ps.close();
            return questionTypes;
        } catch (SQLException e) {
            logger.debug("Hata var in  findByQuestionTypeId method  ve o hsta == > " + e );
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }






    @Override
    public String findTimeDifference (String examId) {
        String sql;
        int timeFark  = 0 ;
        int duationFark = 0  ;
        int duration = 0 ;

        logger.debug("findTimeDifference methodunun icnde ");

        sql = "SELECT\n" +
                "*\n" +
                "FROM onlinesinav.exams  where e_id = ?";

        Connection conn = null;

        try {
            conn = getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            java.util.Date time = null;
            int examTimestamp;
            ps.setString(1, examId);
            ResultSet rs = ps.executeQuery();
            logger.debug("Connection acildi query execute edilmeden oncesi ");
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            while (rs.next()) {
                examTimestamp = Integer.parseInt(rs.getString(4));   // database den gelen tiestamp
                System.out.println("-------------iikğpoupoıhgıouyfuytrdsytres65-------------- " + Integer.parseInt(rs.getString(5)));
                duration =  Integer.parseInt(rs.getString(5)) ;
                logger.debug("Databasse den gelen time stsmp ---- > " + examTimestamp);
                Date serverDate = new Date();
                String formattedDate = df.format(serverDate);
                long serverTimestamp =   timeConversion(formattedDate) ;  /// bizim time stamp
                logger.debug("Serverin time stsmp i  -- > " + serverTimestamp) ;
                timeFark = (int) (serverTimestamp - examTimestamp);

                logger.debug("Zaman farki server saati ile databasedeki veri arasindaki -- >  " + timeFark);

                duationFark =  ((Integer.parseInt(rs.getString(5))* 60 ) - timeFark ) ;

                logger.debug("Saniye cinsinden ogrencinin sinav suresi ");

                if (duationFark <=  0 ) {
                    duationFark = 0 ;
                }

            }
            rs.close();
            ps.close();
            return splitToComponentTimes(duationFark , duration);
        } catch (SQLException e) {
            logger.debug("Hata var in  findTimeDifference method  ve o hsta == > " + e );
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static String  splitToComponentTimes(int biggy , int duration )
    {

        System.out.println();
        System.out.println();
        System.out.println();


        long longVal = biggy ;
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;
        String a   ;
        System.out.println(biggy/60);
        a = biggy/60+"";
        if (Integer.parseInt(a) > duration) {


               a = duration+"";
        }
        return  a  ;
    }

    public long timeConversion(String time)
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
