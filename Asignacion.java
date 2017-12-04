/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.disenosoftware.plataformaschoology.entidad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import mx.itson.disenosoftware.plataformaschoology.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author starw_000
 */
@Entity
public class Asignacion {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private String descripcion;
    @Temporal(TemporalType.DATE)
    private Date publicacion;
    @Temporal(TemporalType.DATE)
    private Date limite;
    private double puntos;
    private String rubrica;

    @ManyToOne//(cascade = {CascadeType.ALL})
    private Curso curso;
    
    @OneToMany(cascade ={CascadeType.ALL})
    @JoinColumn(name="entrega_id")
    private List<Entrega> entregas = new ArrayList<>();

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Date publicacion) {
        this.publicacion = publicacion;
    }

    public Date getLimite() {
        return limite;
    }

    public void setLimite(Date limite) {
        this.limite = limite;
    }

    public double getPuntos() {
        return puntos;
    }

    public void setPuntos(double puntos) {
        this.puntos = puntos;
    }

    public String getRubrica() {
        return rubrica;
    }

    public void setRubrica(String rubrica) {
        this.rubrica = rubrica;
    }

    public List<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }
    
    public void guardar(Asignacion a){
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = sesion.beginTransaction();
            sesion.save(a);
            
 
            transaction.commit();
            //HibernateUtil.getSessionFactory().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Asignacion> obtenerTodos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Asignacion> asignacion = (List<Asignacion>)session.createCriteria(Asignacion.class).list();
        return asignacion;
    }
}

