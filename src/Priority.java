public enum Priority {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");
    private String priority;

    //TODO explicar constructor
    Priority(String priority){
        this.priority = priority;
    }

    // Método para obtener el valor de la prioridad
    public String getPriority() {
        return priority;
    }
}