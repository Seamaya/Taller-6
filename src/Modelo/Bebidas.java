package Modelo;

public class Bebidas extends Pedido{
	String nombre;
	int precio;
	int calorias;
	
	public Bebidas(String nombre, int precio, int calorias){
		super(null, 0, null, null);
		this.nombre = nombre;
		this.precio = precio;
		this.calorias = calorias;
	}
	
	public String dar_nombre() {
		return nombre;
	}
		
	public int dar_precio(){
		return precio;
	}
	public int dar_calorias() {
		return calorias;
	}
	
}
