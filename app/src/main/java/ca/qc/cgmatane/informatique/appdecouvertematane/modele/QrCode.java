package ca.qc.cgmatane.informatique.appdecouvertematane.modele;

/**
 * Created by 1732986 on 2017-09-26.
 */

public class QrCode {

    private String id;

    public QrCode(String id) {
        this.id = id;
    }

    public QrCode() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QrCode{" +
                "id='" + id + '\'' +
                '}';
    }
}
