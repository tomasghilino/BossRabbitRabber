package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Kamehameha {
	double x;
	double y;
	int alto;
	int ancho;
	double ejexC;
	Image kame;
	
	Kamehameha(Conejo c){
		this.alto = 80;
		this.ancho = 80;
		setY(c);
		this.update(c);
		this.kame = Herramientas.cargarImagen("imagenes/kame.png");
	}
	
	public boolean colisiona(Auto au) {
		// si x,y del kame , (+ o -) ancho de kame esta dentro del rectangulo auto devuelve true
		if(this.x -(ancho/2)  < au.getX()+(au.getAncho()/2) && this.x + (ancho/2) > au.getX()-(au.getAncho()/2)) {
			if(this.y + alto/2 > au.getY()- (au.getAlto()/2) && this.y - (alto/2)< au.getY() + (au.getAlto()/2)) {
				return true;
			}
		}
		return false;
				
	}
	public void dibujarse(Entorno e, double x) {
		//e.dibujarRectangulo(x,this.getY(),this.ancho,this.alto,0,Color.yellow); // por ahora dibuja un cuadrado
		e.dibujarImagen(kame, x, y, 0);
	}
	
	public void update(Conejo c) {
		//si se va de la pantalla aparece de nuevo en su posicion inicial respecto al conejo
		if(this.y + alto/2 < 0) {
			this.y = c.getY() - c.getAlto()/2 - this.alto/2;
		}
		setX(c);
		
	}
	
	//setters&getters
	public double getX() {
		return x;
	}

	public void setX(Conejo c) {
		this.x = c.getX();
	}

	public double getY() {
		return y;
	}

	public void setY(Conejo c) {
		this.y = c.getY() - c.getAlto()/2 - this.alto/2;
	}
}
