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
	private String [] punteroImagenes = {"puntero.png","puntero_fuego.png","puntero_hielo.png"};
	private int tipopuntero = 2;
	private Image imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero]); 

	private Image imagenFondo = Herramientas.cargarImagen("mapa2.png"); 
	private int cantMurcielagoPantalla = 10;
	private int cantMurcielagoTotal = 50;
	private int cantMurcielagoGenerados = 0;

	private Murcielago[] murcielagos = new Murcielago[cantMurcielagoTotal]; // Declaramos un array con 50 elementos
	private Hechizos[] hechizoAgua = new Hechizos[3]; // Declaramos Hechizos

	
	private int punteroX;
	private int punteroY;
	
	// Variables y métodos propios de cada grupo
	// ...
	

//----------------Calcular angulo-----------------------------

//----------------Métodos propios para Menu-----------------------
	public void dibujarImagenMenu(Entorno entorno) {
		entorno.dibujarImagen(imagenFondo,0, 0, 0, 1);
	}
	//----------------Métodos propios para Puntero-----------------------
	public void dibujarImagenPuntero(Entorno entorno, int x, int y) {
		entorno.dibujarImagen(imagenPuntero,x, y, 0, 1);
	}

//---------------Métodos propios para Colision----------------------
	
//	Colisión entre mago y murcielago
	/* Primero recorremos todos los murcielagos. Luego de eso preguntamos si el murcielago que
	 * hace colision con el mago es diferente de null. Si es así, entonces si colisiona, será null
	 */

	
//	Colision entre Hechizo y murcielago.
	public void colisionaHechizoAguaMurcielago(Murcielago[] murcielagos) {
		for(int i =0; i < murcielagos.length;i++) { //recorremos los murcielagos
			for(int j = 0; j < hechizoAgua.length;j++) {
				if(this.murcielagos[i] != null && this.hechizoAgua[j] != null) {
					if(this.hechizoAgua[j].limiteIzquierdo() < this.murcielagos[i].limiteDerecho() &&
							this.hechizoAgua[j].limiteSuperior() < this.murcielagos[i].limiteInferior() &&
							this.hechizoAgua[j].limiteInferior() > this.murcielagos[i].limiteSuperior() &&
							this.hechizoAgua[j].limiteDerecho() > this.murcielagos[i].limiteIzquierdo()) {
							this.murcielagos[i] = null; //Que ambos sean null
							this.hechizoAgua[j] = null;
					}
				}
			}
		}
	}			

//	------------------------Generar Hechizo-----------------------
	public void actualizarHechizo() {
		/* Se verificara si los 3 hechizosAgua son diferentes de null. Si lo son, entonces se podrán
		 * mover y dibujar. Y luego hacemos una condicion para que no supere los bordes de la pantalla
		 * del juego. Si la bala supera los bordes del juego, entonces que el hechizo sea null. 
		 * Para eso condicionamos SI "supera" los bordes. Por ende copiamos y pegamos 
		 * lo que usamos en mago, y en vez de verificar si la bala está dentro de la pantalla, 
		 * verificamos si lo supera o es igual. Si es así, será null.
		 */
//		Para hechizoAgua1
		for(int i = 0; i < hechizoAgua.length; i++) {
			if(this.hechizoAgua[i] != null) {
				this.hechizoAgua[i].mover();
				this.hechizoAgua[i].dibujarImagenFuego(entorno,1); // Para que se dibuje
				if(this.hechizoAgua[i].getX() -this.hechizoAgua[i].getAncho() / 2 < 0 ||  // Si supera el izquierdo
						this.hechizoAgua[i].getX() + this.hechizoAgua[i].getAncho() / 2 > menu.getX() - menu.getAncho() / 2 || // Si supera el derecho
						this.hechizoAgua[i].getY() - this.hechizoAgua[i].getAltura() / 2 < 0 || // Si supera arriba
						this.hechizoAgua[i].getY() + this.hechizoAgua[i].getAltura() / 2 > 600) { // Si supera abajo
						this.hechizoAgua[i] = null;
				}
			}
		}
	}

	Juego()
	{
		// Inicializa el objeto entorno
		
		
		this.entorno = new Entorno(this, "Proyecto para TP", anchoVentana, alturaVentana);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.menu = new Menu(anchoVentana,alturaVentana); //inicializamos el menu
		this.mago = new Mago(this.menu.getAncho(),alturaVentana); //inicializamos al mago

		
		
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
		
// 		dibujamos el mapa en la primera capa 
		this.entorno.dibujarImagen(imagenFondo, anchoVentana / 2 + 10, alturaVentana / 2, 0, 1.7);

//------------------------------Mago--------------------------
		this.mago.dibujarImagenMago(entorno); //dibujamos al mago
				
//-------------------Colision entre Mago y Rocas--------------------
		if(this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) { //Movimiento izquierdo
			if(mago.getX() - mago.getAncho() / 2 > 0 ) {
				this.mago.moverIzquierda();
				this.mago.setDireccion("izquierda");
				if(this.mago.colisionaMago(rocas)) {
					this.mago.moverDerecha();

				}
			}
		}
		if(this.entorno.estaPresionada(entorno.TECLA_DERECHA)) { //Movimiento Derecho
			if(mago.getX() + mago.getAncho() / 2 < menu.getX() - menu.getAncho() / 2) {
				this.mago.moverDerecha();
				this.mago.setDireccion("derecha");

				if(this.mago.colisionaMago(rocas)) {
					this.mago.moverIzquierda();
			        
				}
			}
		}
		if(this.entorno.estaPresionada(entorno.TECLA_ARRIBA)) { //Movimiento Ascendente
			if(mago.getY() - mago.getAltura() / 2 > 0) {
				this.mago.moverArriba();
				this.mago.setDireccion("arriba");

				if(this.mago.colisionaMago(rocas)) {
					this.mago.moverAbajo();
				}
			}
		}
		if(this.entorno.estaPresionada(entorno.TECLA_ABAJO)) { //Movimiento Descendente
			if(mago.getY() + mago.getAltura() / 2 < 600 ) {
				this.mago.moverAbajo();
				this.mago.setDireccion("abajo");

				if(this.mago.colisionaMago(rocas)) {
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
//	--------------------------Persecusión Murcielago------------------------------
		for (int i = 0; i < murcielagos.length; i++) {
			if(this.murcielagos[i] != null) { // Si el murcielago es diferente de null, lo persigue
				Funciones_utiles.seguimientoMurcielago(mago, murcielagos[i]); // Actualiza posición del murcielago actual

			    //dibujamos posicion de murcielago actualizada
			    this.murcielagos[i].dibujar(entorno);
			}
		}
		
//		-----------------Colision entre Mago y Murciélago--------------------------------
		Funciones_utiles.colisionMagoMurcielago(mago, murcielagos); // Esto va indicando si el murcielago colisiona con mago
											  // Si es así, entonces el murcielago será null
		
//		-------------------Restablecer Murcielagos------------------------------------
		cantMurcielagoGenerados = Funciones_utiles.actualizarMurcielagos(
			    murcielagos, // Array de murcielagos 
			    cantMurcielagoPantalla, // cantidad en pantalla 
			    cantMurcielagoGenerados, // cantidad de generados
			    cantMurcielagoTotal, // cantidad total
			    anchoVentana, // ancho de ventana del juego
			    alturaVentana, // alto de ventana del juego
			    menu // Y el propio menú
			);	
//-------------------------------------Hechizos-----------------------------------
/* Iniciamos el hechizo en tick ya que queremos que el objeto hechizo se mantenga fijo en el x e y del mago.
 * Es decir, que el objeto hechizo irá siempre con mago en su posicion x e y. Luego pediremos la ultima direccion
 * en la que se encontró el mago. Por ejemplo, si el mago mira a la derecha y lanza el hechizo con espacio,
 * entonces queremos que el hechizo vaya para la derecha. Y así con todos los lados.
 */
		//Si el hechizo no es nuevo, entonces cada vez que toco la tecla espacio, genera uno nuevo.
		for(int a = 0; a < hechizoAgua.length;a++) {
			if(this.entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
				if(this.hechizoAgua[a] == null) {
					this.hechizoAgua[a] = new Hechizos(this.mago.getX(), this.mago.getY(),punteroX,punteroY);
				}else if(this.hechizoAgua[a] == null) {
					this.hechizoAgua[a] = new Hechizos(this.mago.getX(), this.mago.getY(),punteroX,punteroY);
				}else if(this.hechizoAgua[a] == null) {
					this.hechizoAgua[a] = new Hechizos(this.mago.getX(), this.mago.getY(),punteroX,punteroY);
				}
			}
		}
		actualizarHechizo();
		colisionaHechizoAguaMurcielago(murcielagos);
		
//		Puntero
		if (this.entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
			punteroX=this.entorno.mouseX();
			punteroY=this.entorno.mouseY();
			Funciones_utiles.TrayectoriaHechizo(mago, punteroX, punteroY);
		}
		
		if(this.entorno.sePresionoBoton(entorno.BOTON_CENTRAL)) { //Movimiento Ascendente
			if (tipopuntero==0) {
				
				System.out.println("Fuego");
				imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero=1]); 
			}
			else if (tipopuntero==1) {
				tipopuntero=2;
				System.out.println("Hielo");
				imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero=2]); 
			}
			else {
				tipopuntero=0;
				System.out.println("Normal");
				imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero=0]); 
			}
		}
		this.dibujarImagenPuntero(entorno, this.entorno.mouseX(),this.entorno.mouseY());		

//-----------------------Colision Mago-Murcielago = Perder vida-----------------------
		for(int i = 0; i < murcielagos.length;i++) { // recorremos los murcielagos
			if(this.murcielagos[i] != null) { //  Preguntamos si son distintos de null
				Funciones_utiles.colisionMagoMurcielago(mago, murcielagos); // nos vamos a la colision
			} // Ademas de que el elemento murcielago sea null, el mago perderá vida
		}
		entorno.escribirTexto("Vidas:" + this.mago.getVida(), this.menu.getX(),this.menu.getY()); // Escribimos la vida
		if(this.mago.estaMuerto()) { // Si el mago llega a 0 vidas se muere
			entorno.dispose(); // Por ende cerramos el juego
		}
	
	}

	
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
