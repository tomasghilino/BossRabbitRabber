package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
public class Conejo {
	private int alto;
	private int ancho;
	private double x;
	private double y;
	boolean poder;
	boolean ganar;
	boolean perder;
	Kamehameha k;
	Image conejoIzq;
	Image conejoDer;
	boolean derecha; //saber si la imagen del conejo tiene o no que mirar a la derecha dependiendo input
	int puntos;
	int saltos;
	
	
	Conejo(){
		setAlto(50);
		setAncho(50);
		setX(1024/2);
		setY(700);
		this.conejoIzq = Herramientas.cargarImagen("imagenes/conejo.png");
		this.conejoDer = Herramientas.cargarImagen("imagenes/conejoDer.png");
		this.derecha = false;
		this.puntos = 0;
		this.saltos = 0;
	}
	
	public boolean colisiona(Auto au) {
		// si x,y (+ o -) ancho de conejo esta dentro del rectangulo auto devuelve true.
		if(this.x -(ancho/2)  < au.getX()+(au.getAncho()/2) && this.x + (ancho/2) > au.getX()-(au.getAncho()/2)) {
			if(this.y + alto/2 > au.getY()- (au.getAlto()/2) && this.y - (alto/2)< au.getY() + (au.getAlto()/2)) {
				perder = true;
				return true;
			}
		}
		return false;
	}
	public boolean colisiona(Tren tren) {
		//si x,y del conejo, (+ o -) ancho del conejo esta dentro del rectangulo tren devuelve true.
		if(this.x -(ancho/2)  < tren.getX()+(tren.getAncho()/2) && this.x + (ancho/2) > tren.getX()-(tren.getAncho()/2)) {
			if(this.y + alto/2 > tren.getY()- (tren.getAlto()/2) && this.y - (alto/2)< tren.getY() + (tren.getAlto()/2)) {
				perder = true;
				return true;
			}
		}
		return false;
	}
	
	public void conejoColisionaCon(Calle c) {
		for(Carril carr : c.getCarriles()) {
			for(int i=0;i< carr.getAutos().length;i++) {
				//si el auto que estoy corroborando no es null:
				if(carr.getAutos()[i] != null) {
					//se fija si el conejo choco.
					if(this.colisiona(carr.getAutos()[i])) {
						perder = true;
					}
					//Se fija si mientras que estoy tirando el poder,este colisiona con algun auto.
					// aclarar que el kamehameha cuando sale de pantalla se vuelve null y cuando colisiona con un auto, tambien.
					if(poder==true && this.k.colisiona(carr.getAutos()[i])) {
						this.puntos += 5;
						//Todos los auto que atraveso el kamehameha toman el valor de null,
						//y son generados nuevamente la proxima vez que la calle aparezca, de manera random.
						carr.getAutos()[i] = null;
						poder=false;
						k = null;
					}
				}
			}
		}
	}
	
	public void conejoColisionaCon(ViaTren v) {
		this.colisiona(v.getTren());
	}

	public void checkColision(Calle calle, Calle calle1, Calle calle2,ViaTren via) {
		this.conejoColisionaCon(calle);
		this.conejoColisionaCon(calle1);
		this.conejoColisionaCon(calle2);
		this.conejoColisionaCon(via);
	}
	void checkKamehameha(Entorno entorno) {
		if(entorno.sePresiono(entorno.TECLA_ESPACIO)&& poder == false) {
			k = new Kamehameha(this);
			this.poder = true;
			k.ejexC = this.getX();
			Herramientas.play("sonidos/kamehameha.wav");
		}
		if(poder==true) {
			k.dibujarse(entorno,k.ejexC);
			k.update(this);
			k.y -= 10;
			// cuando el kamehameha pasa la pantalla, este se vuelve null.
			if(k.y + k.alto/2 <0) {
				this.poder=false;
				k=null;
			}
		}
		
	}
	public void update(Entorno e) {
		//constante movimiento hacia abajo.
		this.y += 0.5;   
		// controla que el conejo no se salga de la pantalla
		if (this.x-ancho/2 < 0) {
			this.x = 0 + ancho/2;
		}
		else if(this.x+ancho/2 > 1024) {
			this.x = 1024 - ancho/2;
		}
		else if(this.y - ancho/2 < 0) {
			this.y = 0 + ancho/2;
		}
		else if(this.y > 800) { 
			perder = true;
		}
		//dibujar conejo segun teclas
		if (derecha==false) {
			e.dibujarImagen(conejoIzq, x, y, 0);
		}
		if(derecha == true) {
			e.dibujarImagen(conejoDer, x, y, 0);
		}
		if(this.saltos == 200 || this.puntos == 250) {
			this.ganar = true;
		}
		
		
	}
	//movimientos del conejo
	public void saltarAdelante() {
		this.y -= 25;
		this.saltos += 1;
		Herramientas.play("sonidos/pasoConejo.wav");
	}
	public void saltarIzquierda() {
		this.x -= 30;
		derecha = false;
		Herramientas.play("sonidos/pasoConejo.wav");
	}
	public void saltarDerecha() {
		this.x += 30;
		derecha = true;
		Herramientas.play("sonidos/pasoConejo.wav");
	}
	
	//setters&getters--------------------------------
	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
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
