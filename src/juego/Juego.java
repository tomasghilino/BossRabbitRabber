package juego;


import java.awt.Color;
import java.awt.Image;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	// Variables y métodos propios de cada grupo
	// ...
	Conejo conejo;
	ViaTren via;
	Vereda verTren;
	Calle calle;
	Vereda ver;
	Calle calle1;
	Vereda ver1;
	Calle calle2;
	Vereda ver2;
	Image Gameover;
	Image ganarJuego;
	//sonidos
	Clip trafico;
	Clip fondo; //(musica de fondo)
	Clip crash; 
	Clip win;
	
	public boolean EsGameOver() {
		return conejo.perder;
	}
	public boolean ganaste() {
		return conejo.ganar;
	}
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Boss Rabbit Rabber - Grupo 2 / Cardozo-Ghilino-Insua - V0.01", 1024, 768);
		
		// Incia los objetos del juego
		this.conejo = new Conejo();
		this.via = new ViaTren(-1008, entorno);
		this.verTren = new Vereda(124,-866);
		this.calle = new Calle(480,-564,6, entorno);
		this.ver = new Vereda(124,-262);
		this.calle1=new Calle(400,0,5, entorno);
		this.ver1 = new Vereda(124,262);
		this.calle2=new Calle(320,484,4, entorno);
		this.ver2 = new Vereda(124,706);
		this.Gameover = Herramientas.cargarImagen("imagenes/gameOver.png");
		this.ganarJuego = Herramientas.cargarImagen("imagenes/win.png");
		//cargar sonidos
		this.trafico = Herramientas.cargarSonido("sonidos/trafico.wav");
		this.fondo = Herramientas.cargarSonido("sonidos/musicaFondo.wav");
		this.crash = Herramientas.cargarSonido("sonidos/autoCrash.wav");
		this.win = Herramientas.cargarSonido("sonidos/win.wav");
		// Inicia el juego!
		this.entorno.iniciar();
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		// ...

		if(EsGameOver()) {
			fondo.close();
			trafico.close();
			entorno.dibujarImagen(Gameover, 500, 300, 0);
			//escribe la forma de reiniciar.
			this.entorno.cambiarFont("Rockwell", 50, Color.white);
			entorno.escribirTexto("Presiona R para Reiniciar el juego.", 130, 500);
			crash.start();
			if(entorno.sePresiono('R')) {
				crash.close();
				//asi se reinicia el juergo
				entorno.dispose();
			    new Juego();
			}
		}
		else if(ganaste()){
			fondo.close();
			trafico.close();
			win.start();
			entorno.dibujarImagen(ganarJuego, 520, 250,0);
			//escribe los saltos,puntos y la forma para reiniciar.
			this.entorno.cambiarFont("Rockwell", 50, Color.white);
			entorno.escribirTexto("Presiona R para Reiniciar el juego.", 130, 700);
			entorno.escribirTexto("Puntos : "+ conejo.puntos, 390, 550);
			entorno.escribirTexto("Saltos : "+conejo.saltos, 390, 600);
			if(entorno.sePresiono('R')) {
				win.close();
				//asi se reinicia el juego
				entorno.dispose();
			    new Juego();
			}
		}
		else {
			
			fondo.loop(Clip.LOOP_CONTINUOUSLY);
			trafico.loop(Clip.LOOP_CONTINUOUSLY);
			
			verTren.dibujarse(entorno);
			verTren.update(calle);
			    		 
			via.dibujarse(entorno);
			via.update(verTren, entorno);
			
			calle.dibujarse(entorno);
			calle.update(ver,entorno);
			
			
			ver.dibujarse(entorno);
			ver.update(calle1);
			
			
			calle1.dibujarse(entorno);
			calle1.update(ver1,entorno);
			
			
			ver1.dibujarse(entorno);
			ver1.update(calle2);
			
			
			calle2.dibujarse(entorno);
			calle2.update(ver2,entorno);
			
			
			ver2.dibujarse(entorno);
			ver2.update(via);
			
			//control del input de usuario
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				conejo.saltarAdelante();
			}
			if(entorno.sePresiono(entorno.TECLA_IZQUIERDA)) {
				conejo.saltarIzquierda();
			}
			if(entorno.sePresiono(entorno.TECLA_DERECHA)) {
				conejo.saltarDerecha();
			}
			
			
			//actualizacion del conejo en cuanto a movimiento y colision
			conejo.update(entorno);
			conejo.checkColision(calle,calle1,calle2,via);
			conejo.checkKamehameha(entorno);
			 
			//dibuja el score y el mensaje para que usuario sepa cuando se gana la partida.
			entorno.dibujarRectangulo(50, 20, 150, 60, 0, Color.BLACK); //score
			entorno.dibujarRectangulo(500, 15, 300, 35, 0, Color.BLACK); //aviso de cuando se gana
			
			this.entorno.cambiarFont("Rockwell", 17, Color.white);
			entorno.escribirTexto("200 Saltos o 250 puntos para ganar!",365, 25);
			
			this.entorno.cambiarFont("Rockwell", 20, Color.white);
			entorno.escribirTexto("Puntos : "+ conejo.puntos, 20, 20);
			entorno.escribirTexto("Saltos : "+conejo.saltos, 20, 40);
		
			
		}

	}	
	
	public static void main(String[] args)
	{	
		
		Juego juego = new Juego();
		
	}
}
