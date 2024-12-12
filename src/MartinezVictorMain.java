import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.DateTimeException;

public class MartinezVictorMain {
    Scanner input = new Scanner(System.in);
    final String ANSI_RED = "\u001B[31m"; //Color rojo
    final String ANSI_YELLOW = "\u001B[33m"; //Color amarillo
    final String ANSI_RESET = "\u001B[0m"; //Devolver color predeterminado

    ArrayList<MartinezVictorEvent> events = new ArrayList<>(); //ArrayList donde almacenaremos todos los eventos

    public static void main(String[] args) { //Utilizamos el método main únicamente para llamar al método que muestra el menú
        MartinezVictorMain programa = new MartinezVictorMain();
        programa.menu();
    }

    /**
     * Método principal del programa, muestra el menú con el que interactuamos con el programa
     * Cada opción en el menú, corresponde a una función del programa
     * El menú se sequirá mostrando hasta que se seleccione la opción 5 (Salir)
     */
    private void menu(){
        boolean exit = false; //Variable que cierra el menú
        do {
            System.out.println(ANSI_YELLOW+"Bienvenido a Event Planner. Seleccione una opción:\n"+ANSI_RESET+"""
                    [1] Añadir evento
                    [2] Borrar evento
                    [3] Listar eventos
                    [4] Marcar/desmarcar tarea de un evento como completada
                    [5] Salir""");
            switch (intFromConsole(1,5)){
                case 1: //Añadir evento
                    System.out.println(ANSI_YELLOW+"Añadir evento:"+ANSI_RESET);
                    addEvent();
                    break;
                case 2: //Borrar evento
                    System.out.println(ANSI_YELLOW+"Borrar un evento:"+ANSI_RESET);
                    deleteEvent();
                    break;
                case 3: //Listar evento
                    System.out.println(ANSI_YELLOW+"Lista de eventos:"+ANSI_RESET);
                    listEvent();
                    break;
                case 4: //Marcar o desmarcar tareas
                    System.out.println(ANSI_YELLOW+"Marcar tareas:"+ANSI_RESET);
                    markTask();
                        //TODO marcar tareas
                    break;
                case 5: //Salir del programa
                    System.out.println("Saliendo del programa...");
                    exit = true;
                    break;
                default:
                    //Valor erróneo
                    break;
            }
        } while(!exit);
    }

    /**
     * Método que pregunta al usuario varios datos para añadir un nuevo objeto de la clase Event a nuestro array
     * Cada dato hace uso de varios métodos para comprobar que tengan un formato correcto al añadirse
     */
    private void addEvent(){
        String title;
        LocalDate date;
        MartinezVictorPriority priority;
        ArrayList<MartinezVictorEventTask> task;

        System.out.println("Título del evento:");
        title = stringFromConsole();
        System.out.println("Fecha (DD/MM/YYYY):");
        date= esFechaValida();
        System.out.println("Prioridad | 1-Low 2-Medium 3-High:");
        priority = setPriority();
        task = addTask();
        if (task.isEmpty()){ //Si el usuario no ha introducido tareas, llamaremos al constructor que excluye tareas
            events.add(new MartinezVictorEvent(title, date, priority)); //Constructor sin task
        }else{
            events.add(new MartinezVictorEvent(title, date, priority,task)); //Constructor con task
        }
        System.out.println("Añadido evento: " +events.getLast().getTitle()+'\n'); // Muestra el último evento creado (el que acabamos de crear)
    }

    /**
     * Pide al usuario una fecha con el formato "DD/MM/YYYY", de esta manera, no hemos de preguntar al ususario 3 datos distintos
     * El input del usuario es separado en un array, con el cuál intentamos construir un objeto LocalDate
     * Realizar esto puede generar 2 excepciones, NumberFormatException al realizar si String del usuario no puede ser parseado a integer
     *  y DateTimeException si la fecha introducida no es una fecha real.
     * @return LocalDate con el formato correcto
     */
    public LocalDate esFechaValida() {
        String errMsg = "Error. Formato de fecha inválido (DD/MM/YYYY)"; //Mensaje de error variable dependiendo del tipo de error
        String fecha; //Input del usuario en formato String
        String[] parts; //Input del usuario separado en varios Strings
        do {
            fecha = stringFromConsole();
            parts = fecha.split("/"); //Separamos el input del usuario en un array con los valores entre "/"
            //Las posiciones del array corresponden a: parts[0] = day, parts[1] = month, parts[2] = year
            if (parts.length == 3) { //Comnprueba que el usuario no haya añadido datos de más
                try {
                    // Intentamos crear y devolver un LocalDate con los valores dados (almacenados en el array parts)
                    return LocalDate.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
                } catch (NumberFormatException e) {
                    // Si el usuario introduce un valor entre "/" que no sea una integer, se produce una NumberFormatException
                    // Con esta catch box, capturamos el error y mostramos un mensaje por pantalla.
                    errMsg = "Error. Introduce fecha con valores numéricos (DD/MM/YYYY)";
                } catch (DateTimeException e) {
                    // Si se lanza excepción, la fecha no es válida
                    errMsg = "Error. Fecha inexistente";
                }
            }
            System.out.println(ANSI_RED+errMsg+ANSI_RESET); //Muestra los distintos mensajes de error
        }while (true);
    }

    /**
     * Da a escojer entre el 1 y el 3 para escojer el nivel de prioridad
     * @return nivel de Priority
     */
    private MartinezVictorPriority setPriority(){
        do {
            switch (intFromConsole(1,3)){
                case 1:
                    return MartinezVictorPriority.LOW;
                case 2:
                    return MartinezVictorPriority.MEDIUM;
                case 3:
                    return MartinezVictorPriority.HIGH;
                default:
                break;
            }
        }while (true);
    }

    /**
     * Pregunta el nombre de los EventTask a añadir, hasta que el usuario no proporciona ningún nombre
     * Cada vez que se introduce un nombre, se añade el EventTask a un ArrayList
     * @return ArrayList con todas las EventTask añadidas
     */
    private ArrayList<MartinezVictorEventTask> addTask(){
        MartinezVictorEventTask newTask; //Tarea a agregar
        ArrayList<MartinezVictorEventTask> task = new ArrayList<>(); //Lista de tareas
        do {
            System.out.println("Añadir tareas (Dejar en blanco para salir):");
            newTask = new MartinezVictorEventTask(input.nextLine());
            if (!newTask.getText().isBlank()){ //Si el input no está en blanco, se añade la tarea
                task.add(newTask);
            }else {
                if (!task.isEmpty()){ //Muestra el número de tareas por pantalla
                    System.out.println("Se han añadidido "+task.size()+" tareas");
                }else{
                    System.out.println("No se han añadido tareas");
                }
                return task;
            }
        }while (true);
    }

    /**
     * Pide al usuario el título de un evento. Si el evento existe, lo elimina del array y lo notifica por pantalla
     */
    private void deleteEvent(){
        int del; //Posición evento a borrar
        System.out.println("Título del evento a borrar:");
        del = findEvent(input.nextLine()); //Utilizamos el método find event para encontrar la posición del evento
        // introducido por el usuario
        //El usuario también puede introducir valores en blanco en caso de no querer borrar nada
        if(del!=-1){
            System.out.println("Eliminado evento: " +events.get(del).getTitle());
            events.remove(del);
        } else {
            System.out.println("No se ha eliminado ningún evento. Volviendo al menú.");
        }
    }

    //Bucle foreach que imprime todos los eventos por pantalla, utilizando su método toString()
    private void listEvent(){
        for (MartinezVictorEvent event : events){
            System.out.println(event); //El método toString, puede ser llamado directamente imprimiendo el "objeto"
        }
    }

    private void markTask(){
        int edit; //Posición evento a editar
        MartinezVictorEvent toEdit; //Evento a editar
        String findTask; //Nombre tarea a buscar

        System.out.println("Título del evento a editar:");
        edit = findEvent(input.nextLine()); //Utilizamos el método find event para encontrar la posición del evento
        if(edit!=-1){
            toEdit=events.get(edit); //Obtenemos el evento a editar
            if (!(toEdit.getTask()==null)){ //Si el evento no tiene tareas y intentamos mostrarlas, ocurrirá un NullPointerException
                System.out.println("Evento a marcar/desmarcar:\n"+toEdit.showTasks()); //El método showTasks(), muestra los toString() de
                // cada tarea en el array tasks. Tambíen podriamos utilizar el getter de task, y llamar a task.toString, pero
                // de esta manera, tenemos el mismo formato que utilizo en listEvent()
                findTask = input.nextLine();
                //Utilizamos el mismo bucle que findEvent() para buscar el evento, con un tipo de dato distinto, así que no puedo
                // reutilizar la función
                for (MartinezVictorEventTask task : toEdit.getTask()) { //Recorremos el array para encontrar un título igual al introducido por el usuario
                    if (task.getText().equals(findTask)) {
                        task.setCompleted(!task.isCompleted()); //Obtiene el valor booleano de isCompleted y lo revierte con su setter
                        System.out.println("Editado "+toEdit.getTitle()+": "+task);
                        return; //En este caso, return actúa como un break, pero saliendo de la función, no solo el bucle for
                    }
                }
            }else{
                System.out.println(ANSI_RED+"El evento "+toEdit.getTitle()+" no tiene tareas."+ANSI_RESET);
            }
        }
    }

    /**
     * Busca el título de un evento iterando sobre el ArrayList de eventos
     * @param find Título del evento a buscar
     * @return posición del evento a buscar, si no se encuentra, devuelve -1
     */
    private int findEvent(String find){
        int i=0; //El iterador no se declara junto con el bucle ya que utilizo un foreach
        for (MartinezVictorEvent event : events) { //Recorremos el array para encontrar un título igual al introducido por el usuario
            if (event.getTitle().equals(find)) { //Comparamos el valor introducido por el usuario por el del título
                //Al comprobar objetos como String, es recomendable utilizar la función "equals()" en vez de "==", ya
                // que el segundo no funcionaría si fueran distintas instancias del objeto (String). Aunque tengan el mismo valor
                return i;
            }
            ++i;
        }
        System.out.println(ANSI_RED+"El evento no existe. Introduce título exacto de un evento."+ANSI_RESET);
        return -1; //Evento no encontrado
    }

    /**
     * Verifica el input del usuario, para verificar que és una integer entre un rango de valores
     * @param min Valor mínimo de la integer
     * @param max Valor máximo de la integer
     * @return Integer validada, devuelve -1 si el input és inválido
     */
    private int intFromConsole(int min, int max) {
        int x;
        if (input.hasNextInt()) {
            x = input.nextInt();
            if (x >= min && x <= max) {
                input.nextLine(); //Limpiar búfer
                return x;
            }
        }
        input.nextLine(); //Limpiar búfer
        System.out.println(ANSI_RED+"Valor inválido. Introduce un número [" + min + " - " + max + "]"+ANSI_RESET);
        return -1; //Si el número es inválido, el método devuelve -1, para que se vuelva a mostrar el menú
    }

    /**
     * Comprueba que el usuario no inserte un string vacío, se repite hasta que el string tenga el formato correcto
     * @return String validado
     */
    private String stringFromConsole(){
        String x;
        do {
            x = input.nextLine();
            if(!x.isBlank()){
                return x;
            }
            System.out.println(ANSI_RED+"Error. Este campo no puede estar vacío."+ANSI_RESET);
        }while (true);
    }
}