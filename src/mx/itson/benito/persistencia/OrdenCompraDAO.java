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
 * Contiene la persistencia data access object del objeto orden de compra
 * @author Jesus Javier Quintero Fierro
 */
public class OrdenCompraDAO {
    /**
     * Lista de ordenes de compra con sus atributos.
     * @return Retorna una lista de ordenes de compra.
     */
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
    
   /**
     * Elimina un registro de una orden de compra en la base de datos
     * @param id Id de la orden de compra
     * @return Nos indica si el registro se elimino correctamente o no.
     */
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
   
   /**
     * Edita un registro de una orden de compra en la base de datos
     * @param id Id de la orden de compra
     * @param fecha Fecha de la orden de compra
     * @param articulo Articulo de la orden de compra
     * @param proveedor Proveedor de la orden de compra
     * @param estado Estado de la orden de compra
     * @param cantidad Cantidad de articulos pedidos en la orden de compra
     * @return Nos indica si el registro se guardo correctamente o no
     */
   public static boolean editar(int id, Date fecha,Articulo articulo, Proveedor proveedor,Estado estado,int cantidad){
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
            ordenCompra.setCantidad(cantidad);
            session.saveOrUpdate(ordenCompra);
            session.getTransaction().commit();
            resultado = true;
            }       
        }catch(HibernateException ex){
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
   
   /**
     * Guarda un registro de una orden de compra en la base de datos
     * @param fecha Fecha de la orden de compra
     * @param articulo Articulo de la orden de compra
     * @param proveedor Proveedor de la orden de compra
     * @param estado Estado de la orden de compra
     * @param cantidad Cantidad de articulos pedidos en la orden de compra
     * @return Nos indica si el registro se guardo correctamente o no
     */
   public static boolean guardar(Date fecha,Articulo articulo,Proveedor proveedor,Estado estado,int cantidad){
    boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        
            Orden_Compra ordenCompra = new Orden_Compra();
            ordenCompra.getFecha();
            ordenCompra.getArticulo();
            ordenCompra.getProveedor();
            ordenCompra.getEstado();
            ordenCompra.getCantidad();
            session.save(ordenCompra);
        
            session.getTransaction().commit();
        resultado = ordenCompra.getId() !=0;
        }catch(Exception ex){
            System.out.println("Ocurrio un error: " + ex.getMessage());
        }
    return resultado;
    }
}
