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
 * Contiene la persistencia data access object del objeto articulo
 * @author Jesus Javier Quintero Fierro
 */
public class ArticuloDAO {
    /**
     * Lista de articulos con sus atributos.
     * @return Retorna una lista de articulos.
     */
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
    
     /**
     * Elimina un registro de un articulo en la base de datos
     * @param id Id de articulo
     * @return Nos indica si el registro se elimino correctamente o no.
     */
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
    
    /**
     * Edita un registro de un articulo en la base de datos
     * @param id Id del articulo
     * @param nombre Nombre del articulo
     * @param clave Clave del articulo
     * @param precio Precio del articulo
     * @param proveedor Proveedor del articulo
     * @return Nos indica si el registro se guardo correctamente o no
     */
    public static boolean editar(int id, String nombre, String clave, Double precio, Proveedor proveedor){
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
    
    /**
     * Guarda un registro de un articulo en la base de datos
     * @param nombre Nombre del articulo
     * @param clave Clave del articulo
     * @param precio Precio del articulo
     * @param proveedor Proveedor del articulo
     * @return Nos indica si el registro se guardo correctamente o no
     */
    public static boolean guardar(String nombre,String clave, Double precio, Proveedor proveedor){
    boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        
            Articulo articulo = new Articulo();
            articulo.getNombre();
            articulo.getClave();
            articulo.getPrecio();
            articulo.getProveedor();
            session.save(articulo);
        
            session.getTransaction().commit();
        resultado = proveedor.getId() !=0;
        }catch(Exception ex){
            System.out.println("Ocurrio un error: " + ex.getMessage());
        }
    return resultado;
    }
}
