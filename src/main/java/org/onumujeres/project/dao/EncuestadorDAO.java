package org.onumujeres.project.dao;

import org.onumujeres.project.model.EncuestaAsignada;
import org.onumujeres.project.model.Respuesta;
import org.onumujeres.project.config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EncuestadorDAO {

    // Obtener los formularios asignados a un encuestador
    public List<EncuestaAsignada> obtenerFormulariosAsignados(int encuestadorId) throws SQLException {
        List<EncuestaAsignada> formularios = new ArrayList<>();
        String sql = "SELECT ea.asignacion_id, ea.encuesta_id, ea.fecha_asignacion, " +
                "e.nombre AS nombre_encuesta, e.descripcion AS descripcion_encuesta " +
                "FROM encuestas_asignadas ea " +
                "INNER JOIN encuestas e ON ea.encuesta_id = e.encuesta_id " +
                "WHERE ea.encuestador_id = ? AND ea.estado = 'activo'";
        try (Connection conn = DatabaseConfiguration.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, encuestadorId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                EncuestaAsignada asignada = new EncuestaAsignada();
                asignada.setAsignacionId(rs.getInt("asignacion_id"));
                asignada.setEncuestaId(rs.getInt("encuesta_id"));
                asignada.setFechaAsignacion(rs.getTimestamp("fecha_asignacion"));
                asignada.setNombreEncuesta(rs.getString("nombre_encuesta"));
                asignada.setDescripcionEncuesta(rs.getString("descripcion_encuesta"));
                formularios.add(asignada);
            }
        }
        return formularios;
    }

    // Crear una nueva respuesta a una encuesta
    public void crearRespuesta(Respuesta respuesta) throws SQLException {
        String sql = "INSERT INTO respuestas (encuesta_id, encuestador_id, dni_encuestado, asignacion_id, estado) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfiguration.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, respuesta.getEncuestaId());
            pstmt.setInt(2, respuesta.getEncuestadorId());
            pstmt.setString(3, respuesta.getDniEncuestado());
            pstmt.setInt(4, respuesta.getAsignacionId());
            pstmt.setString(5, respuesta.getEstado());
            pstmt.executeUpdate();
        }
    }

    // Obtener una respuesta por su ID (si necesitas editarla)
    public Respuesta obtenerRespuestaPorId(int respuestaId) throws SQLException {
        Respuesta respuesta = null;
        String sql = "SELECT respuesta_id, encuesta_id, encuestador_id, dni_encuestado, asignacion_id, estado, fecha_inicio, fecha_envio " +
                "FROM respuestas WHERE respuesta_id = ?";
        try (Connection conn = DatabaseConfiguration.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, respuestaId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                respuesta = new Respuesta();
                respuesta.setRespuestaId(rs.getInt("respuesta_id"));
                respuesta.setEncuestaId(rs.getInt("encuesta_id"));
                respuesta.setEncuestadorId(rs.getInt("encuestador_id"));
                respuesta.setDniEncuestado(rs.getString("dni_encuestado"));
                respuesta.setAsignacionId(rs.getInt("asignacion_id"));
                respuesta.setEstado(rs.getString("estado"));
                respuesta.setFechaInicio(rs.getTimestamp("fecha_inicio"));
                respuesta.setFechaEnvio(rs.getTimestamp("fecha_envio"));
            }
        }
        return respuesta;
    }

    // Actualizar una respuesta existente
    public void actualizarRespuesta(Respuesta respuesta) throws SQLException {
        String sql = "UPDATE respuestas SET encuesta_id = ?, encuestador_id = ?, dni_encuestado = ?, " +
                "asignacion_id = ?, estado = ?, fecha_envio = ? WHERE respuesta_id = ?";
        try (Connection conn = DatabaseConfiguration.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, respuesta.getEncuestaId());
            pstmt.setInt(2, respuesta.getEncuestadorId());
            pstmt.setString(3, respuesta.getDniEncuestado());
            pstmt.setInt(4, respuesta.getAsignacionId());
            pstmt.setString(5, respuesta.getEstado());
            pstmt.setTimestamp(6, respuesta.getFechaEnvio());
            pstmt.setInt(7, respuesta.getRespuestaId());
            pstmt.executeUpdate();
        }
    }

    // Contar las respuestas completadas por un encuestador
    public int contarRespuestasCompletadas(int encuestadorId) throws SQLException {
        int conteo = 0;
        String sql = "SELECT COUNT(*) FROM respuestas WHERE encuestador_id = ? AND estado = 'completo'";
        try (Connection conn = DatabaseConfiguration.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, encuestadorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                conteo = rs.getInt(1);
            }
        }
        return conteo;
    }

    // Puedes agregar más métodos DAO según las necesidades específicas del encuestador
}