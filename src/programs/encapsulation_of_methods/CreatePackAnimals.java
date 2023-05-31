package encapsulation_of_methods;

import java.time.LocalDate;

public abstract class CreatePackAnimals {



    protected abstract Pack_animals createNewPack_animals(Pack_animals_genus genus);

    public Pack_animals createPack_animals(Pack_animals_genus genus, String name, LocalDate date) {

        Pack_animals pack_animal = createNewPack_animals(genus);
        pack_animal.setName(name);
        pack_animal.setBirthday(date);
        return pack_animal;
    }
}