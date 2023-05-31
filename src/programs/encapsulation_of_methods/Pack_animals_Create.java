package encapsulation_of_methods;


public class Pack_animals_Create extends CreatePackAnimals {

    @Override
    protected Pack_animals createNewPack_animals (Pack_animals_genus genus) {

        switch (genus) {
            case Camel:
                return new Camels();
            case Horse:
                return new Horses();
            case Donkey:
                return new Donkeys();
        }
        return null;
    }
    
}