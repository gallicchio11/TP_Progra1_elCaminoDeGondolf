package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Murcielago {
	// ATRIBUTOS
	private int x;
	private int y;
	private int ancho;
	private int altura;
	
	// Variables de imagenes
	private Image imagen;
	private int contadorFrames = 0;
	private int velocidadFrames = 6;
	private int frameActual = 0;

	// Animacion de movimiento izquierda y derecha
	private String[] movimientoDerechaMurcielago ;
	private String[] movimientoIzquierdaMurcielago;
	
	// Constructor
	public Murcielago(int x, int y, String direccion, int velocidad,  String[] framesDerecha, String[] framesIzquierda) {
		this.x = x;
		this.y = y;
		this.ancho = 20;
		this.altura = 20;
		
		//definimos para nuestro murcielago los array bde frames para cada lado
		this.movimientoIzquierdaMurcielago = framesIzquierda;
		this.movimientoDerechaMurcielago = framesDerecha;

		//cargamos las imagenes rercibidas para cada lado
		this.imagen = Herramientas.cargarImagen(framesDerecha[0]);
		this.imagen = Herramientas.cargarImagen(framesIzquierda[0]);
	}
	
//--------------------------- GETTERS Y SETTERS ---------------------------
	// X
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	// Y
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

//------------------ Actualizar Animacion ------------------------------------
	public void actualizarAnimacion(int posicionMagoX) {
	    String[] frames;  // Nuestro array de imagenes
		if (this.x > posicionMagoX) { // Si está a la derecha el mago, mira para la izquierda
			frames = movimientoIzquierdaMurcielago;
		} else { // Si está a la izquierda el mago, mira para la derecha
			frames = movimientoDerechaMurcielago;
		}
		contadorFrames++; // Aumentamos frames
		if (contadorFrames >= velocidadFrames) { // Si el contador es mayor a la velocidad
			this.imagen = Herramientas.cargarImagen(frames[frameActual]); // entonces que cargue la imagen
			frameActual++; // Aumentamos los frames
			if (frameActual >= frames.length) { 
			    frameActual = 0;
			}
	        contadorFrames = 0;  // reiniciamos los frames
		}
	}

	// Método de dibujo para el murcielago
	public void dibujarImagen(Entorno entorno) {
	    entorno.dibujarImagen(this.imagen, this.x, this.y, 0,2);
	}

// --------------------- BORDES DEL MURCIELAGO ------------------------
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