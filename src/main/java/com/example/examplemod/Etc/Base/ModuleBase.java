package com.example.examplemod.Etc.Base;

import com.example.examplemod.Etc.CheatConfigManager;

public class ModuleBase {
    String name;
    String description;
    boolean enabled;

    public ModuleBase(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void register(CheatConfigManager config) {
    }

    public void run() {
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public final String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
