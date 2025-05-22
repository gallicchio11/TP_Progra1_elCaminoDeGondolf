package juego;
import java.awt.Image;
import java.util.Random;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private int anchoVentana= 900;
	private int alturaVentana= 600;
	private Entorno entorno;
	private Mago mago;
	private Menu menu;
	private Roca[] rocas = {
			new Roca(200,200,1),
			new Roca(600,200,2),
			new Roca(350,100,0),
			new Roca(200,400,2),
			new Roca(550,500,1),

	};
//	private int movimiento;
	private Image imagenFondo = Herramientas.cargarImagen("mapa2.png"); 
	private Murcielago[] murcielagos = new Murcielago[10]; // Declaramos un array con 10 elementos
	                       
	
	// Variables y métodos propios de cada grupo
	// ...
	public void dibujarImagenMenu(Entorno entorno) {
		entorno.dibujarImagen(imagenFondo,0, 0, 0, 1);
	}
	
	
	
	
	
	Juego()
	{
		// Inicializa el objeto entorno
		
		
		this.entorno = new Entorno(this, "Proyecto para TP", anchoVentana, alturaVentana);
		this.menu = new Menu(anchoVentana,alturaVentana); //inicializamos el menu
		this.mago = new Mago(this.menu.getAncho(),alturaVentana); //inicializamos al mago
//		this.movimiento = 0; // Indica la dirección de movimiento
		inicializarMurcielago(); //Vamos a la funcion Inicializar murcielagos 
		
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
	
	public void inicializarMurcielago() {
		Random random = new Random();//inicializamos un objeto random
		
		for (int i = 0; i< murcielagos.length;i++) { // recorremos los murcielagos
			int borde = random.nextInt(4); // Nos indica entre el 0 al 3
			int x = 0; // Inicializamos X e Y en 0
			int y = 0; 
			String direccion = "";
			if(borde == 0) { // Es el lado Izquierdo 
				x = 0;
				y = random.nextInt(alturaVentana);
				direccion = "izquierda";
				
			}else if(borde == 1){ // Es el lado Derecho 
				x = anchoVentana - this.menu.getAncho();
				y = random.nextInt(alturaVentana);
				direccion = "derecha";
				
			}else if(borde == 2){ // Es el lado Superior
				x = random.nextInt(anchoVentana - this.menu.getAncho());
				y = 0;
				direccion = "arriba";
				
			}else { // Es el lado Inferior 
				x = random.nextInt(anchoVentana - this.menu.getAncho());
				y = alturaVentana;
				direccion = "abajo";
			}
			this.murcielagos[i] = new Murcielago(x, y, direccion);
		}
	}
//	
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...
		// dibujamos el mapa en la primera capa 
		this.entorno.dibujarImagen(imagenFondo, anchoVentana / 2 + 10, alturaVentana / 2, 0, 1.7);
		
//		---------------------Mago--------------------------
		this.mago.dibujarImagenMago(entorno); //dibujamos al mago
				
//--------Colision entre Mago y Rocas--------------------
		if(this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) { //Movimiento izquierdo
			if(mago.getX() - mago.getAncho() / 2 > 0 ) {
				this.mago.moverIzquierda();
		        mago.setImagenMago(Herramientas.cargarImagen("mago2.png"));
				if(this.mago.colisionaRoca(rocas)) {
					this.mago.moverDerecha();

				}
			}
		}
		if(this.entorno.estaPresionada(entorno.TECLA_DERECHA)) { //Movimiento Derecho
			if(mago.getX() + mago.getAncho() / 2 < menu.getX() - menu.getAncho() / 2) {
				this.mago.moverDerecha();
		        mago.setImagenMago(Herramientas.cargarImagen("mago.png"));
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
		this.menu.dibujarImagenMenu(entorno); //Dibujamos el menú
		
		
//		-------------------------Rocas--------------------------
		for (int i = 0; i < rocas.length;i++) { //Dibujamos a las Rocas
			rocas[i].dibujarImagenRoca(entorno);
			
		}
//		  ---------------------------Murcielago----------------------
		
		for (int i = 0; i < murcielagos.length; i++) {
			//distancia entre 2 puntos en cada eje
		    int dx = this.mago.getX() - this.murcielagos[i].getX();
		    int dy = this.mago.getY() - this.murcielagos[i].getY();

		    int distanciaCuadrada = dx * dx + dy * dy; // sacamos cuadrado de X y D multiplcando sus lados y sumandolos.

		    if (distanciaCuadrada > 0)// condicional para verificar que la distancia no sea 0(al ser 0 significara que el murcielago esta encima de el mago)
		    						 //posible uso aplicable a daño recibido al mago
		    	{
		        int distancia = (int) Math.sqrt(distanciaCuadrada); // Sigue siendo int

		        int escalaM = 5; // con la escala de movimiento definimos de cuanto sera la velocidad a la que se movera
		        //la escala se toma como la relacion de distancia entre 2 ejes, si uno aumenta el otro tambien, por lo caul se utilizara el mismo valor, osea escalaM
		        
		        
		        int vx = dx * escalaM / distancia;//normalizamos la velocidad multiplicandola por nuestra escala y dividiendola por distanciaa("hipotenusa")
		        int vy = dy * escalaM/ distancia;//para velocidad de X e Y
		        System.out.println(vy);
		        System.out.println(vx);
		        
		        //actualizamos la posicion de nuestro murcielago con setx/y en lugar de crear 4 condicionales para eso(UTILIZAR SETTERS Y GETTERS!!!!)
		        this.murcielagos[i].setX(this.murcielagos[i].getX() + vx);
		        this.murcielagos[i].setY(this.murcielagos[i].getY() + vy);
		    }
		    //dibujamos posicion de murcielago actualizada
		    this.murcielagos[i].dibujar(entorno);
			

		}

	}

		
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}