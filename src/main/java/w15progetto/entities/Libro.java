package w15progetto.entities;

import java.time.LocalDate;
import java.util.Random;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.github.javafaker.Faker;

import w15progetto.enums.Genere;

@Entity
@DiscriminatorValue("Libro")
@NamedQuery(name = "selectByAutore", query = "SELECT a FROM Libro a WHERE a.autore = :autore")
public class Libro extends Pubblicazione {
	@Transient
	Faker faker = new Faker();
	@Transient
	Random rnd = new Random();

	private String autore;
	private Genere genere;

	public Libro() {
		super();
		this.setAutore();
		this.setGenere();
	}

	public Libro(long isbn, String titolo, LocalDate annoPubblicazione, int numPagine, String autore, Genere genere) {
		super(isbn, titolo, annoPubblicazione, numPagine);
		this.autore = autore;
		this.genere = genere;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore() {
		this.autore = faker.book().author();
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public Genere getGenere() {
		return genere;
	}

	public void setGenere() {
		Genere[] generi = Genere.values();
		this.genere = generi[rnd.nextInt(generi.length)];
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	@Override
	public String toString() {
		return "Libro [Autore: " + autore + ", genere: " + genere + ", Titolo: " + getTitolo()
				+ ", Anno Pubblicazione: " + getAnnoPubblicazione() + ", Pagine: " + getNumPagine() + ", Isbn: "
				+ getIsbn() + "]";
	}

}
