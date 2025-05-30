package juego;
import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Menu {
	private int x;
	private int y;
	private int ancho;
	private int altura;
    private Image imagen;

//	Constructor
	public Menu(int anchoVentana, int alturaVentana){ //AnchoVentana = 900 ; alturaVentana = 600
		this.ancho = (anchoVentana - anchoVentana/2) / 2; // (900-450) / 2 = 225
		this.altura = alturaVentana; // 600
		this.x = anchoVentana - this.ancho /2 ; // sería 900 - (225 /2) = 900-112.5 = 788
		this.y = alturaVentana / 2; // sería 600/2 = 300 
        this.imagen = Herramientas.cargarImagen("imagenes\\\\menu.png");

	}

//	Dibujar
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.altura,0, Color.red);
	}
//	Dibujar imagen
	public void dibujarImagenMenu(Entorno entorno) {
		entorno.dibujarImagen(this.imagen,this.x, this.y, 0, 3);
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
//	Boton Hechizo agua
	public boolean hechizoAgua() {
		return true;
	}
	
//	Boton Hechizo fuego
	public boolean hechizoFuego() {
		return true;
	}

}