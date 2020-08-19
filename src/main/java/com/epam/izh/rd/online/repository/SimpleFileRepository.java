package com.epam.izh.rd.online.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class SimpleFileRepository implements FileRepository {

    @Override
    public long countFilesInDirectory(String path) {
        File dir = new File(path);

        File[] files = dir.listFiles();

        if (files == null) {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(path);
            if (resource == null) {
                return 0;
            }
            dir = new File(resource.getFile());
            files = dir.listFiles();
        }

        if (files != null) {
            long count = 0;

            for (File file : files) {
                if (file.isDirectory()) {
                    count += countFilesInDirectory(file.getPath());
                } else {
                    count++;
                }
            }
            return count;
        }

        return 0;
    }

    @Override
    public long countDirsInDirectory(String path) {
        File dir = new File(path);

        File[] files = dir.listFiles();

        if (files == null) {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(path);
            if (resource == null) {
                return 0;
            }
            dir = new File(resource.getFile());
            files = dir.listFiles();
        }

        if ((files != null) && dir.isDirectory()) {
            long count = 1;

            for (File file : files) {
                if (file.isDirectory()) {
                    count += countDirsInDirectory(file.getPath());
                }
            }
            return count;
        }

        return 0;
    }

    @Override
    public void copyTXTFiles(String from, String to) {
        return;
    }

    @Override
    public boolean createFile(String path, String name) {
        if ((path != null) && (name != null) && (path.length() > 0) && (name.length() > 0)) {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("");
            if (resource == null) {
                return false;
            }

            String newPath = resource.getFile() + "/" + path;

            File dir = new File(newPath);

            if (!dir.exists() && !dir.mkdir()) {
                return false;
            }

            File file = new File(newPath + "\\" + name);
            if (!file.exists()) {
                try {
                    return file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    @Override
    public String readFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource != null) {
            File file = new File(resource.getFile());

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder strBuilder = new StringBuilder();

                String line = reader.readLine();

                while (line != null) {
                    strBuilder.append(line);
                    line = reader.readLine();
                }

                return new String(strBuilder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
