public class MartinezVictorMain {
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
            [5] Salir: se terminará el programa
         */

        System.out.println("""
                Bienvenido a Event Planner. Seleccione una opción:
                [1] Añadir evento
                [2] Borrar evento
                [3] Listar eventos
                [4] Marcar/desmarcar tarea de un evento como completada
                [5] Salir
                """);
    }

}