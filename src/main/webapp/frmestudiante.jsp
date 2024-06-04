<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de estudiantes</title>
    </head>
    <body>
        <h1>Formulario de estudiantes</h1>
        <form action="MainController" method="post">
            <input type="hidden" name="id" value="${estudiante.id}">
            <div>
                <label>Nombre:</label>
                <input type="text" name="nombre" value="${estudiante.nombre}">
            </div>
            <br>
            <div>
                <label>Apellidos:</label>
                <input type="text" name="apellidos" value="${estudiante.apellidos}">
            </div>
            <br>
            <div>
                <label>Email:</label>
                <input type="email" name="email" value="${estudiante.email}">
            </div>
            <br>
            <div>
                <label>Fecha de Nacimiento:</label>
                <input type="date" name="fechaNacimiento" value="${estudiante.fechaNacimiento}">
            </div>
            <br>
            <button type="submit">Enviar</button>
        </form>
    </body>
</html>
