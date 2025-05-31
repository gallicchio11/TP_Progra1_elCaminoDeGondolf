package juego;
import entorno.Entorno;
import java.awt.Image;
import entorno.Herramientas;



public class Hechizos {
	// ATRIBUTOS
	
	// Area de Efecto
	private int x;
	private int y;
	private int ancho;
	private int altura;
	
	// Costo de Mana
	private int costoMana;
	
	// Nombre del Hechizo
	private String nombre;
	
	// Direccion del Hechizo
	private double direccion;

 	// Variables para la velocidad de la treayectoria
    private int vx;
    private int vy;
    
	// Imagen del Hechizo
    private Image imagen;
    private Image[] imagenesFuego;
    private Image[] imagenesHielo;
    
 	// Variables de animacion para movimiento  
    private int contadorFrames = 0;
    private int velocidadFrames = 5;
    
	// Nos determinara el fram actual del movimiento hacia la direccion
    private int frame = 0;
	private double angulo = 1;
    
    
    // Array de imagenes de hechizo Fuego 
	private String[] movimientoHechizoFuego = {
    	    "imagenes\\\\fuego1.png",
    	    "imagenes\\\\fuego2.png",
    	    "imagenes\\\\fuego3.png",
    	    "imagenes\\\\fuego4.png",
    	    "imagenes\\\\fuego5.png",
    	    "imagenes\\\\fuego6.png",
    	    "imagenes\\\\fuego7.png",
    	    "imagenes\\\\fuego8.png"
    	};
	// Array de imagenes de hechizo Fuego
    private String[] movimientoHechizoHielo = {
    	    "imagenes\\\\hielo1.png",
    	    "imagenes\\\\hielo2.png",
    	    "imagenes\\\\hielo3.png",
    	    "imagenes\\\\hielo4.png",
    	    "imagenes\\\\hielo5.png",
    	    "imagenes\\\\hielo6.png",
    	    "imagenes\\\\hielo7.png",
    	    "imagenes\\\\hielo8.png",
    	    "imagenes\\\\hielo9.png",
    	    "imagenes\\\\hielo10.png",
    	    "imagenes\\\\hielo11.png",
    	    "imagenes\\\\hielo12.png",
    	    "imagenes\\\\hielo13.png",
    	    "imagenes\\\\hielo14.png",
    	    "imagenes\\\\hielo15.png",
    	    "imagenes\\\\hielo16.png",
    	    "imagenes\\\\hielo17.png",
    	    "imagenes\\\\hielo18.png",
    	    "imagenes\\\\hielo19.png",
    	    "imagenes\\\\hielo20.png"
    	};

	// Constructor
	public Hechizos(int xInicio, int yInicio,
					int xDestino, int yDestino, int ancho,int altura, 
					String nombre, int costoMana, int angulo, boolean tieneTrayectoria) {
		
		this.x = xInicio;
	    this.y = yInicio;
	    this.ancho = ancho;
	    this.altura = altura;
		this.nombre = nombre;
		this.costoMana = costoMana;
		this.angulo = angulo;
 
	    // Cargamos las imagenes a nuestra clasecon "cargarImagenes" con el array de movimientoHechizo
    	this.imagenesFuego = cargarImagenes(movimientoHechizoFuego);
    	this.imagenesHielo = cargarImagenes(movimientoHechizoHielo);

    	if (tieneTrayectoria) {
    	    calcularTrayectoria(xInicio, yInicio, xDestino, yDestino);
    	}
    	
    	// Verifica que el contendido concida con la clase y le asigna las imagenes corrspondientes
    	if (nombre.equals("Fuego")) {
    	    this.imagen = imagenesFuego[0];
    	} else if (nombre.equals("Hielo")) {
    	    this.imagen = imagenesHielo[0];
    	}

	}
	
	// Cargar Imagenes
	private Image[] cargarImagenes(String[] nombresImagenes) {
		//creamos un array de imagenes con el mismo tamaño que nuestreas imagenes
	    Image[] imagenes = new Image[nombresImagenes.length];
	    //reccoremos el array
	    for (int i = 0; i < nombresImagenes.length; i++) {
	    	//cargamos cada imagen en nuestro nuevo array 
	        imagenes[i] = Herramientas.cargarImagen(nombresImagenes[i]);
	    }
	    //retornamos un nuevo array con las imagenes cargadas
	    return imagenes;
	}
	
	// Animar Movimiento de Hechizos
	private int animarMovimiento(Image [] frames, int frameActual) {
	    contadorFrames++; // // aumentamos un contador de frames para generar delay entre frame y frame

	    if (contadorFrames >= velocidadFrames) {// cuando llegue a 5 se aplicara el siguiente frame
	        this.imagen = frames[frameActual]; //cargamos el frame desde el array de imagenes "movimientoAbajoMago" 
	        frameActual++; // aumenta frame
	        
	        if (frameActual >= frames.length) {// si el Frame abajo es  mayor o igual a el tamaño total de nuestro array de frames, volvera a 0
	            frameActual = 0; // reiniciamos nuestro frame actual para volver al inicio(un bucle)
	        }
	        contadorFrames = 0; // reiniciamos el delay entre frame y frame
	    }
	    return frameActual; // nos devolvera el frame actual
	}
	
	// Get costo Mana
	public int getCostoMana() {
		return costoMana;
	}

	// Permite disparar si el mouse se encuentra dentro de la pantalla del juego, sino no puede disparar
	public static boolean permitirDisparar(int xPuntero, Menu menu) {
		return xPuntero < menu.getX() - menu.getAncho() / 2;
	}
	
	//	Dibujar imagen Hechizo Fuego
	public void dibujarImagenFuego(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, this.angulo, 2);
	}
	
	//	Dibujar imagen Hechizo Hielo
	public void dibujarImagenHielo(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, 0, 2);
	}	
	
//-------------------- TRAYECTORIA DE LOS HECHIZOS ----------------------
	private void calcularTrayectoria(int xInicio, int yInicio, int xFin, int yFin) {
        int dx = xFin - xInicio;
        int dy = yFin - yInicio;
        int distanciaCuadrada = dx * dx + dy * dy;
        if (distanciaCuadrada > 0) {
        	int distancia = (int) Math.sqrt(distanciaCuadrada);
            int escala = 10; // medida para normalizar la velocidad en los 2 ejes
            this.vx = dx * escala / distancia;
            this.vy = dy * escala / distancia;
            
	        //------CODIGO PARA ANIMACION DE SPRITES------
	        // toma 2 puntos, calcula los grados (de rotación) entre X y el vector(dx, dy)
	        // y pasamos los valores a condicionales para cada valor de rotar imagen
            
            // se calcula el ángulo en radianes desde el eje "X" y el punto definido por (dx, dy).
            // de esta forma sacamos el angulo que representa la direccion desde el mago hacia el mouse
            double anguloRad = Math.atan2(dy, dx); 
            
            // convertimos radianes a grados , lo caul nos deja valores decimales
            double anguloEnGrados= Math.toDegrees(anguloRad);

            // Redondear a entero más cercano y castearlo a int
            int anguloGrados = (int) Math.round(anguloEnGrados);

            anguloGrados = (anguloGrados + 359) % 359;  //pasamos los valores en negativos(sumamos 360 a el valor "negativo", y solo lo que quede del resto sera el valor)
            
            // grados posibles tanteados con condicionales: 0°, 22.5°, 45°, 67.5°, 90°, 112.5°, 135°, 157.5°, 180°, 202.5°, 225°, 247.5°, 270°, 292.5°, 315°, 337.5°
            // sumamos 11 a nuestros grados apra redondear , ya que cada sector va de 22.5 en 22.5 osea  + 11
            // dividimos por el tamaño de nuestro sector ( / 22)
            //redondeamos el valor y quitamos decimales obteniendo el resto %16
            int indiceSector = ((anguloGrados + 11) / 22) % 16;

            double[] direcciones = {4.75, 5.0, 5.25, 0.25, 0, 0.75, 1.0, 1.25,
                    1.5, 1.75, 2.25, 2.5, 3.25, 3.75, 4.0, 4.5};

            
            this.direccion = direcciones[indiceSector];
            this.angulo = direccion;
        }
    }
    
//-------------- MOVER HECHIZO ---------------------
    public void moverFuego() {
        this.x += vx;
        this.y += vy;
        
        frame = animarMovimiento(imagenesFuego, frame);
    }

    public void moverHielo() {
        this.x += vx;
        this.y += vy;
        frame = animarMovimiento(imagenesHielo, frame);
    }
    
// ---------------------- BORDES DEL HECHIZO ----------------------
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