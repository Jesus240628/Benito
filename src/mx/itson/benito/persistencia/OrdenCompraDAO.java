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
import mx.itson.benito.entidades.Orden_Compra;
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
    public static List<Orden_Compra> obtenerTodos(){
        List<Orden_Compra> ordenesCompras = new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Orden_Compra> criteriaQuery = session.getCriteriaBuilder().createQuery(Orden_Compra.class);
            criteriaQuery.from(Orden_Compra.class);
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
            Orden_Compra ordenCompra = session.get(Orden_Compra.class, id);
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
        Orden_Compra ordenCompra = session.get(Orden_Compra.class, id);
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
   public static boolean guardar(Date fecha,Articulo articulo,Proveedor proveedor,Estado estado){
    boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        
            Orden_Compra ordenCompra = new Orden_Compra();
            ordenCompra.getFecha();
            ordenCompra.getArticulo();
            ordenCompra.getProveedor();
            ordenCompra.getEstado();
            session.save(ordenCompra);
        
            session.getTransaction().commit();
        resultado = ordenCompra.getId() !=0;
        }catch(Exception ex){
            System.out.println("Ocurrio un error: " + ex.getMessage());
        }
    return resultado;
    }
}
