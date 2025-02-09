package Entities;

public enum EntitiesRepresentation {
    TREE("\uD83D\uDFEB"),
    GRASS("\uD83D\uDFE9"),
    ROCK("⬛"),
    HERBIVORE("\\U0001F410"),
    PREDATOR("\\U0001F43A");

    private final String entityRepresentation;

    private EntitiesRepresentation(String entityRepresentation) {
        this.entityRepresentation = entityRepresentation;
    }

    public String getEntityRepresentation() {
        return entityRepresentation;
    }

}
