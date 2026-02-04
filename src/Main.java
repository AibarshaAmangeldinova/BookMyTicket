import controllers.ConsoleController;

public class Main {

    public static void main(String[] args) {
        startApplication();
    }

    private static void startApplication() {
        ConsoleController controller = new ConsoleController();
        controller.start();
    }
}
