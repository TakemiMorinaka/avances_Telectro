package org.onumujeres.project.servlets;

import org.onumujeres.project.dao.EncuestadorDAO;
import org.onumujeres.project.dao.UsuarioDAO;
import org.onumujeres.project.dto.EncuestaAsignadaDTO;
import org.onumujeres.project.dto.RespuestaDTO;
import org.onumujeres.project.model.Respuesta;
import org.onumujeres.project.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/encuestador/*")
public class EncuestadorServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private EncuestadorDAO encuestadorDAO = new EncuestadorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || "/".equals(pathInfo)) {
            // Mostrar la página principal del encuestador (perfil)
            mostrarPerfil(request, response);
        } else if ("/formularios".equals(pathInfo)) {
            // Listar los formularios asignados
            listarFormulariosAsignados(request, response);
        } else if (pathInfo.startsWith("/respuesta/editar/")) {
            // Mostrar el formulario para editar una respuesta
            mostrarFormularioEditarRespuesta(request, response);
        } else {
            // Manejar otras rutas (si es necesario)
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if ("/respuesta/crear".equals(pathInfo)) {
            // Crear una nueva respuesta
            crearRespuesta(request, response);
        } else if ("/respuesta/editar".equals(pathInfo)) {
            // Actualizar una respuesta existente
            actualizarRespuesta(request, response);
        } else {
            // Manejar otras peticiones POST
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // Métodos de apoyo

    private void mostrarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer encuestadorId = (Integer) session.getAttribute("usuarioId"); // Obtener de la sesión

        if (encuestadorId == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirigir al login
            return;
        }

        try {
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(encuestadorId);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/WEB-INF/encuestador/perfil.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private void listarFormulariosAsignados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer encuestadorId = (Integer) session.getAttribute("usuarioId"); // Obtener de la sesión

        if (encuestadorId == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirigir al login
            return;
        }

        try {
            List<EncuestaAsignada> formularios = encuestadorDAO.obtenerFormulariosAsignados(encuestadorId);
            List<EncuestaAsignadaDTO> formulariosDTO = formularios.stream()
                    .map(f -> {
                        EncuestaAsignadaDTO dto = new EncuestaAsignadaDTO();
                        dto.setAsignacionId(f.getAsignacionId());
                        dto.setEncuestaId(f.getEncuestaId());
                        dto.setNombreEncuesta(f.getNombreEncuesta());
                        dto.setDescripcionEncuesta(f.getDescripcionEncuesta());
                        dto.setFechaAsignacion(f.getFechaAsignacion());
                        return dto;
                    }).collect(Collectors.toList());
            request.setAttribute("formularios", formulariosDTO);
            request.getRequestDispatcher("/WEB-INF/encuestador/formularios.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private void mostrarFormularioEditarRespuesta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer encuestadorId = (Integer) session.getAttribute("usuarioId"); // Obtener de la sesión

        if (encuestadorId == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirigir al login
            return;
        }

        String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            String respuestaIdStr = pathInfo.substring("/respuesta/editar/".length());
            try {
                int respuestaId = Integer.parseInt(respuestaIdStr);
                Respuesta respuesta = encuestadorDAO.obtenerRespuestaPorId(respuestaId);
                if (respuesta != null && respuesta.getEncuestadorId() == encuestadorId) {
                    request.setAttribute("respuesta", respuesta);
                    request.getRequestDispatcher("/WEB-INF/encuestador/editar_respuesta.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "No tienes permiso para editar esta respuesta.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de respuesta inválido.");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/error");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de respuesta faltante.");
        }
    }

    private void crearRespuesta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer encuestadorId = (Integer) session.getAttribute("usuarioId"); // Obtener de la sesión

        if (encuestadorId == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirigir al login
            return;
        }

        int encuestaId = Integer.parseInt(request.getParameter("encuestaId"));
        String dniEncuestado = request.getParameter("dniEncuestado");
        int asignacionId = Integer.parseInt(request.getParameter("asignacionId"));
        String estado = request.getParameter("estado"); // Puede ser 'borrador' o 'completo'

        Respuesta respuesta = new Respuesta();
        respuesta.setEncuestaId(encuestaId);
        respuesta.setEncuestadorId(encuestadorId);
        respuesta.setDniEncuestado(dniEncuestado);
        respuesta.setAsignacionId(asignacionId);
        respuesta.setEstado(estado);
        respuesta.setFechaInicio(new Timestamp(System.currentTimeMillis())); // Establecer fecha de inicio

        try {
            encuestadorDAO.crearRespuesta(respuesta);
            response.sendRedirect(request.getContextPath() + "/encuestador/formularios");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    private void actualizarRespuesta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer encuestadorId = (Integer) session.getAttribute("usuarioId"); // Obtener de la sesión

        if (encuestadorId == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirigir al login
            return;
        }

        int respuestaId = Integer.parseInt(request.getParameter("respuestaId"));
        int encuestaId = Integer.parseInt(request.getParameter("encuestaId"));
        String dniEncuestado = request.getParameter("dniEncuestado");
        int asignacionId = Integer.parseInt(request.getParameter("asignacionId"));
        String estado = request.getParameter("estado"); // Puede ser 'borrador' o 'completo'

        Respuesta respuesta = new Respuesta();
        respuesta.setRespuestaId(respuestaId);
        respuesta.setEncuestaId(encuestaId);
        respuesta.setEncuestadorId(encuestadorId);
        respuesta.setDniEncuestado(dniEncuestado);
        respuesta.setAsignacionId(asignacionId);
        respuesta.setEstado(estado);
        respuesta.setFechaEnvio(new Timestamp(System.currentTimeMillis())); // Actualizar fecha de envío

        try {
            Respuesta respuestaExistente = encuestadorDAO.obtenerRespuestaPorId(respuestaId);
            if (respuestaExistente != null && respuestaExistente.getEncuestadorId() == encuestadorId) {
                encuestadorDAO.actualizarRespuesta(respuesta);
                response.sendRedirect(request.getContextPath() + "/encuestador/formularios");
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "No tienes permiso para editar esta respuesta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}