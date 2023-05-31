package encapsulation_of_methods;

public enum Pets_genus {
    
    Cat,
    Dog,
    Hamster;

    public static Pets_genus getType (int id_genus){
        switch (id_genus){
            case 1:
                return Pets_genus.Cat;
            case 2:
                return Pets_genus.Dog;
            case 3:
                return Pets_genus.Hamster;
            default:
                return null;
        }
    }
}