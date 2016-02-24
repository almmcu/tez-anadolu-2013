package tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.dao.impl;

import org.slf4j.LoggerFactory;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.PersonInfo;
import tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.dao.AnswerDAO;
import tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.model.Answer;
import tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.model.AnswerPosted;
import tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.model.Answers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * User: ali
 * Date: 4/8/13
 * Time: 10:56 AM
 */

public class JDBCAnswerDAO implements AnswerDAO {
    private DataSource dataSource;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JDBCAnswerDAO.class);

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void insert(Answers answers, String sid, String examid) {
        String sql, sqlDelete;

        sqlDelete = "DELETE FROM onlinesinav.answers\n" +
                "where q_id = ? and s_tcNo = ? and e_id = ?";


        sql = "INSERT INTO onlinesinav.answers\n" +
                "(\n" +
                "q_id,\n" +
                "s_tcNo,\n" +
                "e_id,\n" +
                "a\n" +
                "\n )" +
                "VALUES" +
                "(" +
                "?, ?, ?, ?" +
                ")";
        ArrayList<Answer> answerPostedList;
        answerPostedList = answers.getAnswers();
        Connection conn = null;
        Answer answer = new Answer();
        answer.setTimeInterval(sid);
        answer.setAccelerometer(examid);
        try {
            conn = dataSource.getConnection();
            for (int i = 0; i < answerPostedList.size() - 1; i++) {

                PreparedStatement psdelete = conn.prepareStatement(sqlDelete);
                psdelete.setString(1, answerPostedList.get(i).getAccelerometer());
                psdelete.setString(2, answer.getTimeInterval());
                psdelete.setString(3, answer.getAccelerometer());
                psdelete.executeUpdate();
                psdelete.close();


                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, answerPostedList.get(i).getAccelerometer());
                ps.setString(2, answer.getAccelerometer());
                ps.setString(3, answer.getAccelerometer());
                if (answerPostedList.get(i).getAccelerometer().startsWith(":-:image:-:") ) {
                    logger.debug(":-:image:-: ile baslayan cevap var ");
                    String name = answer.getAccelerometer().concat("-").concat(answer.getAccelerometer()).concat("-").concat(answerPostedList.get(i).getAccelerometer());
                    DecodeImage decodeImage = new DecodeImage(answerPostedList.get(i).getAccelerometer(), name);

                    ps.setString(4, decodeImage.decodeImage());

                } else {
                    ps.setString(4, answerPostedList.get(i).getAccelerometer());


                }
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException e) {
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




}
