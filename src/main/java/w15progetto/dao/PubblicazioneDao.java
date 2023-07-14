package w15progetto.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import w15progetto.entities.Libro;
import w15progetto.entities.Pubblicazione;
import w15progetto.entities.Rivista;
import w15progetto.enums.Genere;
import w15progetto.enums.Periodicita;

public class PubblicazioneDao {
	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(PubblicazioneDao.class);

	public PubblicazioneDao(EntityManager em) {
		this.em = em;
	}

	// ********************** METODI PER LA CREAZIONE MANUALE E RANDOM DELLE
	// PUBBLICAZIONI ***************************
	public void createLibro(long isbn, String titolo, LocalDate annoPubblicazione, int numPagine, String autore,
			Genere genere) {
		EntityTransaction t = em.getTransaction();

		t.begin();

		Libro libro = new Libro(isbn, titolo, annoPubblicazione, numPagine, autore, genere);

		em.persist(libro);

		t.commit();

		log.info("Libro " + titolo + " inserito corettamente");

	}

	public void createRivista(long isbn, String titolo, LocalDate annoPubblicazione, int numPagine,
			Periodicita periodicita) {
		EntityTransaction t = em.getTransaction();

		t.begin();

		Rivista rivista = new Rivista(isbn, titolo, annoPubblicazione, numPagine, periodicita);

		em.persist(rivista);

		t.commit();

		log.info("Rivista " + titolo + " inserito corettamente");
	}

	public void createRandomPublication() {
		EntityTransaction t = em.getTransaction();

		t.begin();

		for (int i = 0; i < 20; i++) {
			Libro libro = new Libro();
			em.persist(libro);

			Rivista rivista = new Rivista();
			em.persist(rivista);
		}

		log.info("Catalogo creato correttamente:");

		t.commit();

	}

	// ********************** METODI PER LA RICERCA E STAMPA PUBBLICAZIONI
	// ***************************
	public void selectAndPrintThemAll() {
		TypedQuery<Pubblicazione> getAllQuery = em.createNamedQuery("selectAll", Pubblicazione.class);

		List<Pubblicazione> pubblicazioni = getAllQuery.getResultList();

		log.info("Il catalogo contiene le seguenti Pubblicazioni: ");

		pubblicazioni.forEach(el -> log.info(el.toString()));
	}

	public Pubblicazione searchByIsbn(long isbn) {
		Pubblicazione found = em.find(Pubblicazione.class, isbn);
		if (found != null) {
			log.info("Pubblicazione con Isbn " + isbn + " : ");
			log.info(found.toString());
			return found;
		} else {
			log.info("Evento non trovato");
		}

		return null;
	}

	public void searchAndDestroy(long isbn) {
		Pubblicazione found = em.find(Pubblicazione.class, isbn);
		if (found != null) {
			EntityTransaction t = em.getTransaction();

			t.begin();

			em.remove(found);
			t.commit();

			log.info(found.getTitolo() + " è stato rimosso correttamente");
		} else {
			log.info("Pubblicazione non trovata");
		}
	}

	public List<Pubblicazione> searchByYear(LocalDate annoPubblicazione) {
		try {
			TypedQuery<Pubblicazione> getAllQuery = em.createNamedQuery("selectByYear", Pubblicazione.class);
			getAllQuery.setParameter("annoPubblicazione", annoPubblicazione);
			return getAllQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Pubblicazione non trovata");
			return null;
		}

	}

	public List<Libro> searchByAutore(String autore) {
		try {
			TypedQuery<Libro> getAllQuery = em.createNamedQuery("selectByAutore", Libro.class);
			getAllQuery.setParameter("autore", autore);
			return getAllQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Pubblicazione non trovata");
			return null;
		}

	}

	public List<Pubblicazione> searchByTitle(String titolo) {
		try {
			TypedQuery<Pubblicazione> getAllQuery = em.createNamedQuery("selectByTitle", Pubblicazione.class);
			getAllQuery.setParameter("titolo", titolo);
			return getAllQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Pubblicazione non trovata");
			return null;
		}

	}

	public List<Pubblicazione> getRandomPubblicazione(int numPrestito) {
		TypedQuery<Pubblicazione> getAllQuery = em.createNamedQuery("selectRandomNPubblicazioni", Pubblicazione.class);
		getAllQuery.setMaxResults(numPrestito);
		return getAllQuery.getResultList();
	}

}
