import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MartinezVictorEvent {
    private String title; //Título del evento
    private LocalDate date;//Fecha del evento (LocalDate de java.time)
    private Priority priority; //priority: enum que define la prioridad del evento. Acotado a los siguientes valores (HIGH, MEDIUM, LOW)
    private ArrayList<MartinezVictorEventTask> task;

    //Constructor sin task
    public MartinezVictorEvent(String title, LocalDate date, Priority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
    }
    //Constructor con todos los atributos
    public MartinezVictorEvent(String title, LocalDate date, Priority priority, ArrayList<MartinezVictorEventTask> task) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.task = task;
    }

    //Getter titulo del evento
    public String getTitle() {
        return title;
    }

    /**
     * Método toString para mostrar los detalles básicos del evento (title, date, priority) pero también info de estado de las
     *  tareas con cantidad de tareas completadas vs tareas totales.
     */
    //TODO  Info de estado de las tareas con cantidad de tareas completadas vs tareas totales.
    @Override
    public String toString() {
        String dateFormat = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); //Modifica el formato de la fecha
        // utilizando un patrón de la clase DateTimeFormatter, de esta manera, la fecha se muestra en el formato que ha introducido el usuario
        if (task!=null){
            return "["+dateFormat+"] "+title+" | Prioridad: " + priority.getPriority()+'\n'+
                    "\tTareas:"+'\n'+showTasks();
        }else {
            return "["+dateFormat+"] "+title+" | Prioridad: " + priority.getPriority();
        }
    }
    //Constructor helper que permite mostrar las tareas en una lista
    private String showTasks(){
        String taskList = "";
        for (int t=0; t < task.size();++t){
            if (t != task.size()-1){
                taskList = taskList.concat("\t"+t+"\n"); //Concatena una tabulación y salto de línea
            }else {
                taskList = taskList.concat("\t"+t); //Concatena una tabulación sin salto de línea para el último elemento
            }
        }
        return taskList;
    }
}