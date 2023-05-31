package users_interface;

import java.util.Scanner;
import counter.*;
import encapsulation_of_methods.Pets_genus;
import exception.*;


public class Console_menu {

    Pets_counter pets_counter;

    public Console_menu(Pets_counter counter) {
        this.pets_counter = counter;
    }

    public void start() {

        System.out.println();
        try (Scanner in = new Scanner(System.in, "ibm866"); Counter count = new Counter()) {

            boolean flag = true;
            int id;
            while (flag) {

                System.out.println(
                        "\n1 - Список всех животных\n2 - Завести новое животное\n3 - Изменить данные о животном\n4 - Что умеет животное\n5 - Дрессировка\n6 - Удалить запись\n0 - Выход");
                String key = in.next();
                switch (key) {
                    case "1":
                        pets_counter.getAllPet();
                        break;
                    case "2":
                        Pets_genus genus = option_menu(in);
                        if (genus != null) {
                            try {
                                pets_counter.create_Pets(genus);
                                count.add_counter();
                                System.out.println("ОК");
                            } catch (DataEntryErrorException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "3":
                        while (true) {
                            id = menu_pets_option(in);
                            if (id != 0)
                                try {
                                    pets_counter.updatePet(id);
                                } catch (DataEntryErrorException e) {
                                    System.out.println(e.getMessage());
                                }
                            else
                                break;
                        }
                        break;
                    case "4":
                        while (true) {
                            id = menu_pets_option(in);
                            if (id != 0)
                                pets_counter.getCommands(id);
                            else
                                break;
                        }
                        break;
                    case "5":
                        id = menu_pets_option(in);
                        if (id != 0)
                            menuTrainPet(id, in);
                        break;
                    case "6":
                        id = menu_pets_option(in);
                        if (id != 0)
                            pets_counter.delete(id);
                        break;
                    case "0":
                        flag = false;
                        break;
                    default:
                        System.out.println("такого варианта нет");
                        break;
                }
            }
        }
    }

    private Pets_genus option_menu(Scanner in) {
        System.out.println("Какое животное добавить:\n1 - Кошка\n2 - Собака\n3 - Хомяк\n0 - Для возврата в меню выберите 0");

        while (true) {
            String key = in.next();
            switch (key) {
                case "1":
                    return Pets_genus.Cat;
                case "2":
                    return Pets_genus.Dog;
                case "3":
                    return Pets_genus.Hamster;
                case "0":
                    return null;
                default:
                    System.out.println("Будьте внимательны при вводе данных, введите число от 0 до 3");
                    break;
            }
        }
    }

    private int menu_pets_option(Scanner in) {
        System.out.println("\nВведите номер животного, для возврата в меню выберите 0: ");
        while (true) {
            int id = in.nextInt();
            in.nextLine();
            if (id == 0)
                return id;
            if (pets_counter.getById(id) == null) {
                System.out.println("Животного с таким номером нет, попробуйте еще раз, для возврата в меню выберите 0:");
            } else
                return id;

        }
    }

    private void menuTrainPet(int petId, Scanner in) {
        Scanner sc = in;
        while (true) {
            System.out.println("Введите команду, для возврата в меню выберите 0: ");
            String command = sc.nextLine();
            if (command.length() == 1 && command.equals("0"))
                return;
            if (pets_counter.trainPet(petId, command))
                System.out.println("Поздравляю, Вы справились!");
        }
    }
}