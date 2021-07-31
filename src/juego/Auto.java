package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Auto {

	private double x;
	private double y;
	private double alto;
	private double ancho;
	Image auto;
	
	
	Auto(double x , double y, Carril c){
		setX(x);
		setY(y);
		this.alto = 60;
		this.ancho = 80;
		//Dibuja los autos segun la mano del carril, de colores aleatorios.
		if(c.getMano()==-1) {
			setAutoIzq();
		}else if (c.getMano() == 1) {
			setAutoDer();
		}
		
	}
	public void update(int mano, double velocidad,Carril c) {
		this.y = c.getY();
		if(mano==-1) {
			this.x -= velocidad;
			if(this.x+ancho/2<0) {
				this.x=1024+ancho/2;
			}
		}
		else {
			this.x += velocidad;
			if(this.x-ancho/2>1024) {
				this.x=0-ancho/2;
			}
		}
	}
	public void dibujarse(Entorno e) {	
		//e.dibujarRectangulo(this.x,this.y, this.ancho, this.alto,0,Color.RED);
		e.dibujarImagen(auto, x, y, 0);
	}
	//todo este codigo de abajo carga la imagen del auto de forma aleatoria y respetando su direccion
	public void setAutoIzq() {
		Random r =  new Random();
		int azar = r.nextInt(6-1+1) + 1;
		switch(azar) {
		case 1:
			this.auto = Herramientas.cargarImagen("imagenes/autoAmarillo.png");
			break;
		case 2: 
			this.auto = Herramientas.cargarImagen("imagenes/autoAzul.png");
			break;
		case 3:
			this.auto = Herramientas.cargarImagen("imagenes/autoNaranja.png");
			break;
		case 4:
			this.auto = Herramientas.cargarImagen("imagenes/autoRojo.png");
			break;
		case 5:
			this.auto = Herramientas.cargarImagen("imagenes/autoRosa.png");
			break;
		case 6:
			this.auto = Herramientas.cargarImagen("imagenes/autoVerde.png");
			break;
		}	
	}
	public void setAutoDer() {
		Random r =  new Random();
		int azar = r.nextInt(6-1+1) + 1;
		switch(azar) {
		case 1:
			this.auto = Herramientas.cargarImagen("imagenes/autoAmarilloDer.png");
			break;
		case 2: 
			this.auto = Herramientas.cargarImagen("imagenes/autoAzulDer.png");
			break;
		case 3:
			this.auto = Herramientas.cargarImagen("imagenes/autoNaranjaDer.png");
			break;
		case 4:
			this.auto = Herramientas.cargarImagen("imagenes/autoRojoDer.png");
			break;
		case 5:
			this.auto = Herramientas.cargarImagen("imagenes/autoRosaDer.png");
			break;
		case 6:
			this.auto = Herramientas.cargarImagen("imagenes/autoVerdeDer.png");
			break;
		}	
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
	public Image getAuto() {
		return auto;
	}
	
	
}
