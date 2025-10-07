import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GameConfig {

    private static GameConfig instance;
    private Map<String, Character> directionKeys;

    private GameConfig() {
        directionKeys = new HashMap<>();
        loadConfigFromFile();
    }

    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }


    private void loadConfigFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/Config.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim().toUpperCase();
                    char value = parts[1].trim().charAt(0);
                    if (key.equals("UP") || key.equals("UPL") || key.equals("DOWN") || key.equals("DOWNL") || key.equals("LEFT") || key.equals("LEFTL") || key.equals("RIGHT") || key.equals("RIGHTL") || key.equals("RESTART") || key.equals("RESTARTL") || key.equals("YES") || key.equals("YESL")) {
                        directionKeys.put(key, value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public char getCharacterForKey(String key) {
        return directionKeys.getOrDefault(key.toUpperCase(), ' ');
    }
}
