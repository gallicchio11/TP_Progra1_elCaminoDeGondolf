package juego;

import java.util.Random;

public class Funciones_utiles {
	
//--------------------------- COLISION MAGO MURCIELAGO -----------------------------------
	public static int  colisionMagoMurcielago(Mago mago, Murcielago[] murcielagos) {
	    int colisiones = 0;
		for (int i = 0; i < murcielagos.length; i++) {
	        if (murcielagos[i] != null) {
	            if (mago.limiteIzquierdo() < murcielagos[i].limiteDerecho() &&
	                mago.limiteSuperior() < murcielagos[i].limiteInferior() &&
	                mago.limiteInferior() > murcielagos[i].limiteSuperior() &&
	                mago.limiteDerecho() > murcielagos[i].limiteIzquierdo()) {
	                murcielagos[i] = null;  // Convierte el elemento murcielago en null
	                mago.setVida(mago.getVida()-1);
	                colisiones++; 
	            }
	        }
	    }
        return colisiones; // Salimos después de la primera colisión, si solo querés borrar uno
	}
//------------------------------ CALCULA SEGUIMIENTO DE MURCIELAGO ------------------------------
	public static void seguimientoMurcielago (Mago mago, Murcielago murcielagos) {
		if (murcielagos != null) { // Si es diferente de null
			int dx = mago.getX() - murcielagos.getX(); // Obtenemos distancia en X entre el Mago y Murcielago
			int dy = mago.getY() - murcielagos.getY(); // Obtenemos distancia en Y entre el Mago y Murcielago
			int distanciaCuadrada = dx * dx + dy * dy; // Elevamos al cuadrado las distancias
			if (distanciaCuadrada > 0) {  	// Condicional para verificar que la distancia no sea 0.
			    						 	// Si fuera 0 significa que el murcielago está encima del mago.
											// Posible uso aplicable a daño recibido al mago
				
				int distancia = (int) Math.sqrt(distanciaCuadrada); // Ahora sacamos la raiz de la variable distanciaCuadrada
				int escalaM = 4; // Con la escala de movimiento definimos la velocidad a la que se movera el Murcielago
								 // Toma como la relación de distancia entre 2 ejes, si uno aumenta el otro también.
			    
				// Normalizamos la velocidad multiplicandola por nuestra escala y dividiendola por distancia ("Hipotenusa")
				int vx = dx * escalaM / distancia;// Obtenemos la velociad en X. 
				int vy = dy * escalaM/ distancia;// Obtenemos la velociad en Y.
			    
				// Actualizamos la posicion de nuestro murcielago con getters y setters de X e Y
				murcielagos.setX(murcielagos.getX() + vx);
				murcielagos.setY(murcielagos.getY() + vy);
			}
	    }
	}
//----------------------------------- OBTENER IMAGENES ----------------------------------------
/* Funcion para obtener las imagenes de los enemigos dependiendo del numero de ronda para cada enemigo correspondiente.
 * Al pasar de ronda se actualiza el sprite.
 */
	
	// Recibe el numero de ronda y retorna un array de enemigos de su respectiva ronda. 
	public static String[] obtenerSpritesDerechaPorRonda(int ronda) {
		if (ronda >= 2) {
			return new String[]{
				"imagenes\\\\ojobatDerecha1.png",
				"imagenes\\\\ojobatDerecha2.png",
				"imagenes\\\\ojobatDerecha3.png",
				"imagenes\\\\ojobatDerecha4.png"
			};
		} else {
			return new String[]{
				"imagenes\\\\Murcielago_derecha1.png",
				"imagenes\\\\Murcielago_derecha2.png",
				"imagenes\\\\Murcielago_derecha3.png",
				"imagenes\\\\Murcielago_derecha4.png"
			};
		}
	}

	public static String[] obtenerSpritesIzquierdaPorRonda(int ronda) {
		if (ronda >= 2) {
			return new String[]{
				"imagenes\\\\ojobatIzquierda1.png",
				"imagenes\\\\ojobatIzquierda2.png",
				"imagenes\\\\ojobatIzquierda3.png",
				"imagenes\\\\ojobatIzquierda4.png"
			};
		} else {
			return new String[]{
				"imagenes\\\\Murcielago_izquierda1.png",
				"imagenes\\\\Murcielago_izquierda2.png",
				"imagenes\\\\Murcielago_izquierda3.png",
				"imagenes\\\\Murcielago_izquierda4.png"
			};
		}
	}
//------------------------------- CANTIDAD MURCIELAGOS ----------------------------------------
	public static int generarMurcielago(
	    int k,
	    Murcielago[] murcielagos,
	    int cantMurcielagoGenerados,
	    int cantMurcielagoTotal,
	    int anchoVentana,
	    int alturaVentana,
	    int velocidad,
	    String [] framesDerecha, // ahora al generar murcielagos es encesario agregar los sprites correspoondientes
	    String [] framesIzquierda,
	    Menu menu) {
		
	    if (cantMurcielagoGenerados >= cantMurcielagoTotal) {
	        return cantMurcielagoGenerados; // No generar más, retorno la misma cantidad
	    }
	    Random random = new Random();
	    int borde = random.nextInt(4);
	    int x = 0;
	    int y = 0;
	    String direccion = "";

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
	    // Los nuevos murcielagos recibiran el sprite correspondiente a la ronda
	    murcielagos[k] = new Murcielago(x, y, direccion,velocidad, framesDerecha, framesIzquierda);
	    return cantMurcielagoGenerados + 1; // Incremento la cantidad generada
	}


//------------------------------------- ACTUALIZAR MURCIELAGOS -------------------------------------
	public static int actualizarMurcielagos(Murcielago[] murcielagos,
		int cantMurcielagoPantalla,
		int cantMurcielagoGenerados,
		int cantMurcielagoTotal,
		int anchoVentana,
		int alturaVentana,
		int velocidad,
		Menu menu,
		int ronda) { // el numero de ronda es necesario ya que se lo pasara como parametro a "generarMurcielago"
		
		// Llamamos a obtenerSprites para cada lado con su valor ronda para el enemigo
		String[] framesIzquierda = obtenerSpritesIzquierdaPorRonda(ronda);
		String[] framesDerecha = obtenerSpritesDerechaPorRonda(ronda);
		
		// -------------------Restablecer Murcielagos------------------------------------
		int vivosActuales = 0;   // Nos indicará la cantidad de murcielagos vivos que están actualmente
		for (int i = 0; i < murcielagos.length; i++) {
			if (murcielagos[i] != null) {
				vivosActuales++;
			} //Recorremos y marcamos la cantidad de vivos
		}
		
		/* La variable "faltan" nos sirve para indicar cuantos enemigos faltan en pantalla
		* Esto es para que se regeneren los que faltan en pantalla.
		*/
		int faltan = cantMurcielagoPantalla - vivosActuales;
		for (int i = 0; i < murcielagos.length; i++) {
			if (murcielagos[i] == null && faltan > 0 && cantMurcielagoGenerados < cantMurcielagoTotal) {
				cantMurcielagoGenerados = Funciones_utiles.generarMurcielago(
				i, murcielagos, cantMurcielagoGenerados, cantMurcielagoTotal,
		        anchoVentana, alturaVentana, velocidad,framesDerecha, framesIzquierda, menu);
					faltan--;
		            vivosActuales++;
		    }
		}
		return cantMurcielagoGenerados; // Retornamos el nuevo contador actualizado
	}
}