package Utils.Actions;

import Entities.Dynamic.Herbivore;
import Entities.Dynamic.Predator;
import Entities.Entity;
import Entities.Static.Grass;
import Entities.Static.Rock;
import Entities.Static.Tree;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigLoader {

    public static Config loadConfig(String filename) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filename), Config.class);

    }

    public static Map<Class<? extends Entity>, Double> getEntitiesBalanceFromConfig(String filename) {

        Map<Class<? extends Entity>, Double> entityDoubleMap = new HashMap<>();
        try {
            Config config = loadConfig(filename);
            entityDoubleMap.put(Herbivore.class,config.balance.herbivore);
            entityDoubleMap.put(Predator.class,config.balance.predator);
            entityDoubleMap.put(Grass.class,config.balance.grass);
            entityDoubleMap.put(Tree.class,config.balance.tree);
            entityDoubleMap.put(Rock.class,config.balance.rock);

        } catch (IOException e) {
            System.out.println("Конфиг не загружен");
            throw new RuntimeException(e);
        }

        return entityDoubleMap;

    }
}
