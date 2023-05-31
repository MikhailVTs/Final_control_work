package encapsulation_of_methods;

public enum Pack_animals_genus {
    
    Camel,
    Horse,
    Donkey;

    public static Pack_animals_genus getType (int id_genus){
        switch (id_genus){
            case 1:
                return Pack_animals_genus.Camel;
            case 2:
                return Pack_animals_genus.Horse;
            case 3:
                return Pack_animals_genus.Donkey;
            default:
                return null;
        }
    }
}