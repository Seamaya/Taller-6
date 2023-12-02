package Modelo;

public class Combos extends Pedido {
	String nombre;
	float descuento;
	Platos hamburguesa;
	Platos papas;
	Bebidas gaseosa;
	int precio;
	int calorias;
	
	public Combos(String nombre, float descuento, Platos hamburguesa, Platos papas, Bebidas gaseosa ){
		super(null, 0, null, null);
		this.nombre = nombre;
		this.descuento = descuento;
		this.hamburguesa = hamburguesa;
		this.papas = papas;
		this.gaseosa = gaseosa;
		this.precio = (int) ((int)((hamburguesa.precio + papas.precio + gaseosa.precio) * (1 - descuento)));
		this.calorias = (hamburguesa.calorias + papas.calorias + gaseosa.calorias);
	}
	public String dar_nombre() {
		return nombre;
	}
	public float dar_descuento() {
		return descuento;
	}
	public Platos dar_hamburguesa() {
		return hamburguesa;
	}
	public Platos dar_papar () {
		return papas;
	}
	public Bebidas dar_gaseosa() {
		return gaseosa;
	}
	public int dar_precio() {
		return precio;
	}
	public int dar_calorias() {
		return calorias;
	}
	
}
