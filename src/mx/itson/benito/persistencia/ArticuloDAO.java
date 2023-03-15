/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Jesus Javier Quintero Fierro
 */
public class ArticuloDAO {
    
    public static List<Articulo> obtenerTodos(){
        List<Articulo> articulos = new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Articulo> criteriaQuery = session.getCriteriaBuilder().createQuery(Articulo.class);
            criteriaQuery.from(Articulo.class);
            articulos = session.createQuery(criteriaQuery).getResultList();
        }catch(Exception ex){
            System.err.println("Ocurrio un error; "+ ex.getMessage());
        }
    return articulos;
    }
    
    public static boolean eliminar(int id){
    boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Articulo articulo = session.get(Articulo.class, id);
            if(articulo!=null){
                session.delete(articulo);
                session.getTransaction().commit();
            resultado = true;
            }
        }catch(HibernateException ex){
             System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
    
    public static boolean editar(int id, String nombre, String clave, int precio, Proveedor proveedor){
    boolean resultado = false;
    try{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Articulo articulo = session.get(Articulo.class, id);
        if(articulo != null){
            articulo.setNombre(nombre);
            articulo.setClave(clave);
            articulo.setPrecio(precio);
            articulo.setProveedor(proveedor);
            session.saveOrUpdate(articulo);
            session.getTransaction().commit();
            resultado = true;
            }       
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
    
}
