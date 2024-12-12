import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.DateTimeException;

public class MartinezVictorMain {
    Scanner input = new Scanner(System.in);
    final static String ANSI_RED = "\u001B[31m"; //Color rojo
    final static String ANSI_RESET = "\u001B[0m"; //Devolver color predeterminado
    ArrayList<MartinezVictorEvent> events = new ArrayList<>(); //ArrayList donde almacenaremos todos los eventos

    public static void main(String[] args) {
        MartinezVictorMain programa = new MartinezVictorMain();
        programa.inicio();
    }

    private void inicio(){
        menu();
    }

    private void menu(){
        /*
        Bienvenido a Event Planner. Seleccione una opción
            [1] Añadir evento: permite al usuario introducir obligatoriamente sus datos básicos (title, date, priority).
            Opcionalmente permítele introducir las tareas del evento. Cuando haya acabado regresa al menú
            NOTA: Revisa los apartados de ayuda para saber como crear un tipo LocalDate a partir de un día, un mes
            y un año...y poder comprobar que esa fecha existe.

            [2] Borrar evento: permite al usuario introducir el title del evento. Por simplicidad supón que para poder
            borrar un evento el texto introducido por el usuario debe coincidir exactamente con el titulo de uno de los
            eventos. Confirma al usuario si la acción ha podido realizarse. Regresa al menú.

            [3] Listar eventos: muestra los eventos registrados (usa método toString de Event)

            [4] Marcar/desmarcar tarea de un evento como completada: permite al usuario introducir el title del
            evento sobre el que interactuar. Por simplicidad supón que el texto introducido por el usuario debe
            coincidir exactamente con el titulo de uno de los eventos. Si el titulo del evento existe, lista sus tareas
            (usa método toString de EventTask) y el usuario indica la tarea sobre la que quiere interactuar. Informa
            al usuario de la acción realizada. Regresa al menú
         */
        boolean exit = false; //Variable que cierra el menú
        do {
            System.out.println("""
                    Bienvenido a Event Planner. Seleccione una opción:
                    [1] Añadir evento
                    [2] Borrar evento
                    [3] Listar eventos
                    [4] Marcar/desmarcar tarea de un evento como completada
                    [5] Salir""");
            switch (intFromConsole(1,5)){
                case 1: //Añadir evento
                    addEvent();
                    break;
                case 2: //Borrar evento
                    deleteEvent();
                    break;
                case 3: //Listar evento
                    listEvent();
                    break;
                case 4: //Marcar desmarcar tareas

                    break;
                case 5: //Salir del porgrama
                    System.out.println("Saliendo del programa...");
                    exit = true;
                    break;
                default:
                    //Valor erróneo
                    break;
            }
        } while(!exit);
    }

    private void addEvent(){
        System.out.println("Título del evento:");
        String title = stringFromConsole();
        System.out.println("Fecha (DD/MM/YYYY):");
        LocalDate date= esFechaValida();
        System.out.println("Prioridad 1-Low 2-Medium 3-High:");
        Priority priority = setPriority();
        ArrayList<MartinezVictorEventTask> task = addTask();
        if (task == null){
            events.add(new MartinezVictorEvent(title, date, priority));
        }else{
            events.add(new MartinezVictorEvent(title, date, priority,task));
        }
        System.out.println("Añadido evento: " +events.getLast().getTitle()); //events.size()-1
    }

    /**
     *
     * @return
     */
    private Priority setPriority(){
        do {
            switch (intFromConsole(1,3)){
                case 1:
                    return Priority.LOW;
                case 2:
                    return Priority.MEDIUM;
                case 3:
                    return Priority.HIGH;
                default:
                break;
            }
        }while (true);
    }
    private ArrayList<MartinezVictorEventTask> addTask(){
        ArrayList<MartinezVictorEventTask> task = new ArrayList<>();
        do {
            System.out.println("Añadir tareas (Dejar en blanco para salir):");
            MartinezVictorEventTask newTask = new MartinezVictorEventTask(input.nextLine());
            if (!newTask.getText().isBlank()){
                task.add(newTask);
            }else {
                return task;
            }
        }while (true);
    }

    private void deleteEvent(){
        int i = 0;
        for (MartinezVictorEvent event : events){
            System.out.println("Título del evento a borrar:");
            if (event.getTitle() == stringFromConsole()){
                System.out.println("Eliminado evento: " +events.get(i).getTitle()); //events.size()-1
                events.remove(i);
                return;
            }
            ++i;
        }
        System.out.println(ANSI_RED+"El evento no existe. Introduce título exacto de un evento."+ANSI_RESET);
    }

    private void listEvent(){
        for (MartinezVictorEvent event : events){
            System.out.println(event);
        }
    }

    /**
     * Verifica el input del usuario, para verificar que és una integer
     * @param min Valor mínimo de la integer
     * @param max Valor máximo de la integer
     * @return Integer validada, devuelve -1 si el input és inválido
     */
    private int intFromConsole(int min, int max) {
        if (input.hasNextInt()) {
            int x = input.nextInt();
            if (x >= min && x <= max) {
                input.nextLine(); //Limpiar búfer
                return x;
            }
        }
        input.nextLine(); //Limpiar búfer
        System.out.println(ANSI_RED+"Valor inválido. Introduce un número [" + min + " - " + max + "]"+ANSI_RESET);
        return -1; //Si el número es inválido, el método devuelve -1, para que se vuelva a mostrar el menú
    }

    private String stringFromConsole(){
        do {
            String x = input.nextLine();
            if(!x.isBlank()){
                return x;
            }
            System.out.println(ANSI_RED+"Error. Este campo no puede estar vacío."+ANSI_RESET);
        }while (true);
    }

    /**
     *
     * @return
     */
    public LocalDate esFechaValida() {
        String errMsg = "Error. Formato de fecha inválido (DD/MM/YYYY)"; //
        do {
            String fecha = stringFromConsole();
            String[] parts = fecha.split("/");
            if (parts.length == 3) {
                try {
                    /*
                    parts[0] = day
                    parts[1] = month
                    parts[2] = year
                     */
                    // Intentamos crear un LocalDate con los valores dados
                    return LocalDate.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
                } catch (NumberFormatException e) {
                    // Se lanza excepción, si no se puede parsear String a integer
                    errMsg = "Error. Introduce fecha con valores numéricos (DD/MM/YYYY)";
                } catch (DateTimeException e) {
                    // Si se lanza excepción, la fecha no es válida
                    errMsg = "Error. Fecha inexistente";
                }
            }
            System.out.println(ANSI_RED+errMsg+ANSI_RESET);
        }while (true);
    }
}