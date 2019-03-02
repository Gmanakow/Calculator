

public class Starter {
    private static int width = 700;
    private static int height = 85;

    public static void main(String[] args) {
        MainFrame frame = new MainFrame(width, height);
        MainPanel panel = new MainPanel();

        frame.add(panel);
        frame.setVisible(true);


    }
}
