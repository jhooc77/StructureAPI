package com.jhooc77.structureapi;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockRotProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.bukkit.Location;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.util.RandomSourceWrapper;

import java.util.Random;

public class Structure_V1_19_R1_Arclight implements Structure {

    final StructureTemplate structure;
    public Structure_V1_19_R1_Arclight(StructureTemplate structure) {
        this.structure = structure;
    }
    @Override
    public void place(Location location, boolean spawnEntity, StructureRotation rotation, Mirror mirror, float integrity, Random random) {
        ServerLevelAccessor worldAccess = ((CraftWorld) location.getWorld()).getHandle();
        BlockPos blockPosition = new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        RandomSourceWrapper randomSource = new RandomSourceWrapper(random);
        StructurePlaceSettings settings = new StructurePlaceSettings()
                .m_74377_(net.minecraft.world.level.block.Mirror.valueOf(mirror.name()))
                .m_74379_(Rotation.valueOf(rotation.name()))
                .m_74392_(!spawnEntity)
                .m_74383_(new BlockRotProcessor(1))
                .m_230324_(randomSource);
        structure.m_230328_(worldAccess, blockPosition, blockPosition, settings, randomSource, 2);
    }
}
