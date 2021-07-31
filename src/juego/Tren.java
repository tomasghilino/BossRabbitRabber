package juego;

import java.awt.Image;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Tren {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	Image tren;
	Clip sonidoTren;
	
	Tren(double x, double y){
		setX(x);
		setY(y);
		this.alto = 120;
		this.ancho = 1023;
		this.tren = Herramientas.cargarImagen("imagenes/tren.png");
		this.sonidoTren = Herramientas.cargarSonido("sonidos/tren.wav");
	}
	public void update(double velocidad,ViaTren via) {
		this.y = via.getY();
		this.x += velocidad;
		if(this.x-ancho/2>1024) {
			this.x=0-ancho/2;
		}
		//si el tren aparece en pantalla comienza su sonido
		if(this.y +alto/2> 20 ) {
			sonidoTren.start();
		}
		//cuando se va de pantalla se frena, y se resetea el clip al segundo 0
		if(this.y-alto/2 > 768) {
			sonidoTren.stop();
			sonidoTren.setMicrosecondPosition(0);
		}
		
		
	}
	
	public void dibujarse(Entorno e) {	
		e.dibujarImagen(tren, x, y, 0);
	}
	
	
	
	//setters&getters
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
		
}
