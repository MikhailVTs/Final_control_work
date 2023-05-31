package users_interface;

import java.util.List;
import java.util.Scanner;
import encapsulation_of_methods.*;

public class Console implements View_console <Pets> {

    Scanner in;

    public Console() {
        in = new Scanner(System.in);
    }

    @Override
    public String getName() {
        System.out.printf("Имя: ");
        return in.nextLine();
    }

    @Override
    public String getBirthday() {
        System.out.printf("Введите дату рождения в формате 'dd.mm.yyyy': ");
        return in.nextLine();
    }

    @Override
    public <T> void print_data (List <T> list, Class <T> clazz) {

        if (list.isEmpty())
            System.out.println("список пуст");
        else {
            if (clazz == Pets.class)
                System.out.println("\n          Наши питомцы:");
            for (T item : list) {
                System.out.println(item);              
            }
        }
    }
    
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}