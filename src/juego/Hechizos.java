package juego;
import java.awt.Color;
import entorno.Entorno;

import java.awt.Image;
import entorno.Herramientas;



public class Hechizos {

//	Atributos
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int velocidad;
	private double direccion;
	private String nombre;
	private int areaDeEfecto;
	private int costoEnergia;
	private int tipoHechizo;
	
	
    private int vx, vy;// variables para la velocidad de la treayectoria

    private Image imagen;
  //variables de animacion para movimiento  
    private int contadorFrames = 0;
    private int velocidadFrames = 10;
    
    //  nos determinara el fram actual del movimiento hacia la direccion
    private int frameAbajo = 0;
    private int frameArriba = 0;
    private int frameIzquierda = 0;
    private int frameDerecha = 0;
    
	private double angulo =1;

    //nuestras listas de imagenes para cada movimiento(4 direcciones de momento) 
    private String[] movimientoHechizo = {//frames para abajo
    	    "fuego1.png",
    	    "fuego2.png",
    	    "fuego3.png",
    	    "fuego4.png",
    	    "fuego5.png",
    	    "fuego6.png",
    	    "fuego7.png",
    	    "fuego8.png"
    	};

    	private String[] movimientoArribaHechizo = {//frames para arriba
    	    "caminar_arriba1.png",
    	    "caminar_arriba2.png",
    	    "caminar_arriba3.png",
    	    "caminar_arriba4.png"
    	};

    	private String[] movimientoIzquierdaHechizo = {//frames para la izquierda
    	    "caminar_izquierda1.png",
    	    "caminar_izquierda2.png",
    	    "caminar_izquierda3.png",
    	    "caminar_izquierda4.png"
    	};

    	private String[] movimientoDerechaMago = {//frames para la derecha
    	    "caminar_derecha1.png",
    	    "caminar_derecha2.png",
    	    "caminar_derecha3.png",
    	    "caminar_derecha4.png"
    	};

    private String[] DireccionBolaFuego = {//frames para abajo
    	    "fuego1.png",
    	    "fuego2.png",
    	    "fuego3.png",
    	    "fuego4.png",
    	    "fuego5.png",
    	    "fuego6.png",
    	    "fuego7.png",
    	    "fuego8.png"
    	};
//	Constructor
	public Hechizos(int xInicio, int yInicio, int xDestino, int yDestino) {
	    this.x = xInicio;
	    this.y = yInicio;
		this.ancho = 20;
		this.altura = 20;
		this.velocidad = 10;
	    calcularTrayectoria(xInicio, yInicio, xDestino, yDestino);
        this.imagen = Herramientas.cargarImagen("fuego1.png");
	}
	
	private int animarMovimiento(String[] frames, int frameActual) {
	    contadorFrames++; // // aumentamos un contador de frames para generar delay entre frame y frame

	    if (contadorFrames >= velocidadFrames) {// cuando llegue a 5 se aplicara el siguiente frame
	        this.imagen = Herramientas.cargarImagen(frames[frameActual]); //cargamos el frame desde el array de imagenes "movimientoAbajoMago" 
	        frameActual++; // aumenta frame
	        
	        if (frameActual >= frames.length) {// si el Frame abajo es  mayor o igual a el tamaño total de nuestro array de frames, volvera a 0
	            frameActual = 0; // reiniciamos nuestro frame actual para volver al inicio(un bucle)
	        }
	        contadorFrames = 0; // reiniciamos el delay entre frame y frame
	    }
	    return frameActual; // nos devolvera el frame actual
	}
	
//---------------Getters y Setters -------------
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
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public double getDireccion() {
		return direccion;
	}
	public void setDireccion(double direccion) {
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAreaDeEfecto() {
		return areaDeEfecto;
	}
	public void setAreaDeEfecto(int areaDeEfecto) {
		this.areaDeEfecto = areaDeEfecto;
	}
	public int getCostoEnergia() {
		return costoEnergia;
	}
	public void setCostoEnergia(int costoEnergia) {
		this.costoEnergia = costoEnergia;
	}

	// 	Dibujar Hechizo 
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y,ancho, altura,0, Color.red);
	}
	//	Dibujar imagen Hechizo
	public void dibujarImagenFuego(Entorno entorno, int angulo) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, this.angulo, 2);
	}
	
//  Trayectoria Hechizo
    private void calcularTrayectoria(int xInicio, int yInicio, int xFin, int yFin) {
        int dx = xFin - xInicio;
        int dy = yFin - yInicio;
        int distanciaCuadrada = dx * dx + dy * dy;
        if (distanciaCuadrada > 0) {
            int distancia = (int) Math.sqrt(distanciaCuadrada);
            int escala = 10; // Velocidad constante
            this.vx = dx * escala / distancia;
            this.vy = dy * escala / distancia;
            
            // calculamos el angulo engrados 
            double anguloRad = Math.atan2(dy, dx);
            double anguloGrados = (int) Math.round(Math.toDegrees(anguloRad));
            //evitamos que sea un resultado negativo(me cuesta encontrar una logica para calcular el angulo con un numero negativo)
            anguloGrados = (anguloGrados + 360) % 360;  // Versión segura para evitar negativos

            if ((anguloGrados >= 348.75 && anguloGrados < 360) || (anguloGrados >= 0 && anguloGrados < 11.25)) {
                direccion = 4.75; // derecha
            } else if (anguloGrados >= 11.25 && anguloGrados < 33.75) {
                direccion = 5.0; // derecha-arriba
            } else if (anguloGrados >= 33.75 && anguloGrados < 56.25) {
                direccion = 5.25; // arriba-derecha
            } else if (anguloGrados >= 56.25 && anguloGrados < 78.75) {
                direccion = 0.25; // casi arriba
            } else if (anguloGrados >= 78.75 && anguloGrados < 101.25) {
                direccion = 0; // abajo
            } else if (anguloGrados >= 101.25 && anguloGrados < 123.75) {
                direccion = 0.75; // Arriba-izquierda
            } else if (anguloGrados >= 123.75 && anguloGrados < 146.25) {
                direccion = 1.0; // izquierda-arriba
            } else if (anguloGrados >= 146.25 && anguloGrados < 168.75) {
                direccion = 1.25; // casi izquierda
            } else if (anguloGrados >= 168.75 && anguloGrados < 191.25) {
                direccion = 1.5; // izquierda
            } else if (anguloGrados >= 191.25 && anguloGrados < 213.75) {
                direccion = 1.75; // izquierda-abajo
            } else if (anguloGrados >= 213.75 && anguloGrados < 236.25) {
                direccion = 2.25; // abajo-izquierda
            } else if (anguloGrados >= 236.25 && anguloGrados < 258.75) {
                direccion = 2.5; // casi abajo
            } else if (anguloGrados >= 258.75 && anguloGrados < 281.25) {
                direccion = 3.25; // arriba
            } else if (anguloGrados >= 281.25 && anguloGrados < 303.75) {
                direccion = 3.75; // abajo-derecha
            } else if (anguloGrados >= 303.75 && anguloGrados < 326.25) {
                direccion = 4.0; // derecha-abajo
            } else if (anguloGrados >= 326.25 && anguloGrados < 348.75) {
                direccion = 4.5; // casi derecha
            }
            this.angulo = direccion; 
        }
    }
    
    public void mover() {
        this.x += vx;
        this.y += vy;
		frameIzquierda = animarMovimiento(movimientoHechizo, frameIzquierda);//animacion derecha
    }
		
//	Bordes/Limites del Hechizo
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