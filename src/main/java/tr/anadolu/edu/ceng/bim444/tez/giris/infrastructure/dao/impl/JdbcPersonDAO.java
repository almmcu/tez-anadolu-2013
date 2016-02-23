package tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.dao.impl;

import ch.qos.logback.classic.Logger;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.LoggerFactory;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.dao.PersonDAO;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.Person;
import tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model.PersonInfo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class JdbcPersonDAO implements PersonDAO {
    private DataSource dataSource;
    private String personId;
    private String personPassword;
    private String status;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JdbcPersonDAO.class);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Person person, String name) {
        String sql;
        if (name.equals("student")) {

            sql = "INSERT INTO onlinesinav.students (S_tcNo, S_USERNAME, S_PASSWORD,S_NAME, S_SURNAME , S_EMAIL)" +
                    "VALUES" +
                    "(" +
                    "?, ?, ?, ?, ?, ?" +
                    ")";
        } else {
            sql = "INSERT INTO onlinesinav.teachers (T_ID, T_USERNAME,T_PASSWORD,T_NAME , T_SURNAME,  T_EMAIL)" +
                    "VALUES" +
                    "(" +
                    "?, ?, ?, ?, ?, ?" +
                    ")";
        }
        Connection conn = null;
         logger.debug("insert new student --- > ");
        logger.debug(person.toString());
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, person.getId());
            ps.setString(2, person.getUsername());
            ps.setString(3, person.getPassword());
            ps.setString(4, person.getName());
            ps.setString(5, person.getSurnmae());
            ps.setString(6, person.getEmail());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            logger.debug("JDBCPersonDao sinifi insert methodo icinde hsts vsr --- > " + e );
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

    public PersonInfo findByPersonId(String personId, Boolean name) { // bu donuyor
        String sql;
        PersonInfo person = null;
        if (name) {
            sql = "SELECT * FROM onlinesinav.students WHERE S_tcNo = ? or S_USERNAME = ?";

          /*  sql = "SELECT * FROM onlinesinav.teachers WHERE T_ID = ? or T_USERNAME = ?";*/

            logger.debug("kullanici adi dogru ");

            Connection conn = null;

            try {
                conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, getPersonId());
                ps.setString(2, getPersonId());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    person = new PersonInfo(
                            rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                            rs.getString(6), "true"
                    );
                }
                logger.debug(" student -- > " + person.toString());
                rs.close();
                ps.close();
                return person;
            } catch (SQLException e) {
                logger.debug( " findByPersonId metodu  Sql sorgularinda hata var -- > " + e );
                throw new RuntimeException(e);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }
                }
            }

        } else {
            person = new PersonInfo("", "", "", "", "", "", getStatus());
            return person;
        }

    }

    @Override
    public boolean searchPerson(String personInfo, String name) {
        base64Decode(personInfo);
        logger.debug("deneme  " + !searchPersonId(name));
        if (!searchPersonId(name)) {
            logger.debug("K adi yanlis ");
            setStatus("Username is incorrect");
            return false;
        } else if (!searchPersonPassword(name)) {
            logger.debug("K adi dogru sifre yanlis ");
            setStatus("Password is incorrect");
            return false;
        }
        logger.debug("Ikiside dogru  ");
        return true;
    }

    public boolean searchPersonId(String name) {
        String sqlSorgiID;
        Set<String> idSet = new HashSet<String>();
           logger.debug("student searcing ... ");
        if (name.equals("student")) {

            sqlSorgiID = "SELECT S_tcNo, S_USERNAME FROM onlinesinav.students";
        } else {
            sqlSorgiID = "SELECT T_ID, T_USERNAME FROM onlinesinav.teachers";
        }
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlSorgiID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                idSet.add(rs.getString(1));
                idSet.add(rs.getString(2));
            }
            rs.close();
            ps.close();
            System.out.println("id set " + idSet.toString() + "per son get " + getPersonId());
            if (idSet.contains(getPersonId())) {
                System.out.println("K adi dogru  ");
                return true;
            } else
                return false;
        } catch (SQLException e) {
            logger.debug("hata var searchPersonId methodu icinde ve hatada --- > " + e );
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    logger.debug("connection kspsniyor ---- > ");
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public boolean searchPersonPassword(String name) {
        String sqlSorgiPassword;
        String password = null;

        if (name.equals("student")) {

            sqlSorgiPassword = "SELECT S_PASSWORD FROM onlinesinav.students WHERE S_tcNo = ? or S_USERNAME = ?";
        } else {
            sqlSorgiPassword = "SELECT T_PASSWORD FROM onlinesinav.teachers WHERE T_ID = ? or T_USERNAME = ?";
        }
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlSorgiPassword);
            ps.setString(1, getPersonId());
            ps.setString(2, getPersonId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                password = rs.getString(1);
            }
            rs.close();
            ps.close();
            logger.debug("Password ------------------------------ = "
                    + password + " " + getPersonPassword()
                    + "=====0");
            if (password == null) {
                return false;
            }
            if (!password.equals(getPersonPassword())) {
                return false;
            } else
                return true;
        } catch (SQLException e) {
            logger.debug("hata var searchPersonPassword metodu icin de va hatada ---- > " + e );
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    logger.debug("Connection kapaniyooooooooooooooooooooooooooooooooooooooooorrr");
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    void base64Decode(String encodedText) {
        String decodedText;
        decodedText = new String(Base64.decodeBase64(encodedText.getBytes()));
        logger.debug("base64decode methodu ve decoded text --- > " + decodedText);
        String[] splits = decodedText.split("#");
        setPersonId(splits[0]);
        setPersonPassword(splits[1]);
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}




