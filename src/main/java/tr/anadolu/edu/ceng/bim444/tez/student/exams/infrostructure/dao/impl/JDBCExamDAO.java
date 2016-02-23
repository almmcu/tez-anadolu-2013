package tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.dao.impl;

import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.Person;
import tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.dao.ExamDAO;
import tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.model.Exam;
import tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.model.Grades;
import tr.anadolu.edu.ceng.bim444.tez.student.exams.infrostructure.model.Status;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ali
 * Date: 4/8/13
 * Time: 10:56 AM
 */
public class JDBCExamDAO implements ExamDAO {
    private DataSource dataSource;
    private List examList;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Exam findByExamId(String examId, String studentId) {
        String sql;

        sql = "SELECT  * FROM onlinesinav.exams WHERE e_id = ?";

        Connection conn = null;

        try {
            conn = getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Exam exam = null;
            ps.setString(1, examId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                exam = new Exam(
                        rs.getString(1), findByPersonId(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), findGrade(rs.getString(1), studentId)
                );
            }
            rs.close();
            ps.close();
            return exam;
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


    @Override
    public List<Exam> searchExam(String studentId) {
        String sql;
        examList = new ArrayList<Exam>();
        sql = "SELECT\n" +
                "*\n" +
                "FROM onlinesinav.exam_student WHERE S_tcNo = ?";

        sql = "SELECT\n" +
                "*\n" +
                "FROM onlinesinav.exams  where e_id in (SELECT \n" +
                "exam_student.e_id\n" +
                "FROM onlinesinav.exam_student where s_tcNo = ? ) order by e_date asc;";

        Connection conn = null;

        try {
            conn = getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Exam exam;
            int timestamp;
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            while (rs.next()) {
                timestamp = Integer.parseInt(rs.getString(4));
                String examInfo ;
                examInfo = rs.getString(7);
                if( examInfo == null|| examInfo.equals("")){
                    examInfo = "-";
                }
                java.util.Date time = new java.util.Date((long) timestamp * 1000);
                      // rs.getString(7) examID geliyor
                exam = new Exam(
                        rs.getString(1), findByPersonId(rs.getString(2)), rs.getString(3), df.format(time), rs.getString(5),
                        rs.getString(6), examInfo,rs.getString(8),  findGrade(rs.getString(1), studentId)
                );

                examList.add(exam);
            }
            rs.close();
            ps.close();
            return examList;
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

    public Person findByPersonId(String personId) {
        String sql;

        sql = "SELECT * FROM onlinesinav.teachers WHERE T_ID = ?";


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("SD:Ifjawpdf11111111");

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Person person = null;
            ps.setString(1, personId);
            ResultSet rs = ps.executeQuery();
            System.out.println("SD:Ifjawpdf");
            while (rs.next()) {
                System.out.println("SD:Ifjawpdf");
                person = new Person(
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6)
                );
            }
            rs.close();
            ps.close();
            return person;
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

    public String findGrade(String examId, String studentId) {
        String sql;

        sql = "SELECT  * FROM onlinesinav.grades WHERE e_id = ? and s_tcNo = ?";
        String grade = null;
        Connection conn = null;

        try {
            conn = getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Grades grades = null;
            ps.setString(1, examId);
            ps.setString(2, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("aflakdf;lka'ref-------------- = " + rs.getString(3));
                grades = new Grades(rs.getString(3));
                grade = rs.getString(3);
            }
            if (grade == null) {
                grade = "-";
            }
            rs.close();
            ps.close();
            return grade;
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

    @Override
    public Status findStatusByExamId(String examId) {
        String sql;

        sql = "SELECT  is_started FROM onlinesinav.exams WHERE e_id = ?";

        Connection conn = null;

        try {
            conn = getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Status status = null;
            ps.setString(1, examId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                status = new Status(
                        rs.getString(1)

                );
            }
            rs.close();
            ps.close();
            return status;
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
