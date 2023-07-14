package w15progetto.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import w15progetto.entities.Pubblicazione;
import w15progetto.entities.Utente;
import w15progetto.prestito.Prestito;

public class PrestitoDao {
	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(PrestitoDao.class);

	public PrestitoDao(EntityManager em) {
		this.em = em;
	}

	public void createRandomPrestito(int numPrestiti) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		PubblicazioneDao pd = new PubblicazioneDao(em);

		UtenteDao ud = new UtenteDao(em);

		List<Utente> utentiRnd = ud.getRandomUtente(numPrestiti);

		List<Pubblicazione> pubblicazioniRnd = pd.getRandomPubblicazione(numPrestiti);

		Random rnd = new Random();

		for (int i = 0; i < numPrestiti; i++) {
			Prestito prestito = new Prestito();
			prestito.setUtente(utentiRnd.get(i));
			prestito.setPubblicazione(pubblicazioniRnd.get(i));
			int intervallo = rnd.nextInt(15) - 7;
			LocalDate dataInizioPrestito = LocalDate.now().minusDays(30).plusDays(intervallo);
			prestito.setDataInizioPrestito(dataInizioPrestito);
			prestito.setDataRestituzionePrevista(dataInizioPrestito.plusDays(30));
			prestito.setDataRestituzioneEffettiva(dataInizioPrestito.plusDays(30).plusDays(intervallo));

			em.persist(prestito);
		}

		t.commit();

		log.info("Lista Prestiti creata correttamente");

	}

	public void createPrestito(Utente utente, Pubblicazione pubblicazione, LocalDate dataInizioPrestito) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		Prestito prestito = new Prestito();
		prestito.setUtente(utente);
		prestito.setPubblicazione(pubblicazione);
		prestito.setDataInizioPrestito(dataInizioPrestito);
		prestito.setDataRestituzionePrevista(dataInizioPrestito.plusDays(30));

		Random rnd = new Random();
		int intervallo = rnd.nextInt(15) - 7;
		LocalDate dataRestituzioneEffettiva = dataInizioPrestito.plusDays(30).plusDays(intervallo);
		prestito.setDataRestituzioneEffettiva(dataRestituzioneEffettiva);

		em.persist(prestito);

		t.commit();

		log.info("Prestito creato correttamente");
	}

	public void selectThemPrestiti() {
		TypedQuery<Prestito> getAllQuery = em.createNamedQuery("selectAllPrestiti", Prestito.class);
		List<Prestito> prestiti = getAllQuery.getResultList();

		log.info("Il catalogo contiene i seguenti Prestiti: ");

		prestiti.forEach(el -> log.info(el.toString()));
	}

	public List<Prestito> searchByNumeroTessera(long numeroTessera) {

		try {
			TypedQuery<Prestito> getAllQuery = em.createNamedQuery("selectByNumeroTessera", Prestito.class);
			getAllQuery.setParameter("numeroTessera", numeroTessera);
			return getAllQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Prestito non trovato");
			return null;
		}

	}

	public List<Prestito> searchByScaduti(LocalDate currentDate) {

		try {
			TypedQuery<Prestito> getAllQuery = em.createNamedQuery("selectScaduti", Prestito.class);
			getAllQuery.setParameter("currentDate", currentDate);
			return getAllQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Prestito non trovato");
			return null;
		}

	}

	public List<Prestito> searchByScaduti() {

		try {
			TypedQuery<Prestito> getAllQuery = em.createNamedQuery("selectScadutiToday", Prestito.class);
			return getAllQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Prestito non trovato");
			return null;
		}

	}

}
