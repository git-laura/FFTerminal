package ff.findyourfriend.model;

/**
 * Created by laura on 06/04/2016.
 */
public class User {
    int id;
    String name;
    String surname;
    String photo;
    String telephone;
    Coordenate coordenate;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Coordenate getCoordenate() {
        return coordenate;
    }

    public void setCoordenate(Coordenate coordenate) {
        this.coordenate = coordenate;
    }
}
