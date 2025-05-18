package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	
	private Entorno entorno;
	private Mago mago;
	private Menu menu;
	Roca[] rocas = {
		    new Roca(500, 500),
		    new Roca(300, 200),
		    new Roca(600, 200),
		    new Roca(300, 600),
		};
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		
		this.entorno = new Entorno(this, "Proyecto para TP", 1000, 600);
		this.mago = new Mago(); //inicializamos al mago
		this.menu = new Menu(); //inicializamos el menu

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
	
	
	// Colisiones
	//funcion de colision del mago
	//toma los parametros de 2 objetos(cordenadas X/Y,alto y ancho)
	public boolean colisionMago(int x1, int y1, int ancho1, int alto1,
            					int x2, int y2, int ancho2, int alto2) {
	//calculamos los lados de nuestro objeto A
	int izquierda1 = x1 - ancho1 / 2; 
	int derecha1 = x1 + ancho1 / 2;
	int arriba1 = y1 - alto1 / 2;
	int abajo1 = y1 + alto1 / 2;
	
	//y calculamos los lados de nuestro objeto B

	int izquierda2 = x2 - ancho2 / 2;
	int derecha2 = x2 + ancho2 / 2;
	int arriba2 = y2 - alto2 / 2;
	int abajo2 = y2 + alto2 / 2;
	
	
	//Calculo AABB, si 2 recatangulos se superponen se compara sus bordes
	return derecha1 >= izquierda2 &&
	izquierda1 <= derecha2 &&
	abajo1 >= arriba2 &&
	arriba1 <= abajo2;
	}
	
	// Procesamiento de un instante de tiempo
	public void tick()
	{
	//variables que guardaran el valor en el que se movera, eje X/Y
	int moverseEnX = 0;
    int moverseEnY = 0;

    
    //nos indicara en que direccion el mago se VA a mover
    //nos guardara el valor positivo/negativo en X o el positivo/negativo en Y
    if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
    	moverseEnX = moverseEnX - mago.getVelocidad();
    
    if (entorno.estaPresionada(entorno.TECLA_DERECHA))
    	moverseEnX = moverseEnX + mago.getVelocidad();
    
    if (entorno.estaPresionada(entorno.TECLA_ARRIBA))
    	moverseEnY = moverseEnY-mago.getVelocidad();
    
    if (entorno.estaPresionada(entorno.TECLA_ABAJO))
    	moverseEnY = moverseEnY + mago.getVelocidad();

    //variables para calcular la proxima posicion del mago
    int futuroX = mago.getX() + moverseEnX;
    int futuroY = mago.getY() + moverseEnY;

    // calculamos el proximo valor que tendra la direccion a la que nos movamos
    int magoIzq = futuroX - mago.getAncho() / 2;//aumento izquierda
    int magoDer = futuroX + mago.getAncho() / 2;//aumento derecha
    
    int magoArr = futuroY - mago.getAltura() / 2;//aumento abajo
    int magoAba = futuroY + mago.getAltura() / 2;//aumento arriba

    // Validamos colision con roca y que no se salga del escenario
    // tomamos los valores de nuestra roca pero tambien 
    // los valores de la futura posicion del mago
    
    boolean colision = false;
    for (int i = 0; i < rocas.length; i++) {
        Roca roca = rocas[i]; // accedemos a cada roca individualmente
        
        // tomamos los valores de nuestra x roca pero tambien 
        // los valores de la futura posicion del mago
        if (colisionMago(futuroX, futuroY, mago.getAncho(), mago.getAltura(),
                         roca.getX(), roca.getY(), roca.getAncho(), roca.getAltura())) {
            colision = true;
            break; // salimos si ya encontramos una colision y la retornamos
        }
    }
    
    // pasamos los parametros del escenario a variables
    int limiteIzquierdo = 0;
    int limiteDerecho = menu.getX() - menu.getAncho() / 2;
    int limiteSuperior = 0;
    int limiteInferior = 600;
    
    //booleano para comparativa de colision con el escenario
    boolean dentroDelEscenario = magoIzq > limiteIzquierdo &&
                                 magoDer < limiteDerecho &&
                                 magoArr > limiteSuperior &&
                                 magoAba < limiteInferior;
    //si no se produce colision futura (false) y esta dentro del escenario (True)
    if (!colision && dentroDelEscenario) {
    	//verifica el valor del proximo movimiento para cada lado
    	//primero verificar valor y luego ejecuta movimiento
        if (moverseEnX < 0) mago.moverIzquierda();
        if (moverseEnX > 0) mago.moverDerecha();
        if (moverseEnY < 0) mago.moverArriba();
        if (moverseEnY > 0) mago.moverAbajo();
    }
		
//		---------------------Mago--------------------------
		this.mago.dibujar(entorno); 
		
		
		//ciclo for para dibujar las rocas
		for (int i = 0; i < rocas.length; i++) {
		    rocas[i].dibujar(entorno);
		}


//		-------------------------Menu---------------------------
		this.menu.dibujar(entorno);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
