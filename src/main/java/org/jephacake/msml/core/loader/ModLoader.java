package org.jephacake.msml.core.loader;

import org.jephacake.msml.api.utils.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {

    public static HashMap<String, Mod> mods = new HashMap<>();

    public static void locateMods(File pluginFolder) {
        File[] files = pluginFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    checkForModYml(file);
                }
            }
        }
    }

    private static void checkForModYml(File jarFile) {
        try (JarFile jar = new JarFile(jarFile)) {
            JarEntry modYmlEntry = jar.getJarEntry("mod.yml");
            if (modYmlEntry != null) {
                try (InputStream is = jar.getInputStream(modYmlEntry)) {
                    Yaml yaml = new Yaml();
                    Map<String, Object> data = yaml.load(is);
                    Logger.info("Loaded mod.yml from " + jarFile.getName() + ": " + data.toString());

                    String name = (String) data.get("name");
                    String version = (String) data.get("version");
                    String entryPoint = (String) data.get("entryPoint");
                    List<String> authors = (List<String>) data.get("authors");
                    assert authors != null;
                    Map<String, String> additionalData = (Map<String, String>) data.get("additionalData");

                    ModConfig mod = new ModConfig(name, version, entryPoint, authors, additionalData, jarFile);
                    Logger.info("Parsed mod information: " + mod.toString());

                }
            }
        } catch (Exception e) {
            Logger.severe("Failed to load mod.yml from " + jarFile.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void loadMod(ModConfig modConfig) {
        Logger.info("Loading mod: " + modConfig.name);
        Mod mod = new Mod(modConfig);
        mods.put(modConfig.name, mod);
    }
}