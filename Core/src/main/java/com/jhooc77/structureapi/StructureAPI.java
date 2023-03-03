package com.jhooc77.structureapi;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

public final class StructureAPI {

    static String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];


    public static StructureManager getStructureManager() {
        int subVersion = Integer.parseInt(version.replace("v1_", "").replaceAll("_R\\d", ""));
        Logger logger = Bukkit.getLogger();
        try {
            Class.forName("io.izzel.arclight.common.mod.server.ArclightServer");
            switch (version) {
                case "v1_19_R1":
                    logger.info("StructureAPI loaded - v1_19_R1_Arclight");
                    return new StructureManager_V1_19_R1_Arclight();
                case "v1_19_R2":
                    logger.info("StructureAPI loaded - v1_19_R2_Arclight");
                    return new StructureManager_V1_19_R2_Arclight();
            }
        } catch (ClassNotFoundException e) {
        }
        if (subVersion >= 17) {
            logger.info("StructureAPI loaded - v1.17+");
            return new StructureManager_V1_17();
        } else {
            switch (version) {
                case "v1_12_R1":
                    logger.info("StructureAPI loaded - v1_12_R1");
                    return new StructureManager_V1_12_R1();
                case "v1_16_R3":
                    logger.info("StructureAPI loaded - v1_16_R3");
                    return new StructureManager_V1_19_R2_Arclight();
            }
        }
        logger.info("StructureAPI failed to load");
        return null;
    }

}
