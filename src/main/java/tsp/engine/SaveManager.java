package tsp.engine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class SaveManager {

    private final Properties scores;
    private final Path saveFile;

    public SaveManager() {
        this.scores = new Properties();

        // Dossier de sauvegarde dans le dossier utilisateur
        Path saveDirectory = Path.of(System.getProperty("user.home"), ".tsp_plateformer");
        this.saveFile = saveDirectory.resolve("scores.properties");

        try {
            Files.createDirectories(saveDirectory);
            load();
        } catch (IOException e) {
            System.err.println("Impossible d'initialiser les sauvegardes : " + e.getMessage());
        }
    }

    private void load() throws IOException {
        if (!Files.exists(saveFile)) {
            return;
        }

        try (InputStream input = Files.newInputStream(saveFile)) {
            scores.load(input);
        }
    }

    private void save() throws IOException {
        try (OutputStream output = Files.newOutputStream(saveFile)) {
            scores.store(output, "Best scores by seed");
        }
    }

    public int getBestScore(int seed) {
        String key = String.valueOf(seed);
        String value = scores.getProperty(key, "0");

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean updateBestScore(int seed, int score) {
        int oldBestScore = getBestScore(seed);

        if (score <= oldBestScore) {
            return false;
        }

        scores.setProperty(String.valueOf(seed), String.valueOf(score));

        try {
            save();
            return true;
        } catch (IOException e) {
            System.err.println("Impossible de sauvegarder le score : " + e.getMessage());
            return false;
        }
    }
}