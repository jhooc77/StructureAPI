package com.jhooc77.structureapi;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Structure_V1_16_R3 implements Structure {

    final DefinedStructure structure;
    public Structure_V1_16_R3(DefinedStructure structure) {
        this.structure = structure;
    }
    @Override
    public void place(Location location, boolean spawnEntity, StructureRotation rotation, Mirror mirror, float integrity, Random random) {
        WorldAccess worldAccess = ((CraftWorld) location.getWorld()).getHandle();
        BlockPosition blockPosition = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        DefinedStructureInfo settings = new DefinedStructureInfo()
                .a(random)
                .a(EnumBlockRotation.valueOf(rotation.name()))
                .a(new DefinedStructureProcessorRotation(integrity))
                .a(EnumBlockMirror.valueOf(mirror.name()))
                .a(!spawnEntity);
        structure.a(worldAccess, blockPosition, blockPosition, settings, random, 2);
    }
}
