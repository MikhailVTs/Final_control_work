package encapsulation_of_methods;

import java.time.LocalDate;

public abstract class CreatePets {

    protected abstract Pets createNewPet(Pets_genus genus);

    public Pets createPet(Pets_genus genus, String name, LocalDate date) {

        Pets pet = createNewPet(genus);
        pet.setName(name);
        pet.setBirthday(date);
        return pet;
    }

}