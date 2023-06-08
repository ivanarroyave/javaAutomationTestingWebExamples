#language: es
Característica: Inicios de sesión
    Como un usuario registrado del sistema
    nececito validar las operaciones de logueo y disponibilidad al sitio web
    para poder tener seguridad en el perfil de los usuarios

    Escenario: login exitoso
        Dado que el usuario está en la página de inicio de sesión con el correo de usuario "eve.holt@reqres.in" y la contraseña "cityslicka"
        Cuando el usuario hace una petición de inicio
        Entonces el usuario deberá ver un código de respuesta exitoso y un token de respuesta
