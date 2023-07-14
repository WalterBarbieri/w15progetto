package w15progetto.entities;

import java.time.LocalDate;
import java.util.Random;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import w15progetto.enums.Periodicita;

@Entity
@DiscriminatorValue("Rivista")
public class Rivista extends Pubblicazione {
	@Transient
	Random rnd = new Random();

	private Periodicita periodicita;

	public Rivista() {
		super();
		this.setPeriodicita();
	}

	public Rivista(long isbn, String titolo, LocalDate annoPubblicazione, int numPagine, Periodicita periodicita) {
		super(isbn, titolo, annoPubblicazione, numPagine);
		this.periodicita = periodicita;
	}

	public Periodicita getPeriodicita() {
		return periodicita;
	}

	public void setPeriodicita() {
		Periodicita[] periodi = Periodicita.values();
		this.periodicita = periodi[rnd.nextInt(periodi.length)];
	}

	@Override
	public String toString() {
		return "Rivista [Periodico :" + periodicita + ", Titolo: " + getTitolo() + ", Anno Pubblicazione: "
				+ getAnnoPubblicazione() + ", Pagine: " + getNumPagine() + ", Isbn: " + getIsbn() + "]";
	}

}
