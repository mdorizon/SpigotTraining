package fr.wakusay.training.config;

import fr.wakusay.training.Training;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private String prefix;
    private String lang;

    final private File configFile;

    public Config(Training plugin) {
        configFile = new File(plugin.getDataFolder() + File.separator + "config.yml");

        if(!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }

        //Load config
        load();
    }


    public void load() {
        final YamlConfiguration conf = YamlConfiguration.loadConfiguration(configFile);
        this.initGeneralConfig(conf);
    }

    public void save() throws IOException {
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(configFile);
        conf.save(configFile);
    }

    private void initGeneralConfig(YamlConfiguration conf) {
        prefix = conf.getString("general.prefix", "Plugin > ");
        lang = conf.getString("general.lang", "FR");
    }

    public String getPrefix() {
        return prefix.replace("&", "§");
    }

    public String getLang() {
        return lang;
    }
}
