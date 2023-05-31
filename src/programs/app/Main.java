import counter.*;
import encapsulation_of_methods.*;
import repository.*;
import users_interface.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Repository <Pets> myFarm = new Pets_Repository();
        Pets_counter counter = new Pets_counter(myFarm);
        new Console_menu (counter).start();
    }
}    