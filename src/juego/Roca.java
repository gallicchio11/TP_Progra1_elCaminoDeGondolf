package juego;
import java.awt.Color;
import entorno.Entorno;

public class Roca {
	private int x;
	private int y;
	private int ancho;
	private int altura;

	
//	Constructor
	public Roca(int x, int y) {
		this.x = x;
		this.y = y;
		this.ancho = 30;
		this.altura = 30;
	}
//-------------Getters y Setters-----------------
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
//	---------------------------

//	Dibujar
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y,ancho, altura,0, Color.GREEN);
	}
	
//	Bordes/limites de la Roca
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
