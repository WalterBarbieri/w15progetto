package w15progetto.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import w15progetto.entities.Utente;

public class UtenteDao {
	private final EntityManager em;
	private static Logger log = LoggerFactory.getLogger(UtenteDao.class);

	public UtenteDao(EntityManager em) {
		this.em = em;
	}

	// ********************** METODI PER LA CREAZIONE MANUALE E RANDOM DEGLI UTENTI
	// ***************************

	public void createUser(long numeroTessera, String nome, String cognome, LocalDate dataNascita) {
		EntityTransaction t = em.getTransaction();

		t.begin();

		Utente utente = new Utente(numeroTessera, nome, cognome, dataNascita);

		em.persist(utente);

		t.commit();

		log.info(nome + " è stato correttamente aggiunto agli utenti");
	}

	public void createRandomUsers() {
		EntityTransaction t = em.getTransaction();

		t.begin();

		for (int i = 0; i < 20; i++) {
			Utente utente = new Utente();
			em.persist(utente);
		}

		t.commit();

		log.info("Utenti creati con successo");
	}
	// ********************** METODI PER LA RICERCA E STAMPA UTENTI
	// ***************************

	public void selectAndPrintThemUtenti() {
		TypedQuery<Utente> getAllQuery = em.createNamedQuery("selectAllUtenti", Utente.class);
		List<Utente> utenti = getAllQuery.getResultList();

		log.info("Lista Utenti Biblioteca: ");

		utenti.forEach(el -> log.info(el.toString()));
	}

}
