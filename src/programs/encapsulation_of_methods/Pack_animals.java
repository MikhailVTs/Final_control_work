package encapsulation_of_methods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Pack_animals {
    
    protected int idPack_animals;
    protected String name;
    protected LocalDate birthday;
    
    public void setIdPack_animals(int pack_animalsId) {
        this.idPack_animals = pack_animalsId;
    }

    public int getIdPack_animals() {
        return idPack_animals;
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
        return String.format("%d. %s: name: %s, birthday: %s ", getIdPack_animals(), getClass().getSimpleName(), name, getBirthday());
    }
}