package LoaderHamburguesas;

import java.util.List;

import Modelo.Bebidas;
import Modelo.Combos;
import Modelo.Ingredientes;
import Modelo.Pedido;
import Modelo.Platos;

public class Restaurante {

	List<Platos> platos;
	List<Bebidas> bebidas;
	List<Ingredientes> ingredientes;
	List<Combos> combos;
	List<Pedido> pedidos;
	
	public Restaurante(List<Platos> platos,List<Bebidas> bebidas, List<Ingredientes> ingredientes, List<Combos>combos, List<Pedido> pedidos){
		this.platos = platos;
		this.bebidas = bebidas;
		this.ingredientes = ingredientes;
		this.combos = combos;
		this.pedidos = pedidos;
	}
	
	public List<Platos> darPlatos() {
		return platos;
	}
	public List<Bebidas> darBebidas() {
		return bebidas;
	}
	public List<Combos> darCombos() {
		return combos;
	}
	public List<Ingredientes> daringredientes() {
		return ingredientes;
	}
	public void agregar_pedido(Pedido pedido){
		this.pedidos.add(pedido);
	}
	public List<Pedido> darListaPedidos() {
		return pedidos;
	}
	public Combos look_for_combo(List<Combos> combos_lista, String nombre_combo) {
		Combos combo_buscado = null;

		for (int i = combos_lista.size() - 1; i >= 0; i--)
		{
			Combos combo = combos_lista.get(i);
			if (combo.dar_nombre().equals(nombre_combo))
			{
				combo_buscado = combo;
			}
		}
		return combo_buscado;
		}
	public Bebidas look_for_bebida(List<Bebidas> bebidas_lista, String nombre_bebida) {
		Bebidas bebida_buscado = null;

		for (int i = bebidas_lista.size() - 1; i >= 0; i--)
		{
			Bebidas bebida = bebidas_lista.get(i);
			if (bebida.dar_nombre().equals(nombre_bebida))
			{
				bebida_buscado = bebida;
			}
		}
		return bebida_buscado;
		}
	public Platos look_for_plato(List<Platos> platos_lista, String nombre_plato) {
		Platos plato_buscado = null;

		for (int i = platos_lista.size() - 1; i >= 0; i--)
		{
			Platos plato = platos_lista.get(i);
			if (plato.dar_nombre().equals(nombre_plato))
			{
				plato_buscado = plato;
			}
		}

		return plato_buscado;
		}
	public Ingredientes look_for_ingrediente(List<Ingredientes> ingredientes_lista, String nombre_ingrediente) {
		Ingredientes ing_buscado = null;

		for (int i = ingredientes_lista.size() - 1; i >= 0; i--)
		{
			Ingredientes ingrediente = ingredientes_lista.get(i);
			if (ingrediente.dar_nombre().equals(nombre_ingrediente))
			{
				ing_buscado = ingrediente;
			}
		}

		return ing_buscado;
		}
	public Pedido look_for_pedido(List<Pedido> pedidos_lista, int id_pedido) {
		for(int i = 0; i<pedidos_lista.size(); i++)	{
			Pedido pedido_buscado = pedidos_lista.get(i);
			int id_buscado = pedido_buscado.dar_id();
			if(id_buscado == id_pedido) {
				return pedido_buscado;
			}
		}
		return null;
	}
	public Pedido equals(List<Pedido> todos_los_pedidos, Pedido pedido_actual) {
		Pedido pedido_retorno = null;
		for (Pedido pedido : todos_los_pedidos) {
			List<Pedido> lista_elemento_pedido = pedido.dar_lista();
			List<Pedido> lista_pedido_actual = pedido.dar_lista();
			
			if(lista_elemento_pedido.containsAll(lista_pedido_actual) && lista_pedido_actual.containsAll(lista_elemento_pedido)) {
				pedido_retorno = pedido;
			}
		}
		return pedido_retorno;
	}
}
