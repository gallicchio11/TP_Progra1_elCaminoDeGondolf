package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Boton {
//	Atributos
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private Image imagenS;
    private Image imagenDS;

	private Boolean seleccionado;

//	Constructor
	public Boton(int x, int y,Image imagenA,Image imagenB ) {
		this.x = x;
		this.y = y;
		this.ancho = 32;
		this.altura = 32;
		this.seleccionado = false;
		this.imagenS = imagenA;
		this.imagenDS = imagenB;

	}
	
// Booleano para saber si el Mouse está dentro del boton
	public boolean estaDentro(int mouseX, int mouseY) {
		return mouseX >= x - ancho / 2 && 
			   mouseX <= x + ancho / 2 &&
			   mouseY >= y - altura / 2 && 
			   mouseY <= y + altura / 2;
	}
	
	public void seleccionar() {
		this.seleccionado = true;		
	}

	public void deseleccionar() {
		this.seleccionado = false;
	}
	public boolean estadoActual() {
		return this.seleccionado;
	}
	public void cambiarEstado() {
	    this.seleccionado = !this.seleccionado;
	}

//	Dibujar boton
	public void dibujarBotonSeleccionado(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, altura, 0, Color.GREEN);
	}
	public void dibujarBotonDeseleccionado(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, altura,0, Color.RED);
	}

	public void dibujarnImagenBoton(Entorno entorno) {
		if(this.estadoActual()){
			entorno.dibujarImagen(this.imagenDS, this.x, this.y, 0, 2.5);
		}
		else {
			entorno.dibujarImagen(this.imagenS, this.x, this.y, 0, 2.7);
		}
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
	

}
