/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.disenosoftware.plataformaschoology.entidad;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class Entrega {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Usuario alumno;
    private String archivo;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private double puntuacion;
    private String comentario;
    
    @ManyToOne
    private Asignacion asignacion;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getAlumno() {
        return alumno;
    }

    public void setAlumno(Usuario alumno) {
        this.alumno = alumno;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
    
    public void guardar(Entrega en){
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = sesion.beginTransaction();
            sesion.save(en);
            
 
            transaction.commit();
            //HibernateUtil.getSessionFactory().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Entrega> obtenerTodos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Entrega> entrega = (List<Entrega>)session.createCriteria(Entrega.class).list();
        return entrega;
    }
    
}
