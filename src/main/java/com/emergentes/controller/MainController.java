package com.emergentes.controller;

import com.emergentes.bean.BeanEstudiante;
import com.emergentes.entidades.Estudiante;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "listar";
        }
        
        try {
            switch (action) {
                case "nuevo":
                    nuevo(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
                    break;
                case "listar":
                default:
                    listar(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 0;
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }

        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));

        Estudiante est = new Estudiante();
        est.setId(id);
        est.setNombre(nombre);
        est.setApellidos(apellidos);
        est.setEmail(email);
        est.setFechaNacimiento(fechaNacimiento);

        BeanEstudiante dao = new BeanEstudiante();
        if (id == 0) {
            try {
                dao.insertar(est);
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            try {
                dao.editar(est);
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        response.sendRedirect("MainController?action=listar");
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Estudiante estudiante = new Estudiante();
        request.setAttribute("estudiante", estudiante);
        request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            BeanEstudiante dao = new BeanEstudiante();
            Estudiante e = dao.buscar(id);
            request.setAttribute("estudiante", e);
            request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
        } else {
            response.sendRedirect("MainController?action=listar");
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            BeanEstudiante dao = new BeanEstudiante();
            dao.eliminar(id);
        }
        response.sendRedirect("MainController?action=listar");
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanEstudiante dao = new BeanEstudiante();
        List<Estudiante> lista = dao.listarTodos();
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("estudiantes.jsp").forward(request, response);
    }
}
