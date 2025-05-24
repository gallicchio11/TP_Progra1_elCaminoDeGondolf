package juego;
import java.awt.Color;
import entorno.Entorno;

import java.awt.Image;
import entorno.Herramientas;



public class Hechizos {

//	Atributos
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int velocidad;
	private String direccion;
	private String nombre;
	private int areaDeEfecto;
	private int costoEnergia;
	
//	Constructor
	public Hechizos(int x, int y, String direccion) {
		this.x = x;
		this.y = y;
		this.ancho = 20;
		this.altura = 20;
		this.velocidad = 10;
		this.direccion = direccion;
		this.nombre = nombre;
		this.areaDeEfecto = areaDeEfecto;
		this.costoEnergia = costoEnergia;
	}
	
	
	
//---------------Getters y Setters -------------
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAreaDeEfecto() {
		return areaDeEfecto;
	}
	public void setAreaDeEfecto(int areaDeEfecto) {
		this.areaDeEfecto = areaDeEfecto;
	}
	public int getCostoEnergia() {
		return costoEnergia;
	}
	public void setCostoEnergia(int costoEnergia) {
		this.costoEnergia = costoEnergia;
	}


	// 	Dibujar Hechizo 
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y,ancho, altura,0, Color.red);
	}
	
// 	Movimientos Hechizos
	public void mover() {
		if(this.direccion.equals("derecha")) { //Movimiento Derecha
			this.x = x + velocidad;
		}else if(this.direccion.equals("izquierda")) { //Movimiento Izquierda
			this.x = x - velocidad;
		}else if(this.direccion.equals("arriba")) { //Movimiento Arriba
			this.y = y - velocidad;
		}else {
		    this.y = y + velocidad; //Movimiento Abajo
		}
	}
		
//	Bordes/Limites del Hechizo
	public int limiteSuperior() {
		return this.y - this.altura/2 ;
	}
	public int limiteInferior() {
		return this.y + this.altura/2;
	}
	public int limiteIzquierdo() {
		return this.x - this.ancho/2;
	}
	public int limiteDerecho() {
		return this.x + this.ancho/2;
	}




}

