package juego;
import java.awt.Color;
import entorno.Entorno;

public class Menu {
	private int x;
	private int y;
	private int ancho;
	private int altura;
	
//	Constructor
	public Menu(int anchoVentana, int alturaVentana){
		this.x = anchoVentana - this.ancho /2 ;
		this.y = alturaVentana/2;
		this.ancho = anchoVentana - anchoVentana/2;
		this.altura = alturaVentana;

	}

//	Dibujar
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.altura,0, Color.red);
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

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
}