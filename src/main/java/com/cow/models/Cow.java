package com.cow.models;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;


@Entity
@Table(name = "cow", schema = "public")
public class Cow implements Serializable {
	
	public Cow(){}

	public Cow(String collarId,String cowNumber) {
		this.collarId = collarId;
		this.cowNumber = cowNumber;
	}
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true)
	private String id;
	
	@NotNull
	private String collarId;
	
	@NotNull
	private String cowNumber;
	private String collarStatus;
	private HashMap<String,Double> lastLocation;
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCollarId() {
		return collarId;
	}
	public void setCollarId(String collarId) {
		this.collarId = collarId;
	}
	public String getCowNumber() {
		return cowNumber;
	}
	public void setCowNumber(String cowNumber) {
		this.cowNumber = cowNumber;
	}
	public String getCollarStatus() {
		return collarStatus;
	}
	public void setCollarStatus(String collarStatus) {
		this.collarStatus = collarStatus;
	}
	public HashMap<String, Double> getLastLocation() {
		return lastLocation;
	}
	public void setLastLocation(HashMap<String, Double> lastLocation) {
		this.lastLocation = lastLocation;
	}
	
	

}
