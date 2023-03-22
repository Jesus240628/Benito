/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Jesus Javier Quintero Fierro
 */
public class ProveedorDAO {
 /**
 * Lista de proveedores con sus atributos.
 * @return Retorna una lista de proveedores.
 */
 public static List<Proveedor> obtenerTodos(){
        List<Proveedor> proveedores = new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Proveedor> criteriaQuery = session.getCriteriaBuilder().createQuery(Proveedor.class);
            criteriaQuery.from(Proveedor.class);
            proveedores = session.createQuery(criteriaQuery).getResultList();
        }catch(Exception ex){
            System.err.println("Ocurrio un error; "+ ex.getMessage());
        }
    return proveedores;
    }
 
 /**
 * Elimina un registro de una orden de compra en la base de datos
 * @param id Id del proveedor
 * @return Nos indica si el registro se elimino correctamente o no.
 */
 public static boolean eliminar(int id){
    boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Proveedor proveedor = session.get(Proveedor.class, id);
            if(proveedor!=null){
                session.delete(proveedor);
                session.getTransaction().commit();
            resultado = true;
            }
        }catch(HibernateException ex){
             System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
 
 /**
 * Edita un registro de proveedor en la base de datos
 * @param id Id del proveedor
 * @param nombre Nombre del proveedor
 * @param telefono Telefono del proveedor
 * @param direccion Direccion del proveedor
 * @param email Email del proveedor
 * @param contacto Contacto del proveedor
 * @return Nos indica si el registro se guardo correctamente o no
 */
 public static boolean editar(int id, String nombre, String telefono, String direccion,String email,String contacto){
    boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Proveedor proveedor = session.get(Proveedor.class, id);
            if(proveedor != null){
                proveedor.setNombre(nombre);
                proveedor.setTelefono(telefono);
                proveedor.setDireccion(direccion);
                proveedor.setEmail(email);
                proveedor.setContacto(contacto);
                session.saveOrUpdate(proveedor);
                session.getTransaction().commit();
            resultado = true;
                }       
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
 
 /**
 * Guarda un registro de proveedor en la base de datos
 * @param nombre Nombre del proveedor
 * @param telefono Telefono del proveedor
 * @param direccion Direccion del proveedor
 * @param email Email del proveedor
 * @param contacto Contacto del proveedor
 * @return Nos indica si el registro se guardo correctamente o no
 */
 public static boolean guardar(String nombre,String telefono,String direccion,String email,String contacto){
    boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        
            Proveedor proveedor = new Proveedor();
            proveedor.getNombre();
            proveedor.getTelefono();
            proveedor.getDireccion();
            proveedor.getDireccion();
            proveedor.getEmail();
            proveedor.getContacto();
            session.save(proveedor);
        
            session.getTransaction().commit();
        resultado = proveedor.getId() !=0;
        }catch(Exception ex){
            System.out.println("Ocurrio un error: " + ex.getMessage());
        }
    return resultado;
    }
}
