API REST con Spring boot


Definición de verbos HTTP

- GET: Realiza una petición a un recurso específico. No permite el envió de datos a excepción si dichos datos se envían como parámetro en la Url que realiza la petición.
- POST: este puede enviar datos al servidor por medio del cuerpo (body)
- DELETE: permite eliminar un recurso específico.
- PATH:Modifica parcialmente un recurso en el servidor  en lugar de reemplazar completamente el recurso como lo hace PUT
- PUT:se utiliza para la actualización de información existente, es semejante a un UPDATE de datos a nivel de base de datos
- HEAD:Se utiliza para obtener los encabezados de la respuesta del servidor sin el cuerpo de la respuesta. Es similar a GET, pero la respuesta no contiene el cuerpo, solo los encabezados. Es útil para comprobar la existencia de un recurso o para obtener metadatos.

  
Anotaciones

- @RestController: Se usa en spring para indicar que una clase va a manejar solicitudes HTTP y devolver respuestas/datos en formato JSON o XML, usado en el controller
- @RequestMapping("item") : Se utiliza para mapear solicitudes HTTP a métodos específicos en un controlador, su propósito es definir la ruta o el endpoint que las solicitudes HTTP deben seguir para ser manejadas por el controlador o el método correspondiente. @RequestMapping("item") establece que todas las rutas dentro de ItemController comenzarán con "/item"
- @Autowired: se utiliza para habilitar la inyección de dependencias de manera automática. Esto significa que Spring se encarga de crear e insertar automáticamente los objetos que una clase necesita para funcionar sin necesidad de declarar un constructor



clase GroceryItem

constructores:  Se definen dos constructores para la clase de GroceryItem
- GroceryItem(): es un conatructor vacio, no recibe parametros, indicado para cuando se quiere crear una instancia de GroceryItem sin dar valores iniciales para sus atributos
- GroceryItem(String id, String name, int quantity, String category) : constructor que recibe cuatro parametros.  usado cuando se quiere crear una instancia de GroceryItem , asignando los valores dados a los atributos de la instancia


  
Método toString(): método para personalizar como se muestra un objeto en forma de cadena


etiqueta
- @Override: se utiliza para indicarle al compilador que el método anotado está sobrescribiendo un método en una superclase como pasa con el método toString() de la clase object

