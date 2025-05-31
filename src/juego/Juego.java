package juego;
import java.awt.Image;
import java.util.Random;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego{	
	// El objeto Entorno que controla el tiempo y otros
	
	// Necesario del Juego
	private int anchoVentana= 900;
	private int alturaVentana= 600;
	private Entorno entorno;
	
	// Mago
	private Mago mago;
	
	// Menu
	private Menu menu;
	
	// Instanciamos las variables para los objetos de pantalla
	private PantallaFinJuego PantallaFinJuegoGana;  // Pantalla de ganar
	private PantallaFinJuego PantallaFinJuegoPierde; // Pantalla de perder
//	int pantallaFinal; preguntar a Agustin
	
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
	boolean enPausa = false; // Variable para el estado de la pausa
	boolean esperaEnter = true;
	
	// Murcielago	
	private int cantMurcielagoPantalla = 10;
	private int cantMurcielagoTotal = 50;
	private int cantMurcielagoGenerados = 0;
	public int cantMurcielagosEliminados = 0; // Cantidad de murcielagos eliminados
	private Murcielago[] murcielagos = new Murcielago[cantMurcielagoTotal]; // Declaramos un array con 50 elementos
	private int velocidadMurcielago = 2; // preguntar a Agustin

	// Hechizos	
	private Hechizos hechizoFuego ; // Declaramos Hechizo Fuego
	private Hechizos hechizoHielo ; // Declaramos Hechizo Hielo
	
	// Variables para el puntero
	private int punteroX;
	private int punteroY;

	// Variable de tiempo
//	private int tiempo =0;
	private int regenerarMana;
	private int expirarHielo;
//	private int enfriamientoHechizo;
	
	// Variables de Ronda
	private int numeroRonda = 1;

	// Botones
	//S = seleccionado - DS = deseleccionado
	private Boton botonHechizoFuego; // Boton del Hechizo Fuego
	private Image imagenBotonHieloS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoB1.png");
	private Image imagenBotonHieloDS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoB2.png");
	private Boton botonHechizoHielo; // Boton del Hechizo Hielo
	private Image imagenBotonFuegoS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoA1.png");
	private Image imagenBotonFuegoDS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoA2.png");

//----------------Métodos propios para Menu-----------------------
//	public void dibujarImagenMenu(Entorno entorno) {
//		entorno.dibujarImagen(imagenFondo,0, 0, 0, 1);
//	}

//---------------- Métodos propios para Hud del Mago -----------------------
	public void dibujarImagenHudMago(Entorno entorno, int x, int y) {		
		entorno.dibujarImagen(imagenMago,x, y, 0, 1);
	}
	
//------------------ Métodos propios para Puntero -----------------------
	public void dibujarImagenPuntero(Entorno entorno, int x, int y) {
		entorno.dibujarImagen(imagenPuntero,x, y, 0, 1);
	}
	
//-------------------- Métodos de dibujo de pausa --------------------
	public void dibujarImagenPausa(Entorno entorno) {
	    entorno.dibujarImagen(imagenPausa, anchoVentana / 2 + 10, alturaVentana / 3, 0, 1.7);
	}
//------------------------- Cambio de pausa --------------------------
	public void manejarPausa() {
	    enPausa = !enPausa; // Cambiamos el estado con negación
	}
	public void esperaEnter() {
		esperaEnter = !esperaEnter; // Lo utilizamos para que el jugador presione enter
	}
	
//	------------------------ Funcion de estado del Juego ------------------------------------
	/*  Funcion para Dibujar lo que está dentro del juego de forma pausada
	 * (solo recibe como verdadero el booleano "textoinicio" para esperar la tecla "enter")
	 */
	
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
	    
	    // Dibujar Menu	    
	    menu.dibujarImagenMenu(entorno);
	    
	    // Dibujar Puntero
	    dibujarImagenPuntero(entorno, entorno.mouseX(), entorno.mouseY());
	    
	    // Dibujar Hud Del mago
		dibujarImagenHudMago(entorno, 5+this.menu.getX(), this.menu.getY()+150);
		
		// Texto para decir que presione enter para jugar
		if (textoinicio) {
			entorno.escribirTexto("PRESIONE ENTER PARA INICIAR", this.menu.getX()-80,this.mago.getY()); 
		}
	}

//----------------------- Funcion para indicar la ronda siguiente ---------------------
	public void rondaSiguiente(boolean activar) { // preguntar a agustin
	    if (activar) {
	    	
	        // Aumentar la dificultad
	        this.cantMurcielagoPantalla += 5; // aummentamos la cantidad de murcielagos en pantalla
	        this.cantMurcielagoTotal += 10; // aumentamos la cantidad total en la ronda
	        this.velocidadMurcielago += 1; // aumentamos la velocidad de los murciélagos

	        // reiniciamos nuestro contador de generados y eliminados
	        this.cantMurcielagoGenerados = 0;
	        this.cantMurcielagosEliminados = 0;

	        // Crear un nuevo array de murciélagos con la nueva cantidad total
	        this.murcielagos = new Murcielago[this.cantMurcielagoTotal];
	        this.numeroRonda++;
	    }
	}

//--------------------- Métodos propios para Colision --------------------------------
	
//	Colision entre Hechizo y murcielago.
	public void colisionaHechizoMurcielago(Murcielago[] murcielagos , int n) {
		if(n == 1) { // Si es 1, hablamos de Hechizo Fuego
			for(int i =0; i < murcielagos.length;i++) { // Recorremos los murcielagos
				if(this.murcielagos[i] != null && this.hechizoFuego != null) { // Si ambos son diferentes de null
					if(this.hechizoFuego.limiteIzquierdo() < this.murcielagos[i].limiteDerecho() && // Condición de colision
							this.hechizoFuego.limiteSuperior() < this.murcielagos[i].limiteInferior() &&
							this.hechizoFuego.limiteInferior() > this.murcielagos[i].limiteSuperior() &&
							this.hechizoFuego.limiteDerecho() > this.murcielagos[i].limiteIzquierdo()) {
							this.murcielagos[i] = null; // Que ambos sean null
							this.hechizoFuego = null; 
							cantMurcielagosEliminados++; // Aumentamos nuestro contador
					}
				}
			}
		}else { // Si es 2, hablamos de Hielo
			for(int i =0; i < murcielagos.length;i++) { // Recorremos los murcielagos
				if(this.murcielagos[i] != null && this.hechizoHielo != null) { // Si ambos son diferentes de null
					if(this.hechizoHielo.limiteIzquierdo() < this.murcielagos[i].limiteDerecho() && // Condición de colision
							this.hechizoHielo.limiteSuperior() < this.murcielagos[i].limiteInferior() &&
							this.hechizoHielo.limiteInferior() > this.murcielagos[i].limiteSuperior() &&
							this.hechizoHielo.limiteDerecho() > this.murcielagos[i].limiteIzquierdo()) {
							this.murcielagos[i] = null; // Que ambos sean null
							cantMurcielagosEliminados++;// Aumentamos nuestro contador
					}
				}
			}
		}
	}
	
//	----------------- Murcielagos eliminados Total -----------------------
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
		if(n == 1) { // Si hablamos de Hechizo Fuego
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
		}else { // Si hablamos de Hechizo Hielo
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
	
//	Para indicar si se presiono enter
	public void PresionarEnter(boolean enter) {
		if(enter) {
			return;
		}
		else {
			enPausa = true;
			System.out.println("ENTER");
		}
	}
	
	Juego() {
		
		if (enPausa) {
		    dibujarEstadoJuego(false);
		    dibujarImagenPausa(entorno); // Cartel de pausa (si no te gusta el cartel , lo cambio ;__;)
		    return;
		}
		
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", anchoVentana, alturaVentana);
		
		// Inicializar lo que haga falta para el juego
		
		// Inicializamos el menu
		this.menu = new Menu(anchoVentana,alturaVentana); 
		
		// Inicializamos al mago
		this.mago = new Mago(this.menu.getAncho(),alturaVentana); 

		// Inicializamos las pantallas
		this.PantallaFinJuegoGana = new PantallaFinJuego(anchoVentana, alturaVentana, 1); // Pantalla Juego Gana
		this.PantallaFinJuegoPierde = new PantallaFinJuego(anchoVentana, alturaVentana, 2); // Pantalla Juego Pierde
		
		// Inicializamos los botones del Hechizo
		this.botonHechizoFuego = new Boton(790,150,imagenBotonHieloS, imagenBotonHieloDS); // Hechizo Fuego
		this.botonHechizoHielo = new Boton (790,250,imagenBotonFuegoS, imagenBotonFuegoDS); // Hechizo Hielo
		
		// Regenerar Mana
		regenerarMana = 0;
		
		// Expirar el Hechizo Hielo
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
	
	public void tick() {			
		
		// Obtenemos los valores del punteroX y el PunteroY propios del mouse
		punteroX = entorno.mouseX();
		punteroY = entorno.mouseY();
		
		// Regeneramos el mana
		regenerarMana ++; // Constantemente irá subiendo
		if(regenerarMana >= 60) { // Hasta que se llegue a 60 ticks --> 1 segundo
			if(this.mago.getMana() < 10) { // Si el mana del mago es menor a 10
				this.mago.setMana(this.mago.getMana()+1); // Se aumenta el mana +1
			}
			regenerarMana = 0; // Si no es así, vuelve a 0 
		}
		
//---------------Condicionales para controlar el juego-PAUSA-FIN DEL JUEGO(GANAR/PERDER)-RONDAS
		if (mago.estaMuerto()) { //si el mago esta sin vida, mostramos pantalla fin de juego perdido
		    dibujarEstadoJuego(false);
		    PantallaFinJuegoPierde.dibujarImagenFinJuego(entorno);
			return;
		}
		
		if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {//tecla escape para activar la pausa
		    manejarPausa();
		}
	
		if (enPausa) {
		    dibujarEstadoJuego(false);
		    dibujarImagenPausa(entorno); //cartel de pausa (si no te gusta el cartel , lo cambio ;__;)
		    return;
		}
		
		if (this.entorno.sePresiono(entorno.TECLA_ENTER) && esperaEnter==true) {//tecla e
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

//---------------------------------------- MAGO -------------------------------------------
		this.mago.dibujarImagenMago(entorno); // Dibujamos al mago
		
		//---------- Movimientos y colision con rocas --------------------
		// Movimiento izquierdo
		if(this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {  // Si se presiona la tecla izquierda
			if(mago.getX() - mago.getAncho() / 2 > 0 ) { // Si no supera el lado izquierdo de la pantalla del juego
				this.mago.moverIzquierda(); // Se movera a la izquierda
				if(this.mago.colisionaMagoRoca(rocas)) { // Si colisiona con una roca moviendose a la izquierda
					this.mago.moverDerecha(); // Entonces que se mueva al lado contrario
				}
			}
		}
		
		// Movimiento Derecho
		if(this.entorno.estaPresionada(entorno.TECLA_DERECHA)) { // Si se presiona la tecla derecha
			if(mago.getX() + mago.getAncho() / 2 < menu.getX() - menu.getAncho() / 2) { // Si no supera el lado derecho del menú
				this.mago.moverDerecha(); // Se movera a la derecha
				if(this.mago.colisionaMagoRoca(rocas)) { // Si colisiona con una roca moviendose a la derecha
					this.mago.moverIzquierda(); // Entonces que se mueva al lado contrario
				}
			}
		}
		
		// Movimiento Arriba
		if(this.entorno.estaPresionada(entorno.TECLA_ARRIBA)) { // Si se presiona la tecla de arriba 
			if(mago.getY() - mago.getAltura() / 2 > 0) { // Si no supera el lado de arriba de la pantalla del juego
				this.mago.moverArriba(); // Se movera arriba
				if(this.mago.colisionaMagoRoca(rocas)) { // Si colisiona con una roca moviendose para arriba
					this.mago.moverAbajo(); // Entonces que se mueva al lado contrario
				}
			}
		}
		
		// Movimiento Abajo
		if(this.entorno.estaPresionada(entorno.TECLA_ABAJO)) { // Si se presiona la tecla de abajo 
			if(mago.getY() + mago.getAltura() / 2 < 600 ) { // Si no supera el lado de abajo de la pantalla del juego
				this.mago.moverAbajo(); // Se movera abajo
				if(this.mago.colisionaMagoRoca(rocas)) { // Si colisiona con una roca moviendose para abajo
					this.mago.moverArriba(); // Entonces que se mueva al lado contrario
				}
			}
		}	
		
		//-----------------Colision entre Mago y Murciélago------------------------
		// Esto va indicando si el murcielago colisiona con mago, si es así el murcielago se vuelve null
		 int ColisionesMurcielagos = Funciones_utiles.colisionMagoMurcielago(mago, murcielagos);
		
		
		
//--------------------------------------------- MENU -------------------------------------------------
		this.menu.dibujarImagenMenu(entorno); //Dibujamos el menú
		
//-------------------------------------------- ROCAS ------------------------------------------------
		for (int i = 0; i < rocas.length;i++) { //Dibujamos a las Rocas
			rocas[i].dibujarImagenRoca(entorno);
			
		}
		
//------------------------------------------ MURCIELAGO ----------------------------------------------	
		// Persecusión Murcielago
		for (int i = 0; i < murcielagos.length; i++) {
			if(this.murcielagos[i] != null) { // Si el murcielago es diferente de null, lo persigue
				Funciones_utiles.seguimientoMurcielago(mago, murcielagos[i]); // Actualiza posición del murcielago actual
			    this.murcielagos[i].actualizarAnimacion(this.mago.getX()); // Actualizamos la animacion del murcielago
	            this.murcielagos[i].dibujarImagen(entorno); // Dibujamos al Murcielago
			}
		}
		
		// Restablecer Murcielagos
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
		
//--------------------------------------------- HECHIZOS ---------------------------------------------
		// Hechizo Fuego
		if(this.botonHechizoFuego.estadoActual() == true) {
			if(this.entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
				if(Hechizos.permitirDisparar(punteroX, menu)) {
					if(this.hechizoFuego == null) {
						this.hechizoFuego = new Hechizos(this.mago.getX(),this.mago.getY(), punteroX,punteroY, 25,25, "Fuego", 0,1,true);
						this.mago.setMana(this.mago.getMana() - this.hechizoFuego.getCostoMana());
					}
				}
					
			}
			actualizarHechizo(1);
			colisionaHechizoMurcielago(murcielagos,1);
		}
		
		// Hechizo Hielo
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
		
//----------------------------------------- PUNTERO Y HUD -------------------------------------------
		if (this.botonHechizoFuego.estadoActual()) { // Puntero "Fuego"
			imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero=1]); 
		}
		else if (this.botonHechizoHielo.estadoActual()) { // Puntero "Hielo"
			tipopuntero=2;
			imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero=2]); 
		}
		else { // Puntero normal
			tipopuntero=0;
			imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero=0]); 
		}
		
		// Dibujamos el Puntero
		this.dibujarImagenPuntero(entorno, this.entorno.mouseX(),this.entorno.mouseY());
		// Dibujamos la vida del Mago
		this.mago.dibujarVida(entorno, 5+this.menu.getX(), this.menu.getY()+86);
		// Dibujamos el mana del Mago
		this.mago.dibujarMana(entorno, 5+this.menu.getX(), this.menu.getY()+100);
		// Dibujamos el Hud del Mago
		this.dibujarImagenHudMago(entorno, 5+this.menu.getX(), this.menu.getY()+150);//dibujamos el hub del mago para mostrar la interfaz
		// Escribimos en texto la Vida actual
		entorno.escribirTexto("Vidas:" + this.mago.getVida(), this.menu.getX(),this.menu.getY()); 
		// Escribimos en texto la ronda actual
        entorno.escribirTexto("Ronda: " + this.numeroRonda, this.menu.getX(), this.menu.getY() + 20);

//--------------------------------------- RONDA ---------------------------------------------------
		
		if (MurcielagosEliminados() && cantMurcielagoGenerados == cantMurcielagoTotal) {
			if (numeroRonda==2) {//indicamos la ronda final
				dibujarEstadoJuego(false);
				PantallaFinJuegoGana.dibujarImagenFinJuego(entorno);
			    return;
			}
			else {
				rondaSiguiente(true); // Si no es la ronda final, aumenta la dificultad
			}
		}
		cantMurcielagosEliminados += ColisionesMurcielagos;

//---------------------------------------- BOTONES ------------------------------------------
		// Boton para Hechizo Fuego
		if(entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {
			if(this.botonHechizoFuego.estaDentro(punteroX, punteroY)) {
				this.botonHechizoFuego.cambiarEstado();
				if(this.botonHechizoHielo.estadoActual()) {
					this.botonHechizoHielo.cambiarEstado();
				}
			}
		}
		
		// Boton para Hechizo Hielo
		if(entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {
			if(this.botonHechizoHielo.estaDentro(punteroX, punteroY)) {
				this.botonHechizoHielo.cambiarEstado();
				if(this.botonHechizoFuego.estadoActual()) {
						this.botonHechizoFuego.cambiarEstado();
				}
			}
		}
		
		// Dibujar el boton del Hechizo Fuego
		if(this.botonHechizoFuego.estadoActual()) {
			this.botonHechizoFuego.dibujarnImagenBoton(entorno);
		}else {
			this.botonHechizoFuego.dibujarnImagenBoton(entorno);
		}

		// Dibujar el boton del Hechizo Hielo
		if(this.botonHechizoHielo.estadoActual()) {
			this.botonHechizoHielo.dibujarnImagenBoton(entorno);
		}else {
			this.botonHechizoHielo.dibujarnImagenBoton(entorno);
		}	
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		Juego juego = new Juego();
	}
}