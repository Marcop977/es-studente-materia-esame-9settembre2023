package gestoreesami;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestoreEsami {
	public static void main(String[] args) {
		ArrayList<Studente> studenti = new ArrayList<>();
		ArrayList<Materia> materie = new ArrayList<>();
		ArrayList<Esame> esami = new ArrayList<>();

		File out = new File("files/esami.txt");

		try {
			BufferedReader reader = new BufferedReader(new FileReader("files/studenti.txt"));
			BufferedReader reader2 = new BufferedReader(new FileReader("files/materie.txt"));
			String line;
			String line2;

			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				int studenteId = Integer.parseInt(parts[0]);
				String studenteNome = parts[1];
				Studente studente = new Studente(studenteId, studenteNome);
				studenti.add(studente);
			}
			reader.close();

			while ((line2 = reader2.readLine()) != null) {
				String[] parts = line2.split(",");
				int materiaCodice = Integer.parseInt(parts[0]);
				String materiaNome = parts[1];
				Materia materia = new Materia(materiaCodice, materiaNome);
				materie.add(materia);
			}
			reader2.close();

			Scanner s = new Scanner(System.in);

			boolean condizione = true;
//			while(condizione) {
//				System.out.println("Seleziona ID dello studente (da 1 a 5), 7 per scrivere gli studenti, 8 per calcolare la media dei voti di tutti gli studenti, 9 per calcolare la media di tutti gli studenti per ogni materia, 0 per uscire:");
//				int sceltaStudente = s.nextInt();
//				if(sceltaStudente > 0 && sceltaStudente < studenti.size()) {
//					System.out.println(studenti.get(sceltaStudente - 1));
//					System.out.println("Seleziona ID materia (da 1 a 5):");
//					int sceltaMateria = s.nextInt();
//					System.out.println(materie.get(sceltaMateria - 1));
//					System.out.println("Inserisci voto per " + materie.get(sceltaMateria - 1).getNome());
//					int voto = s.nextInt();
//					Esame esame = new Esame(studenti.get(sceltaStudente - 1), materie.get(sceltaMateria - 1), voto);
//					esami.add(esame);
//					System.out.println(esame);
//
//				}else if(sceltaStudente == 7) {
//					PrintWriter pw = new PrintWriter(out);
//
//					for (Esame esame : esami) {
//						pw.println(esame);
//					}
//					pw.close();	
//				}else if(sceltaStudente == 8) {
//					System.out.println(calcolaMediaStudenti(esami));
//				}else if(sceltaStudente == 9) {
//					System.out.println("Inserisci l'ID della materia:");
//					int materiaScelta = s.nextInt();
//					System.out.println(materie.get(materiaScelta - 101));
//					System.out.println(calcolaMediaStudentiMateria(esami, materiaScelta));					
//				}else if(sceltaStudente == 0){
//					for (Esame esame : esami) {
//						System.out.println(esame);
//					}
//					System.out.println("Programma terminato.");
//					s.close();
//					System.exit(0);
//				}else{
//					System.out.println("ID non valido.");
//					s.close();
//					System.exit(0);
//				}
//			}
			
			while(condizione) {
				System.out.println("\n1. Visualizza studenti\n2. Inserisci voto agli studenti\n3. Registra studenti su file\n4. Calcola media voti di tutti gli studenti\n5. Calcola media di tutti gli studenti per materia\n6. Visualizza voti studenti per materia\n7. Chiudi il programma\n");
				int scelta = s.nextInt();
				switch(scelta) {
					case 1:
						System.out.println("Studenti:");
						for (Studente studente : studenti) {
							System.out.println(studente);
						}
						break;
					case 2:
						System.out.println("Studenti:");
						for (Studente studente : studenti) {
							System.out.println(studente);
						}
						System.out.println("Seleziona ID studente:");
						int sceltaStudente = s.nextInt();
						System.out.println(studenti.get(sceltaStudente - 1) + "\n");
						System.out.println("Materie:");
						for (Materia materia : materie) {
							System.out.println(materia);
						}
						System.out.println("Seleziona ID materia:");
						int sceltaMateria = s.nextInt();
						System.out.println(materie.get(sceltaMateria - 101));
						System.out.println("Inserisci voto per " + materie.get(sceltaMateria - 101).getNome() + " allo studente " + studenti.get(sceltaStudente - 1));
						int voto = s.nextInt();
						Esame esame = new Esame(studenti.get(sceltaStudente - 1), materie.get(sceltaMateria - 101), voto);
						esami.add(esame);
						System.out.println("Esame per " + esame.getStudente().getNome() + " in " + esame.getMateria().getNome() + " superato con un voto di " + esame.getVoto() + ": aggiunto con successo.");
						break;
					case 3:
						PrintWriter pw = new PrintWriter(out);
						for (Esame esame2 : esami) {
							pw.println(esame2);
						}
						pw.close();
						System.out.println("Studenti registrati");
						break;
					case 4:
						System.out.println("La media voti di tutti gli studenti è: " + calcolaMediaStudenti(esami));
						break;
					case 5:
						for (Materia materia : materie) {
							System.out.println(materia);
						}
						System.out.println("Inserisci l'ID della materia:");
						int materiaScelta = s.nextInt();
						System.out.println(materie.get(materiaScelta - 101));
						System.out.println("La media degli studenti in " + materie.get(materiaScelta - 101) + " è: " + calcolaMediaStudentiMateria(esami, materiaScelta));
						break;
					case 6:
						for (Studente studente : studenti) {
							System.out.println(studente);
						}
						System.out.println("Seleziona ID studente di cui visualizzare i voti:");
						int sceltaStudente2 = s.nextInt();
						System.out.println(mostraVotiStudente(esami, sceltaStudente2));
						break;
					case 7:
						s.close();
						System.out.println("Programma terminato.");
						System.exit(0);
				}
			}


		} catch (IOException e) {
			System.out.println("Errore nella lettura del file: " + e.getMessage());
		}
	
}


	public static double calcolaMediaStudenti(ArrayList<Esame> esami) {
		double totale = 0;
		int numEsami = 0;

		for (Esame esame : esami) {
			totale += esame.getVoto();
			numEsami++;
		}

		return totale / numEsami;
	}

	public static double calcolaMediaStudentiMateria(ArrayList<Esame> esami, int codiceMateria) {
		double totale = 0;
		int numMaterie = 0;

		for (Esame esame : esami) {
			if(codiceMateria == (esame.getMateria().getCodice())) {  //se il codice che inserisce l'utente corrisponde al codice della materia
				totale += esame.getVoto();  //tutti i voti della materia corrispondente all'input utente
				numMaterie++;
			}else{
				System.out.println("Lo studente non ha tenuto nessun esame.");
			}
		}
		
		return totale / numMaterie;		
	}
	
	public static String mostraVotiStudente(ArrayList<Esame> esami, int idStudente) {
		String voto = "";
		for (Esame esame : esami) {
			if(idStudente == esame.getStudente().getId()) {
				
				voto = esame.getMateria() + ", voto: " + esame.getVoto();
			}
			
		}
		return voto;
	}
}



