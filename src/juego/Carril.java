package juego;

import java.util.Random;
import entorno.Entorno;

public class Carril {
	
	private Auto [] autos;
	private double alto;
	private double ancho;
	private double y;
	private double x;
	private double velocidad;
	private int mano; // -1 izquierda / 1 derecha
	private int cantAutos;
	
	
	
	Carril( double y, int cantAutos, int mano, double velocidad){
		this.cantAutos = cantAutos;
		setAutos(new Auto[cantAutos]);
		setVelocidad(velocidad);
		setAlto(80);
		setAncho(1024);
		setY(y);
		setX(1024/2);
		setMano(mano);
		this.generarAutos();	
	}
	
	public void dibujarse(Entorno entorno) {
		for (Auto a : autos) {
			if(a!=null) {
				a.dibujarse(entorno);
				a.update(mano,velocidad,this);
			}
			continue;
		}
	}
	public void update(Calle calle) {
		this.y += 0.5;
		
	}
	public void update(ViaTren via) {
		this.y += 0.5;
		
	}
	public void generarAutos() {
		Random r = new Random();
		int x = 500;
		for(int i=0; i<cantAutos;i++) {
			Auto au =  new Auto(x, this.y,this);
			autos[i] = au;
			//numero aleatorio entre 190 y 240 para el espaciado de los autos de un carril.
			int esp = r.nextInt(240-190+1) + 190;
			if (this.mano == -1) {
				x+=esp;
			}
			else if(this.mano == 1) {
				x-=esp;
			}
				
		}
	}
	
	//setters&getters
	public Auto[] getAutos() {
		return autos;
	}

	public void setAutos(Auto[] autos) {
		this.autos = autos;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public int getMano() {
		return mano;
	}

	public void setMano(int mano) {
		if (mano == -1 || mano == 1) {
			this.mano = mano;
		}
		else {
			throw new RuntimeException("La mano es incorrecta ('-1'  izq // '1'  der");
		}
		
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
