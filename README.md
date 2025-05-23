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
5to Commit: Persecución de Murciélagos v1.0

Modificaciones en clase Juego:
- Creamos una variable llamada movimiento, el cual lo inicializamos en la sección de Juego, y le asignamos como valor 0 . Esta variable nos servirá para indicar la dirección en la que se encuentra el mago en relación con los murciélagos.
- Agregamos más elementos en el array de tipo Murciélago.
- Luego en la parte del tick, condicionamos a los murciélagos para que puedan moverse hacia donde se encuentre el mago. Para esto, recorremos con un for el array de murcielagos, y luego condicionamos. Cada condición es respectivo del lado que se encuentre el mago con los murcielagos; Por ejemplo, si el mago se encuentra por debajo del murcielago entonces, el murcielago se moverá para abajo. Además, se le asigna un valor a la variable un numero (1-4) que indicará a que lado tiene que moverse el murciélago. Por ende, si la variable movimiento es 1 entonces se moverá a abajo; si es 2, arriba; si es 3, izquierda; y si es 4, derecha.

Modificaciones en la clase Murciélago:
- La velocidad del murciélago se modifica a 5, misma velocidad que la del mago.
- Se agrega movimientos de arriba y abajo para el murciélago.
- Se agrega movimientoMurcielago que recibe la posición X e Y del mago. Esto es más que nada para que el murciélago, una vez que detecte que el mago se encuentra dentro de sus ejes, pueda moverse. Y cuando se mueva hasta la posición donde se encuentra el mago, el murcielago se mantendrá allí.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
6to Commit:

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
7to Commit: Colision magoMurcielago - Cantidad murcielagos en pantalla

Modificaciones en la clase Juego:
- Agregamos 3 variables nuevas, que nos ayudarán para determinar la cantidad de enemigos (Murcielagos) que aparecerán por pantalla. La primera es la cantidad de enemigos por pantalla (10); luego tenemos a la cantidad de enemigos total (50); y luego tenemos a la cantidad de enemigos generados, este mismo nos servirá como contador de enemigos que se generen, tanto los que están en pantalla más los nuevos que salen.
- Modificamos la cantidad de elementos en el array de Murcielago, donde pondremos cantEnemigosTotal (50).
- Movimos algunas funciones que son propias del grupo en un sector llamado "Variables y métodos propios de cada grupo".
- Agregamos una función llamada "colisionaMagoMurcielago". Este recibe un array de tipo Murcielago llamado murcielagos. Este mismo recorre todos los elementos del array. Luego hacemos una condición donde si el elemento murcielago es diferente de null, entonces que se aplique las condiciones de colision. Esto nos sirve para que el for no acceda a elementos donde su valor es null; con esto nos ahorramos una excepción. Si es diferente de null, se aplican las condiciones de colision. Si colisiona, entonces el elemento se convierte null.
- Modificamos la función InicializarMurcielago(). Cambiamos su nombre a generarMurcielago(), ya que nos parecía el mas apropiado. Recibe como parámetro un valor k, este valor k será el elemento Murcielago que se generará en pantalla. Si recorremos el array y vamos por el elemento 20, entonces se generará el elemento 20. Antes tenía un for que recorría a todos y les daba los atributos correspondientes a todos los elementos del array Murcielago; pero eso lo cambiamos y será una función llamada solo para generar 1 solo elemento murcielago. Luego tenemos una condicion que nos dice si la cantidad de murcielagos generados es mayor a la cantidad de murcielagos totales, entonces que no devuelva nada; en otras palabras, es como un break. No queremos que la cantidad de los elementos generados supere al total, ya que sino se rompe el juego. Luego esto es sencillo ya que dependiendo del lado que salga, al murcielago se le atribuira su respectiva dirección, x e y. Y a lo ultimo se sumará el contador de Murcielagos generados.

Parte del Tick()
- En el sector de la persecusión de murcielago a mago, le  agregamos  una condición que nos dice si el elemento murcielago es diferente de null. Solo el elemento murcielago persiguirá al mago si el elemento es diferente de null.
- Luego llamamos a colisionaMagoMurcielago que recibe murcielagos. Esto es para demostrar que si el enemigo colisiona con el mago, entonces que genere null sobre el elemento.
- Agregamos un sector para restablecer murcielagos cada vez que se eliminan por pantalla. Primero empezamos con los vivos actuales, que nos indicará la cantidad de murcielagos vivos que están actualmente por pantalla, por ende siempre mostrará 10. Luego tenemos el int falta, esta variable hace una resta de la cantidad de murcielago por pantalla (10) y le resta la cantidad de vivos que hay actualmente. Es decir si no falta ningun elemento por agregar en pantalla, entonces no se generan.
Luego recorremos a los murcielagos; en eso condicionamos diciendo: si el elemento murcielago es null, si la cantidad de murcielagos que faltan en pantalla es mayor a 0; y si la cantidad de murcielagos generados es menor a la cantidad de murcielagos totales, entonces: que se genere un murcielago nuevo, que se resten los que faltan por pantalla, y que aumente los vivos actuales por pantalla.

Modificaciones en la clase Murcielago:
- Agregamos una seccion llamada "Bordes/limites del murciélago". Esto nos ayudará para que se cumpla la colision entre el mago y el murcielago. Al murciélago le otorgamos el borde: Superior, Inferior, Izquierdo, Derecho.

