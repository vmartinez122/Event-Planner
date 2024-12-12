## Event-Planner
### PR01-Ejercicio 2 M03-UF2: Programación Estructurada
### Víctor Martínez
** **
**Descripción del proyecto:**

Este ejercicio, consiste en la creación de un programa de planificación de eventos, utilizando un ArrayList de eventos. Además, cada evento tiene la posibilidad de almacenar un listado de tareas.

Para navegar por este programa utilizamos un menú, donde el usuario puede interactuar con el Array y sus eventos:
```
[1] Añadir evento
[2] Borrar evento
[3] Listar eventos
[4] Marcar/desmarcar tarea de un evento como completada
[5] Salir
```
[1] Añade un nuevo evento a partir de los datos solicitados

[2] Elimina un evento a partir de su nombre

[3] Muestra una lista de los eventos por pantalla

[4] Permite marcar o desmarcar una tarea de un evento como completada 

[5] Cierra el programa

** **
**Clase Event:**

*La clase contiene datos del perfil de un usuario. En el caso de este programa, estos datos són cargados al principido del programa y no hay implementada una función para que el usuario introduzca su propio perfil*
- ***title:*** Título del evento
- ***date:*** Fecha del evento (en formato LocalDate)
- ***priority:*** Enum con los valores HIGH, MEDIUM o LOW
- ***task:*** Array almacenando los objetos EventTask de cada evento

**Clase EventTask:**

*La clase contiene datos del perfil de un usuario. En el caso de este programa, estos datos són cargados al principido del programa y no hay implementada una función para que el usuario introduzca su propio perfil*
- ***text:*** Descrpción/nombre de la tarea
- ***isCompleted:*** Estado de la tarea (true: hecha, false: sin hacer)
