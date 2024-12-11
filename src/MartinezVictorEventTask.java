public class MartinezVictorEventTask {
    private String text; //Descrpción de la tarea
    private boolean isCompleted; //Estado de la tarea (true: hecha, false: sin hacer)

    //Método para marcar la tarea como completada
    public void setCompleted(boolean set){
        isCompleted=set;
    }

    //Método toString para mostrar los detalles de la tarea.
    @Override
    public String toString() {
        return "MartinezVictorEventTask{" +
                "text='" + text + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
