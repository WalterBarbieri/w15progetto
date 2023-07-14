package w15progetto.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import w15progetto.dao.PubblicazioneDao;
import w15progetto.dao.UtenteDao;
import w15progetto.utils.JpaUtil;

public class App {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		PubblicazioneDao pd = new PubblicazioneDao(em);

		UtenteDao ud = new UtenteDao(em);

//		pd.createRandomPublication();
//		ud.createRandomUsers();

		pd.selectAndPrintThemAll();
		ud.selectAndPrintThemUtenti();
	}

}
