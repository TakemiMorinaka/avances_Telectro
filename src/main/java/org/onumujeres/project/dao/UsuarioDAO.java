package org.onumujeres.project.dao;

import org.onumujeres.project.dto.UsuarioDTO;
import org.onumujeres.project.model.Usuario;
import org.onumujeres.project.config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para obtener un Usuario por su ID
    public UsuarioDTO obtenerUsuarioPorId(int usuarioId) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE usuario_id = ?";
        try (Connection conn = DatabaseConfiguration.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapearResultSetAUsuarioDTO(rs);
            }
        }
        return null;
    }

    // Método para obtener todos los Usuarios
    public List<UsuarioDTO> obtenerTodosLosUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuarios";
        List<UsuarioDTO> usuarios = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(mapearResultSetAUsuarioDTO(rs));
            }
        }
        return usuarios;
    }

    // Método para crear un nuevo Usuario
    public boolean crearUsuario(UsuarioDTO usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (rol, nombre, apellido_paterno, apellido_materno, dni, correo, contrasena_hash, direccion, distrito_id, zona_id, codigo_unico_encuestador, estado, correo_verificado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfiguration.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getRol());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellidoPaterno());
            pstmt.setString(4, usuario.getApellidoMaterno());
            pstmt.setString(5, usuario.getDni());
            pstmt.setString(6, usuario.getCorreo());
            pstmt.setString(7, usuario.getContrasenaHash());
            pstmt.setString(8, usuario.getDireccion());
            pstmt.setObject(9, usuario.getDistritoId(), Types.INTEGER); // Usar setObject para Integer
            pstmt.setObject(10, usuario.getZonaId(), Types.INTEGER);    // Usar setObject para Integer
            pstmt.setString(11, usuario.getCodigoUnicoEncuestador());
            pstmt.setString(12, usuario.getEstado());
            pstmt.setBoolean(13, usuario.isCorreoVerificado());
            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;
        }
    }

    // Método para actualizar un Usuario existente
    public boolean actualizarUsuario(UsuarioDTO usuario) throws SQLException {
        String sql = "UPDATE usuarios SET rol = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ?, dni = ?, correo = ?, contrasena_hash = ?, direccion = ?, distrito_id = ?, zona_id = ?, codigo_unico_encuestador = ?, estado = ?, correo_verificado = ? WHERE usuario_id = ?";
        try (Connection conn = DatabaseConfiguration.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getRol());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellidoPaterno());
            pstmt.setString(4, usuario.getApellidoMaterno());
            pstmt.setString(5, usuario.getDni());
            pstmt.setString(6, usuario.getCorreo());
            pstmt.setString(7, usuario.getContrasenaHash());
            pstmt.setString(8, usuario.getDireccion());
            pstmt.setObject(9, usuario.getDistritoId(), Types.INTEGER);
            pstmt.setObject(10, usuario.getZonaId(), Types.INTEGER);
            pstmt.setString(11, usuario.getCodigoUnicoEncuestador());
            pstmt.setString(12, usuario.getEstado());
            pstmt.setBoolean(13, usuario.isCorreoVerificado());
            pstmt.setInt(14, usuario.getUsuarioId());
            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        }
    }

    // Método para eliminar un Usuario por su ID
    public boolean eliminarUsuario(int usuarioId) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE usuario_id = ?";
        try (Connection conn = DatabaseConfiguration.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            int filasEliminadas = pstmt.executeUpdate();
            return filasEliminadas > 0;
        }
    }

    // Método privado para mapear un ResultSet a un UsuarioDTO
    private UsuarioDTO mapearResultSetAUsuarioDTO(ResultSet rs) throws SQLException {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsuarioId(rs.getInt("usuario_id"));
        usuario.setRol(rs.getString("rol"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
        usuario.setApellidoMaterno(rs.getString("apellido_materno"));
        usuario.setDni(rs.getString("dni"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setContrasenaHash(rs.getString("contrasena_hash"));
        usuario.setDireccion(rs.getString("direccion"));
        usuario.setDistritoId(rs.getObject("distrito_id", Integer.class)); // Usar getObject
        usuario.setZonaId(rs.getObject("zona_id", Integer.class));         // Usar getObject
        usuario.setCodigoUnicoEncuestador(rs.getString("codigo_unico_encuestador"));
        usuario.setEstado(rs.getString("estado"));
        usuario.setCorreoVerificado(rs.getBoolean("correo_verificado"));
        usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        usuario.setUltimaConexion(rs.getTimestamp("ultima_conexion"));
        return usuario;
    }
}