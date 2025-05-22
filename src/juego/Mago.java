package juego;
import java.awt.Color;
import java.awt.Image;
import java.awt.Menu;

import entorno.Entorno;
import entorno.Herramientas;

public class Mago {
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int velocidad;
    private Image imagen;
	private String [] imagenes= {
			("mago.png"),
			("mago2.png"),
	};

//	Constructor
	public Mago(int anchoMenu, int alturaVentana) {
		this.ancho = 30;
		this.altura = 30;
		this.x = anchoMenu + anchoMenu/2 ; // 225 + 112.5 = 338 aprox
		this.y = (alturaVentana/2) - this.altura/2; // (600/2) - (30/2) --> 300 - 15 = 285
		this.velocidad = 5;
        this.imagen = Herramientas.cargarImagen("mago.png");

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
	
	public void setImagenMago(Image imagen) {
		this.imagen = imagen;
	}
	public Image getImagen() {
		return this.imagen = imagen;
	}
//	----------------------------------------
	
//	Dibujar
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.altura,0, Color.BLUE);
	}
//	Dibujar imagen
	public void dibujarImagenMago(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, 0, 1.5);
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