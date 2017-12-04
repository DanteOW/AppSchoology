package mx.itson.disenosoftware.plataformaschoology.entidad;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import mx.itson.disenosoftware.plataformaschoology.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author starw_000
 */
@Entity
public class Curso {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    @OneToOne(cascade = {CascadeType.ALL})
    private Usuario profesor;
    
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="curso_usuario",
            joinColumns = @JoinColumn(name="Curso_id"),
            inverseJoinColumns = @JoinColumn(name = "alumnos_id")
    )//(name="Curso_id")
    private List<Usuario> alumnos = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL})//(mappedBy="curso")
    @JoinColumn (name="curso_id")
    private List<Asignacion> asignaciones = new ArrayList<>();

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

    public Usuario getProfesor() {
        return profesor;
    }

    public void setProfesor(Usuario profesor) {
        this.profesor = profesor;
    }

    public List<Usuario> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Usuario> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }
    
    public void guardar(Curso c){
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = sesion.beginTransaction();
            sesion.save(c);
            
 
            transaction.commit();
            //HibernateUtil.getSessionFactory().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void actualizar(Curso c){
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = sesion.beginTransaction();
            sesion.update(c);
            
            transaction.commit();
            //HibernateUtil.getSessionFactory().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Curso> obtenerTodos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Curso> curso = (List<Curso>)session.createCriteria(Curso.class).list();
        return curso;
    }
    
}
