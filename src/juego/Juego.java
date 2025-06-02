package juego;
import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{	
	// Necesario del Juego
	private int anchoVentana= 900;
	private int alturaVentana= 600;
	private Entorno entorno;
	
	private boolean inicioJuego = false;// Variable booleana para iniciar el juego
	// Mago
	private Mago mago;
	
	// Menu
	private Menu menu;
	
	// Instanciamos las variables para los objetos de pantalla
	private PantallaFinJuego PantallaFinJuegoGana;  // Pantalla de ganar
	private PantallaFinJuego PantallaFinJuegoPierde; // Pantalla de perder
	
	// Rocas	
	private Roca[] rocas = {
			new Roca(200,200,1),
			new Roca(600,200,2),
			new Roca(350,100,0),
			new Roca(200,400,2),
			new Roca(550,500,1),

	};
	// Hud mago
	private String hudMago = ("imagenes\\\\hud_mago.png");
	private Image imagenMago = Herramientas.cargarImagen(hudMago); 	
	// Cargamos la imagen de Inicio
	private Image imagenInicio = Herramientas.cargarImagen("imagenes\\\\imagenInicio.png");
	// Puntero	
	private String [] punteroImagenes = {"imagenes\\\\puntero.png","imagenes\\\\puntero_fuego.png","imagenes\\\\puntero_hielo.png"};
	private int tipopuntero = 2;
	private Image imagenPuntero = Herramientas.cargarImagen(punteroImagenes[tipopuntero]); 

	// Imagen de Mapa	
	private Image imagenFondo = Herramientas.cargarImagen("imagenes\\\\mapa2.png");
	
	// Imagenes del Menu de seleccion de Dificultad-Fondo-Dificultad
	private Image imagenDificultad = Herramientas.cargarImagen("imagenes\\\\selectorDificultad.png");
	private Image imagenBtnFacil = Herramientas.cargarImagen("imagenes\\\\botonFacil.png");
	private Image imagenBtnNormal= Herramientas.cargarImagen("imagenes\\\\botonNormal.png");
	private Image ImagenBtnDificil = Herramientas.cargarImagen("imagenes\\\\botonDificil.png");

	// Botones de dificultad
	private Boton botonFacil;
	private Boton botonNormal;
	private Boton botonDificil;
	private boolean seleccionDificultad = false;//Booleano para seleccionar una sola vez la dificultad
	private int rondaFinal; // Ronda final definida por la dificultad

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
	private int velocidadMurcielago = 2; // Velocidad de murcielagos
	private int puntajeMurcielagos = 0;
	// Hechizos	
	private Hechizos hechizoFuego ; // Declaramos Hechizo Fuego
	private Hechizos hechizoHielo ; // Declaramos Hechizo Hielo
	
	// Variables para el puntero
	private int punteroX;
	private int punteroY;

	// Variable de tiempo
	private int tiempo =0;
    private int tiempoInicio = -1;
    private boolean temporizadorEjecutado = false;
    private int tiempoFinal;
	private boolean puntajesGuardados = false;
	boolean salircarga1 = false;
	boolean salircarga2 = false;
	private Image imagenRonda1 = Herramientas.cargarImagen("imagenes\\\\ronda1.png"); ;
	private Image imagenRonda2 = Herramientas.cargarImagen("imagenes\\\\ronda2.png"); ;
	private int regenerarMana;
	private int expirarHielo;
	private int tiempoCarga = 2;//tiempo de espera para las pantallas de carga
	
	private int numeroRonda = 1;// Variable del numero de ronda

	// Botones
	//S = seleccionado - DS = deseleccionado
	private Boton botonHechizoFuego;
	private Image imagenBotonHieloS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoB1.png");
	private Image imagenBotonHieloDS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoB2.png");
	private Boton botonHechizoHielo;
	private Image imagenBotonFuegoS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoA1.png");
	private Image imagenBotonFuegoDS = Herramientas.cargarImagen("imagenes\\boton_menu_hechizoA2.png");


	//----------------Inicio-------------------------
	public void dibujarinicio() {
		entorno.dibujarImagen( imagenInicio, anchoVentana / 2 + 10, alturaVentana / 2, 0, 0.77);
	}
	//----------------Dificultad-------------------------
	public void dibujarDificultad() {
	    this.entorno.dibujarImagen(imagenDificultad, anchoVentana / 2 + 10, alturaVentana / 2, 0, 1.6);
	}
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
	//
	public void esperaEnter() {
		esperaEnter = !esperaEnter;// Lo utilizamos para que el jugador presione enter
	}

//---------------------------Inicio de juego--------------------------

	private boolean inicio() {
		dibujarinicio();
		if (!inicioJuego) {//si ya se inicio el juego anteriormente, no pedira volver a esta pantalla
		    if (this.entorno.sePresiono(entorno.TECLA_ENTER) ) {//Presiona enter para iniciar
		    	inicioJuego = !inicioJuego;
				return inicioJuego;
		    }
			return inicioJuego;
		}
		return true;

	}
//	------------------------ Funcion de estado del Juego ------------------------------------(Se retiro parametro enter para texto)

	public void dibujarEstadoJuego() {
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
	}
//	------------------------ Funcion para aumentar la dificultad de la siguiente ronda ------------------------------------
	public void rondaSiguiente(boolean activar) {// Si recibe true como parametro, ejecutara la siguiente ronda(Se puede modificar)
	    if (activar) {
	        // Aumentar la dificultad
	        this.cantMurcielagoPantalla += 5; // Aummentamos la cantidad de murcielagos en pantalla
	        this.cantMurcielagoTotal += 10; // Aumentamos la cantidad total en la ronda
	        this.velocidadMurcielago += 1; // Aumentamos la velocidad de los murciélagos
	        								//Mayor  "DIFICULTAD"
	        // Reiniciamos nuestro contador de generados y eliminados(y guardamos nuestro puntaje de enemigos eliminados)
			this.puntajeMurcielagos += cantMurcielagosEliminados ; // Acumulador para el puntaje de enemigos por pantalla
	        this.cantMurcielagoGenerados = 0;
	        this.cantMurcielagosEliminados = 0;
	        // Generamos un nuevo array de murciélagos con la nueva cantidad total
	        this.murcielagos = new Murcielago[this.cantMurcielagoTotal];
	        this.numeroRonda++;
	    }
	}
//---------------Métodos propios para Colision----------------------

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
				return false; // Si almenos hay un solo murcielago, no retorna nadfa
			}
		}
		return true; // todos los murcielagos son nulos
	}
	
//	-----------------------------Generar Hechizo-----------------------------------------
	public void actualizarHechizo(int n) {
		if(n == 1) { // Si hablamos de Hechizo Fuego
			if(this.hechizoFuego != null) {
				this.hechizoFuego.moverFuego();
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
	//----------------Cambio de pausa--------------------------
	private boolean manejarPausa() {
	    if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {
	        enPausa = !enPausa;
	    }
	    if (enPausa) {
	        if (this.entorno.sePresiono(entorno.TECLA_ESCAPE)) {//Reutilizamos el reinicio para salir reiniciar desde el Menu
	        	resetearValores();
	        	System.out.println("reinicio");    
	        }
	        dibujarEstadoJuego();
	        dibujarImagenPausa(entorno);
		    return true;

	    }
	    return false;
	}	
	private void SalirJuego() {
		if(this.entorno.estaPresionada(entorno.TECLA_ESCAPE)) { //Tecla Escape
			entorno.dispose();
		}
	}
  	public void resetearValores() {
  		//reiniciamos las variables y objetos a sus valores por default
	    this.numeroRonda = 1;
	    this.enPausa = false;
	    this.esperaEnter = true;
	    this.tiempo = 0;
	    this.tiempoInicio = -1;
	    this.temporizadorEjecutado = false;
	    this.tiempoFinal = 0;
	    this.puntajesGuardados = false;
	    this.salircarga1 = false;
	    this.salircarga2 = false;
	    this.cantMurcielagoPantalla = 10;
	    this.cantMurcielagoTotal = 50;
	    this.cantMurcielagoGenerados = 0;
	    this.cantMurcielagosEliminados = 0;
	    this.puntajeMurcielagos = 0;
	    this.hechizoFuego = null;
	    this.hechizoHielo = null;
	    this.expirarHielo = 0;
	    this.murcielagos = new Murcielago[this.cantMurcielagoTotal];

	    //Restablecemos los apartados visuales del jugador
		this.mago = new Mago(this.menu.getAncho(),alturaVentana); 
		this.botonHechizoFuego = new Boton(790,150, 120, 67,imagenBotonHieloS, imagenBotonHieloDS); // Hechizo Fuego
		this.botonHechizoHielo = new Boton (790,250, 120, 67,imagenBotonFuegoS, imagenBotonFuegoDS); // Hechizo Hielo
		
		//variables de Hechizo y Mana
		regenerarMana = 0;
		expirarHielo = 0;
		this.entorno.iniciar();	// Inicia el juego

	}
    private void ReiniciarJuego() {
        if (this.entorno.estaPresionada(entorno.TECLA_ENTER)) {//Si se presiona Enter en la pantalla final del juegop. Podra Reiniciarlo
        	resetearValores();
        	System.out.println("reinicio");    
        }
    }
   
	//------------------- Movimiento y Colision Mago-Rocas --------------------
	private void actualizarMovimientoMago() {
				if(this.entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) { //Movimiento izquierdo
					if(mago.getX() - mago.getAncho() / 2 > 0 ) {
						this.mago.moverIzquierda();
						if(this.mago.colisionaMagoRoca(rocas)) {
							this.mago.moverDerecha();
						}
					}
				}
				if(this.entorno.estaPresionada(entorno.TECLA_DERECHA)) { //Movimiento Derecho
					if(mago.getX() + mago.getAncho() / 2 < menu.getX() - menu.getAncho() / 2) {
						this.mago.moverDerecha();
						if(this.mago.colisionaMagoRoca(rocas)) {
							this.mago.moverIzquierda();
					        
						}
					}
				}
				if(this.entorno.estaPresionada(entorno.TECLA_ARRIBA)) { //Movimiento Ascendente
					if(mago.getY() - mago.getAltura() / 2 > 0) {
						this.mago.moverArriba();
						if(this.mago.colisionaMagoRoca(rocas)) {
							this.mago.moverAbajo();
						}
					}
				}
				if(this.entorno.estaPresionada(entorno.TECLA_ABAJO)) { //Movimiento Descendente
					if(mago.getY() + mago.getAltura() / 2 < 600 ) {
						this.mago.moverAbajo();
						if(this.mago.colisionaMagoRoca(rocas)) {
							this.mago.moverArriba();
						}
					}
				}	
			
		
	}
	//------------------------Metodo para procesar los Hechizos-----------------------------------
	//Indica si el Hechizo se lanzo, si colisiono o si debera volverse null
	private void procesarHechizos() {
		//-------------------------------------Hechizos-----------------------------------

	    // Hechizo Fuego
	    if (this.botonHechizoFuego.estadoActual()) {
	        if (this.entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
	            if (Hechizos.permitirDisparar(punteroX, menu)) {
	                if (this.hechizoFuego == null) {
	                    this.hechizoFuego = new Hechizos(mago.getX(), mago.getY(), punteroX, punteroY, 25, 25, "Fuego", 1, 1, true);
	                    this.mago.setMana(mago.getMana() - this.hechizoFuego.getCostoMana());
	                }
	            }
	        }
	    }

	    // Hechizo Hielo
	    if (this.botonHechizoHielo.estadoActual()) {
	        if (this.entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
	            if (Hechizos.permitirDisparar(punteroX, menu)) {
	                if (this.hechizoHielo == null && this.mago.getMana() > 5) {
	                    this.hechizoHielo = new Hechizos(punteroX, punteroY, punteroX, punteroY, 200, 200, "Hielo", 7, 1, false);
	                    this.mago.setMana(mago.getMana() - hechizoHielo.getCostoMana());
	                    expirarHielo = 0;
	                }
	            }
	        }

	    }
	    //Sacamos estos metodos de los condicionales, ya que si los dejamos dentro de estos
	    //Al lanzar un hechizo y deselecionar el boton de este, el hechizo se congela
        actualizarHechizo(2);
        colisionaHechizoMurcielago(murcielagos, 2);
        actualizarHechizo(1);
        colisionaHechizoMurcielago(murcielagos, 1);
	}
	//------------------------Metodo para generar una espera-----------------------------------
    public boolean esperarTiempo(int tiempoEspera, int tiempoActual) {
    	if (temporizadorEjecutado){	// Variable para que no se vuelva a aplicar la funcion a no ser que la reiniciemos
    								// Reiniciar definido mas abajo
			return false;
		}
        if (tiempoInicio == -1) { 
            tiempoInicio = tiempoActual; // Se guarda el tiempo cuando se llama por primera vez
        }
        //Si mi "tiempoActual" es mayor o igual a el "tiempoInicio"  + el tiempo esperado "tiempoEspera" retornara true
        if (tiempoActual >= tiempoInicio + tiempoEspera) {
        	temporizadorEjecutado = true;
            return true; 
        }

        return false; // Si no se cumplio el tiempo, sigue pasando el tiempo sin pasar por el primer condicional
    }
    //-----------------------Metodo para reiniciar "esperarTiempo"-------------------------------------
    public void reiniciar() {
        //reinicia tiempo inicio a -1  y el booleano para ejecutar el temporizador
        tiempoInicio = -1;
        temporizadorEjecutado = false;
        
    }
    //-----------------------Metodo para detener el tiempo interno del juego-----------------------------

    //-----------------------Metodo especifico para dibujar los hechizos-------------------------------------
    private void dibujarHechizos() {
        if (this.hechizoFuego != null) {
            this.hechizoFuego.dibujarImagenFuego(entorno);
        }
        if (this.hechizoHielo != null) {
            this.hechizoHielo.dibujarImagenHielo(entorno);
        }
    }
    //-----------------------METODO PARA DIBUJAR INTERFAZ DEL JUEGO-----------------------------------------------
	private void dibujarUI() {
		  
		this.menu.dibujarImagenMenu(entorno); //Dibujamos el menú
		
		this.mago.dibujarVida(entorno, 5+this.menu.getX(), this.menu.getY()+86); // Dibujamos la vida del Mago
		this.mago.dibujarMana(entorno, 5+this.menu.getX(), this.menu.getY()+100);// Dibujamos el manadel Mago
		this.dibujarImagenHudMago(entorno, 5+this.menu.getX(), this.menu.getY()+150);// Dibujamos el Hud del Mago

        entorno.cambiarFont("OCR A Extended", 22, Color.BLACK, entorno.NEGRITA);// Le cambiamos la fuente por una mas estetica
        entorno.escribirTexto("Ronda: " + this.numeroRonda, this.menu.getX()-40, this.menu.getY() + 20);//Mostramos la ronda actual en el Menu
        
        entorno.cambiarFont("OCR A Extended", 18, Color.BLACK, entorno.NEGRITA);// Le cambiamos la fuente por una mas estetica
        entorno.escribirTexto("" + cantMurcielagosEliminados, this.menu.getX()+ 20, this.menu.getY() + 70);//Mostramos los murcielagos eliminados por pantallas de todas las rondas

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
 	 // Dibujar Puntero
	    dibujarImagenPuntero(entorno, entorno.mouseX(), entorno.mouseY());

	}
	//------------------- Pantallas de Carga Entre Ronda y Ronda --------------------
	private boolean manejarPantallasDeCarga() {
	    if (numeroRonda == 1 && !salircarga1) {
	        if (esperarTiempo(tiempoCarga, tiempo)) {// Tiempo de finalizacion de pantalla de carga
	            salircarga1 = true;
	            reiniciar();
	        } else {
	            dibujarEstadoJuego();
	            this.entorno.dibujarImagen(imagenRonda1, anchoVentana / 2 + 10, alturaVentana / 2, 0, 1.7);
	            return true; // Sigue mostrando pantalla de carga
	        }
	    }

	    if (numeroRonda == 2 && !salircarga2) {
	        if (esperarTiempo(tiempoCarga, tiempo)) {
	            salircarga2 = true;
	        } else {
	        	dibujarEstadoJuego();
	            this.entorno.dibujarImagen(imagenRonda2, anchoVentana / 2 + 10, alturaVentana / 2, 0, 1.7);
	            return true; // Sigue mostrando pantalla de carga
	        }
	    }

	    return false; // No hay pantalla de carga activa
	}
	//----------------------------------------- PUNTERO Y HUD -------------------------------------------
	private void actualizarPuntero() {// Puntero "Fuego"
	    if (botonHechizoFuego.estadoActual()) {
	        imagenPuntero = Herramientas.cargarImagen(punteroImagenes[1]); 
	        tipopuntero = 1;
	    } else if (botonHechizoHielo.estadoActual()) {// Puntero "Hielo"
	        imagenPuntero = Herramientas.cargarImagen(punteroImagenes[2]); 
	        tipopuntero = 2;
	    } else {// Puntero normal
	        imagenPuntero = Herramientas.cargarImagen(punteroImagenes[0]); 
	        tipopuntero = 0;
	    }
	}
	//----------------------------------------- PUNTERO Y HUD -------------------------------------------
	private void manejarEntradas() {//Metodo para verificar cuando se presionaron los botones del Mouse
		// Obtenemos los valores del punteroX y el PunteroY propios del mouse
	    punteroX = entorno.mouseX();
	    punteroY = entorno.mouseY();
	    
	    if (entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {//Condicional al levantar el Boton
	    	
	        if (botonHechizoFuego.estaDentro(punteroX, punteroY)) {//Si esta dentro del area del boton(Boton Fuego)
	            botonHechizoFuego.cambiarEstado();
	            if (botonHechizoHielo.estadoActual()) botonHechizoHielo.cambiarEstado();
	        } else if (botonHechizoHielo.estaDentro(punteroX, punteroY)) {//Si esta dentro del area del boton(Boton Hielo)
	            botonHechizoHielo.cambiarEstado();
	            if (botonHechizoFuego.estadoActual()) botonHechizoFuego.cambiarEstado();
	        }
	    }

	    actualizarPuntero();//llamamos a nuestro metodo para modificar el puntero
	}
	//----------------------------------------- TIEMPO DEL JUEGO -------------------------------------------
	private void actualizarTiempo() {
		//Manejamos el tiempo con el tick ya que el tiempó no es preciso(por lo que vi,, es por los bucles internos tambien)
		if(this.entorno.numeroDeTick() % 100 == 1) {// Dividimos la cantidad de ticks y cuanto el resto sea exactamente 1 aumenta el "tiempo"
			tiempo++;
			System.out.println(tiempo);
		}
	}
	//----------------------------------------- METODO PARA DIBUJAR EL ESTADO ACTUAL DEL MURCIELAGO-------------------------------------------
	private void dibujarEstadoMurcielago() {
		//	--------------------------Persecusión Murcielago------------------------------
			for (int i = 0; i < murcielagos.length; i++) {
				if(this.murcielagos[i] != null) { // Si el murcielago es diferente de null, lo persigue
					Funciones_utiles.seguimientoMurcielago(mago, murcielagos[i]); // Actualiza posición del murcielago actual

				    this.murcielagos[i].actualizarAnimacion(this.mago.getX()); // Actualizamos la animacion del murcielago
		            this.murcielagos[i].dibujarImagen(entorno); // Dibujamos al Murcielago
				}
			}
	}
	//----------------------------------------- METODO PARA DIBUJAR EL ENTORNO DEL JUEGO-------------------------------------------
	 private void dibujarEscena() {
			this.entorno.dibujarImagen(imagenFondo, anchoVentana / 2 + 10, alturaVentana / 2, 0, 1.7);

			//------------------------------ Mago --------------------------
					this.mago.dibujarImagenMago(entorno); //dibujamos al mago											
			//------------------------------ Menu --------------------------
					this.menu.dibujarImagenMenu(entorno); //Dibujamos el menú			
		
			//------------------------------ Rocas --------------------------
					for (int i = 0; i < rocas.length;i++) { //Dibujamos a las Rocas
						rocas[i].dibujarImagenRoca(entorno);	
					}
			//------------------------------ Murcielagos--------------------------
					dibujarEstadoMurcielago();
			//------------------------------ Interfaz de Usuario--------------------------
					dibujarUI();
			//------------------------------ Hechizos--------------------------
					dibujarHechizos();
				
	}
	 //----------------------------------------- METODO PARA GUARDAR EL TIEMPO FINAL DEL JUEGO-------------------------------------------
	private void guardarTiempoPuntaje() {
		if (!puntajesGuardados) {	//Sin este condicional, sigue aumentando el tiempoFinal
			tiempoFinal = tiempo; // Guardamos el tiempo para mostralo al final
			puntajesGuardados = true;
			puntajeMurcielagos += cantMurcielagosEliminados ;
			System.out.println("Tiempo Guardado");
		}
	}
	 //----------------------------------------- METODO PARA VERIFICAR QUE TERMINO EL JUEGO-------------------------------------------
	private void verificarFinJuego() {
		//-----------------Condicion para finalizar la Ronda/Juego------------------------
		if (MurcielagosEliminados() && cantMurcielagoGenerados == cantMurcielagoTotal) 
		{	
			if (numeroRonda==rondaFinal) {//Indicamos la ronda final
				guardarTiempoPuntaje();//Guardamos el tiempo y la cantidad de murcielaghos eliminados total
				dibujarEstadoJuego();
				PantallaFinJuegoGana.dibujarImagenFinJuego(entorno);
		        entorno.cambiarFont("Arial", 38, Color.BLACK, entorno.NEGRITA);// Le cambiamos la fuente por una mas estetica
		        entorno.escribirTexto("" + tiempoFinal, this.PantallaFinJuegoGana.getX()+ 200, this.PantallaFinJuegoGana.getY() - 30);
		        entorno.escribirTexto("" + puntajeMurcielagos, this.PantallaFinJuegoGana.getX()+ 200, this.PantallaFinJuegoGana.getY() + 15);
		        SalirJuego();
		        ReiniciarJuego();
			    return;
			}
			else {
				rondaSiguiente(true); // Si no es la ronda final, aumenta la dificultad
			}	
		}
	}
	//---------------Funcion para el control del juego-PAUSA-FIN DEL JUEGO(GANAR/PERDER)-RONDAS
	private void controlarFinDeJuego() {
		if (mago.estaMuerto()) { //si el mago esta sin vida, mostramos pantalla fin de juego perdido
			guardarTiempoPuntaje();//Guardamos el tiempo y la cantidad de murcielaghos eliminados total
		    dibujarEstadoJuego();
		    PantallaFinJuegoPierde.dibujarImagenFinJuego(entorno);
	        entorno.cambiarFont("Arial", 38, Color.BLACK, entorno.NEGRITA);// Le cambiamos la fuente por una mas estetica
	        this.entorno.escribirTexto("" + tiempoFinal, this.PantallaFinJuegoPierde.getX()+ 200, this.PantallaFinJuegoPierde.getY() - 30);//Mostramos los murcielagos eliminados por pantallas de todas las rondas
	        this.entorno.escribirTexto("" + puntajeMurcielagos, this.PantallaFinJuegoPierde.getX()+ 200, this.PantallaFinJuegoPierde.getY() + 25);
	        SalirJuego();
	        ReiniciarJuego();
	        return;
		}
	}
	 //-----------------------------------------PROCESA TODA LA LOGICA DE LOS MURCIELAGOS-------------------------------------------
	 //Esto realiza: Colision de Mago/murcielago, Actualiza posicion y Estado Murcielago, Generacion de Murcielagos, Contador total
	private void procesarMurcielagos() {
			//-----------------Colision entre Mago y Murciélago--------------------------------
			int ColisionesMurcielagos=Funciones_utiles.colisionMagoMurcielago(mago, murcielagos); // Esto va indicando si el murcielago colisiona con mago
		 	// Si es así, entonces el murcielago será null
		 	//		-------------------Restablecer Murcielagos------------------------------------
			cantMurcielagoGenerados = Funciones_utiles.actualizarMurcielagos(
				    murcielagos, // Array de murcielagos 
				    cantMurcielagoPantalla, // Cantidad en pantalla 
				    cantMurcielagoGenerados, // Cantidad de generados
				    cantMurcielagoTotal, // Cantidad total
				    anchoVentana, // Ancho de ventana del juego
				    alturaVentana, // Alto de ventana del juego
				    velocidadMurcielago, // Velocidad de murcielago
				    menu, // Y el propio menú
				    numeroRonda//valor de nuestra ronda
				);	

			//-----------------Colision entre Mago y Murciélago = Perder vida------------------------
			for(int i = 0; i < murcielagos.length;i++) { // recorremos los murcielagos
				if(this.murcielagos[i] != null) { //  Preguntamos si son distintos de null
					Funciones_utiles.colisionMagoMurcielago(mago, murcielagos); // nos vamos a la colision
				}// Ademas de que el elemento murcielago sea null, el mago perderá vida
			}
			//-----------------Contador de murcielagos totales eliminados------------------------
					cantMurcielagosEliminados += ColisionesMurcielagos;//Contador que definira si se termina la ronda/juego
				}
	 //-----------------------------------------ACTUALIZA EL MANA DEL JUGADOR-------------------------------------------

	public void actualizarMana() {
		// Regeneramos el mana
		regenerarMana ++; // Constantemente irá subiendo
		if(regenerarMana >= 60) { // Hasta que se llegue a 60 ticks --> 1 segundo
			if(this.mago.getMana() < 10) { // Si el mana del mago es menor a 10
				this.mago.setMana(this.mago.getMana()+1); // Se aumenta el mana +1
			}
			regenerarMana = 0; // Si no es así, vuelve a 0 
		}
	}
	//Metodo para  dibujar el selector de Dificultad con los botones
	public void dibujarSelectorDificultad() {
		dibujarDificultad();
		this.botonFacil.dibujarnImagenDificultad(entorno, 1.5);
	    this.botonNormal.dibujarnImagenDificultad(entorno, 1.5);
	    this.botonDificil.dibujarnImagenDificultad(entorno, 1.5);
	}
	 //-----------------------------------------LOGICA DEL SELECTOR DE DIFICULTAD-------------------------------------------
	public void manejarSelectorDificultad() {//Se seleciona una dificultad para definir la cantidad de rondas a jugar
	    int mouseX = entorno.mouseX();
	    int mouseY = entorno.mouseY();
	    	//
	    if (entorno.seLevantoBoton(entorno.BOTON_IZQUIERDO)) {//Condicional al levantar el Boton
	        if (botonFacil.estaDentro(mouseX, mouseY)) {
	        	rondaFinal = 1;
	            seleccionDificultad = true;
	            System.out.println("Dificultad Fácil");
	        } else if (botonNormal.estaDentro(mouseX, mouseY)) {
	            seleccionDificultad = true;
	            rondaFinal = 2;
	            System.out.println("Dificultad Normal");
	       // } else if (botonDificil.estaDentro(mouseX, mouseY)) {//Modo Dificil "bloqueado"
	            //     seleccionDificultad = true;
	            //rondaFinal = 3;
	            //System.out.println("Dificultad Difícil");
	        }
	    }
	}

	Juego()
	{	
		//instanciamos los botones de dificultad
		this.botonFacil = new Boton(450, 250, 200, 50, imagenBtnFacil, imagenBtnFacil);
		this.botonNormal = new Boton(450, 330, 200, 50, imagenBtnNormal, imagenBtnNormal);
		this.botonDificil = new Boton(450, 410, 200, 50, ImagenBtnDificil, ImagenBtnDificil);       
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", anchoVentana, alturaVentana);
				
		// Inicializamos el menu
		this.menu = new Menu(anchoVentana,alturaVentana); 
		
		// Inicializamos al mago
		this.mago = new Mago(this.menu.getAncho(),alturaVentana); 

		// Inicializamos las pantallas
		this.PantallaFinJuegoGana = new PantallaFinJuego(anchoVentana, alturaVentana, 1); // Pantalla Juego Gana
		this.PantallaFinJuegoPierde = new PantallaFinJuego(anchoVentana, alturaVentana, 2); // Pantalla Juego Pierde
		
		// Inicializamos los botones del Hechizo
		this.botonHechizoFuego = new Boton(790,150, 120, 67, imagenBotonHieloS, imagenBotonHieloDS); // Hechizo Fuego
		this.botonHechizoHielo = new Boton (790,250, 120, 67,imagenBotonFuegoS, imagenBotonFuegoDS); // Hechizo Hielo
		
		// Regenerar Mana
		regenerarMana = 0;
		
		// Expirar el Hechizo Hielo
		expirarHielo = 0;
		
		
		// Inicia el juego!
		this.entorno.iniciar();	
		
	
		
	}

	// Metodo tick para cada instante del juego
	public void tick()
	{		   
		// Funcion de inicio de juego para ejecutarse una sola vez(Solo muestra pantalla de inicio y espera ENTER)
		if (!inicio()) {
		    return;
		}
		// Funcion para seleccionar dificultad despues del inicio
		if (!seleccionDificultad) {
			dibujarSelectorDificultad();
		    manejarSelectorDificultad();

			System.out.println("select D");
		    return;
		}
		//Funcion para aplicar pausa(Tecla ESPACIO)
		if (manejarPausa()) {
			return;
		}
		//Inicia el juego  con el contador de tiempo
	    actualizarTiempo();
		//Pantalla de cargas entre Rondas
		if (manejarPantallasDeCarga()) {
		    return; // se está mostrando la pantalla de ronda
		}
		//Controla el si el jugador a Perdido el juego
	    controlarFinDeJuego();
	    if (mago.estaMuerto()) {
		    return;
		}
	    //Realizara el nuevo dibujo del mago al moverse
		actualizarMovimientoMago();
		//Funcion para actualizar el mana que utilizo el jugador
		actualizarMana();
		//Funcion para procesar el estado y contador de Murcielagos
		procesarMurcielagos();
		//Funcion para el manejo de entrada del click dek jugador
	    manejarEntradas();
	    //Funcion de dibujo de objetos estaticos Y dinamicos, como la la interfaz(UI) y los Hechizos 
	    dibujarEscena();
	    //Proocesamos la logica de los hechizos y su estado
		procesarHechizos();
		//Verificamos si se cumplen las condiciones para finalizar la Ronda/Juego
		verificarFinJuego();

	}
	
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
            Juego juego = new Juego();  
            
	}
}
