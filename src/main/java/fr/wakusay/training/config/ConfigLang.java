package fr.wakusay.training.config;

import fr.wakusay.training.Training;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.text.FieldPosition;
import java.util.Objects;

public class ConfigLang {

    final private Training plugin;

    private File configFile;

    final private String[] availableLang = {"EN", "FR"};

    public ConfigLang(Training plugin) {
        this.plugin = plugin;

        generateConfigFiles();

        configFile = new File(plugin.getDataFolder() + File.separator + "lang" + File.separator + plugin.getConfiguration().getLang() + ".yml");

        Bukkit.getLogger().info("[Training] Nous utilisons le fichier: " + configFile.getName());

        load();

    }

    private void generateConfigFiles() {
        for (String lang : availableLang) {
            configFile = new File(plugin.getDataFolder() + File.separator + "lang" + File.separator + lang + ".yml");
            if(!configFile.exists()) {
                plugin.saveResource("lang" + File.separator + lang + ".yml", false);
                Bukkit.getLogger().info("[Training] Le fichier de lang vient d'être sauvegardé: " + lang);
            }
        }

    }

    public void load() {
        final YamlConfiguration conf = YamlConfiguration.loadConfiguration(configFile);

        conf.getConfigurationSection("messages");
    }

    public void save() throws IOException {
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(configFile);
        conf.save(configFile);
    }

    public String get(String message) {
        final YamlConfiguration conf = YamlConfiguration.loadConfiguration(configFile);

        if(Objects.requireNonNull(conf.getConfigurationSection("messages")).getString(message) == null) {
            return "§c§lTRADUCTION INVALIDE §o" + message;
        }

        return Objects.requireNonNull(Objects.requireNonNull(conf.getConfigurationSection("messages"))
                .getString(message)
                .replace("&", "§")
                .replace("{{prefix}}", plugin.getConfiguration().getPrefix())
        );
    }
}
