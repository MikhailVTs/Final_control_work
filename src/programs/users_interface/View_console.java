package users_interface;

import java.util.List;

public interface View_console <T>{
    
    String getName();
    String getBirthday();
    <U> void print_data (List <U> list, Class <U> clazz);
    void showMessage (String s);

}