import controllers.AppController;
import data.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.init();   // создаст таблицы/категории если нужно
        new AppController().start();
    }
}

