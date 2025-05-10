package org.onumujeres.project.dto;

import java.sql.Timestamp;

public class EncuestaAsignadaDTO {
    private int asignacionId;
    private int encuestaId;
    private String nombreEncuesta;
    private String descripcionEncuesta;
    private Timestamp fechaAsignacion;

    public int getAsignacionId() { return asignacionId; }
    public void setAsignacionId(int asignacionId) { this.asignacionId = asignacionId; }

    public int getEncuestaId() { return encuestaId; }
    public void setEncuestaId(int encuestaId) { this.encuestaId = encuestaId; }

    public String getNombreEncuesta() { return nombreEncuesta; }
    public void setNombreEncuesta(String nombreEncuesta) { this.nombreEncuesta = nombreEncuesta; }

    public String getDescripcionEncuesta() { return descripcionEncuesta; }
    public void setDescripcionEncuesta(String descripcionEncuesta) { this.descripcionEncuesta = descripcionEncuesta; }

    public Timestamp getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(Timestamp fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
}