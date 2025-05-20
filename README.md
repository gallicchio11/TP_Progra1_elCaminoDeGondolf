1er commit:
-creacion del repositorio privado para proyecto de programacion 1
el camino de gondolf(ECDG)
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
2do commit: 
Definicion de la clase Mago, clase Menu.
Constructor de la clase mago definiendo sus propiedades en variables.
Funcion de dibujo para la visualizarcion del mago en el entorno del juego.
Metodos gets y sets para la interaccion del con respecto a sus variables privadas.

Constructor de la clase menu, metodos gets y sets creados, y metodo de dibujo para el entorno.
Movimiento de mago en 4 direcciones con funciones de aumento de cordenadas 
en las 4 direcciones.

En juego: inicializamos objetos Mago y Menu
dibujar al mago dentro del metodo tick ya que se actualizara constantemente su ubicacion.
Condicionales de movimiento al presionar una tecla ademas
teniendo encuenta las dimensiones del escenario(movimiento derecha tambien
tendra en cuentra el tamaño del menu para no atravesarlo.)
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
3er commit: 
Definicion de la clase Roca tomando como referencia la clase mago
Construcor de la clase mago con sus propiedades, solicitando 2 parametros 
de ubicacion para colocarlos en el entorno dibujado(X,Y)
Creacion de funcion dentro del juego ColisionMago:
funcion que toma las propiedades de 2 objetos y devolvera True en caso
de que estos colisiones.
se reutilizo codigo similar de colision visto en clase
Primero se calculara los lados de nuestros objetos A y B.
Segundo se comprobara si 2 recatangulos se superponen, mediante la
comparacion de sus bordes
Creacion de variable para los movimeintos en los 2 ejes(X,Y)
Condicionales para guardar el valor del movimiento que se quiere realizar
Creacion de variables para calcular la proxima posicion del mago(futuroX/Y)
variables de calculo para el proximo valor que tendra la direccion 
a la que nos movamos(izquierda,derecha,arriba,abajo)

Variable booleana para guardar la colison(flag)
Se utilizo un for para comprobar la colision con todas las rocas:
		El for iterara con el array de rocas, accediendo a ellas.
		dentro de este se realizara la comprobacion de la colision
		del mago y las rocas,pero con la futura posicion del mago, no la actual
		En caso de que si se produzca una colision, devolvera True y terminara.
	------------------------------------------------------------------------
	Pasamos las caracteristicas del entorno(limites de movimiento) a variables 
	Creacionde booleano para comprobar que se encuentre el mago dentro de los limites 
	del juego.
	--------------------------------------------------------------------------
	En el apartado de dibujo se agrego un for para mostrar el array de rocas.
 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------
4to Commit:
Modificaciones en la clase Juego:
-  Dentro de los atributos, inicializamos 2 variables que guardarán el ancho y el alto de la ventana del juego.
-  Se agrega una roca más, llegando a 5 rocas en total.
-  Inicializamos un Array de murcielagos de tipo Murcielago.
-  Modificamos en entorno los valores del ancho y alto de la ventana. No serán más numeros fijos, sino que serán variables ya inicializados como atributos de la clase Juego con sus respectivos valores.
-  La misma modificación va para menú y mago. No usarán numeros fijos que se adaptarán al tamaño de la ventana del juego gracias a estas variables. 
-  Quitamos la mayoría de código que había en el tick sobre las colisiones y lo simplificamos.
-  Se agrega la condición de las colisiones entre Mago y Roca en el Tick(). Antes en el código generaba un problema debido a que cada vez que uno de los lados del mago colisionaba con uno de los lados de la roca, cuando verificabamos las colisiones, y condicionabamos al mago para que no traspasara la roca, el mago no se movería para cualquier lado. Para solucionar este problema, en la clase juego modificamos en los movimientos del mago con 3 condiciones. La primera es que si toca una x tecla, ya sea: izquierda, derecha, arriba o abajo. La segunda es que si se toca x tecla, entonces el movimiento que haga el mago no debe superar x lado de la pantalla, incluyendo al menú que se encuentra al lado derecho. Si cumple estas condiciones se puede mover. Pero luego hacemos una condicion donde si x lado del mago colisiona con x lado de roca, entonces el mago podrá moverse al lado contrario. Esto permite que el mago pueda moverse libremente en el mapa colisionando sin problema con las rocas.
-  Modificamos el for que dibuja las rocas y los murciélagos
-  Hacemos un for para que el murciélago inicialice desde el borde de la pantalla y pueda moverse solo a la derecha hasta que "choque" con el menú.

Modificaciones en la clase Mago:
- Modificamos los argumentos que recibe como parámetro el constructo mago. Su finalidad es que el mago se encuentre en el centro de la pantalla del juego.
- Agregamos una función que detecte si hay colision o no entre el mago y las rocas. La función recibe un array de rocas de tipo "Roca", se recorre todas las rocas del juego, y preguntamos si uno de los lados/bordes del mago hace contacto con uno de los lados/bordes de la roca. Devuelve true si toca, sino devuelve false.

Modificaciones en la clase Menú:
- En vez de que el constructor genere el tamaño del menú con valores fijos, decidimos cambiarlos por variables que almacenan el ancho y el alto de la ventana del juego. Esto permite que el menú se pueda posicionar a la derecha de todo y que se adapte su tamaño relativamente al tamaño de la ventana del juego.

Creación de la clase Murcielago:
- Le otorgamos sus respectivos atributos.
- Generamos un constructor
- Generamos los respectivos Getters y Setters
- Creamos 2 funciones para que el murcielago pueda moverse de dercha o a la izquierda
- Creamos su función dibujar

Modificaciones en la clase Roca:
- Quitamos los valores del atributo de ancho y alto, y quitamos el atributo "tipo 1". En vez de eso solo agregamos el ancho y altura, y los valores se lo otorgamos en el constructor.
- En el constructor, solo modificacmos la sintaxis. En vez de "ancho = 30", lo cambiamos a "this.ancho = 30". Lo mismo también pero con altura.

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
