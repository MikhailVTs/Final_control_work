package counter;

public class Counter implements AutoCloseable {

    static int sum;
    {
        sum = 0;
    }

    public void add_counter() {
        sum++;
    }

    public void close() {
        System.out.println("Завершение работы программы");
    }


}