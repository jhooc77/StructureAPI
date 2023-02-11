package com.jhooc77.structureapi;

import net.minecraft.server.v1_15_R1.DefinedStructure;
import net.minecraft.server.v1_15_R1.DefinedStructureManager;
import net.minecraft.server.v1_15_R1.MinecraftKey;
import net.minecraft.server.v1_15_R1.MinecraftServer;
import org.bukkit.NamespacedKey;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StructureManager_V1_15_R1 implements StructureManager {

    final DefinedStructureManager structureManager;
    final Method getFromInputStream;

    public StructureManager_V1_15_R1() {
        this.structureManager = new DefinedStructureManager(MinecraftServer.getServer(), new File("world"), MinecraftServer.getServer().getServer().dataConverterManager);
        try {
            getFromInputStream = DefinedStructureManager.class.getDeclaredMethod("a", InputStream.class);
            getFromInputStream.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Structure loadStructure(InputStream inputStream) throws IOException {
        try {
            return new Structure_V1_15_R1((DefinedStructure) getFromInputStream.invoke(structureManager, inputStream));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Structure loadStructure(NamespacedKey key) {
        return new Structure_V1_15_R1(structureManager.a(new MinecraftKey(key.getNamespace(), key.getKey())));
    }
}
