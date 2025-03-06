package game.entities;

public enum EntitiesRepresentation {
    TREE("\uD83D\uDFEB"),      // 🟫
    GRASS("\uD83D\uDFE9"),     // 🟩
    ROCK("\u2B1B"),                // ⬛
    HERBIVORE("\uD83D\uDC10"), // 🐐
    PREDATOR("\uD83D\uDC3A");  // 🐺

    private final String entityRepresentation;

    EntitiesRepresentation(String entityRepresentation) {
        this.entityRepresentation = entityRepresentation;
    }

    public String getEntityRepresentation() {
        return entityRepresentation;
    }

}
