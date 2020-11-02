# ProyectoConcesionario
Proyecto académico de desarrollo de software colaborativo de la asignatura Proyecto profesional I.

Documentación del proyecto  

## Arquitectura

### Responsabilidades de las capas lógicas del sistema.

    presentation:

        views: Son interfaces gráficas de usuario.
            - Deben presentar los modelos.
            - Deben interactuar con el usuario.
            - Deben delegar en los presentadores el tratamiento de las iniciativas del usuario.

        presenters: Son controladores de lógica de presentación.
            - Deben gestionar la lógica de presentación y el estado de las vistas.
            - Deben delegar en los controladores de lógica de negocio la ejecución de la operación, a travez de los dtos de entrada.
            - Deben delegar en los dto's la validaciónes de lo campos.
            - Deben delegar en las vistas la presentación de los dtos de salida.

        business_controllers: Son controladores de lógica de negocio.
            - Deben desarrollar las operaciones de negocio que conllevan a ejecución de una operación.
            - Construyen las entidades a partir de los dtos de entrada o pueden delegar en los dtos de entrada la contrucción de las entidades.
            - Delega en los dtos la contruccion de los dtos de salida a partir de las entidades.
            - Delega en los repositorios el acceso y procesamiento de operaciones de persistencia en base de datos.
            - Delega en los data_services la ejecución de operaciones avanzadas/complejas en base de datos.
            - Delega en los business_services la ejecución de procesos avanzados/complejos de caracter genérico de la capa de negocio.
   
            Exceptions: Clases que epecializan excepciones comunes ocurrentes en la capa de negocio.
  
         dtos: Son objetos de transeferencia de datos entre la capa de presentación - la capa de negocio - la capa de persistencia. 
            - deben poder construirse a partir de entidades de entrada. 
            - deben poder validarse a partir de regoger las restricciones de construcción de la entidad.
            - deben definir las restricciones en la construcción de su estado.

         services: Clases avanzadas de apoyo, fachadas de librerias de terceros para dar soporte a los procesos de negocio.

         repositories: 
            - Módulos de acceso a base de datos mediante la implementación del patrón DAO.
            - Deben desarrollar operaciones CRUD sobre base de datos.
            - Deben desarrollar operaciones de consulta sobre base de datos.
            - data_services: Clases avanzadas de apoyo a la capa de negocio para la gestion de operaciones complejas en la base de datos.

### Tratamiento de errores

    El tratamiento de errores no se ejerce de manera centralizada.
    Los controladores de la capa de negocio utilizarán excepciones para gestionr las violaciones de dominio y/o a las reglas de negocio.
    Los daos gestionaran los errores de la capa de persistenca como excepciones runtime, delegando al cliente el tratamiento de la excepción.
    
    Tipos de excepciones:
    - NotFoundException: lanzadas en la capa de negocio por la solicitud de deleteado o modificaciòn de entidad inexistente.
    - ConflictException: lanzadas en la capa de negocio por la solicitud de inserción u actualizacion de entidad que pose atr. unicos.
    - ForbiddenException: lanzadas en la capa de negocio por la solicitud de una operación no permitida. relacionad con permisos y seguridad.
    - ConstraintViolationException: lanzadas en la capa dao por violacion del modelo relacional.

### Persistencia

    - La gestión de la persistencia se realizará utilizando JDBC.
    - La capa de persistencia se implementará con la utilización del patrón DAO en combinación con otros patrones tales como DTO, Absract Factory y Factory method.
    - Se dispone de un servicio para poblar la base de datos con datos cargados desde un fichero db.yml ("YAML") con datos de prueba para depuración, desarrollo y pruebas.
  
### Perfiles

    - Se manejaran dos contextos de persistencia basado en perfiles.
    - Perfil de desarrollo: base de datos relacional en memoria H2 para pruebas y depuración.
    - Perfil de producción: base de datos relacional MariaDB, desplegada en el nodo en el que se desplegará el sistema.
