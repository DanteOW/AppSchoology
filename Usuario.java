/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.disenosoftware.plataformaschoology.entidad;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import mx.itson.disenosoftware.plataformaschoology.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author starw_000
 */
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private String correo;

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void guardar(Usuario u){
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = sesion.beginTransaction();
            sesion.save(u);
            
 
            transaction.commit();
            HibernateUtil.getSessionFactory().close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Usuario> obtenerTodos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> usuario = (List<Usuario>)session.createCriteria(Usuario.class).list();
        return usuario;
    }
}
