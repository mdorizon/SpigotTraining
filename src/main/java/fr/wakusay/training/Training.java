package fr.wakusay.training;

import fr.wakusay.training.config.Config;
import fr.wakusay.training.config.ConfigLang;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public final class Training extends JavaPlugin {

    private static Config configuration;
    private static ConfigLang configLang;
    @Override
    public void onEnable() {
        // Plugin startup logic

        //Chargement de la config
        configuration = new Config(this);
        configLang = new ConfigLang(this);

        getLogger().info("coucou" + getMessage("test"));

        getLogger().info("Plugin vient d'être chargé avec succès !");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        try {
            configuration.save();
            configLang.save();
            getLogger().info("Configuration config.yml & langues sauvegardée");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bukkit.getLogger().log(Level.INFO, "Plugin Training disable !");

    }

    public Config getConfiguration() {
        return configuration;
    }

    public ConfigLang getConfigLang() {
        return configLang;
    }

    public String getMessage(String message) {
        return getConfigLang().get(message);
    }

}
