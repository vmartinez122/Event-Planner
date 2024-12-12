public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");
    private String priority;

    //Constructor implícitamente privado
    Priority(String priority){
        this.priority = priority;
    }

    // Método para obtener el valor de la prioridad
    public String getPriority() {
        return priority;
    }
}