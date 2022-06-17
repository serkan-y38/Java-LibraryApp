
import java.util.Date;

public class PersonModel {

    private int id;
    private String name;
    private String surname;
    private String bookname;
    private Date date;

    public PersonModel(int id, String ad, String soyad, String bookname, Date date) {
        this.id = id;
        this.name = ad;
        this.surname = soyad;
        this.bookname = bookname;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
