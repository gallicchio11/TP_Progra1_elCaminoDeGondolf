package juego;

import java.util.Random;

public class Funciones_utiles {

	//COLISIONES DEL MURCIELAGO //lo cambiamos a int para devolver ademas la cantidad de colisiones
	public static int  colisionMagoMurcielago(Mago mago, Murcielago[] murcielagos) {
	    int colisiones = 0;
		for (int i = 0; i < murcielagos.length; i++) {
	        if (murcielagos[i] != null) {
	            if (mago.limiteIzquierdo() < murcielagos[i].limiteDerecho() &&
	                mago.limiteSuperior() < murcielagos[i].limiteInferior() &&
	                mago.limiteInferior() > murcielagos[i].limiteSuperior() &&
	                mago.limiteDerecho() > murcielagos[i].limiteIzquierdo()) {
	                murcielagos[i] = null;  // Convierte el elemento murcielago en null
	                mago.perderVida();
	                colisiones++; 
	            }
	        }
	    }
        return colisiones; // Salimos después de la primera colisión, si solo querés borrar uno

	}
//	--------------------------Calcula seguimiento de Murcielago------------------------------

static void seguimientoMurcielago (Mago mago, Murcielago murcielagos) {
	        if (murcielagos != null) {
			    int dx = mago.getX() - murcielagos.getX();
			    int dy = mago.getY() - murcielagos.getY();
	
			    int distanciaCuadrada = dx * dx + dy * dy; // sacamos cuadrado de X y D multiplcando sus lados y sumandolos.
	
			    if (distanciaCuadrada > 0)// condicional para verificar que la distancia no sea 0(al ser 0 significara que el murcielago esta encima de el mago)
			    						 //posible uso aplicable a daño recibido al mago
			    	{
			        int distancia = (int) Math.sqrt(distanciaCuadrada); // Sigue siendo int
	
			        int escalaM = 5; // con la escala de movimiento definimos de cuanto sera la velocidad a la que se movera
			        //la escala se toma como la relacion de distancia entre 2 ejes, si uno aumenta el otro tambien, por lo caul se utilizara el mismo valor, osea escalaM
			        
			        
			        int vx = dx * escalaM / distancia;//normalizamos la velocidad multiplicandola por nuestra escala y dividiendola por distanciaa("hipotenusa")
			        int vy = dy * escalaM/ distancia;//para velocidad de X e Y
			     
			        
			        //actualizamos la posicion de nuestro murcielago con setx/y en lugar de crear 4 condicionales para eso(UTILIZAR SETTERS Y GETTERS!!!!)
			        murcielagos.setX(murcielagos.getX() + vx);
			        murcielagos.setY(murcielagos.getY() + vy);
			  }
	    }
	}

	
///-------------------------------cantidad murcielagos----------------------------------------
public static int generarMurcielago(
	    int k,
	    Murcielago[] murcielagos,
	    int cantMurcielagoGenerados,
	    int cantMurcielagoTotal,
	    int anchoVentana,
	    int alturaVentana,
	    Menu menu
	) {
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

	    murcielagos[k] = new Murcielago(x, y, direccion);
	    return cantMurcielagoGenerados + 1; // Incremento la cantidad generada
	}


//--------------------------------------Actualizar murcielagos 
	public static int actualizarMurcielagos(Murcielago[] murcielagos,
			int cantMurcielagoPantalla,
			int cantMurcielagoGenerados,
			int cantMurcielagoTotal,
			int anchoVentana,
			int alturaVentana,
			Menu menu
		) {
		    // -------------------Restablecer Murcielagos------------------------------------
		    int vivosActuales = 0;   // Nos indicará la cantidad de murcielagos vivos que están actualmente
		    for (int i = 0; i < murcielagos.length; i++) {
		        if (murcielagos[i] != null) {
		            vivosActuales++;
		        } //Recorremos y marcamos la cantidad de vivos
		    }
		    //System.out.println(vivosActuales); //descomentar para ver la cantidad de murcielagos vivos actuales en pantalla
		    
		    /* La variable "faltan" nos sirve para indicar cuantos enemigos faltan en pantalla
		     * Esto es para que se regeneren los que faltan en pantalla.
		     */
		    int faltan = cantMurcielagoPantalla - vivosActuales;

		    for (int i = 0; i < murcielagos.length; i++) {
		        if (murcielagos[i] == null && faltan > 0 && cantMurcielagoGenerados < cantMurcielagoTotal) {
		            cantMurcielagoGenerados = Funciones_utiles.generarMurcielago(
		                i, murcielagos, cantMurcielagoGenerados, cantMurcielagoTotal,
		                anchoVentana, alturaVentana, menu
		            );
		            faltan--;
		            vivosActuales++;
		        }
		    }

		    return cantMurcielagoGenerados; // Retornamos el nuevo contador actualizado
		}
//Trayectoria hechizo
	static void TrayectoriaHechizo(Mago mago, int punteroX, int punteroY) {

		    int dx = mago.getX() - punteroX;
		    int dy = mago.getY() - punteroY;

		    int distanciaCuadrada = dx * dx + dy * dy; // sacamos cuadrado de X y D multiplcando sus lados y sumandolos.

		    if (distanciaCuadrada > 0)// condicional para verificar que la distancia no sea 0(al ser 0 significara que el murcielago esta encima de el mago)
		    						 //posible uso aplicable a daño recibido al mago
		    	{
		        int distancia = (int) Math.sqrt(distanciaCuadrada); // Sigue siendo int

		        int escalaM = 5; // con la escala de movimiento definimos de cuanto sera la velocidad a la que se movera
		        //la escala se toma como la relacion de distancia entre 2 ejes, si uno aumenta el otro tambien, por lo caul se utilizara el mismo valor, osea escalaM
		        
		        
		        int vx = dx * escalaM / distancia;//normalizamos la velocidad multiplicandola por nuestra escala y dividiendola por distanciaa("hipotenusa")
		        int vy = dy * escalaM/ distancia;//para velocidad de X e Y

				double anguloRad = Math.atan2(dy, dx);                // Calcula el ángulo en radianes
				int anguloGrados = (int) Math.round(Math.toDegrees(anguloRad)); // Lo convierte a grados y redondea

		        
		        //actualizamos la posicion de nuestro murcielago con setx/y en lugar de crear 4 condicionales para eso(UTILIZAR SETTERS Y GETTERS!!!!)

		        //System.out.println("Trayectoria hechizo");
		        //System.out.println(vx + "/" + vy);
				//System.out.println(anguloGrados);

    }
}

}
