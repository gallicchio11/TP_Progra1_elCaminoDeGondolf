package juego;
import java.awt.Color;
import entorno.Entorno;

public class Mago {
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int velocidad;
	

//	Constructor
	public Mago() {
		this.x = 500;
		this.y = 300;
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
