package tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model;

/**
 * User: ali
 * Date: 4/11/13
 * Time: 3:28 PM
 */
public class PersonInfo {

    private String id;
    private String username;
    private String password;
    private String name;
    private String surnmae;
    private String email;
    private String bilgi;

    public PersonInfo(String id, String username, String password, String name, String surnmae, String email, String bilgi) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surnmae = surnmae;
        this.email = email;
        this.bilgi = bilgi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnmae() {
        return surnmae;
    }

    public void setSurnmae(String surnmae) {
        this.surnmae = surnmae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBilgi() {
        return bilgi;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }
}
