package ar.fiuba.tdd.tp.server;

/**
 * Created by jorlando on 16/05/16.
 */

import ar.fiuba.tdd.tp.engine.motor2.GameBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

// TODO: this class should be part of the server
public class BuilderLoader {

    private static String escape(String name) {
        if (name.contains("/")) {
            name = name.replaceAll("/", ".");
        }
        if (name.contains("\\")) {
            name = name.replaceAll("\\\\", ".");
        }
        return name;
    }

    private static String processEntry(JarEntry entry) {
        String name = entry.getName();
        return escape(name.substring(0, name.lastIndexOf(".class")));
    }

    private static List<String> scanJar(File file) throws IOException, IllegalArgumentException {
        if (file == null || !file.exists() || !file.getName().endsWith(".jar")) {
            throw new IllegalArgumentException("file");
        }

        List<String> foundClasses = new ArrayList<>();
        try (JarFile jarFile = new JarFile(file)) {
            jarFile.stream()
                    .filter(e -> e.getName().endsWith(".class"))
                    .forEach(e -> foundClasses.add(processEntry(e)));
        }
        return foundClasses;
    }

    public static String getMainClass(String pathFile) throws IOException, IllegalArgumentException {
        JarFile jarFile = new JarFile(new File(pathFile));
        ;
        String mainClass = "";
        try {
            mainClass = jarFile.getManifest().getMainAttributes().getValue("Main-Class");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jarFile.close();
        }
        return mainClass;
    }

    public static GameBuilder load(String filePath)
            throws ClassNotFoundException, IOException,
            IllegalAccessException, InstantiationException {
        File file = new File(filePath);
        String mainClass = getMainClass(filePath);
        URL[] urls = {new URL("jar:file:" + filePath + "!/")};
        ClassLoader loader = URLClassLoader.newInstance(urls);
        for (String classFile : scanJar(file)) {
            Class<?> foundClass;
            if (loader == null) {
                foundClass = Class.forName(classFile);
            } else {
                foundClass = Class.forName(classFile, true, loader);
            }

            if (mainClass.equals(classFile) && GameBuilder.class.isAssignableFrom(foundClass) && !foundClass.equals(GameBuilder.class)) {
                return (GameBuilder) foundClass.newInstance();
            }
        }
        return null;
    }
}
