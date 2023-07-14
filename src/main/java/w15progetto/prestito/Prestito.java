package w15progetto.prestito;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import w15progetto.entities.Pubblicazione;
import w15progetto.entities.Utente;

@Entity
@Table(name = "prestiti")
public class Prestito {
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;

	@ManyToOne
	@JoinColumn(name = "pubblicazione_id")
	private Pubblicazione pubblicazione;

	private LocalDate dataInizioPrestito;
	private LocalDate dataRestituzionePrvista;
	private LocalDate dataRestituzioneEffettiva;

	public Prestito() {

	}

	public Prestito(Utente utente, Pubblicazione pubblicazione, LocalDate dataInizioPrestito,
			LocalDate dataRestituzionePrvista, LocalDate dataRestituzioneEffettiva) {
		this.utente = utente;
		this.pubblicazione = pubblicazione;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrvista = dataRestituzionePrvista;
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Pubblicazione getPubblicazione() {
		return pubblicazione;
	}

	public void setPubblicazione(Pubblicazione pubblicazione) {
		this.pubblicazione = pubblicazione;
	}

	public LocalDate getDataInizioPrestito() {
		return dataInizioPrestito;
	}

	public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
		this.dataInizioPrestito = dataInizioPrestito;
	}

	public LocalDate getDataRestituzionePrvista() {
		return dataRestituzionePrvista;
	}

	public void setDataRestituzionePrvista(LocalDate dataRestituzionePrvista) {
		this.dataRestituzionePrvista = dataRestituzionePrvista;
	}

	public LocalDate getDataRestituzioneEffettiva() {
		return dataRestituzioneEffettiva;
	}

	public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	@Override
	public String toString() {
		return "Prestito [id=" + id + ", utente=" + utente + ", pubblicazione=" + pubblicazione
				+ ", dataInizioPrestito=" + dataInizioPrestito + ", dataRestituzionePrvista=" + dataRestituzionePrvista
				+ ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva + "]";
	}

}
