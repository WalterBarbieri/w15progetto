package w15progetto.app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

//		pd.createRandomPublication();
//		ud.createRandomUsers();
		log.info("*****************RICERCA E STAMPA INTERA LISTA PUBBLICAZIONI E UTENTI***********************");
		pd.selectAndPrintThemAll();
		ud.selectAndPrintThemUtenti();
		log.info("*****************RICERCA TRAMITE ISBN***********************");
		pd.searchByIsbn(1129226167532L);
		log.info("*****************RIMUOVI TRAMITE ISBN**********************");
		pd.searchAndDestroy(9920549429472L);
		log.info("*****************RICERCA TRAMITE ANNO***********************");
		pd.searchByYear(LocalDate.of(2001, 12, 8)).forEach(el -> log.info(el.toString()));
		log.info("*****************RICERCA TRAMITE AUTORE***********************");
		pd.searchByAutore("Earline Rice III").forEach(el -> log.info(el.toString()));

	}

}
