package com.jtristan.reservarestaurantev2.entidades;

import java.util.Set;

import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;


@PersistenceCapable
public class Restaurante {

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;	
	private String nombre;	
	private String estrellas;
					
	@Persistent(defaultFetchGroup="true")
	@Embedded
	private Direccion direccion;
	
	@Unowned
	private Set<Carta> carta;
	@Unowned	
	private Set<Oferta> ofertas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstrellas() {
		return estrellas;
	}
	public void setEstrellas(String estrellas) {
		this.estrellas = estrellas;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public Set<Carta> getCarta() {
		return carta;
	}
	public void setCarta(Set<Carta> carta) {
		this.carta = carta;
	}
	public Set<Oferta> getOfertas() {
		return ofertas;
	}
	public void setOfertas(Set<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	
	
	
	


}
