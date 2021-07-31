package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class ViaTren {
	private double alto;
	private double ancho;
	private double x;
	private double y;
	private double velocidad;
	Tren tren;
	Image via;
	
	ViaTren(double y, Entorno entorno){
		this.alto = 160;
		this.ancho = 1024;
		this.x = 1024/2;
		this.y = y;
		this.velocidad = 3;
		this.generarTren();
		this.via = Herramientas.cargarImagen("imagenes/viaTren.jpg");
	}
	
	public void dibujarse(Entorno entorno){
		entorno.dibujarImagen(via, x, y, 0);
		tren.dibujarse(entorno);
		tren.update(this.velocidad,this);
	}
	public void update(Vereda v,Entorno e){
		this.y+=0.5;
		if(this.y-alto/2>=768){
			this.y=(v.getY() - v.getAlto()/2) - (this.alto /2);
		}
		
	}
	
	public void generarTren() {
		this.tren = new Tren(100,this.y);
		
	}

	public Tren getTren() {
		return tren;
	}

	public void setTren(Tren tren) {
		this.tren = tren;
	}

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
	
}
