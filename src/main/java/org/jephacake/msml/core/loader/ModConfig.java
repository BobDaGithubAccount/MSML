package org.jephacake.msml.core.loader;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ModConfig {

    public String name;
    public String version;
    public String entryPoint;
    public List<String> authors;
    public Map<String, String> additionalData;
    public File jarFile;

    public ModConfig(@NotNull String name, @NotNull String version, @NotNull String entryPoint,
                     List<String> authors, Map<String, String> additionalData, File jarFile) {
        this.name = name;
        this.version = version;
        this.entryPoint = entryPoint;
        this.authors = authors;
        this.additionalData = additionalData;
        this.jarFile = jarFile;
    }

    @Override
    public String toString() {
        return "Mod{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", entryPoint='" + entryPoint + '\'' +
                ", authors=" + authors +
                ", additionalData=" + additionalData +
                ", jarFile=" + jarFile +
                '}';
    }
}
