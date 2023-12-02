package Hamburguesas.consola;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import LoaderHamburguesas.LoaderRestaurante;
import LoaderHamburguesas.Restaurante;
import Modelo.Bebidas;
import Modelo.Combos;
import Modelo.HamburguesaException;
import Modelo.IngredienteRepetidoException;
import Modelo.Ingredientes;
import Modelo.Pedido;
import Modelo.Platos;
import Modelo.ProductoRepetidoException;


public class ConsolaHamburguesas {

	static Restaurante restaurante;
	static Pedido pedido_general;
	static List<Pedido> lista_total = new ArrayList<>();
	public static void main(String[] args) {
		System.out.println("¡¡Bienvenido a nuestra tienda de hamburguesas!!");
		logo();
		boolean continuar = true;
		while(continuar)
		{
			try 
			{
				mostrarOpciones();
				cargarArchivos();
				int seleccion = Integer.parseInt(input("Por favor seleccione una opción"));
				
				if(seleccion == 1)
					mostrarMenu();
				else if(seleccion == 2)
					iniciarPedido();
				else if(seleccion == 3)
					if (pedido_general == null)
						System.out.println("\nDebes primero iniciar un pedido antes de agregar elementos :)\n");
					else {
						agregarElemento();
					}
				else if(seleccion == 4)
					cerrarPedido();
				else if(seleccion == 5)
					buscarPedido();
				else if (seleccion == 6) {
					continuar = false;
					System.out.println("Vuelva pronto a nuesta tienda!!! <3");
				}
				else
				{
					System.out.println("Seleccione una de las 5 opciones de la aplicación\n");
				}
				
			}
			catch (NumberFormatException e)
			{
				System.out.println("\nDebe seleccionar uno de los números de las opciones.\n");
			}
		}

	}

	private static void cargarArchivos() {
		try {
			restaurante = LoaderRestaurante.cargar_archivos("./data/menu.txt","./data/bebidas.txt", "./data/combos.txt", "./data/ingredientes.txt");
		}catch (ProductoRepetidoException e)
		{if (e.getPassword()==ProductoRepetidoException.PRODUCTO_REPETIDO)
				System.out.println("Dentro del archivo se encontraron mismos productos, estan repetidos");
			e.printStackTrace();
		}catch (IngredienteRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} 
	

	private static void logo() {
		// TODO Auto-generated method stub
		String hamburguesa =
	           "\r\n"
	           + "  _   _       _        __  __      ____      _   _     ____        ____   U _____ u    ____       ____     \r\n"
	           + " |'| |'|  U  /\"\\  u  U|' \\/ '|u U | __\")u U |\"|u| | U |  _\"\\ u  U /\"___|u \\| ___\"|/ U |  _\"\\ u   / __\"| u  \r\n"
	           + "/| |_| |\\  \\/ _ \\/   \\| |\\/| |/  \\|  _ \\/  \\| |\\| |  \\| |_) |/  \\| |  _ /  |  _|\"    \\| |_) |/  <\\___ \\/   \r\n"
	           + "U|  _  |u  / ___ \\    | |  | |    | |_) |   | |_| |   |  _ <     | |_| |   | |___     |  _ <     u___) |   \r\n"
	           + " |_| |_|  /_/   \\_\\   |_|  |_|    |____/   <<\\___/    |_| \\_\\     \\____|   |_____|    |_| \\_\\    |____/>>  \r\n"
	           + " //   \\\\   \\\\    >>  <<,-,,-.    _|| \\\\_  (__) )(     //   \\\\_    _)(|_    <<   >>    //   \\\\_    )(  (__) \r\n"
	           + "(_\") (\"_) (__)  (__)  (./  \\.)  (__) (__)     (__)   (__)  (__)  (__)__)  (__) (__)  (__)  (__)  (__)      \r\n";
		System.out.println(hamburguesa);
	}

	public static void buscarPedido() {
		int total = 0;
		int calorias = 0;
		int id = Integer.parseInt(input("Por favor ingrese el id del pedido que busca"));
		Pedido pedido_buscado = restaurante.look_for_pedido(lista_total, id);
		if (pedido_buscado == null) {
			if (id == pedido_general.dar_id()) {
				int total_pedido_actual = 0;
				int calorias_actual = 0;
				System.out.println("Por ahora este es tu pedido con id " + pedido_general.dar_id());
				for(Pedido elemento : pedido_general.dar_lista()) {
					System.out.println(elemento.dar_nombre() + " - " + elemento.dar_precio() + "$");
					total_pedido_actual += elemento.dar_precio();
					calorias_actual += elemento.dar_calorias();
				}
				System.out.println("-----------------------------");
				System.out.println("\nEl precio parcial de tu pedido es de " + total_pedido_actual + "$");
				System.out.println("Con una cantidad parcial de " + calorias_actual + " calorias\n");
			}
			else {
			System.out.println("Este pedido no fue encontrado :(");
			}
		}
		else {
		System.out.println("\nEl pedido buscado con #id " + pedido_buscado.dar_id() + " a nombre de " 
				+ pedido_buscado.dar_nombre() + " para la dirección " + pedido_buscado.dar_direccion_cliente() + "\n");
		for(Pedido elemento : pedido_buscado.dar_lista()) {
			System.out.println(elemento.dar_nombre() + " - " + elemento.dar_precio() + "$");
			total += elemento.dar_precio();
			calorias += elemento.dar_calorias();
		}
		System.out.println("-----------------------------");
		System.out.println("\nEl precio total de este pedido es de " + total + "$");
		System.out.println("Con un total de " + calorias + " calorias\n");
		}
	}

	public static void cerrarPedido() {
		int total = 0;
		int calorias = 0;
		System.out.println("\nEste es tu pedido con #id " + pedido_general.dar_id() + " a nombre de " 
		+ pedido_general.dar_nombre_cliente() + " para la dirección " + pedido_general.dar_direccion_cliente() + "\n");
		for(Pedido elemento : pedido_general.dar_lista()) {
			System.out.println(elemento.dar_nombre() + " - " + elemento.dar_precio() + "$");
			total += elemento.dar_precio();
			calorias += elemento.dar_calorias();
		}
		System.out.println("------------------------");
		System.out.println("\nEl precio total de tu pedido es de " + total + "$");
		System.out.println("Con un total de " + calorias + " calorias\n");
		Pedido pedido_igual = restaurante.equals(lista_total, pedido_general);
		if (pedido_igual != null) {
			System.out.println("\n¡Hemos encontrado que " + pedido_igual.dar_nombre_cliente() + " ha realizado el mismo pedido!\n");
			}
		lista_total.add(pedido_general);
			
	}
	
	public static void agregarElemento() {
		System.out.println("\n¿Que deseas agregar?");
		mostrar_opciones_pedido();
		int seleccion = Integer.parseInt(input("Por favor seleccione una opción"));
		
		if(seleccion == 1)
			mostrarPlatos();
		else if(seleccion == 2)
			mostrasBebidas();
		else if(seleccion == 3)
			mostrarCombos();
		else if(seleccion == 4)
			mostrarIngredientes();
		else
		{
			System.out.println("Seleccione una de las 3 opciones de la aplicación\n");
		}
		
	}

	public static void mostrarIngredientes() {
		List<Ingredientes> ingredientes = restaurante.daringredientes();
		System.out.println("\nEstas son las adiciones que puedes hacerle a tu pedido:\n");
		int seleccion = 1;
		for(Ingredientes adicion : ingredientes) {
			System.out.print(seleccion + ". " + adicion.dar_nombre() + " - ");
			System.out.println(adicion.dar_precio() + "$");
			seleccion ++;
		}	
		Ingredientes ingrediente;
		System.out.println("¿Deseas agregar o eliminar algun ingrediente de tu pedido?");
		System.out.println("\n1. Agregar\n2. Eliminar");
		int agregar_eliminar = Integer.parseInt(input("\nSeleccione una opción"));
		if(agregar_eliminar == 1) {
			int opcion_agregar = Integer.parseInt(input("\nPor favor seleccione que opción desea agregar a su pedido")) - 1;
			List<Ingredientes> lista_ingredientes = restaurante.daringredientes();
			ingrediente = lista_ingredientes.get(opcion_agregar);
			pedido_general.agregar_ingrediete(ingrediente);
			System.out.println("\nAdición agregada correctamente!\n");
		}
		else if(agregar_eliminar == 2) {
			int opcion_agregar = Integer.parseInt(input("\nPor favor seleccione que opción desea eliminar de su pedido")) - 1;
			List<Ingredientes> lista_ingredientes = restaurante.daringredientes();
			ingrediente = lista_ingredientes.get(opcion_agregar);
			String nombre_ingrediente_a_quitar = ingrediente.dar_nombre();
			Ingredientes ingrediente_a_quitar = new Ingredientes(nombre_ingrediente_a_quitar);
			pedido_general.agregar_ingrediete(ingrediente_a_quitar);
			System.out.println("\nIngrediente eliminado correctamente!\n");
			
		}
	}

	public static void mostrarCombos() {
		List<Combos> combos = restaurante.darCombos();
		System.out.println("\nEstos son nuestros combos:\n");
		int seleccion = 1;
		for(Combos combo : combos) {
			System.out.print(seleccion + ". " + combo.dar_nombre() + " - ");
			System.out.println(combo.dar_precio() + "$");
			seleccion ++;
		}
		Combos combo;
		int opcion_agregar = Integer.parseInt(input("\nPor favor seleccione que opción desea agregar a su pedido")) - 1;
		List<Combos> lista_combos = restaurante.darCombos();
		combo = lista_combos.get(opcion_agregar);
		pedido_general.agregar_combo(combo);
		System.out.println("\nCombo agregado correctamente!\n");
	}
	
	public static void mostrasBebidas() {
		List<Bebidas> bebidas = restaurante.darBebidas();
		System.out.println("Estas son nuestas bebidas: \n");
		int seleccion = 1;
		for (Bebidas bebida : bebidas) {
			System.out.print(seleccion + ". " + bebida.dar_nombre() + " - ");
			System.out.println(bebida.dar_precio() + "$");
			seleccion  ++;
		}
		Bebidas bebida_seleccion;
		int opcion_agregar = Integer.parseInt(input("\nPor favor seleccione que opción desea agregar a su pedido")) - 1;
		bebida_seleccion = bebidas.get(opcion_agregar);
		pedido_general.agregar_bebida(bebida_seleccion);
		System.out.println("\nBebida agregada correctamente!\n");
	}

	public static void mostrarPlatos() {
		List<Platos> menu = restaurante.darPlatos();
		System.out.println("Estos son nuestros platos:\n");
		int seleccion = 1;
		for (Platos plato : menu) {
			System.out.print(seleccion + ". " + plato.dar_nombre() + " - ");
			System.out.println(plato.dar_precio() + "$");
			seleccion ++;
		}
		Platos plato_eleccion;
		int opcion_agregar = Integer.parseInt(input("\nPor favor seleccione que opción desea agregar a su pedido")) - 1;
		List<Platos> lista_platos = restaurante.darPlatos();
		plato_eleccion = lista_platos.get(opcion_agregar);
		pedido_general.agregar_plato(plato_eleccion);
		System.out.println("\nPlato agregado correctamente!\n");
	}

	public static void iniciarPedido() {
		List<Pedido> lista = new ArrayList<>();
		Random random_number = new Random();
		int id = random_number.nextInt(100);
		String nombre = input("Ingresa tu nombre");
		String direccion = input("Ingresa la dirección");
		pedido_general = new Pedido(lista, id, nombre, direccion);
		System.out.println("\nPedido con #id " + id + " fue inicado correctamente!\n");
	}

	public static void mostrarMenu() {
		List<Platos> menu = restaurante.darPlatos();
		List<Combos> combos = restaurante.darCombos();
		List<Ingredientes> ingredientes = restaurante.daringredientes();
		List<Bebidas> bebidas = restaurante.darBebidas();
		
		System.out.println("Este es nuestro menu\n");
		System.out.println("Estos son nuestros platos:");
		for (Platos plato : menu) {
			System.out.print(plato.dar_nombre() + " - ");
			System.out.println(plato.dar_precio() + "$");
		}
		System.out.println("\nEstas son nuestras bebidas:");
		for (Bebidas bebida : bebidas) {
			System.out.print(bebida.dar_nombre() + " - ");
			System.out.println(bebida.dar_precio() + "$");
		}
		System.out.println("\nEstos son nuestros combos:");
		for(Combos combo : combos) {
			System.out.print(combo.dar_nombre() + " - ");
			System.out.println(combo.dar_precio() + "$");
		}
		System.out.println("\nEstas son las adiciones que puedes hacerle a tu pedido:");
		for(Ingredientes adicion : ingredientes) {
			System.out.print(adicion.dar_nombre() + " - ");
			System.out.println(adicion.dar_precio() + "$");
		}
		System.out.println();
	}

	public static void mostrarOpciones() {
		System.out.println("1. Mostrar el menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar la factura");
		System.out.println("5. Consultar la información de un pedido con su id");
		System.out.println("6. Salir de la aplicación\n");
	}
	
	public static void mostrar_opciones_pedido() {
		System.out.println("1. Agregar un plato");
		System.out.println("2. Agregar una bebida");
		System.out.println("3. Agregar un combo");
		System.out.println("4. Agregar o eliminar una adición\n");
	}
	
	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	
	}
}
