package tr.anadolu.edu.ceng.bim444.tez.giris.infrastructure.model;

public class Person {


    private String id;
    private String username;
    private String password;
    private String name;
    private String surnmae;
    private String email;


    public Person() {
    }

    public Person(String id, String username, String password, String name, String surnmae, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surnmae = surnmae;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurnmae() {
        return surnmae;
    }

    public void setSurnmae(String surnmae) {
        this.surnmae = surnmae;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mname='" + username + '\'' +
                ", surnmae='" + surnmae + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
