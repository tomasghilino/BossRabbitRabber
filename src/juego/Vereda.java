package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Vereda {
	private double alto;
	private double ancho;
	private double x;
	private double y;
	private double movimiento;
	Image vereda;
	
	Vereda(double alto, double y){
		setAlto(alto);
		setY(y);
		this.x=1024/2;
		this.ancho=1024;
		this.movimiento=0.5;
		this.vereda = Herramientas.cargarImagen("imagenes/vereda.jpg");
	}
	
	public void dibujarse(Entorno entorno){
		entorno.dibujarImagen(vereda, x, y, 0);
	}
	
	public void update(Calle c){
		this.y=y+movimiento;
		if(this.y-alto/2>=768){
			this.y=(c.getY() - c.getAlto()/2) - (this.alto /2);
		}
	}
	public void update(ViaTren via){
		this.y=y+movimiento;
		if(this.y-alto/2>=768){
			this.y=(via.getY() - via.getAlto()/2) - (this.alto /2);
		}
	}
	
	
	
	//setters&getters
	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(double movimiento) {
		this.movimiento = movimiento;
	}
}
