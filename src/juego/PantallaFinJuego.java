package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

// Clase que utilzaremos para mostrar el final del juego, si el jugador gana o pierde.
public class PantallaFinJuego {
	// ATRIBUTOS
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private Image imagenFin;
	private int estadoFin;
	
	// Constructor
	public PantallaFinJuego(int anchoVentana, int alturaVentana, int finDeJuego) {
		this.ancho = anchoVentana;
		this.altura = alturaVentana;
		this.x = anchoVentana / 2;
		this.y = alturaVentana / 2;
		this.estadoFin = finDeJuego;

		if (estadoFin == 1) {
			this.imagenFin = Herramientas.cargarImagen("imagenes\\\\pantalla_ganaste.png");
		} else {
			this.imagenFin = Herramientas.cargarImagen("imagenes\\\\pantalla_perdiste.png");
		}
	}
	
	// Para salir del Juego
	public void salirJuego(Entorno entorno) {
		if(estadoFin == 2) {
			if(entorno.sePresiono(entorno.TECLA_ESCAPE)) {
				System.exit(0);
			}
		}
	}
	
	// Dibujamos la imagen del Fin Juego
	public void dibujarImagenFinJuego(Entorno entorno) {
		entorno.dibujarImagen(this.imagenFin, this.x, this.y, 0, 1.5);
	}

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
	
	// Ancho
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	// Altura
	public int getAltura() {
		return altura;
	}
	
}
