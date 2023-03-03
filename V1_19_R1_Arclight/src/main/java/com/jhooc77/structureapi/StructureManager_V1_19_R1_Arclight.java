package com.jhooc77.structureapi;

import net.minecraft.nbt.NbtIo;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.craftbukkit.v1_19_R1.structure.CraftStructureManager;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Random;

public class StructureManager_V1_19_R1_Arclight implements StructureManager {

    final StructureTemplateManager structureManager;

    public StructureManager_V1_19_R1_Arclight() {
        try {
            Field field = CraftStructureManager.class.getDeclaredField("structureManager");
            field.setAccessible(true);
            this.structureManager = (StructureTemplateManager) field.get(Bukkit.getStructureManager());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Structure loadStructure(InputStream inputStream) throws IOException {
        return new Structure_V1_19_R1_Arclight(structureManager.m_230404_(NbtIo.m_128939_(inputStream)));
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
