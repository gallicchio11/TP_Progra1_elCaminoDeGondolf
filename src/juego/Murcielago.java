package juego;

import java.awt.Color;

import entorno.Entorno;

public class Murcielago {
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int velocidad;

	//	Constructor
	public Murcielago(int x, int y) {
		this.x = x;
		this.y = y;
		this.ancho = 20;
		this.altura = 20;
		this.velocidad = 2;
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
	public void moverDerecha() {
		this.x = x + velocidad;
	}
	public void moverIzquierda() {
		this.x = x - velocidad;
	}
	
//	Dibujar
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y,ancho, altura,0, Color.YELLOW);
	}

	
}
