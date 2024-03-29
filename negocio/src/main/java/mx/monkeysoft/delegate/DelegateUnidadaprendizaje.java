/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.monkeysoft.delegate;

import java.util.List;
import mx.monkeysoft.entidad.Profesor;
import mx.monkeysoft.entidad.Unidadaprendizaje;
import mx.monkeysoft.integracion.ServiceLocator;

/**
 *
 * @author monkeysoft
 */
public class DelegateUnidadaprendizaje {
    
    /**
     *
     * @param UA 
     */
    public void saveUnidadaprendizaje (Unidadaprendizaje UA){
        ServiceLocator.getInstanceUnidadaprendizajeDAO().save(UA);
    }
    
    public Unidadaprendizaje buscarPorNombre(String nombre){
        Unidadaprendizaje unidad = null;
        List<Unidadaprendizaje> unidades = ServiceLocator.getInstanceUnidadaprendizajeDAO().findAll();
        
        for(Unidadaprendizaje ua: unidades){
            if(ua.getNombre().compareToIgnoreCase(nombre)==0){
                unidad = ua;
            }
        }
        return unidad;
    }
    
    public List<Unidadaprendizaje> getByProf(int idProf){
        return ServiceLocator.getInstanceUnidadaprendizajeDAO().findUnidadByProf(idProf);
    }
    
    public List<Unidadaprendizaje> obtenerUnidadesaprendizaje() {
        return ServiceLocator.getInstanceUnidadaprendizajeDAO().findAll();
    }
    
    public void asignarProfesor(int idProfesor, int idUnidadAprendizaje){
        ServiceLocator.getInstanceProfesorDao().registerProfesor(idProfesor, idUnidadAprendizaje);
    }
    
    public void removeProfesor(int idProfesor, int idUnidadaprendizaje) {
        ServiceLocator.getInstanceProfesorDao().deleteFromRegistroProfesor(idProfesor, idUnidadaprendizaje);
    }
}
