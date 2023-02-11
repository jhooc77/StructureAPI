package com.jhooc77.structureapi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class StructureManager_V1_17 implements StructureManager {
    @Override
    public Structure loadStructure(InputStream inputStream) throws IOException {
        return new Structure() {
            final org.bukkit.structure.Structure structure = Bukkit.getStructureManager().loadStructure(inputStream);
            @Override
            public void place(Location location, boolean spawnEntity, StructureRotation rotation, Mirror mirror, float integrity, Random random) {
                structure.place(location, spawnEntity, rotation, mirror, 0, integrity, random);
            }
        };
    }

    @Override
    public Structure loadStructure(NamespacedKey key) {
        return new Structure() {
            final org.bukkit.structure.Structure structure = Bukkit.getStructureManager().loadStructure(key);
            @Override
            public void place(Location location, boolean spawnEntity, StructureRotation rotation, Mirror mirror, float integrity, Random random) {
                structure.place(location, spawnEntity, rotation, mirror, 0, integrity, random);
            }
        };
    }
}
