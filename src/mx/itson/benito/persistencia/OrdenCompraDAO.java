/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.OrdenCompra;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.enumerador.Estado;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Jesus Javier Quintero Fierro
 */
public class OrdenCompraDAO {
    public static List<OrdenCompra> obtenerTodos(){
        List<OrdenCompra> ordenesCompras = new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<OrdenCompra> criteriaQuery = session.getCriteriaBuilder().createQuery(OrdenCompra.class);
            criteriaQuery.from(OrdenCompra.class);
            ordenesCompras = session.createQuery(criteriaQuery).getResultList();
        }catch(Exception ex){
            System.err.println("Ocurrio un error; "+ ex.getMessage());
        }
    return ordenesCompras;
    }
    
   public static boolean eliminar(int id){
       boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            OrdenCompra ordenCompra = session.get(OrdenCompra.class, id);
            if(ordenCompra!=null){
                session.delete(ordenCompra);
                session.getTransaction().commit();
            resultado = true;
            }
        }catch(HibernateException ex){
             System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
   public static boolean editar(int id, Date fecha,Articulo articulo, Proveedor proveedor,Estado estado){
    boolean resultado = false;
    try{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        OrdenCompra ordenCompra = session.get(OrdenCompra.class, id);
        if(ordenCompra != null){
            ordenCompra.setFecha(fecha);
            ordenCompra.setArticulo(articulo);
            ordenCompra.setProveedor(proveedor);
            ordenCompra.setEstado(estado);
            session.saveOrUpdate(ordenCompra);
            session.getTransaction().commit();
            resultado = true;
            }       
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
}
