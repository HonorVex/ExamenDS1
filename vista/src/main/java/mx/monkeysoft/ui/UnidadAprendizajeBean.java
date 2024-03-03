/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.monkeysoft.ui;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mx.monkeysoft.entidad.Profesor;
import mx.monkeysoft.helper.UnidadaprendizajeHelper;
import mx.monkeysoft.entidad.Unidadaprendizaje;
import mx.monkeysoft.helper.ProfesorHelper;

/**
 *
 * @author monkeysoft
 */

@ManagedBean (name = "unidadAprendizajeBean")
@SessionScoped
public class UnidadAprendizajeBean implements Serializable {
    private UnidadaprendizajeHelper unidadHelper;
    private List<Unidadaprendizaje> unidades;
    private Unidadaprendizaje unidadAprendizaje;
    private List<Profesor> profesores;
    
    private ProfesorHelper profesorHelper;
    
    @PostConstruct
    public void init() {
        unidadHelper = new UnidadaprendizajeHelper();
        unidades = unidadHelper.getAll();
        unidadAprendizaje = new Unidadaprendizaje();
        profesorHelper = new ProfesorHelper();
    }
        
    public List<Unidadaprendizaje> getUnidades() {
        return unidades;
    }
    
    //Este método consigue los profesores inscritos a las UA
    public String viewUnidadAprendizaje(Unidadaprendizaje unidad) {
        this.unidadAprendizaje = unidad;
        profesores = profesorHelper.getAll(this.unidadAprendizaje.getIdUnidadAprendizaje());
        return "/ua/profesores";
    }
    
    public Unidadaprendizaje getUnidadAprendizaje() {
        return unidadAprendizaje;
    }
    
    public List<Profesor> getProfesores() {
        return profesores;
    }
    
    //Este método debería regresar los profesores no inscritos a la UA
    public List<Profesor> getProfesoresDisponibles() {
        List<Profesor> profesoresDisponibles = profesorHelper.getStandard();
        profesoresDisponibles.removeAll(profesores);
        return profesoresDisponibles;
    }
    
    //Este método debería eliminar los profesores de una UA específica, no borrarlos de la BD sino eliminarlos de RegistroProfesor.
    public void removeProfesor(Profesor profesor) {
        // TODO: eliminar profesor en la tabla intermedia
        unidadAprendizaje.getProfesorList().remove(profesor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "El profesor se eliminó correctamente"));
    }
}