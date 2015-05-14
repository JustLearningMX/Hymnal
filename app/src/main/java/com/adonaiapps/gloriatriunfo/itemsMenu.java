package com.adonaiapps.gloriatriunfo;

import android.graphics.drawable.Drawable;

public class itemsMenu {

	private String Nombre;
	private String Descripcion;
	private Drawable img;
	private int id;
	
	public itemsMenu(int id, String nombre, String descripcion, Drawable img) {
		super();
		this.id=id;
		this.Nombre = nombre;
		this.Descripcion = descripcion;
		this.img = img;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public Drawable getImg() {
		return img;
	}
	public void setImg(Drawable img) {
		this.img = img;
	}
}
