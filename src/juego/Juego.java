package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private int anchoVentana= 1000;
	private int alturaVentana= 600;
	private Entorno entorno;
	private Mago mago;
	private Menu menu;
	private Roca[] rocas = {
			new Roca(200,200),
			new Roca(600,200),
			new Roca(350,100),
			new Roca(200,400),
			new Roca(550,500),
	};
	private Murcielago[] murcielagos = {
			new Murcielago(0,200)
	};
	                       
	
	// Variables y métodos propios de cada grupo
	// ...
	
	
	
	Juego()
	{
		// Inicializa el objeto entorno
		
		this.entorno = new Entorno(this, "Proyecto para TP", anchoVentana, alturaVentana);
		this.menu = new Menu(anchoVentana,alturaVentana); //inicializamos el menu
		this.mago = new Mago(anchoVentana,alturaVentana,this.menu.getX()); //inicializamos al mago

		// Inicializar lo que haga falta para el juego
		// ...
		
			
		
		
		// Inicia el juego!
		this.entorno.iniciar();
		
		
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	
	
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		
//		---------------------Mago--------------------------
		this.mago.dibujar(entorno); //dibujamos al mago
				
//--------Colision entre Mago y Rocas--------------------
		if(this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) { //Movimiento izquierdo
			if(mago.getX() - mago.getAncho() / 2 > 0 ) {
				this.mago.moverIzquierda();
				if(this.mago.colisionaRoca(rocas)) {
					this.mago.moverDerecha();
				}
			}
		}
		if(this.entorno.estaPresionada(entorno.TECLA_DERECHA)) { //Movimiento Derecho
			if(mago.getX() + mago.getAncho() / 2 < menu.getX() - menu.getAncho() / 2) {
				this.mago.moverDerecha();
				if(this.mago.colisionaRoca(rocas)) {
					this.mago.moverIzquierda();
				}
			}
		}
		if(this.entorno.estaPresionada(entorno.TECLA_ARRIBA)) { //Movimiento Ascendente
			if(mago.getY() - mago.getAltura() / 2 > 0) {
				this.mago.moverArriba();
				if(this.mago.colisionaRoca(rocas)) {
					this.mago.moverAbajo();
				}
			}
		}
		if(this.entorno.estaPresionada(entorno.TECLA_ABAJO)) { //Movimiento Descendente
			if(mago.getY() + mago.getAltura() / 2 < 600 ) {
				this.mago.moverAbajo();
				if(this.mago.colisionaRoca(rocas)) {
					this.mago.moverArriba();
				}
			}
		}	
		
//		-------------------------Menu---------------------------
		this.menu.dibujar(entorno); //Dibujamos el menú
		
//		-------------------------Rocas--------------------------
		for (int i = 0; i < rocas.length;i++) { //Dibujamos a las Rocas
			rocas[i].dibujar(entorno);
		}
//		  ---------------------------Murcielago----------------------
		for (int i = 0 ; i < murcielagos.length;i++) { //Dibujamos a los murcielagos
			murcielagos[i].dibujar(entorno);
		}
		for (int i = 0 ; i < murcielagos.length;i++) {
			if(murcielagos[i].getX() + murcielagos[i].getAncho() / 2 < menu.getX() - menu.getAncho() / 2) {
				this.murcielagos[i].moverDerecha();
			}
		}
		
		
		
		
		
		
	}
		
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}

