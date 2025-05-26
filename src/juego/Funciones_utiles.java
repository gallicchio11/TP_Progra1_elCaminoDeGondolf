package juego;

import java.util.Random;

public class Funciones_utiles {

//-------------------- Colision Mago Murciélago ----------------------------
	public static void colisionMagoMurcielago(Mago mago, Murcielago[] murcielagos) {
	    for (int i = 0; i < murcielagos.length; i++) { // Recorremos los murcielagos
	        if (murcielagos[i] != null) { // Si el elemento murcielago es distinto de null
	            if (mago.limiteIzquierdo() < murcielagos[i].limiteDerecho() &&
	                mago.limiteSuperior() < murcielagos[i].limiteInferior() &&
	                mago.limiteInferior() > murcielagos[i].limiteSuperior() &&
	                mago.limiteDerecho() > murcielagos[i].limiteIzquierdo()) {
	                murcielagos[i] = null;  // Convierte el elemento murcielago en null
	                mago.perderVida();
	                return; // Salimos después de la primera colisión, si solo querés borrar uno
	            }
	        }
	    }
	}
//	--------------------------Calcula seguimiento de Murcielago------------------------------
	 public static void seguimientoMurcielago (Mago mago, Murcielago murcielagos) {
		 if (murcielagos != null) {
			 int dx = mago.getX() - murcielagos.getX();
			 int dy = mago.getY() - murcielagos.getY();	
			 int distanciaCuadrada = dx * dx + dy * dy; // sacamos cuadrado de X y D multiplcando sus lados y sumandolos.
			 
			 if (distanciaCuadrada > 0){// condicional para verificar que la distancia no sea 0(al ser 0 significara que el murcielago esta encima de el mago)
				 						//posible uso aplicable a daño recibido al mago
				 int distancia = (int) Math.sqrt(distanciaCuadrada); // Sigue siendo int
			     int escalaM = 2; // con la escala de movimiento definimos de cuanto sera la velocidad a la que se movera
			        //la escala se toma como la relacion de distancia entre 2 ejes, si uno aumenta el otro tambien, por lo caul se utilizara el mismo valor, osea escalaM   
			        
			     int vx = dx * escalaM / distancia;//normalizamos la velocidad multiplicandola por nuestra escala y dividiendola por distanciaa("hipotenusa")
			     int vy = dy * escalaM/ distancia;//para velocidad de X e Y
		
			     //actualizamos la posicion de nuestro murcielago con setx/y en lugar de crear 4 condicionales para eso(UTILIZAR SETTERS Y GETTERS!!!!)
			     murcielagos.setX(murcielagos.getX() + vx);
			     murcielagos.setY(murcielagos.getY() + vy);
			  }
	    }
	}

//------------------------------- Generar murcielagos----------------------------------------
	public static void generarMurcielago(
	    int k, // Elemento mucielago
	    Murcielago[] murcielagos, // El array de murcielagos
	    int cantMurcielagoGenerados, // Cantidad de murcielagos generados
	    int cantMurcielagoTotal, // Cantidad total de murcielagos
	    int anchoVentana, // Ancho de la ventana
	    int alturaVentana, // Altura de la ventana
	    Menu menu){ // Menu
		
	    if (cantMurcielagoGenerados >= cantMurcielagoTotal) {
	    	return; // No generar más, retorna hasta cantidad total
	    }
	    Random random = new Random();
	    int borde = random.nextInt(4);
	    int x = 0;
	    int y = 0;
	    String direccion = "";
//	    Dependiendo del numero random, saldrá en x lado de la pantalla del juego
	    if (borde == 0) { // Lado izquierdo
	        x = 0;
	        y = random.nextInt(alturaVentana);
	        direccion = "izquierda";
	    } else if (borde == 1) { // Lado derecho
	        x = anchoVentana - menu.getAncho();
	        y = random.nextInt(alturaVentana);
	        direccion = "derecha";
	    } else if (borde == 2) { // Lado superior
	        x = random.nextInt(anchoVentana - menu.getAncho());
	        y = 0;
	        direccion = "arriba";
	    } else { // Lado inferior
	        x = random.nextInt(anchoVentana - menu.getAncho());
	        y = alturaVentana;
	        direccion = "abajo";
	    }
//	    Una vez obtuvimos los valores deseados, generamos el elemento murcielago
	    murcielagos[k] = new Murcielago(x, y, direccion);
	}

//-------------------------Actualizar murcielagos---------------------------------- 
	public static int actualizarMurcielagos(Murcielago[] murcielagos, // Recibe el array murcielagos
		int cantMurcielagoPantalla, // Cantidad en pantalla
		int cantMurcielagoGenerados, // Cantidad de generados
		int cantMurcielagoTotal, // Cantidad total
		int anchoVentana, // Ancho de la ventana
		int alturaVentana, // Altura de la ventana
		Menu menu) { // Menu
		int vivosActuales = 0;   // Nos indicará la cantidad de murcielagos vivos que están actualmente
		for (int i = 0; i < murcielagos.length; i++) { // Recorremos
			if (murcielagos[i] != null) { // Si es diferente de null
				vivosActuales++;
			} 
		}
//		System.out.println(vivosActuales); //descomentar para ver la cantidad de murcielagos vivos actuales en pantalla
		    
		/* La variable "faltan" nos sirve para indicar cuantos enemigos faltan en pantalla
		 * Esto es para que se regeneren los que faltan en pantalla.
		 */
		int faltan = cantMurcielagoPantalla - vivosActuales;
		for (int i = 0; i < murcielagos.length; i++) { // Recorremos los murcielagos 
			/* Si el elemento murcielago es igual a null, si la cantidad de murcielagos que faltan
			 * Es mayor a 0, y que la cantidad de murcielagos generados sea menor a la total; entonces...
			 */
			if (murcielagos[i] == null && faltan > 0 && cantMurcielagoGenerados < cantMurcielagoTotal) {
				generarMurcielago(i, murcielagos, cantMurcielagoGenerados, // Se genera un murcielago
				cantMurcielagoTotal,anchoVentana, alturaVentana, menu);
				cantMurcielagoGenerados++; // Aumenta la cantidad de murcielagos generados
//				System.out.println(cantMurcielagoGenerados);
				faltan--; // Que se resten los que falten
				vivosActuales++; // Aumente los vivos en pantalla.
			}
		}
		return cantMurcielagoGenerados; // Retornamos el nuevo contador actualizado
	}
// --------------------------- Trayectoria Hechizo --------------------------------
	public static void TrayectoriaHechizo(Mago mago, int punteroX, int punteroY) {
		int dx = punteroX - mago.getX();
		int dy = punteroY - mago.getY();

		int distanciaCuadrada = dx * dx + dy * dy; // Sacamos cuadrado de X e Y multiplcando sus lados y sumandolos.

		if (distanciaCuadrada > 0){ /* Condicional para verificar que la distancia no sea 0 (al ser 0 significara 
									*  que el murcielago esta encima de el mago) posible uso aplicable a daño recibido al mago
		   							*/
			int distancia = (int) Math.sqrt(distanciaCuadrada); // Sigue siendo int
		    int escalaM = 5; // Variable que determina la velocidad de movimiento. La escala toma como relacion de 
		    				 //	distancia entre 2 ejes. Si uno aumenta o desciende, el otro eje hará lo mismo
		    int vx = dx * escalaM / distancia;// Normalizamos la velocidad multiplicandola por nuestra escala y dividiendola por distanciaa("hipotenusa")
		    int vy = dy * escalaM/ distancia;// para velocidad de X e Y

		}
	}
	
	

}
