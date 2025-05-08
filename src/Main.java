import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Main {

    public static void main(String[] args) {
        String basePath = "D:/Games"; // Укажите путь к вашей папке Games
        StringBuilder log = new StringBuilder();

        // Создание основной директории Games
        File gamesDir = new File(basePath);
        if (!gamesDir.exists()) {
            if (gamesDir.mkdir()) {
                log.append("папка 'Games' успешно создана.\n");
            } else {
                log.append("Не удалось создать папку 'Games'.\n");
            }
        }
        //Создание поддиректорий
        createDirectory(gamesDir, "src", log);
        createDirectory(gamesDir, "res", log);
        createDirectory(gamesDir, "savegames", log);
        createDirectory(gamesDir, "temp", log);

        // Создание поддиректорий в src
        File srcDir = new File(gamesDir, "src");
        createDirectory(srcDir, "main", log);
        createDirectory(srcDir, "test", log);
        // Создание файлов в main
        File mainDir = new File(srcDir, "main");
        createFile(mainDir, "Main.java", log);
        createFile(mainDir, "Utils.java", log);

        // Создание поддиректорий в res
        File resDir = new File(gamesDir, "res");
        createDirectory(resDir, "drawables", log);
        createDirectory(resDir, "vectors", log);
        createDirectory(resDir, "icons", log);

        // Создание файла temp.txt
        File tempFile = new File(gamesDir, "temp/temp.txt");
        try {
            if (tempFile.createNewFile()) {
                log.append("Файл 'temp.txt' Успешно создан.\n");
            } else {
                log.append("Ошибка создания файла 'temp.txt'.\n");
            }
        } catch (IOException e) {
            log.append("Ошибка создания файла 'temp.txt': ").append(e.getMessage()).append("\n");
        }

        // Запись лога в temp.txt
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(log.toString());
            System.out.println("Log записан в 'temp.txt'.");
        } catch (IOException e) {
            System.out.println("Ошибка записи в 'temp.txt': " + e.getMessage());
        }
    }
    private static void createDirectory(File parent, String name, StringBuilder log) {
        File dir = new File(parent, name);
        if (dir.mkdir()) {
            log.append("Папка '").append(name).append("' успешно создано.\n");
        } else {
            log.append("Не удалось создать папку! '").append(name).append("'.\n");
        }
    }

    private static void createFile(File parent, String name, StringBuilder log) {
        File file = new File(parent, name);
        try {
            if (file.createNewFile()) {
                log.append("Файл '").append(name).append("' создано успешно.\n");
            } else {
                log.append("Не удалось создать файл! '").append(name).append("'.\n");
            }
        } catch (IOException e) {
            log.append("Ошибка создания файла! '").append(name).append("': ").append(e.getMessage()).append("\n");
        }
    }
}

