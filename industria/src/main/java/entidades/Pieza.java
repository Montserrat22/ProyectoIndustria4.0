/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jaker
 */
@Entity
@Table(name = "pieza")
@NamedQueries({
    @NamedQuery(name = "Pieza.findAll", query = "SELECT p FROM Pieza p"),
    @NamedQuery(name = "Pieza.findById", query = "SELECT p FROM Pieza p WHERE p.id = :id"),
    @NamedQuery(name = "Pieza.findByIdProveedor", query = "SELECT p FROM Pieza p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "Pieza.findByNombre", query = "SELECT p FROM Pieza p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Pieza.findByDescripcion", query = "SELECT p FROM Pieza p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Pieza.findByPreciou", query = "SELECT p FROM Pieza p WHERE p.preciou = :preciou"),
    @NamedQuery(name = "Pieza.findByStatus", query = "SELECT p FROM Pieza p WHERE p.status = :status")})
public class Pieza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Short id;
    @Column(name = "id_proveedor")
    private Short idProveedor;
    @Size(max = 99)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 99)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preciou")
    private BigDecimal preciou;
    @Column(name = "status")
    private Short status;
    @OneToMany(mappedBy = "idPieza")
    private Collection<Inventario> inventarioCollection;

    public Pieza() {
    }

    public Pieza(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Short idProveedor) {
        this.idProveedor = idProveedor;
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

    public BigDecimal getPreciou() {
        return preciou;
    }

    public void setPreciou(BigDecimal preciou) {
        this.preciou = preciou;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pieza)) {
            return false;
        }
        Pieza other = (Pieza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pieza[ id=" + id + " ]";
    }
    
}
