package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

//clase que utilzaremos para mostrar el final del juego, si el jugador gana o pierde.
public class PantallaFinJuego {
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private Image imagenFin;
	private int estadoFin;

	public PantallaFinJuego(int anchoVentana, int alturaVentana, int finDeJuego) {
		this.ancho = anchoVentana;
		this.altura = alturaVentana;
		this.x = anchoVentana / 2;
		this.y = alturaVentana / 2;
		this.estadoFin = finDeJuego;

		if (estadoFin == 1) {
			this.imagenFin = Herramientas.cargarImagen("pantalla_ganaste.png");
		} else {
			this.imagenFin = Herramientas.cargarImagen("pantalla_perdiste.png");
		}
	}
	
	public void salirJuego(Entorno entorno) {
		if(estadoFin == 2) {
			if(entorno.sePresiono(entorno.TECLA_ESCAPE)) {
				System.exit(0);
			}
		}
	}

	public void dibujarImagenFinJuego(Entorno entorno) {
		entorno.dibujarImagen(this.imagenFin, this.x, this.y, 0, 1.5);
	}
	
}
