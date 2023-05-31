package encapsulation_of_methods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Pets {
    
    protected int idPets;
    protected String name;
    protected LocalDate birthday;
    
    public void setIdPets(int petId) {
        this.idPets = petId;
    }

    public int getIdPets() {
        return idPets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(LocalDate date) {
        this.birthday = date;
    }

    public LocalDate getBirthdayLocalDate(){
        return birthday;
    }

    public String getBirthday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(birthday);
    }

    @Override
    public String toString() {
        return String.format("%d. %s: name: %s, birthday: %s ", getIdPets(), getClass().getSimpleName(), name, getBirthday());
    }
}