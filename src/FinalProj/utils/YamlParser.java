package FinalProj.utils;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Using external library : SnakeYaml
 */
public class YamlParser {
    private final Yaml yamlizer = new Yaml(new Constructor(Data.class));
    private List<Data> profiles = new ArrayList<>();
    public static class Data
    {
        public String name;
        public String savedScene;
        public int guilt = 0;
        public int resolve = 0;

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", savedScene='" + savedScene + '\'' +
                    ", guilt=" + guilt +
                    ", resolve=" + resolve +
                    '}';
        }
    }

    public Data[] getProfiles()
    {
        return profiles.toArray(new Data[3]);
    }

    YamlParser(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if(files == null)
        {
            System.out.println("Cannot find profile directory");
            return;
        }
        profiles = Arrays.stream(dir.listFiles())
                 .map(f -> {
                     try
                     {
                         return (Data) yamlizer.load(new FileInputStream(f));
                     } catch (FileNotFoundException e)
                     {
                         e.printStackTrace();
                     }
                     return null;
                 }).collect(Collectors.toList());


    }


}


