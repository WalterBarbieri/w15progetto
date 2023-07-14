package w15progetto.app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import w15progetto.dao.PrestitoDao;
import w15progetto.dao.PubblicazioneDao;
import w15progetto.dao.UtenteDao;
import w15progetto.utils.JpaUtil;

public class App {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
	private static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		PubblicazioneDao pd = new PubblicazioneDao(em);

		UtenteDao ud = new UtenteDao(em);

		PrestitoDao prd = new PrestitoDao(em);
		log.info("*****************CREAZIONE LISTA PUBBLICAZIONI E UTENTI***********************");
//		pd.createRandomPublication();
//		ud.createRandomUsers();
		log.info("*****************RICERCA E STAMPA INTERA LISTA PUBBLICAZIONI E UTENTI***********************");
		pd.selectAndPrintThemAll();
		ud.selectAndPrintThemUtenti();
		log.info("*****************RICERCA TRAMITE ISBN***********************");
		pd.searchByIsbn(1787869281814L);
		log.info("*****************RIMUOVI TRAMITE ISBN**********************");
		pd.searchAndDestroy(9979010181207L);
		log.info("*****************RICERCA TRAMITE ANNO***********************");
		pd.searchByYear(LocalDate.of(1962, 8, 25)).forEach(el -> log.info(el.toString()));
		log.info("*****************RICERCA TRAMITE AUTORE***********************");
		pd.searchByAutore("Gerald Turcotte").forEach(el -> log.info(el.toString()));
		log.info("*****************RICERCA TRAMITE TITOLO***********************");
		pd.searchByTitle("Blue Remembered Earth").forEach(el -> log.info(el.toString()));

		pd.searchByTitle("The Monkey's Raincoat").forEach(el -> log.info(el.toString()));

		log.info("*****************CREAZIONE LISTA PRESTITI***********************");

//		prd.createRandomPrestito(10);
		log.info("*****************RICERCA E STAMPA INTERA LISTA PRESTITI***********************");
		prd.selectThemPrestiti();

		log.info("*****************RICERCA TRAMITE NUMERO TESSERA***********************");
		prd.searchByNumeroTessera(1367188757L).forEach(el -> log.info(el.toString()));

		log.info("*****************RICERCA TRAMITE SCADUTI***********************");
		prd.searchByScaduti().forEach(el -> log.info(el.toString()));
		prd.searchByScaduti(LocalDate.of(2023, 7, 17)).forEach(el -> log.info(el.toString()));

	}

}
