package Utils;

import Entities.Entity;
import Entities.Static.Grass;

public class Cell {

    // А нужен ли мне отдельный класс ячеек, если я храню координаты в объекте
    // флаг проходимости можно сделать при поиске пути, назначая тот или иной вес и пропуская статические объекты
    Coordinates coords;
    Entity entity;
    boolean isWalkable = false;

    public Cell(Entity entity) {
        this.coords = entity.getEntityCoords();
        this.entity = entity;

        if (entity instanceof Grass) {
            this.isWalkable = true;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return coords.equals(obj);
    }

    @Override
    public int hashCode() {
        return coords.hashCode();
    }
}
