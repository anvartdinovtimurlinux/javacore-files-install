import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    private final static String BASE_PATH = "/Users/anvartdinovtimur/Documents/games/";
    private static StringBuilder logger = new StringBuilder("Журнал логирования:\n");

    public static void main(String[] args) {
        Arrays.stream(new String[]{"src", "res", "savegames", "temp",
                "src/main","src/test",
                "res/drawables", "res/vectors", "res/icons"})
                .forEach(Main::makeDirectory);

        Arrays.stream(new String[]{"src/main/Main.java", "src/main/Utils.java", "temp/temp.txt"})
                .forEach(Main::makeFile);


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BASE_PATH + "temp/temp.txt"))) {
            String text = logger.toString();
            bw.write(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void makeDirectory(String childDirectory) {
        File directory = new File(BASE_PATH + childDirectory);
        makeLog(false, childDirectory, directory.mkdir());
    }

    private static void makeFile(String pathToFile) {
        File file = new File(BASE_PATH + pathToFile);
        try {
            makeLog(false, pathToFile, file.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeLog(boolean isFile, String name, boolean isSuccess) {
        logger.append("Создание ")
                .append(isFile ? "файла " : "папки ")
                .append(name)
                .append(isSuccess ? " - успешно" : " - неуспешно")
                .append("\n");
    }

}
