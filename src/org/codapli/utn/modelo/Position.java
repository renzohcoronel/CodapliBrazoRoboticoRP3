package org.codapli.utn.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombre;
	private float angule;
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;

	private Position() {
	}

	public Position(String nombre, float angule) {
		this();
		this.nombre = nombre;
		this.angule = angule;
		this.timeStamp = new Date();
	}

	public Position(Long id, String nombre, float angule) {
		this();
		this.id = id;
		this.nombre = nombre;
		this.angule = angule;
		this.timeStamp = new Date();
	}

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

	public float getAngule() {
		return angule;
	}

	public void setAngule(float angule) {
		this.angule = angule;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Posicion [id=" + id + ", nombre=" + nombre + ", angule=" + angule + ", timeStamp=" + timeStamp + "]";
	}

}
