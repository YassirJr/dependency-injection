package com.jee.dynamic_instanciation.presentation;

import com.jee.dynamic_instanciation.dao.IDao;
import com.jee.dynamic_instanciation.metier.IMetier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Presentation {
    public static void main(String[] args) throws Exception {
        Map<String, Object> yamlData = getYamlFileData();
        Map<String, String> classes = (Map<String, String>) yamlData.get("classes");
        String daoClassName = classes.get("dao");
        String metierClassName = classes.get("metier");
        IDao dao = (IDao) Class.forName(daoClassName).getConstructor().newInstance();
        IMetier metier = (IMetier) Class.forName(metierClassName).getConstructor().newInstance();
        Method method = metier.getClass().getDeclaredMethod("setDao", IDao.class);
        method.invoke(metier, dao);
        System.out.println(metier.calcul());
    }

    public static Map<String, Object> getYamlFileData() throws Exception {
        Map<String, Object> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("config.yaml"))) {
            String line;
            String currentKey = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.endsWith(":")) {
                    currentKey = line.substring(0, line.length() - 1).trim();
                    data.put(currentKey, new HashMap<String, String>());
                } else if (line.contains(":")) {
                    String[] parts = line.split(":");
                    String key = parts[0].trim();
                    String value = parts[1].trim().replace("'", "");
                    if (currentKey != null) {
                        ((Map<String, String>) data.get(currentKey)).put(key, value);
                    } else {
                        data.put(key, value);
                    }
                }
            }
        }
        return data;
    }
}