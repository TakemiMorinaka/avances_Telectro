package org.onumujeres.project.model;

import java.sql.Timestamp;

public class Usuario {
    private int usuarioId;
    private String rol;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String dni;
    private String correo;
    private String contrasenaHash;
    private String direccion;
    private Integer distritoId;
    private Integer zonaId;
    private String codigoUnicoEncuestador;
    private String estado;
    private boolean correoVerificado;
    private Timestamp fechaRegistro;
    private Timestamp ultimaConexion;

    // Constructores
    public Usuario() {
    }

    public Usuario(int usuarioId, String rol, String nombre, String apellidoPaterno, String apellidoMaterno, String dni, String correo, String contrasenaHash, String direccion, Integer distritoId, Integer zonaId, String codigoUnicoEncuestador, String estado, boolean correoVerificado, Timestamp fechaRegistro, Timestamp ultimaConexion) {
        this.usuarioId = usuarioId;
        this.rol = rol;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.dni = dni;
        this.correo = correo;
        this.contrasenaHash = contrasenaHash;
        this.direccion = direccion;
        this.distritoId = distritoId;
        this.zonaId = zonaId;
        this.codigoUnicoEncuestador = codigoUnicoEncuestador;
        this.estado = estado;
        this.correoVerificado = correoVerificado;
        this.fechaRegistro = fechaRegistro;
        this.ultimaConexion = ultimaConexion;
    }

    // Getters y Setters
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(Integer distritoId) {
        this.distritoId = distritoId;
    }

    public Integer getZonaId() {
        return zonaId;
    }

    public void setZonaId(Integer zonaId) {
        this.zonaId = zonaId;
    }

    public String getCodigoUnicoEncuestador() {
        return codigoUnicoEncuestador;
    }

    public void setCodigoUnicoEncuestador(String codigoUnicoEncuestador) {
        this.codigoUnicoEncuestador = codigoUnicoEncuestador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isCorreoVerificado() {
        return correoVerificado;
    }

    public void setCorreoVerificado(boolean correoVerificado) {
        this.correoVerificado = correoVerificado;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Timestamp ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", rol='" + rol + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", dni='" + dni + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenaHash='" + contrasenaHash + '\'' +
                ", direccion='" + direccion + '\'' +
                ", distritoId=" + distritoId +
                ", zonaId=" + zonaId +
                ", codigoUnicoEncuestador='" + codigoUnicoEncuestador + '\'' +
                ", estado='" + estado + '\'' +
                ", correoVerificado=" + correoVerificado +
                ", fechaRegistro=" + fechaRegistro +
                ", ultimaConexion=" + ultimaConexion +
                '}';
    }
}