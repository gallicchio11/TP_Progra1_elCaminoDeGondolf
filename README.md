1er commit:
	-creacion del repositorio privado para proyecto de programacion 1
	el camino de gondolf(ECDG)
2do commit: 
	Definicion de la clase Mago, clase Menu.
	Constructor de la clase mago definiendo sus propiedades en variables.
	Funcion de dibujo para la visualizarcion del mago en el entorno del juego.
	Metodos gets y sets para la interaccion del con respecto a sus variables privadas.
	---------------------------------------------------------------
	Constructor de la clase menu, metodos gets y sets creados, y metodo de dibujo para el entorno.
	Movimiento de mago en 4 direcciones con funciones de aumento de cordenadas 
	en las 4 direcciones.
	----------------------------------------------------------------
	En juego: inicializamos objetos Mago y Menu
	dibujar al mago dentro del metodo tick ya que se actualizara constantemente su ubicacion.
	Condicionales de movimiento al presionar una tecla ademas
	teniendo encuenta las dimensiones del escenario(movimiento derecha tambien
	tendra en cuentra el tamaño del menu para no atravesarlo.)
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
	------------------------------------------------------------------------
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
