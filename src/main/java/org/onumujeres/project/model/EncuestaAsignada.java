package org.onumujeres.project.model;

import java.sql.Timestamp;

public class EncuestaAsignada {
    private int asignacionId;
    private int encuestaId;
    private int encuestadorId;
    private int coordinadorId;
    private Timestamp fechaAsignacion;
    private String nombreEncuesta;
    private String descripcionEncuesta;

    public int getAsignacionId() { return asignacionId; }
    public void setAsignacionId(int asignacionId) { this.asignacionId = asignacionId; }

    public int getEncuestaId() { return encuestaId; }
    public void setEncuestaId(int encuestaId) { this.encuestaId = encuestaId; }

    public int getEncuestadorId() { return encuestadorId; }
    public void setEncuestadorId(int encuestadorId) { this.encuestadorId = encuestadorId; }

    public int getCoordinadorId() { return coordinadorId; }
    public void setCoordinadorId(int coordinadorId) { this.coordinadorId = coordinadorId; }

    public Timestamp getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(Timestamp fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }

    public String getNombreEncuesta() { return nombreEncuesta; }
    public void setNombreEncuesta(String nombreEncuesta) { this.nombreEncuesta = nombreEncuesta; }

    public String getDescripcionEncuesta() { return descripcionEncuesta; }
    public void setDescripcionEncuesta(String descripcionEncuesta) { this.descripcionEncuesta = descripcionEncuesta; }
}