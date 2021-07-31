package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Calle {
	private double alto;
	private double ancho;
	private double x;
	private double y;
	private double movimiento;
	private Carril [] carriles;
	int cantCarriles;
	Image calle;
	
	
	Calle(double alto, double y, int cantCarriles, Entorno entorno){
		setAlto(alto);
		setY(y);
		this.ancho=1024;
		this.x=ancho/2;
		this.movimiento=0.5;
		this.cantCarriles=cantCarriles;
		this.carriles= new Carril[cantCarriles];
		this.generarCarriles(entorno);
		setCalle();
		
	}
	public void dibujarse(Entorno entorno){
		entorno.dibujarImagen(calle, x, y, 0);
		for(Carril c : carriles) {
			c.dibujarse(entorno);
			c.update(this); 	
		}
	
	}
	
	public void update(Vereda v,Entorno e){
		this.y=y+movimiento;
		if(this.y-alto/2>=768){
			this.y=(v.getY() - v.getAlto()/2) - (this.alto /2);
			this.generarCarriles(e);
		}
		
	}
	
	
	public void generarCarriles(Entorno e) {
		
		boolean cambiarMano = false;
		
		for(int i=0;i<cantCarriles;i++) {
		
			Random r = new Random();
			//genera un numero entre 3 y 5 para la cantidad de autos por carril.
			int cantAuto = r.nextInt(5-3+1) + 3;
			//genera un numero entre 0.8 y 2.3 para la velocidad del carril.
			double velocidad = 0.8 + (2.3 - 0.8) *r.nextDouble();
			if(!cambiarMano) {
				Carril c = new Carril(this.y - (this.alto/2) +(i*80)+40,cantAuto,1,velocidad);
				carriles[i] = c;
				
			}else {
				Carril c = new Carril(this.y - (this.alto/2) +(i*80)+40,cantAuto,-1,velocidad);
				carriles[i] = c;
				
			}
			cambiarMano  = !cambiarMano;
		}
		
			
	}
	//carga la imagen correspondiente de la calle segun su altura.
	public void setCalle() {
		if(this.alto == 320) {
			this.calle = Herramientas.cargarImagen("imagenes/carril320.jpg");
		}
		if(this.alto == 400) {
			this.calle = Herramientas.cargarImagen("imagenes/carril400.jpg");
		}
		if(this.alto == 480) {
			this.calle = Herramientas.cargarImagen("imagenes/carril480.jpg");
		}
	}
	
	//getters&setter
	public Carril[] getCarriles() {
		return carriles;
	}

	public void setCarriles(Carril[] carriles) {
		this.carriles = carriles;
	}

	public int getCantCarriles() {
		return cantCarriles;
	}

	public void setCantCarriles(int cantCarriles) {
		this.cantCarriles = cantCarriles;
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
		this.y=y;
	}
	

	public Image getCalle() {
		return calle;
	}

	public double getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(double movimiento) {
		this.movimiento = movimiento;
	}
	
	

}

