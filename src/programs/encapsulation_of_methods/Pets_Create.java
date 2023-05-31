package encapsulation_of_methods;


public class Pets_Create extends CreatePets {

    @Override
    protected Pets createNewPet (Pets_genus genus) {

        switch (genus) {
            case Cat:
                return new Cats();
            case Dog:
                return new Dogs();
            case Hamster:
                return new Hamsters();
        }
        return null;
    }
    
}
