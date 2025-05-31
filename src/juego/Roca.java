package juego;
import entorno.Entorno;
import java.awt.Image;
import entorno.Herramientas;

public class Roca {
	// ATRIBUTOS
	private int x;
	private int y;
	private int ancho;
	private int altura;
    private Image imagen;
	private String [] imagenes= {
			("imagenes\\\\roca1.png"),
			("imagenes\\\\roca2.png"),
			("imagenes\\\\roca1.png"),
	};
	
//	Constructor
	public Roca(int x, int y, int tipo) {
		this.x = x;
		this.y = y;
		this.ancho = 50;
		this.altura = 50;
        this.imagen = Herramientas.cargarImagen(imagenes[tipo]);
	}
	
	// Dibujar imagen
	public void dibujarImagenRoca(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, 0, 3.5);
	}
	
//---------------------- BORDES DE LAS ROCAS -----------------------
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