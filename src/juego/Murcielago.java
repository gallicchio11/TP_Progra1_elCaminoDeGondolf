package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Murcielago {
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int velocidad;
	private String direccion;
	
// variables de imagenes
	private Image imagen;
	private int contadorFrames = 0;
	private int velocidadFrames = 6;
	private int frameActual = 0;

	private String[] movimientoDerechaMurcielago ;
 String[] movimientoIzquierdaMurcielago;
//	Constructor
	public Murcielago(int x, int y, String direccion, int velocidad,  String[] framesDerecha, String[] framesIzquierda) {
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		this.ancho = 20;
		this.altura = 20;
		this.velocidad = velocidad;
		
		//definimos para nuestro murcielago los array bde frames para cada lado
		this.movimientoIzquierdaMurcielago = framesIzquierda;
		this.movimientoDerechaMurcielago = framesDerecha;

		//cargamos las imagenes rercibidas para cada lado
		this.imagen = Herramientas.cargarImagen(framesDerecha[0]);
		this.imagen = Herramientas.cargarImagen(framesIzquierda[0]);


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
//	Bordes/Limites del murciélago
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
	
//reutilizamos logica de animar mago
	public void actualizarAnimacion(int posicionMagoX) {
	    String[] frames;  // nuestro array de imagenes
		
		if (this.x > posicionMagoX) {
		    // si esta a la dereecha el mago, mira para la izquierda
		frames = movimientoIzquierdaMurcielago;
		} else {
		    // si está a la izquierda el mago, izquierda, mira para la derecha
		frames = movimientoDerechaMurcielago;
		}

		contadorFrames++;
		if (contadorFrames >= velocidadFrames) {
			this.imagen = Herramientas.cargarImagen(frames[frameActual]);
			frameActual++;
			if (frameActual >= frames.length) {
			    frameActual = 0;
			}
	        contadorFrames = 0;  // reiniciamos los frames

		}
	}
	
	//metodo de dibujo para el murcielago
	public void dibujarImagen(Entorno entorno) {
	    entorno.dibujarImagen(this.imagen, this.x, this.y, 0,2);
	}

}