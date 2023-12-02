package Modelo;

public abstract class HamburguesaException extends Exception {
	private int password;

    public static final int COMBO_REPETIDO = 1;
    public static final int PRODUCTO_REPETIDO = 2;
    public static final int INGREDIENTE_REPETIDO = 3;
    public static final int BEBIDA_REPETIDA = 4;

    public int getPassword()
    {
        return this.password;
    }

    public HamburguesaException(int password)
    {
        this.password = password;
    }
}
