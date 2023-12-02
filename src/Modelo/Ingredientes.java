package Modelo;

public class Ingredientes extends Pedido{
	String nombre;
	int precio;
	int calorias;
	
	public Ingredientes(String nombre, int precio, int calorias)
	{
		super(null, 0, null, null);
		this.nombre = nombre;
		this.precio = precio;
		this.calorias = calorias;
	}
	public Ingredientes(String nombre) {
		super(null, 0, null, null);
		this.nombre = "sin " + nombre;
		this.precio = 0;
	}
	public String dar_nombre() {
		return nombre;
	}
	public int dar_precio() {
		return precio;
	}
	public int dar_calorias() {
		return calorias;
	}
	
}
