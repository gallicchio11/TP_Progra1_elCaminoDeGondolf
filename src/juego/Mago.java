package juego;
import java.awt.Color;
import java.awt.Menu;

import entorno.Entorno;

public class Mago {
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int velocidad;
	

//	Constructor
	public Mago(int anchoVentana, int alturaVentana, int x) {
		this.x = anchoVentana/2  ;
		this.y = alturaVentana/2;
		this.ancho = 30;
		this.altura = 30;
		this.velocidad = 5;
	}
	
//	-----------Getter y Setter--------------
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
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getVelocidad() {
		return velocidad;
	}
//	----------------------------------------
	
//	Dibujar
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.altura,0, Color.BLUE);
	}
	
//------------Bordes/limites del mago----------
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
	
//	Colision Mago-Roca
	//Esto nos sirve para determinar si el mago colisiona
	//con las rocas.
	public boolean colisionaRoca(Roca[] rocas) {  
		for(int i =0; i < rocas.length;i++) {
			if (this.limiteIzquierdo() < rocas[i].limiteDerecho() &&
				this.limiteSuperior() < rocas[i].limiteInferior() &&
				this.limiteInferior() > rocas[i].limiteSuperior() &&
				this.limiteDerecho() > rocas[i].limiteIzquierdo()) {
				return true; // Si colisiona es true
			}	
			
		}
		return false; // sino, no colisiona
	}
	
	
	
//	Movimientos
	public void moverDerecha() {
		this.x = x + velocidad;
	}
	
	public void moverIzquierda() {
		this.x = x - velocidad;
	}
	
	public void moverArriba() {	
		this.y = y - velocidad;
	}
	
	public void moverAbajo() {
		this.y = y + velocidad;	
	}
	
	
}
