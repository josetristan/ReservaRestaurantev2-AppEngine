package com.jtristan.reservarestaurantev2.entidades;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;



@PersistenceCapable
public class Oferta {

	public enum Dias{LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO}
				
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)	
	private Key id;
	private String descripcion;
	private List<Dias> diasDisponibles;
	//Propiedad multivalor
	private List<String> nombreRestaurante;
		
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Dias> getDiasDisponibles() {
		return diasDisponibles;
	}
	public void setDiasDisponibles(List<Dias> diasDisponibles) {
		this.diasDisponibles = diasDisponibles;
	}	
	public List<String> getNombreRestaurante() {
		return nombreRestaurante;
	}
	public void setNombreRestaurante(List<String> nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	
	
	
	

}
