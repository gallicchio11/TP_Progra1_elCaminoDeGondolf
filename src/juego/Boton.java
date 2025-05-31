package juego;
import java.awt.Image;
import entorno.Entorno;

public class Boton {
	// ATRIBUTOS
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private Image imagenS;
    private Image imagenDS;

	private Boolean seleccionado;

	// Constructor
	public Boton(int x, int y,Image imagenA,Image imagenB ) {
		this.x = x;
		this.y = y;
		this.ancho = 120;
		this.altura = 67;
		this.seleccionado = false;
		this.imagenS = imagenA;
		this.imagenDS = imagenB;
	}
	
	// Funcion para saber si el Mouse está dentro del botón
	public boolean estaDentro(int mouseX, int mouseY) {
		return mouseX >= x - ancho / 2 && 
			   mouseX <= x + ancho / 2 &&
			   mouseY >= y - altura / 2 && 
			   mouseY <= y + altura / 2;
	}
	
//-------------------------- ESTADO DEL BOTÓN -----------------------
	public boolean estadoActual() {
		return this.seleccionado;
	}
	public void cambiarEstado() {
	    this.seleccionado = !this.seleccionado;
	}

//-------------------- DIBUJAR IMAGEN DEL BOTÓN -----------------------------
	public void dibujarnImagenBoton(Entorno entorno) {
		if(this.estadoActual()){
			entorno.dibujarImagen(this.imagenDS, this.x, this.y, 0, 2.5);
		}
		else {
			entorno.dibujarImagen(this.imagenS, this.x, this.y, 0, 2.7);
		}
	}
}