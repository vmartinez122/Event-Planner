public enum MartinezVictorPriority {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");
    private String priority; //Almacenrá el nombre del constructor en letras minúsculas (primera mayúscula)
    // una vez se llame al constructor

    //Constructor de Priority, sirve para dar un valor adicional al enum, en este caso el String priority
    MartinezVictorPriority(String priority){
        this.priority = priority;
    }

    // Método para obtener el valor de la prioridad
    public String getPriority() {
        return priority;
    }
}