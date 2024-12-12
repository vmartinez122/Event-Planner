public enum MartinezVictorPriority {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");
    private String priority;

    //TODO explicar constructor
    MartinezVictorPriority(String priority){
        this.priority = priority;
    }

    // Método para obtener el valor de la prioridad
    public String getPriority() {
        return priority;
    }
}