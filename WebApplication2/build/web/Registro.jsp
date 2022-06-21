<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrarse</title>
        <link rel="stylesheet" href="https://necolas.github.io/normalize.css/8.0.1/normalize.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet"> 
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/navegacion.css">
        <link rel="stylesheet" href="css/registro.css">


    </head>
    <body>
        <div class="nav-bg">
            <nav class="navegacion-principal contenedor">
                <a href="index.html"><img src="logo1-removebg-preview.png" alt="Logo" class="element_nav"></a>
            </nav>
        </div>
        <main>
            <form method="post" name="formulario" action="Registro"  class="formulario padre" id="formulario">


                <!--Grupo Nombre-->
                <div class="formulario__grupo" id="grupo__nombre">
                    <label for="nombre" class="formulario__label"> Nombre </label>
                    <div class="formulario__grupo-input">
                        <input type="text" class="formulario__input" name="nombre" id="nombre" placeholder="Nombre">
                        <i class="formulario__validacion-estado fas fa-times-circle" ></i>
                    </div>
                    <p class="formulario__input-error"> El nombre debe contener entre 4 y 16 digitos y solo puedes incluir letras, numeros y guion bajo. </p>
                </div>

                <!--Grupo Apellido-->
                <div class="formulario__grupo" id="grupo__apellido">
                    <label for="apellido" class="formulario__label"> Apellido </label>
                    <div class="formulario__grupo-input">
                        <input type="text" class="formulario__input" name="apellido" id="apellido" placeholder="Apellido">
                        <i class="formulario__validacion-estado fas fa-times-circle" ></i>
                    </div>
                    <p class="formulario__input-error"> El apellido debe contener entre 4 y 16 digitos y solo puedes incluir letras, numeros y guion bajo. </p>
                </div>
                <!--Grupo Usuario-->
                <div class="formulario__grupo " id="grupo__usuario">
                    <label for="usuario" class="formulario__label"> Usuario </label>

                    <div class="formulario__grupo-input">
                        <input type="text" class="formulario__input" name="usuario" id="usuario" placeholder="Usuario">
                        <i class="formulario__validacion-estado fas fa-times-circle" ></i>
                    </div>
                    <p class="formulario__input-error"> El usuario debe contener entre 4 y 16 digitos y solo puedes incluir letras, numeros y guion bajo. </p>
                </div>
                <!--Grupo Correo Electronico-->
                <div class="formulario__grupo" id="grupo__correo">
                    <label for="correo" class="formulario__label"> Correo Electronico </label>
                    <div class="formulario__grupo-input">
                        <input type="email" class="formulario__input" name="correo" id="correo" placeholder="Correo Electrónico">
                        <i class="formulario__validacion-estado fas fa-times-circle" ></i>
                    </div>
                    <p class="formulario__input-error"> El correo solo puede incluir letras, numeros y guion bajo. </p>
                </div>

                <!--Grupo Contraseña-->
                <div class="formulario__grupo" id="grupo__password">
                    <label for="password" class="formulario__label"> Contraseña </label>
                    <div class="formulario__grupo-input">
                        <input type="password" class="formulario__input" name="password" id="password">
                        <i class="formulario__validacion-estado fas fa-times-circle" ></i>
                    </div>
                    <p class="formulario__input-error"> La contraseña debe contener entre 8 y 16 digitos. </p>
                </div>

                <!--Grupo Contraseña2-->
                <div class="formulario__grupo" id="grupo__password2">
                    <label for="password2" class="formulario__label"> Repetir Contraseña  </label>
                    <div class="formulario__grupo-input">
                        <input type="password" class="formulario__input" name="password2" id="password2">
                        <i class="formulario__validacion-estado fas fa-times-circle" ></i>
                    </div>
                    <p class="formulario__input-error"> Las contraseñas deben ser identicas  </p>
                </div>


                <div class="formulario__mensaje" id="formulario__mensaje">
                    <p><i class="fas fa-exclamation-triangle"></i> <b> Error </b> Por favor rellena el formulario correctamente </p>

                </div>

                <div class=" formulario__grupo formulario__grupo-btn-enviar">
                    <input type="submit" value="Enviar">
                    <p class="formulario__mensaje-exito" id="formulario__mensaje-exito"> Formulario enviado con éxito </p>
                </div>
                <div class="formulario__grupo formulario__grupo-btn-a">
                    <p>Si ya tienes una cuenta <a href="InicioSesion.jsp">inicia sesion</a></p>
                </div>
            </form>
        </main>
        
        <script src="registro.js"></script>

    </body>
</html>