package counter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import encapsulation_of_methods.*;
import repository.*;
import users_interface.*;


public class Pets_counter {
    private Repository<Pets> petRepository;
    private CreatePets pets_Create;
    private final View_console<Pets> view;
    private Check check;

    public Pets_counter(Repository<Pets> petRepository) {
        this.petRepository = petRepository;
        this.pets_Create = new Pets_Create();
        this.view = new Console();
        this.check = new Check();
    }

    public void create_Pets(Pets_genus genus) {

        String[] data = new String[] { view.getName(), view.getBirthday() };
        check.validate(data);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        try {
            int res = petRepository.create(pets_Create.createPet(genus, data[0], birthday));
            view.showMessage(String.format("%d запись добавлена", res));
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }

    }

    public void updatePet(int id) {

        Pets pets = getById(id);
        String[] data = new String[] { view.getName(), view.getBirthday() };

        check.validate(data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(data[1], formatter);
        pets.setName(data[0]);
        pets.setBirthday(birthday);
        try {
            int res = petRepository.update(pets);
            view.showMessage(String.format("%d запись изменена \n", res));
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }

    }

    public void getAllPet() {
        try {
            view.print_data(petRepository.getAll(), Pets.class);
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }

    public boolean trainPet(int id, String command) {
        try {

            if (((Pets_Repository) petRepository).getCommandsById(id, 1).contains(command))
                view.showMessage("это мы уже умеем");
            else {
                if (!((Pets_Repository) petRepository).getCommandsById(id, 2).contains(command))
                    view.showMessage("невыполнимая команда");
                else {
                    ((Pets_Repository) petRepository).train(id, command);
                    view.showMessage("команда разучена\n");
                    return true;
                }
            }
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
        return false;
    }

    public Pets getById(int id) {
        try {
            return petRepository.getById(id);
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
        return null;
    }

    public void delete(int id) {
        try {
            petRepository.delete(id);
            view.showMessage("запись удалена");
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }

    public void getCommands(int id) {
        try {
            view.print_data(((Pets_Repository) petRepository).getCommandsById(id, 1), String.class);
        } catch (RuntimeException e) {
            view.showMessage(e.getMessage());
        }
    }
}