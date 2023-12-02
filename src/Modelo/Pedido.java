package Modelo;

import java.util.List;

public class Pedido {
	
	List<Pedido> pedido;
	int id;
	String nombre_cliente;
	String direccion;
	
	public Pedido(List<Pedido> pedido, int id, String nombre, String direccion) {
		this.pedido = pedido;
		this.id = id;
		this.nombre_cliente = nombre;
		this.direccion = direccion;
	}
	
	public void agregar_plato(Platos plato) {
		this.pedido.add(plato);
	}
	public void agregar_bebida(Bebidas bebida) {
		this.pedido.add(bebida);
	}
	public void agregar_combo(Combos combo) {
		this.pedido.add(combo);
	}
	public void agregar_ingrediete(Ingredientes ingrediente) {
		this.pedido.add(ingrediente);
	}
	public void eliminar_ingrediente(Ingredientes ingrediente) {
		
	}
	public List<Pedido> dar_lista(){
		return this.pedido;
	}
	public int dar_id() {
		return this.id;
	}
	public String dar_nombre_cliente() {
		return this.nombre_cliente;
	}
	public String dar_direccion_cliente() {
		return this.direccion;
	}
	public int dar_precio(){
		return 0;
	}
	public String dar_nombre() {
		return "";
	}
	public int dar_calorias() {
		return 0;
	}
}
