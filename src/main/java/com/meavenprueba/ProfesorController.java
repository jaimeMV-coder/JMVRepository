/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meavenprueba;

/**
 *
 * @author User
 */
import com.meavenprueba.util.JsfUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import javax.faces.model.DataModel;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
@ManagedBean(name="profesor_C")
@ViewScoped
public final class ProfesorController {
    
    String id_p;
    String alum_id;
    String usern;
    private Profesor current;
    private List<Profesor>lista;
    private Profesor profeselected;
    private HtmlCommandLink  button;

    public HtmlCommandLink getButton() {
        return button;
    }

    public void setButton(HtmlCommandLink button) {
        this.button = button;
    }
    
    public Profesor getProfeselected() {
        return profeselected;
    }

    public void setProfeselected(Profesor profeselected) {
        this.profeselected = profeselected;
    }

    

    public List<Profesor> getLista() {
        return lista;
    }

    public void setLista(List<Profesor> lista) {
        this.lista = lista;
    }

    
    public String getId_p() {
        return id_p;
    }

    public void setId_p(String id_p) {
        this.id_p = id_p;
    }

    public String getAlum_id() {
        return alum_id;
    }

    public void setAlum_id(String alum_id) {
        this.alum_id = alum_id;
    }

    public String getUsern() {
        return usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }
    
    @PostConstruct
    public void carga(){
        this.lista= new ArrayList<>();
       this.lista=ejb.obtall();
    }
    public void onRowSelect(SelectEvent event) {
        this.profeselected=new Profesor();
        this.profeselected=(Profesor) event.getObject();
        FacesMessage msg = new FacesMessage("Profesor seleccionado", ((Profesor) event.getObject()).getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Profesor deseleccionado", ((Profesor) event.getObject()).getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void Destruido(){
        
    }

    public void destruir(){
        ejb.destruir(profeselected);
        carga();
    }
    @EJB
    private profesorEJB ejb;
    
   
  
    public String registrar() {
        current = new Profesor();
        this.current.setProfesor_id(Integer.parseInt(this.id_p));
        this.current.setAlumno_id(Integer.parseInt(this.alum_id));
        this.current.setUsername(this.usern);
        String mensaje;
        mensaje=ejb.guardar(current);
        return mensaje;
    }
    public void update(){
        
    }
   public void enableEditBtn(){
       button.setDisabled(false);
   }
   
}

