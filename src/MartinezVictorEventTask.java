public class MartinezVictorEventTask {
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";

    private String text; //Descripción de la tarea
    private boolean isCompleted; //Estado de la tarea (true: hecha, false: sin hacer)

    //Constructor de EventTask, las tareas nuevas, siempre se marcan como no completadas
    public MartinezVictorEventTask(String text) {
        this.text = text;
        this.isCompleted = false;
    }

    //Getter de text (Descripción de la tarea)
    public String getText() {
        return text;
    }
    //Getter del valor booleano de la compleción de la tarea
    public boolean isCompleted() {
        return isCompleted;
    }

    //Método para marcar la tarea como completada (Setter)
    public void setCompleted(boolean set){
        isCompleted=set;
    }

    /**
     * Método toString para mostrar los detalles de la tarea
     * Para mostrar si la tarea esta completada o no, utiliza los carácteres "✔" y "✘" con distintos colores
     */
    @Override
    public String toString() {
        String sym;
        if(isCompleted){
            sym = ANSI_GREEN+"✔";
        }else {
            sym = ANSI_RED+"✘";
        }

        return text +" "+ sym +ANSI_RESET;
    }
}