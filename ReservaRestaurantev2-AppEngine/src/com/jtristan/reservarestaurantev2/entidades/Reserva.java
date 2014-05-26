package com.jtristan.reservarestaurantev2.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reserva {

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;	
		
	private String nombreUsuario;	
	
	//Desnormalización campos restaurante
	private String nombreRestaurante;	
	private String estrellas;
	
	private String direccionTipo;
	private String direccionCalle;	
	private int direccionNumero;
			
	//Propiedad multivalores
	private List<String> nombreComensales = new ArrayList<String>();
		
	private Date horaReserva;	
	private int numeroPersonas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getNombreRestaurante() {
		return nombreRestaurante;
	}
	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}
	public String getEstrellas() {
		return estrellas;
	}
	public void setEstrellas(String estrellas) {
		this.estrellas = estrellas;
	}
	public String getDireccionTipo() {
		return direccionTipo;
	}
	public void setDireccionTipo(String direccionTipo) {
		this.direccionTipo = direccionTipo;
	}
	public String getDireccionCalle() {
		return direccionCalle;
	}
	public void setDireccionCalle(String direccionCalle) {
		this.direccionCalle = direccionCalle;
	}
	public int getDireccionNumero() {
		return direccionNumero;
	}
	public void setDireccionNumero(int direccionNumero) {
		this.direccionNumero = direccionNumero;
	}	
	public Date getHoraReserva() {
		return horaReserva;
	}
	public void setHoraReserva(Date horaReserva) {
		this.horaReserva = horaReserva;
	}
	public int getNumeroPersonas() {
		return numeroPersonas;
	}
	public void setNumeroPersonas(int numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public List<String> getNombreComensales() {
		return nombreComensales;
	}
	public void setNombreComensales(List<String> nombreComensales) {
		this.nombreComensales = nombreComensales;
	}
	
	
}
