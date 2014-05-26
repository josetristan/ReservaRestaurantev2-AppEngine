package com.jtristan.reservarestaurantev2.entidades;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.datanucleus.api.jpa.annotations.Extension;


@PersistenceCapable
public class Carta {

	public enum TipoDePlato{ENTRANTE, PRIMER_PLATO, SEGUNDO_PLATO, POSTRE, OTROS}
		
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.pk-name"	, value="true")	
    private String id;
	
	
	private TipoDePlato tipoPlato;
	private String nombre;	
	private double precio;
	
	public TipoDePlato getTipoPlato() {
		return tipoPlato;
	}
	public void setTipoPlato(TipoDePlato tipoPlato) {
		this.tipoPlato = tipoPlato;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
