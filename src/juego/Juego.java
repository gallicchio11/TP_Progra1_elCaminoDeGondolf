package juego;
import java.awt.Image;
import java.util.Random;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{	// El objeto Entorno que controla el tiempo y otros
	
	// Necesario del Juego
	private int anchoVentana= 900;
	private int alturaVentana= 600;
	private Entorno entorno;
	
	// Mago
	private Mago mago;
	
	// Menu
	private Menu menu;
	private PantallaFinJuego PantallaFinJuegoGana; //instanciamos las variables para los obejtos de pantalla
	private PantallaFinJuego PantallaFinJuegoPierde;
	int pantallaFinal;
	
	// Rocas	
	private Roca[] rocas = {
			new Roca(200,200,1),
			new Roca(600,200,2),
			new Roca(350,100,0),
			new Roca(200,400,2),
			new Roca(550,500,1),

	};
	//	Hud mago
	private String hudMago = ("imagenes\\\\hud_mago.png");
	private Image imagenMago = Herramientas.cargarImagen(hudMago); 	

	// Puntero	
	private String [] punteroImagenes = {"imagenes\\\\puntero.png","imagenes\\\\puntero_fuego.png","imagenes\\\\puntero_hielo.png"};
	private int tipopuntero = 2;
	private Image imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero]); 

	// Imagen de Mapa	
	private Image imagenFondo = Herramientas.cargarImagen("imagenes\\\\mapa2.png");
	
	// Imagen para la pausa del juego
	private Image imagenPausa = Herramientas.cargarImagen("imagenes\\\\pausa.png"); 
	boolean enPausa = false;//variable para el estado de la pausa
	boolean esperaEnter = true;
	
	// Murcielago	
	private int cantMurcielagoPantalla = 10;
	private int cantMurcielagoTotal = 50;
	private int cantMurcielagoGenerados = 0;
	public int cantMurcielagosEliminados = 0;//cantidad de murcielagos eliminados
	private Murcielago[] murcielagos = new Murcielago[cantMurcielagoTotal]; // Declaramos un array con 50 elementos
	private int velocidadMurcielago = 2;

	// Hechizos	
	private Hechizos hechizoFuego ; // Declaramos Hechizos
	private Hechizos hechizoHielo ;
//	private Hechizos[] hechizoFuego = new Hechizos[2];
	
	//variables para el puntero
	private int punteroX;
	private int punteroY;

	//variable de tiempo
//	private int tiempo =0;
	private int regenerarMana;
	private int expirarHielo;
//	private int enfriamientoHechizo;
	
	private int numeroRonda = 1;

	// Botones
	//S = seleccionado - DS = deseleccionado
	private Boton botonHechizoFuego;
	private Image imagenBotonHieloS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoB1.png");
	private Image imagenBotonHieloDS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoB2.png");
	private Boton botonHechizoHielo;
	private Image imagenBotonFuegoS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoA1.png");
	private Image imagenBotonFuegoDS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoA2.png");

//----------------Métodos propios para Menu-----------------------
//	public void dibujarImagenMenu(Entorno entorno) {
//		entorno.dibujarImagen(imagenFondo,0, 0, 0, 1);
//	}

//----------------Métodos propios para Hud del Mago-----------------------
	public void dibujarImagenHudMago(Entorno entorno, int x, int y) {		
		entorno.dibujarImagen(imagenMago,x, y, 0, 1);
	}
	//----------------Métodos propios para Puntero-----------------------
	public void dibujarImagenPuntero(Entorno entorno, int x, int y) {
		entorno.dibujarImagen(imagenPuntero,x, y, 0, 1);
	}
	//----------------Metodo de dibujo de pausa--------------------
	public void dibujarImagenPausa(Entorno entorno) {
	    entorno.dibujarImagen(imagenPausa, anchoVentana / 2 + 10, alturaVentana / 3, 0, 1.7);
	}
	//----------------Cambio de pausa--------------------------
	public void manejarPausa() {
	    // cambiamos el estado de pausa(NEGACIONNNNN)
	    enPausa = !enPausa;
	}
	public void esperaEnter() {
		//lo utilizamos cada vez que qeuramos que el juagdor presione enter
		esperaEnter = !esperaEnter;
	}
	
	// ----- Funcion para Dibujar lo que está dentro del juego de forma pausada(solo recibe como verdadero el booleano "textoinicio" para esperar la tecla "enter")
	public void dibujarEstadoJuego(boolean textoinicio) {
	    entorno.dibujarImagen(imagenFondo, anchoVentana / 2 + 10, alturaVentana / 2, 0, 1.7);
	    // Dibujamos rocas
	    for (int i = 0; i < rocas.length; i++) {
	        if (rocas[i] != null) {
	            rocas[i].dibujarImagenRoca(entorno);
	        }
	    }
	    // Dibujar Mago
	    this.mago.dibujarImagenMago(entorno);
	    
	    // Dibujar Murcielago
	    for (int i = 0; i < murcielagos.length; i++) {
	        if (murcielagos[i] != null) {
	        	murcielagos[i].actualizarAnimacion(this.mago.getX());
	            murcielagos[i].dibujarImagen(entorno);
	        }
	    }

	    // Dibujar Hechizos		    
//	    for (int i = 0; i < hechizoAgua.length; i++) {
//	        if (hechizoAgua[i] != null) {
//	            hechizoAgua[i].dibujarImagenFuego(entorno, 1);
//	        }
//	    }
	    // Dibujar Menu	    
	    menu.dibujarImagenMenu(entorno);
	    // Dibujar Puntero
	    dibujarImagenPuntero(entorno, entorno.mouseX(), entorno.mouseY());
	    // Dibujar Hud Del mago
		dibujarImagenHudMago(entorno, 5+this.menu.getX(), this.menu.getY()+150);
		
		if (textoinicio) {
			entorno.escribirTexto("PRESIONE ENTER PARA INICIAR", this.menu.getX()-80,this.mago.getY()); // escribimos nuestro texto de presione enter

		}

	}

	
	public void rondaSiguiente(boolean activar) {
	    if (activar) {
	        // Aumentar la dificultad
	        this.cantMurcielagoPantalla += 5; // aummentamos la cantidad de murcielagos en pantalla
	        this.cantMurcielagoTotal += 10; // aumentamos la cantidad total en la ronda
	        this.velocidadMurcielago += 1; // aumentamos la velocidad de los murciélagos
	        								//MAYOR DIFICULTAD

	        // reiniciamos nuestro contador de generados y eliminados
	        this.cantMurcielagoGenerados = 0;
	        this.cantMurcielagosEliminados = 0;

	        // Crear un nuevo array de murciélagos con la nueva cantidad total
	        this.murcielagos = new Murcielago[this.cantMurcielagoTotal];
	        this.numeroRonda++;

	    }
	}

//---------------Métodos propios para Colision----------------------
	
//	Colisión entre mago y murcielago
	/* Primero recorremos todos los murcielagos. Luego de eso preguntamos si el murcielago que
	 * hace colision con el mago es diferente de null. Si es así, entonces si colisiona, será null
	 */

	
//	Colision entre Hechizo y murcielago.
	public void colisionaHechizoMurcielago(Murcielago[] murcielagos , int n) {
		if(n == 1) {	
			for(int i =0; i < murcielagos.length;i++) { //recorremos los murcielagos
					if(this.murcielagos[i] != null && this.hechizoFuego != null) {
						if(this.hechizoFuego.limiteIzquierdo() < this.murcielagos[i].limiteDerecho() &&
								this.hechizoFuego.limiteSuperior() < this.murcielagos[i].limiteInferior() &&
								this.hechizoFuego.limiteInferior() > this.murcielagos[i].limiteSuperior() &&
								this.hechizoFuego.limiteDerecho() > this.murcielagos[i].limiteIzquierdo()) {
								this.murcielagos[i] = null; //Que ambos sean null
								this.hechizoFuego = null;
								cantMurcielagosEliminados++;//aumentamos nuestro contador
					}
				}
			}
		}else {
			for(int i =0; i < murcielagos.length;i++) { //recorremos los murcielagos
				if(this.murcielagos[i] != null && this.hechizoHielo != null) {
					if(this.hechizoHielo.limiteIzquierdo() < this.murcielagos[i].limiteDerecho() &&
							this.hechizoHielo.limiteSuperior() < this.murcielagos[i].limiteInferior() &&
							this.hechizoHielo.limiteInferior() > this.murcielagos[i].limiteSuperior() &&
							this.hechizoHielo.limiteDerecho() > this.murcielagos[i].limiteIzquierdo()) {
							this.murcielagos[i] = null; //Que ambos sean null
							cantMurcielagosEliminados++;//aumentamos nuestro contador
					}
				}
			}
		}
	}			
	//--------------------------------------Murcielagos eliminados------------------
	private boolean MurcielagosEliminados() {
		for (int i = 0; i < murcielagos.length; i++) {
			if (murcielagos[i] != null) {
				return false; // si almenos hay un solo murcielago, no retorna nadfa
			}
		}
		return true; // todos los murcielagos son nulos
	}

//	-----------------------------Generar Hechizo-----------------------------------------
	public void actualizarHechizo(int n) {
//		/* Se verificara si los 3 hechizosAgua son diferentes de null. Si lo son, entonces se podrán
//		 * mover y dibujar. Y luego hacemos una condicion para que no supere los bordes de la pantalla
//		 * del juego. Si la bala supera los bordes del juego, entonces que el hechizo sea null. 
//		 * Para eso condicionamos SI "supera" los bordes. Por ende copiamos y pegamos 
//		 * lo que usamos en mago, y en vez de verificar si la bala está dentro de la pantalla, 
//		 * verificamos si lo supera o es igual. Si es así, será null.
//		 */
//		for(int i = 0; i < hechizoAgua.length; i++) {
		if(n == 1) {	
			if(this.hechizoFuego != null) {
					this.hechizoFuego.moverFuego();
					this.hechizoFuego.dibujarImagenFuego(entorno); // Para que se dibuje
					if(this.hechizoFuego.limiteIzquierdo() < 0 || // Si supera el izquierdo
							this.hechizoFuego.limiteDerecho() > menu.getX() - menu.getAncho() / 2 ||  //|| // Si supera el derecho
							this.hechizoFuego.limiteSuperior() < 0 || // Si supera arriba
							this.hechizoFuego.limiteInferior() > alturaVentana) { // Si supera abajo
							this.hechizoFuego = null;
					}
				}
		}else {
			if(this.hechizoHielo != null) {
				this.hechizoHielo.moverHielo();
				this.hechizoHielo.dibujarImagenHielo(entorno);
				expirarHielo++;
				if(this.hechizoHielo.limiteIzquierdo() < 0 || // Si supera el izquierdo
						this.hechizoHielo.limiteDerecho() > menu.getX() - menu.getAncho() / 2 ||  //|| // Si supera el derecho
						this.hechizoHielo.limiteSuperior() < 0 || // Si supera arriba
						this.hechizoHielo.limiteInferior() > alturaVentana) { // Si supera abajo
						this.hechizoHielo = null;
						expirarHielo =0;
				}
				if(expirarHielo >= 180) {
					this.hechizoHielo = null;
					expirarHielo = 0;
				}
			}
		}
		
	}
	public void PresionarEnter(boolean enter) {
		if(enter) {
			return;
		}
		else {
			enPausa = true;
			System.out.println("ENTER");
		}
	}
	Juego()
	{	
		if (enPausa) {
		    dibujarEstadoJuego(false);
		    dibujarImagenPausa(entorno); //cartel de pausa (si no te gusta el cartel , lo cambio ;__;)
		    return;
		}
		// Inicializa el objeto entorno

		
		this.entorno = new Entorno(this, "Proyecto para TP", anchoVentana, alturaVentana);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.menu = new Menu(anchoVentana,alturaVentana); //inicializamos el menu
		this.mago = new Mago(this.menu.getAncho(),alturaVentana); //inicializamos al mago

		this.PantallaFinJuegoGana = new PantallaFinJuego(anchoVentana, alturaVentana, 1); // Pantalla Juego Gana
		this.PantallaFinJuegoPierde = new PantallaFinJuego(anchoVentana, alturaVentana, 2); // Pantalla Juego Pierde
		
		
//		this.botonHechizoAgua = new Boton(766,535);
		this.botonHechizoFuego = new Boton(790,150,imagenBotonHieloS, imagenBotonHieloDS); //
		this.botonHechizoHielo = new Boton (790,250,imagenBotonFuegoS, imagenBotonFuegoDS);
		
//		Mana
		regenerarMana = 0;
		expirarHielo = 0;
		
		// Inicia el juego!
		this.entorno.iniciar();
		
		
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	// Dentro de la clase Juego (por ejemplo)
	
	public void tick()
	{			
		
		punteroX = entorno.mouseX();
		punteroY = entorno.mouseY();
		
		regenerarMana ++;
		if(regenerarMana >= 60) {
			if(this.mago.getMana() < 10) {
				this.mago.setMana(this.mago.getMana()+1);
			}
			regenerarMana = 0;
		}
		
//---------------Condicionales para controlar el juego-PAUSA-FIN DEL JUEGO(GANAR/PERDER)-RONDAS
		if (mago.estaMuerto()) { //si el mago esta sin vida, mostramos pantalla fin de juego perdido
		    dibujarEstadoJuego(false);
		    PantallaFinJuegoPierde.dibujarImagenFinJuego(entorno);
			return;
		}
		if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {//tecla escape para activar la pausa
//			System.out.println("sdsd");
		    manejarPausa();
		
		}
		if (enPausa) {
		    dibujarEstadoJuego(false);
		    dibujarImagenPausa(entorno); //cartel de pausa (si no te gusta el cartel , lo cambio ;__;)
		    return;
		}
		if (this.entorno.sePresiono(entorno.TECLA_ENTER) && esperaEnter==true) {//tecla e
//			System.out.println("sdsd");
		    esperaEnter();
		}
		if (esperaEnter) {
			
		    dibujarEstadoJuego(true);//el unico que tendra true en dibujarEstadoJuego para mostrar una imagen de espera de tecla o texto
		    return;
		}
		
		//manejamos el tiempo con el tick ya que el tiempó no es preciso(por lo que vi,, es por los bucles internos tambien)
//		if (this.entorno.numeroDeTick() % 100 == 1) {//dividimos la cantiidad de ticks y cuanto el resto sea exactamente 1 aumenta el "tiempo"
////			System.out.println("aaa");
//			tiempo++;
////			System.out.println(tiempo);
//		}
//		
		// Procesamiento de un instante de tiempo
		// ...
		
		// Dibujamos el mapa en la primera capa 
		this.entorno.dibujarImagen(imagenFondo, anchoVentana / 2 + 10, alturaVentana / 2, 0, 1.7);

//------------------------------ Mago --------------------------
		this.mago.dibujarImagenMago(entorno); //dibujamos al mago
				
//------------------- Movimiento y Colision Mago-Rocas --------------------
		if(this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) { //Movimiento izquierdo
			if(mago.getX() - mago.getAncho() / 2 > 0 ) {
				this.mago.moverIzquierda();
				if(this.mago.colisionaMago(rocas)) {
					this.mago.moverDerecha();
				}
			}
		}
		if(this.entorno.estaPresionada(entorno.TECLA_DERECHA)) { //Movimiento Derecho
			if(mago.getX() + mago.getAncho() / 2 < menu.getX() - menu.getAncho() / 2) {
				this.mago.moverDerecha();
				if(this.mago.colisionaMago(rocas)) {
					this.mago.moverIzquierda();
			        
				}
			}
		}
		if(this.entorno.estaPresionada(entorno.TECLA_ARRIBA)) { //Movimiento Ascendente
			if(mago.getY() - mago.getAltura() / 2 > 0) {
				this.mago.moverArriba();
				if(this.mago.colisionaMago(rocas)) {
					this.mago.moverAbajo();
				}
			}
		}
		if(this.entorno.estaPresionada(entorno.TECLA_ABAJO)) { //Movimiento Descendente
			if(mago.getY() + mago.getAltura() / 2 < 600 ) {
				this.mago.moverAbajo();
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
			    this.murcielagos[i].actualizarAnimacion(this.mago.getX());
	            this.murcielagos[i].dibujarImagen(entorno);
			}
		}

//		-----------------Colision entre Mago y Murciélago--------------------------------
		 int ColisionesMurcielagos=Funciones_utiles.colisionMagoMurcielago(mago, murcielagos); // Esto va indicando si el murcielago colisiona con mago
											  // Si es así, entonces el murcielago será null

//		-------------------Restablecer Murcielagos------------------------------------
		cantMurcielagoGenerados = Funciones_utiles.actualizarMurcielagos(
			    murcielagos, // Array de murcielagos 
			    cantMurcielagoPantalla, // cantidad en pantalla 
			    cantMurcielagoGenerados, // cantidad de generados
			    cantMurcielagoTotal, // cantidad total
			    anchoVentana, // ancho de ventana del juego
			    alturaVentana, // alto de ventana del juego
			    velocidadMurcielago,
			    menu, // Y el propio menú
			    numeroRonda//valor de nuestra ronda
			);	
//-------------------------------------Hechizos-----------------------------------
/* Iniciamos el hechizo en tick ya que queremos que el objeto Hechizo inicie desde la posicion X e Y del mago;
 * y que vaya en direccion donde esté el puntero. Pero para generar un Hechizo, debemos preguntar Si 
 * el boton del Hechizo correspondiente está seleccionado. Si lo está, podrá disparar; sino no podrá. 
 * En el caso de que si está seleccionado, entonces recorrera el array de Hechizos y luego preguntará 
 * si se presiono el click izquierdo. Si se presionó, entonces preguntamos si el elemento del Hechizo es null, 
 * diciendo que el elemento Hechizo a sido disparado. Si es null entonces se genera en el mismo indice
 * un nuevo elemento Hechizo.
 */
//		if(this.botonHechizoAgua.estadoActual() == true) { 
//			for(int a = 0; a < hechizoAgua.length;a++) {
//				if(this.entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
//					if(this.hechizoAgua[a] == null) {
//						this.hechizoAgua[a] = new Hechizos(this.mago.getX(), this.mago.getY(),punteroX,punteroY);
//						this.mago.setMana(this.mago.getMana()-1);
//					}
//				}
//			}
//			actualizarHechizo();
//			colisionaHechizoAguaMurcielago(murcielagos);
//		}
		
		
//		if(this.botonHechizoFuego.estadoActual() == true) {
//			for(int f = 0; f < hechizoFuego.length;f++) {
//				if(this.entorno.sePresiono(entorno.TECLA_SHIFT)) {
//					if(this.hechizoFuego[f] == null) {
//						this.hechizoAgua[f] = new Hechizos(this.mago.getX(), this.mago.getY(),punteroX,punteroY);
//					}
//				}
//			}
//			actualizarHechizo();
//		}
		
//		Hechizo Fuego
		if(this.botonHechizoFuego.estadoActual() == true) {
			if(this.entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
				if(Hechizos.permitirDisparar(punteroX, menu)) {
					if(this.hechizoFuego == null) {
						this.hechizoFuego = new Hechizos(this.mago.getX(),this.mago.getY(), punteroX,punteroY, 25,25, "Fuego", 1,1,true);
						this.mago.setMana(this.mago.getMana() - this.hechizoFuego.getCostoMana());
					}
				}
					
			}
			actualizarHechizo(1);
			colisionaHechizoMurcielago(murcielagos,1);
				
			
		}
		
//		Hechizo Hielo
		if(this.botonHechizoHielo.estadoActual() == true) {
			if(this.entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
				if(Hechizos.permitirDisparar(punteroX, menu)) {
					if(this.hechizoHielo == null && this.mago.getMana() > 5) {
						this.hechizoHielo = new Hechizos(punteroX,punteroY, punteroX,punteroY, 200,200, "Hielo", 7,1,false);
						this.mago.setMana(this.mago.getMana() - this.hechizoHielo.getCostoMana());
						expirarHielo = 0;
					}
				}
			}
			actualizarHechizo(2);
			colisionaHechizoMurcielago(murcielagos,2);
			
			
		}
		
		
		
//		Puntero
//		if (this.entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
//			punteroX=this.entorno.mouseX();
//			punteroY=this.entorno.mouseY();
//			Funciones_utiles.TrayectoriaHechizo(mago, punteroX, punteroY);
//		}
//		
			if (this.botonHechizoFuego.estadoActual()) {
				
//				System.out.println("Fuego");
				imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero=1]); 
			}
			else if (this.botonHechizoFuego.estadoActual()) {
				tipopuntero=2;
//				System.out.println("Hielo");
				imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero=2]); 
			}
			else {
				tipopuntero=0;
//				System.out.println("Normal");
				imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero=0]); 
			}
		
		this.dibujarImagenPuntero(entorno, this.entorno.mouseX(),this.entorno.mouseY());
		//aca debera ir la barra de vida y mana , esta ira por encima del menu pero por abajo del hub 
		this.mago.dibujarVida(entorno, 5+this.menu.getX(), this.menu.getY()+86);
		this.mago.dibujarMana(entorno, 5+this.menu.getX(), this.menu.getY()+100);
	

		
		this.dibujarImagenHudMago(entorno, 5+this.menu.getX(), this.menu.getY()+150);//dibujamos el hub del mago para mostrar la interfaz

//-----------------------Colision Mago-Murcielago = Perder vida-----------------------
		for(int i = 0; i < murcielagos.length;i++) { // recorremos los murcielagos
			if(this.murcielagos[i] != null) { //  Preguntamos si son distintos de null
				Funciones_utiles.colisionMagoMurcielago(mago, murcielagos); // nos vamos a la colision
			} // Ademas de que el elemento murcielago sea null, el mago perderá vida
		}
		entorno.escribirTexto("Vidas:" + this.mago.getVida(), this.menu.getX(),this.menu.getY()); // escribimos la vida
        entorno.escribirTexto("Ronda: " + this.numeroRonda, this.menu.getX(), this.menu.getY() + 20);//mostramos la ronda actual

		if (MurcielagosEliminados() && cantMurcielagoGenerados == cantMurcielagoTotal) 
		{
			if (numeroRonda==2) {//indicamos la ronda final
				dibujarEstadoJuego(false);
				PantallaFinJuegoGana.dibujarImagenFinJuego(entorno);
			    return;
			}
			else {
				// si no es la ronda final, aumenta la dificultad
				rondaSiguiente(true);
			}
			
		}
		cantMurcielagosEliminados += ColisionesMurcielagos;
//		System.out.println("murcielagos eliminados:" + cantMurcielagosEliminados);

// -------------- INICIALIZACION DE BOTONES -------------------------
//		Boton para Hechizo Agua
//		if(entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {
//			if(this.botonHechizoAgua.estaDentro(punteroX, punteroY)) {
//				if(this.botonHechizoAgua.estadoActual()) {
//					this.botonHechizoAgua.deseleccionar();
//				}else {
//					this.botonHechizoAgua.seleccionar();
//				}
//			}
//		}
		
//		Si ambos botones se presionan.
		
//		 Boton para Hechizo Fuego
		if(entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {
			if(this.botonHechizoFuego.estaDentro(punteroX, punteroY)) {
				this.botonHechizoFuego.cambiarEstado();
				if(this.botonHechizoHielo.estadoActual()) {
					this.botonHechizoHielo.cambiarEstado();
				}
			}
		}
		
//		Boton para Hechizo Hielo
		if(entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {
			if(this.botonHechizoHielo.estaDentro(punteroX, punteroY)) {
				this.botonHechizoHielo.cambiarEstado();
				if(this.botonHechizoFuego.estadoActual()) {
						this.botonHechizoFuego.cambiarEstado();
				}
			}
		}
		
		
//		Hechizo Fuego Boton izquierdo
		if(this.botonHechizoFuego.estadoActual()) {
			this.botonHechizoFuego.dibujarnImagenBoton(entorno);
		}else {
			this.botonHechizoFuego.dibujarnImagenBoton(entorno);
		}

//		Hechizo Hielo Boton derecho
		if(this.botonHechizoHielo.estadoActual()) {
			this.botonHechizoHielo.dibujarnImagenBoton(entorno);
		}else {
			this.botonHechizoHielo.dibujarnImagenBoton(entorno);
		}	
			

		
//		Hechizo Agua estado
//		if(this.botonHechizoAgua.estadoActual()) {
//			this.botonHechizoAgua.dibujarBotonSeleccionado(entorno);
//		}else {
//			this.botonHechizoAgua.dibujarBotonDeseleccionado(entorno);
//		}
		
//		Hechizo Fuego estado
		
	
	}
	
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
