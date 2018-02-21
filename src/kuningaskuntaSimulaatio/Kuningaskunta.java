package kuningaskuntaSimulaatio;

import java.util.*;

public class Kuningaskunta {

	public static void main(String[] args) {

		// prologi
		Scanner vastaus = new Scanner(System.in);
		System.out.println(
				"Tervetuloa pelaamaan Kuningaskunta Simulaattoria! Miten haluatte ett� kutsun teit�, teid�n ylh�isyytenne? (nimi ja titteli)");
		String nimi = vastaus.nextLine();
		System.out.println("Kuinka monta vuotta aiot hallita? (Joka kuukausi tapahtuu jotain!)");
		while (!vastaus.hasNextInt()) {
			vastaus.next();
		}
		int vuorot = vastaus.nextInt();
		vuorot = vuorot * 12;
		Kuningas kunkku = new Kuningas(nimi, vuorot);
		meillaOnOngelmia(kunkku, vuorot);
		System.out.println("On kunnia tavata teid�t, " + kunkku.annaNimi());
		
		kunkku.tulostaSuvut();
		
		// peli alkaa
		kunkku.vuorokierto();
		vastaus.close();
	}

	// Luo kaikki pelin ongelmat, pit�� kutsua sukujen luonnin j�lkeen.
	public static void meillaOnOngelmia(Kuningas kunkku, int vuorot) {
		ArrayList<Paatos> paatokset = new ArrayList<Paatos>();

		paatokset.add(new Paatos(new Vaatimus[] { new Vaatimus(Tyyppi.RAHA, kunkku.annaSukujenLKM() * 5, null) },
				new Seuraus[] { new Seuraus(Tyyppi.RAHA, -(kunkku.annaSukujenLKM() * 5), null),
						new Seuraus(Tyyppi.SUKUSUHDE, 20, kunkku.suvut) },
				"Voin auttaa teit� korjaust�iss� viidell� kullalla per suku."));
		
		paatokset.add(new Paatos(new Vaatimus[] { new Vaatimus(Tyyppi.SUKUSUHDE, 1, /*kunkku.etsiSuosituinMagia().get(0)*/) },
				new Seuraus[] { new Seuraus(Tyyppi.SUKUSUHDE, 10, kunkku.suvut),
						new Seuraus(Tyyppi.SUKUSUHDE, -20, /*kunkku.etsiSuosituinUskonnollinen()*/),
						new Seuraus(Tyyppi.SUKUSUHDE, 20, /*etsisuku(maaginen & uskonnollinen)*/),
						/*Puuttuu viel� sukujen v�liset muutokset*/},
				"Kansa on selv�stikkin k�sitt�nyt jotain v��rin. Annas kun selit�n, mist� t�m� johtui."));

		Random r = new Random();
		Suku x = kunkku.suvut.get(r.nextInt(kunkku.suvut.size()));
		String neitsyenKoti = x.annaNimi();
		ArrayList<Suku> uhri = new ArrayList<Suku>(Arrays.asList(x));
		// Huumori mieless� j�timem mahdolliseksi tapattaa ehdottajan tytt�ren xD
		
		paatokset.add(new Paatos(new Vaatimus[] { new Vaatimus(Tyyppi.SUKUSUHDE, 1, /*kunkku.etsiSuosituinUskonnollinen().get(0)*/) },
				new Seuraus[] { new Seuraus(Tyyppi.SUKUSUHDE, 10, kunkku.suvut),
						new Seuraus(Tyyppi.SUKUSUHDE, -20, uhri),
						new Seuraus(Tyyppi.SUKUSUHDE, 10, kunkku.suvut),
						/*Puuttuu viel� sukujen v�liset muutokset*/},
				"Jos jumalat ovat tosiaan niin vihaisia meid�n on uhrattava neitsyt " + neitsyenKoti + " suvulta mit� pikimmiten."));
		
		paatokset.add(new Paatos(new Vaatimus[] { new Vaatimus(Tyyppi.NULL, 0, null) },
				new Seuraus[] { new Seuraus(Tyyppi.SUKUSUHDE, -20, kunkku.suvut)},
				"En n�e miten t�m� ongelma koskee minua."));
		
		kunkku.ongelmat.add(new Ongelma("Maanj�ristys",
				"Jumalat ovat vihaisia meille. Kaikkia sukuja on kohdannut onnettomuus ja heid�n "
						+ "tilansa ovat kokeneet suurta vahinkoa. Miten toimimme?",
				kunkku.annaAatelisin(), paatokset));
	}
}