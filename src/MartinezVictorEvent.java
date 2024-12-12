import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MartinezVictorEvent {
    private String title; //Título del evento
    private LocalDate date;//Fecha del evento (LocalDate de java.time)
    private MartinezVictorPriority priority; //priority: enum que define la prioridad del evento. Acotado a los siguientes valores (HIGH, MEDIUM, LOW)
    private ArrayList<MartinezVictorEventTask> task;

    //Constructor sin task
    public MartinezVictorEvent(String title, LocalDate date, MartinezVictorPriority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
    }
    //Constructor con todos los atributos
    public MartinezVictorEvent(String title, LocalDate date, MartinezVictorPriority priority, ArrayList<MartinezVictorEventTask> task) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.task = task;
    }

    //Getter titulo del evento
    public String getTitle() {
        return title;
    }
    //Getter array de tareas
    public ArrayList<MartinezVictorEventTask> getTask() {
        return task;
    }

    /**
     * Método toString para mostrar los datos del evento en formato [date] title | Priority
     * Incluye un listado de las tareas dentro del evento y un contador de tareas completadas
     */
    @Override
    public String toString() {
        String dateFormat = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); //Modifica el formato de la fecha
        // utilizando un patrón de la clase DateTimeFormatter, de esta manera, la fecha se muestra en el formato que ha introducido el usuario
        if (task!=null){
            return "["+dateFormat+"] "+title+" | Prioridad: " + priority.getPriority()+'\n'+
                    "Tareas ("+completedTasks()+"/"+task.size()+"):"+'\n'+showTasks();
        }else {
            return "["+dateFormat+"] "+title+" | Prioridad: " + priority.getPriority();
        }
    }

    //Método que permite mostrar las tareas en una lista ordenada
    public String showTasks(){
        String taskList = "";
        for (int t=0; t < task.size();++t){
            if (t != task.size()-1){
                taskList = taskList.concat("- "+task.get(t).toString()+'\n'); //Concatena una tabulación y salto de línea
            }else {
                taskList = taskList.concat("- "+task.get(t).toString()); //Concatena una tabulación sin salto de línea para el último elemento
            }
        }
        return taskList;
    }

    /**
     * Cuenta el número de tareas completadas del evento
     * @return número de tareas completadas
     */
    private int completedTasks(){
        int complete=0;
        for(MartinezVictorEventTask t : task){
            if (t.isCompleted()){
                ++complete;
            }
        }
        return complete;
    }
}