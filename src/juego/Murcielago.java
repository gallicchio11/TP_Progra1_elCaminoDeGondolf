package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Murcielago {
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int velocidad;
	private String direccion;

//	Constructor
	public Murcielago(int x, int y, String direccion) {
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		this.ancho = 20;
		this.altura = 20;
		this.velocidad = 1;
	}
	
//	Getters y Setters
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
	
//	Movimiento Murcielago
	public void mover() {
		if(this.direccion == "izquierda") { //Si se encuentran a la izquierda, se moveran a la derecha
			this.x = x + velocidad;
		}else if(this.direccion == "derecha") {//Si se encuentran a la derecha, se moveran a la izquierda
			this.x = x - velocidad;
		}else if(this.direccion == "arriba") {//Si se encuentran arriba, se moveran abajo
			this.y = y + velocidad;
		}else { //Si se encuentran abajo, se moveran arriba
			this.y = y - velocidad;
		}
		
	}
	
	
	public void moverDerecha() { 
		this.x = x + velocidad;
	}
	public void moverIzquierda() {
		this.x = x - velocidad;
	}
	public void moverAbajo() {
		this.y = y + velocidad;
	}
	public void moverArriba() {
		this.y = y - velocidad;
	}
	public void movimientoMurcielago(int posXM , int posYM ) {
		if(this.ancho == posXM){
			moverAbajo();	
		}
	}
//	
//	Dibujar
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y,ancho, altura,0, Color.YELLOW);
	}
	
}