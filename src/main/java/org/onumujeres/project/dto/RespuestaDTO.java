package org.onumujeres.project.dto;

import java.sql.Timestamp;

public class RespuestaDTO {
    private int respuestaId;
    private int encuestaId;
    private int encuestadorId;
    private String dniEncuestado;
    private int asignacionId;
    private String estado;
    private Timestamp fechaInicio;
    private Timestamp fechaEnvio;

    public int getRespuestaId() { return respuestaId; }
    public void setRespuestaId(int respuestaId) { this.respuestaId = respuestaId; }

    public int getEncuestaId() { return encuestaId; }
    public void setEncuestaId(int encuestaId) { this.encuestaId = encuestaId; }

    public int getEncuestadorId() { return encuestadorId; }
    public void setEncuestadorId(int encuestadorId) { this.encuestadorId = encuestadorId; }

    public String getDniEncuestado() { return dniEncuestado; }
    public void setDniEncuestado(String dniEncuestado) { this.dniEncuestado = dniEncuestado; }

    public int getAsignacionId() { return asignacionId; }
    public void setAsignacionId(int asignacionId) { this.asignacionId = asignacionId; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Timestamp getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Timestamp fechaInicio) { this.fechaInicio = fechaInicio; }

    public Timestamp getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(Timestamp fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}