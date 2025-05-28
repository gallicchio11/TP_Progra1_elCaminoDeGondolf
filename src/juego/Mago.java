package juego;
import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mago {
	// variables de base par el mago	
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int velocidad;
	private int vida;
    private Image imagen;
    
    //variables de animacion para movimiento  
    private int contadorFrames = 0;
    private int velocidadFrames = 10;
    
    //  nos determinara el fram actual del movimiento hacia la direccion
    private int frameAbajo = 0;
    private int frameArriba = 0;
    private int frameIzquierda = 0;
    private int frameDerecha = 0;

    //nuestras listas de imagenes para cada movimiento(4 direcciones de momento) 
    private String[] movimientoAbajoMago = {//frames para abajo
    	    "caminar_abajo1.png",
    	    "caminar_abajo2.png",
    	    "caminar_abajo3.png",
    	    "caminar_abajo4.png"
    	};

    	private String[] movimientoArribaMago = {//frames para arriba
    	    "caminar_arriba1.png",
    	    "caminar_arriba2.png",
    	    "caminar_arriba3.png",
    	    "caminar_arriba4.png"
    	};

    	private String[] movimientoIzquierdaMago = {//frames para la izquierda
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

//	Constructor
	public Mago(int anchoMenu, int alturaVentana) {
		this.ancho = 30;
		this.altura = 30;
		this.x = anchoMenu + anchoMenu/2 ; // 225 + 112.5 = 338 aprox
		this.y = (alturaVentana/2) - this.altura/2; // (600/2) - (30/2) --> 300 - 15 = 285
		this.velocidad = 5;
        this.imagen = Herramientas.cargarImagen("caminar_abajo2.png");
        this.vida = 10;

	}

//  -----------Metodo de animacion ---------(Reutilizar en otros objetos como murcielagos para darle animacion)
// tener en cuenta variables: int contadorFrames, int velocidadFrames, int frameDireccion, String[] movimientDireccionMago
	
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

//	-----------Getter y Setter--------------
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
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getVelocidad() {
		return velocidad;
	}
	
	public void setImagenMago(Image imagen) {
		this.imagen = imagen;
	}

	
	
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
//	----------------------------------------
	
	//	Dibujar
	public void dibujar(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.altura,0, Color.BLUE);
	}
//	Dibujar imagen
	public void dibujarImagenMago(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, 0, 3);
	}
	
//------------Bordes/limites del mago----------
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
	
//	Colision Mago-Roca
	//Esto nos sirve para determinar si el mago colisiona
	//con las rocas.
	public boolean colisionaMago(Roca[] rocas) {  
		for(int i =0; i < rocas.length;i++) {
			if (this.limiteIzquierdo() < rocas[i].limiteDerecho() &&
				this.limiteSuperior() < rocas[i].limiteInferior() &&
				this.limiteInferior() > rocas[i].limiteSuperior() &&
				this.limiteDerecho() > rocas[i].limiteIzquierdo()) {
				return true; // Si colisiona es true
			}	
			
		}
		return false; // sino, no colisiona
	}

	
	
//	Movimientos
	public void moverDerecha() {
		this.x = x + velocidad;
	    frameDerecha = animarMovimiento(movimientoDerechaMago, frameDerecha); //animacion derecha

	}
	
	public void moverIzquierda() {
		this.x = x - velocidad;
	    frameIzquierda = animarMovimiento(movimientoIzquierdaMago, frameIzquierda); //animacion derecha

	}
	
	public void moverArriba() {	
		this.y = y - velocidad;
	    frameArriba = animarMovimiento(movimientoArribaMago, frameArriba); //animacion arriba

	}
	
	public void moverAbajo() {
	    this.y = y + velocidad;
	    frameAbajo = animarMovimiento(movimientoAbajoMago, frameAbajo);//animacion arriba
	    															   //llamamos a nuestro animarMovimiento para cambiar de frame
	}

//	----------- Perder vida ------------
	public void perderVida() {
		this.vida --;
	}
//	----------- Detectar si Murio --------
	public boolean estaMuerto() {
		if(this.vida <= 0) {
			return true;
		}else {
			return false;
		}
	}

}