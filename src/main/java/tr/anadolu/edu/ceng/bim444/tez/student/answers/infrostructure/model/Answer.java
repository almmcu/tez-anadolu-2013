package tr.anadolu.edu.ceng.bim444.tez.student.answers.infrostructure.model;

/**
 * User: ali
 * Date: 4/8/13
 * Time: 10:57 AM
 */
public class Answer {


    private String id ;
    private  String ingilzce  ;
    private  String turkce  ;
    private String erisim ;
    private String status ;

    public Answer() {
    }

    public String getIngilzce() {
        return ingilzce;
    }

    public void setIngilzce(String ingilzce) {
        this.ingilzce = ingilzce;
    }

    public String getTurkce() {
        return turkce;
    }

    public void setTurkce(String turkce) {
        this.turkce = turkce;
    }

    public String getErisim() {
        return erisim;
    }

    public void setErisim(String erisim) {
        this.erisim = erisim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
