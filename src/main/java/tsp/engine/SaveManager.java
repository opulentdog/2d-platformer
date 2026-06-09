package tsp.engine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Permet de garder le score max en mémoire
 */
public class SaveManager {

    private final Properties scores;
    private final Path saveFile;

    /**
     * Initialise le gestionnaire de sauvegarde.
     * Crée le dossier de sauvegarde si nécessaire et charge les scores existants.
     */
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
    
    /**
     * Charge les scores depuis le fichier de sauvegarde.
     * Ne fait rien si le fichier n'existe pas encore.
     * @throws IOException si la lecture du fichier échoue
     */
    private void load() throws IOException {
        if (!Files.exists(saveFile)) {
            return;
        }

        try (InputStream input = Files.newInputStream(saveFile)) {
            scores.load(input);
        }
    }

    /**
     * Sauvegarde les scores dans le fichier de sauvegarde.
     * @throws IOException si l'écriture du fichier échoue
     */
    private void save() throws IOException {
        try (OutputStream output = Files.newOutputStream(saveFile)) {
            scores.store(output, "Best scores by seed");
        }
    }
    
    /**
     * Retourne le meilleur score enregistré pour une seed donnée.
     * @param seed la seed dont on veut le meilleur score
     * @return le meilleur score enregistré, ou 0 si aucun score n'existe pour cette seed
     */
    public int getBestScore(int seed) {
        String key = String.valueOf(seed);
        String value = scores.getProperty(key, "0");

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    /**
     * Met à jour le meilleur score pour une seed donnée si le nouveau score est supérieur.
     * @param seed la seed concernée
     * @param score le nouveau score à comparer
     * @return true si le score a été mis à jour, false si l'ancien score était déjà supérieur ou égal
     */
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