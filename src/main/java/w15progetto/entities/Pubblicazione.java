package w15progetto.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.github.javafaker.Faker;

import w15progetto.prestito.Prestito;

@Entity
@Table(name = "pubblicazioni")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pubblicazioni")
@NamedQuery(name = "selectAll", query = "SELECT a FROM Pubblicazione a")
public abstract class Pubblicazione {
	@Transient
	Faker faker = new Faker();
	@Transient
	Random rnd = new Random();

	@Id
	private long isbn;
	private String titolo;
	private LocalDate annoPubblicazione;
	private int numPagine;

	@OneToMany(mappedBy = "pubblicazione")
	private List<Prestito> prestiti = new ArrayList<>();

	public Pubblicazione() {
		this.setTitolo();
		this.setAnnoPubblicazione();
		this.setNumPagine();
		this.setIsbn();
	}

	public Pubblicazione(long isbn, String titolo, LocalDate annoPubblicazione, int numPagine) {
		this.isbn = isbn;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numPagine = numPagine;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn() {
		this.isbn = faker.number().randomNumber(13, true);
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo() {
		this.titolo = faker.book().title();
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public void setAnnoPubblicazione() {
		this.annoPubblicazione = LocalDate.of(rnd.nextInt(2023 - 1950) + 1950, rnd.nextInt(12) + 1,
				rnd.nextInt(28) + 1);
	}

	public void setAnnoPubblicazione(LocalDate annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

	public int getNumPagine() {
		return numPagine;
	}

	public void setNumPagine() {
		this.numPagine = rnd.nextInt((500) + 100);
	}

	public void setNumPagine(int numPagine) {
		this.numPagine = numPagine;
	}

	@Override
	public String toString() {
		return "Pubblicazione [isbn=" + isbn + ", titolo=" + titolo + ", annoPubblicazione=" + annoPubblicazione
				+ ", numPagine=" + numPagine + "]";
	}

}
