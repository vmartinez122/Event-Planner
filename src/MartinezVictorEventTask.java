public class MartinezVictorEventTask {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private String text; //Descrpción de la tarea
    private boolean isCompleted; //Estado de la tarea (true: hecha, false: sin hacer)

    public MartinezVictorEventTask(String text) {
        this.text = text;
        this.isCompleted = false;
    }

    public String getText() {
        return text;
    }

    //Método para marcar la tarea como completada
    public void setCompleted(boolean set){
        isCompleted=set;
    }

    //Método toString para mostrar los detalles de la tarea.
    @Override
    public String toString() {
        String sym;
        if(isCompleted){
            sym = ANSI_GREEN+"✔";
        }else {
            sym = ANSI_RED+"✘";
        }

        return "- "+ text +" "+ sym +ANSI_RESET;
    }
}
