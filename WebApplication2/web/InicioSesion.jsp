<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/navegacion.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/Sesion.css">
        <title>Iniciar Sesion</title>
    </head>

    <body>
        <div class="nav-bg">
            <nav class="navegacion-principal contenedor">
                <a href="index.html"><img src="logo1-removebg-preview.png" alt="Logo" class="element_nav"></a>
            </nav>
        </div>
        <main>
            <h3>Inicia sesion para ingresar al contenido</h3>
            <form action="InicioSesion" method="POST"class="formulario" id="formulario">
                <!--Grupo Usuario-->
                <div class="formulario__grupo " id="grupo__usuario">
                    <label for="usuario" class="formulario__label"> Usuario </label>

                    <div class="formulario__grupo-input">
                        <input type="text" class="formulario__input" name="usuario" id="usuario" placeholder="Usuario123">
                        <i class="formulario__validacion-estado fas fa-times-circle" ></i>
                    </div>
                    <p class="formulario__input-error"> El usuario debe contener entre 4 y 16 digitos y solo puedes incluir letras, numeros y guion bajo. </p>
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



                <div class="formulario__mensaje" id="formulario__mensaje">
                    <p><i class="fas fa-exclamation-triangle"></i> <b> Error </b> Por favor rellena el formulario correctamente </p>

                </div>

                <div class=" formulario__grupo formulario__grupo-btn-enviar">
                    <button type="submit" class="formulario__btn"> Iniciar Sesion </button>
                    <p class="formulario__mensaje-exito" id="formulario__mensaje-exito"> Formulario enviado con éxito </p>
                </div>
                <div class="formulario__grupo formulario__grupo-btn-a">
                    <p>Si no tienes una cuenta <a href="Registro.jsp">registrate.</a></p>
                </div>
                <div class="formulario__grupo formulario__grupo-btn-a">
                    <p>Inicia sesion como administrador <a href="../html/administrador_sesion.html">aqui.</a></p>
                </div>
            </form>
        </main>

        <script src="registro.js"></script>

    </body>

</html>