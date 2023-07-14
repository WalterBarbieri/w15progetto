package w15progetto.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.github.javafaker.Faker;

import w15progetto.prestito.Prestito;

@Entity
@Table(name = "utenti")
@NamedQuery(name = "selectAllUtenti", query = "SELECT a FROM Utente a")
public class Utente {

	@Transient
	Faker faker = new Faker();
	@Transient
	Random rnd = new Random();
	@Id
	private long numeroTessera;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;

	@OneToMany(mappedBy = "utente")
	private List<Prestito> prestiti = new ArrayList<>();

	public Utente() {
		this.setNumeroTessera();
		this.setNome();
		this.setCognome();
		this.setDataNascita();
	}

	public Utente(long numeroTessera, String nome, String cognome, LocalDate dataNascita) {
		this.numeroTessera = numeroTessera;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
	}

	public long getNumeroTessera() {
		return numeroTessera;
	}

	public void setNumeroTessera() {
		this.numeroTessera = faker.number().randomNumber(10, true);
	}

	public void setNumeroTessera(long numeroTessera) {
		this.numeroTessera = numeroTessera;
	}

	public String getNome() {
		return nome;
	}

	public void setNome() {
		this.nome = faker.name().firstName();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome() {
		this.cognome = faker.name().lastName();
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita() {
		this.dataNascita = LocalDate.of(rnd.nextInt(2023 - 1950) + 1950, rnd.nextInt(12) + 1, rnd.nextInt(28) + 1);
		;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
		;
	}

	public List<Prestito> getPrestiti() {
		return prestiti;
	}

	public void setPrestiti(List<Prestito> prestiti) {
		this.prestiti = prestiti;
	}

	@Override
	public String toString() {
		return "Utente [Numero Tessera: " + numeroTessera + ", Nome: " + nome + ", Cognome: " + cognome
				+ ", Data Nascita: " + dataNascita + "]";
	}

}
