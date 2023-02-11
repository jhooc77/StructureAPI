package com.jhooc77.structureapi;

import org.bukkit.Bukkit;

public final class StructureAPI {

    static String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];


    public static StructureManager getStructureManager() {
        int subVersion = Integer.parseInt(version.replace("v1_", "").replaceAll("_R\\d", ""));
        if (subVersion >= 17) {
            return new StructureManager_V1_17();
        } else {
            switch (version) {
                case "v1_12_R1":
                    return new StructureManager_V1_12_R1();
                case "v1_16_R3":
                    return new StructureManager_V1_16_R3();
            }
        }
        return null;
    }

}
