import java.util.Arrays;
import java.util.Scanner;

public class JuegoDelaVida {

	public static void main(String[] args) {
		
		int valor,tamanio=0,contador=0;
		boolean seguir = true;//declaración variables
		Scanner teclado = new Scanner(System.in);		

		System.out.println("de que tamaño desea el tablero?");
		try {//controla si es introducido un dato que cause fallo
			tamanio = teclado.nextInt();
			
		} catch (Exception e) {
			System.out.println("debe seleccionar solo numeros para la ejecución del juego");
			seguir=false;
		}					
		
		int tablero[][] = new int[tamanio + 2][tamanio + 2]; //matriz para el tablero que recoge los números aleatorios
		int tablero2[][] = new int[tamanio + 2][tamanio + 2];//matriz para el tablero que hace los primeros cambios
		
		// Relleno todo el tablero
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {

				valor = (int) (Math.random() * 10);//se crean valores aleatorios
				
				//condición para que no sean tenidas en cuenta la primera y última posición del array en cada fila y cada columna
				if (i != 0 && i != (tablero.length - 1) && j != 0 && (j != tablero.length - 1)) {
					//los valores menores a 5 se asigna un 0 por defecto y a los mayores de 4 un 1
					if (valor <= 4) {
						valor = 0;
					} else {
						valor = 1;
					}
				} else {
					valor = 0;
				}

				tablero[i][j] = valor;// se guarda los valores 0 o 1 según sea el caso
				tablero2[i][j] = 0;// se rellena el tablero de relevo con ceros para evitar los null
			}
		}

		pinta(tablero); //con el metodo pinta, se dibuja el tablero 1
		
		while (seguir) { //para repetir el ciclo

			for (int i = 1; i < tablero.length - 1; i++) {
				for (int j = 1; j < tablero.length - 1; j++) {
					int celulasVecinas = 0; //contador de celulas vivas vecinas de una celula viva
					int celulasVecinasM = 0;//contador de celulas vivas vecinas de una celula muerta

					if (tablero[i][j] == 1) {//asignación de valores a contador

						if (tablero[i - 1][j - 1] == 1) {
							celulasVecinas++;
						}
						if (tablero[i][j - 1] == 1) {
							celulasVecinas++;
						}
						if (tablero[i + 1][j - 1] == 1) {
							celulasVecinas++;
						}
						if (tablero[i - 1][j] == 1) {
							celulasVecinas++;
						}
						if (tablero[i + 1][j] == 1) {
							celulasVecinas++;
						}
						if (tablero[i - 1][j + 1] == 1) {
							celulasVecinas++;
						}
						if (tablero[i][j + 1] == 1) {
							celulasVecinas++;
						}
						if (tablero[i + 1][j + 1] == 1) {
							celulasVecinas++;
						}

					}
					if (tablero[i][j] == 0) {//asignación de valores a contador

						if (tablero[i - 1][j - 1] == 1) {
							celulasVecinasM++;
						}
						if (tablero[i][j - 1] == 1) {
							celulasVecinasM++;
						}
						if (tablero[i + 1][j - 1] == 1) {
							celulasVecinasM++;
						}
						if (tablero[i - 1][j] == 1) {
							celulasVecinasM++;
						}
						if (tablero[i + 1][j] == 1) {
							celulasVecinasM++;
						}
						if (tablero[i - 1][j + 1] == 1) {
							celulasVecinasM++;
						}
						if (tablero[i][j + 1] == 1) {
							celulasVecinasM++;
						}
						if (tablero[i + 1][j + 1] == 1) {
							celulasVecinasM++;
						}
					}
					
					if (celulasVecinas < 2) {//primera condición del programa
						tablero2[i][j] = 0;
					}
					if (celulasVecinas >= 2 && celulasVecinas <= 3) {//segunda condición del programa
						tablero2[i][j] = 1;
					}
					if (celulasVecinas > 3) {//tercer condición del programa
						tablero2[i][j] = 0;
					}
					if (celulasVecinasM == 3) {//cuarta condición del programa
						tablero2[i][j] = 1;
					}
				}
			}

			pinta(tablero2);//con el metodo pinta, se dibuja el tablero 2, es decir el de relevo

			for (int i = 1; i < tablero.length - 1; i++) {
				for (int j = 1; j < tablero.length - 1; j++) {
					tablero[i][j] = tablero2[i][j];
					if (tablero[i][j] == 0) {

					}
				}
			}

			try {
				Thread.sleep(1500);// instrucción para retardar la ejecución del programa
				contador++;
				if(contador==30) { // para terminar el juego de forma automatica con 30 rotaciones
					seguir=false;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}

	public static void pinta(int[][] tablero) {//metodo para dibujar los tableros
		String parteSuperiorInferior = "-";
		for (int i = 1; i < (tablero.length - 2) * 6; i++) {
			parteSuperiorInferior = parteSuperiorInferior + "-";
		}
		System.out.println(parteSuperiorInferior);
		for (int i = 1; i < tablero.length - 1; i++) {
			for (int j = 1; j < tablero.length - 1; j++) {
				if (tablero[i][j] == 0) {
					System.out.print("|     ");
				} else {
					System.out.print("|  O  ");
				}
			}
			System.out.println("|");
			System.out.println(parteSuperiorInferior);
		}
	}

}
