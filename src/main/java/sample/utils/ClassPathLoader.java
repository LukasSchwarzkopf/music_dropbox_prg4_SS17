package sample.utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class ClassPathLoader {

    private final List<ClassLoader> classLoaders = new ArrayList<>();

    public ClassPathLoader() {
        classLoaders.add(Thread.currentThread().getContextClassLoader());
        classLoaders.add(getClass().getClassLoader());
        classLoaders.add(ClassLoader.getSystemClassLoader());
    }

    public static InputStream getResourceAsInputStream(String path) {
        return new ClassPathLoader().getResourceAsStream(path);
    }

    public static URL getResourceUrl(String path) {
        return new ClassPathLoader().getResourceUrld(path);
    }

    public InputStream getResourceAsStream(String path) {
        try {
            return getResourceUrl(path).openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public URL getResourceUrld(String path) {
        try {
            for (ClassLoader classLoader : classLoaders) {
                final URL url = classLoader.getResource(removeStart(path, "/"));
                if (url != null) {
                    return url;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String removeStart(String str, String remove) {
        if (str.equals("") || remove.equals("")) {
            return str;
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

}
