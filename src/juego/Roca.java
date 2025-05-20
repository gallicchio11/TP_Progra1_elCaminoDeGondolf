package juego;
import java.awt.Color;
import entorno.Entorno;

import java.awt.Image;
import entorno.Herramientas;

public class Roca {
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int rocaTipo;
    private Image imagen;
	private String [] imagenes= {
			("roca1.png"),
			("roca2.png"),
			("roca1.png"),
	};
	
//	Constructor
	public Roca(int x, int y, int tipo) {
		this.x = x;
		this.y = y;
		this.ancho = 50;
		this.altura = 50;
		this.rocaTipo  = tipo;
        this.imagen = Herramientas.cargarImagen(imagenes[tipo]);

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
    public int getTipo() {
        return rocaTipo;
    }

    public String getNombreImagen() {
            return imagenes[this.rocaTipo];
 
    }



//	Dibujar imagen
	public void dibujarImagenRoca(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, 0, 3.5);
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
