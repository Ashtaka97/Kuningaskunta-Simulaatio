package kuningaskuntaSimulaatio;

import java.util.*;

public class Kuningaskunta{

	public static void main(String[] args) {

		// prologi
		Scanner vastaus = new Scanner(System.in);
		System.out.println("Tervetuloa pelaamaan Kuningaskunta Simulaattoria! Haluatko...");
		System.out.println("1. aloittaa uuden pelin? (Mahdollinen vanha pelisi katoaa)");
		System.out.println("2. jatkaa siit�, mihin j�it?");
		while (!vastaus.hasNextInt()) {
			vastaus.next();
		}
		int vast = vastaus.nextInt();
		if(vast == 1) {
			System.out.println("Miten haluatte ett� kutsun teit�, teid�n ylh�isyytenne? (nimi ja titteli)");
			String nimi = vastaus.next();
			System.out.println("Kuinka monta vuotta aiot hallita? (Joka kuukausi tapahtuu jotain!)");
			while (!vastaus.hasNextInt()) {
				vastaus.next();
			}
			int vuorot = vastaus.nextInt();
			vuorot = vuorot * 12;
			Kuningas kunkku = new Kuningas(nimi, vuorot);
			meillaOnOngelmia(kunkku);
			System.out.println("On kunnia tavata teid�t, " + kunkku.annaNimi());
			kunkku.scan(vastaus);
			kunkku.vuorokierto();
		}
		if(vast == 2) {
			Kuningas kunkku = TallennaLataaPisteet.lataa();
			System.out.println("Hyv� ett� palasitte, " + kunkku.annaNimi());
			kunkku.scan(vastaus);
			kunkku.vuorokierto();
		}
		if(vast == 1337) {
			Kuningas kunkku = new Kuningas("Kuningas Arthur", 80);
			kunkku.asetaRaha(10000);
			kunkku.asetaRuoka(10000);
			kunkku.asetaRahaTuotto(10);
			kunkku.asetaRuokaTuotto(10);
			for(Suku s : kunkku.suvut) {
				s.asetaSuhdeKuninkaaseen(80);
			}
			kunkku.tulostaSuvut();
			meillaOnOngelmia(kunkku);
			System.out.println("On kunnia tavata teid�t, " + kunkku.annaNimi());
			kunkku.scan(vastaus);
			kunkku.vuorokierto();
		}
		
		vastaus.close();
	}

	// Luo kaikki pelin ongelmat, pit�� kutsua sukujen luonnin j�lkeen.
	public static void meillaOnOngelmia(Kuningas kunkku) {
		ArrayList<Paatos> paatokset = new ArrayList<Paatos>();

		paatokset.add(new Paatos(new Vaatimus[] { new Vaatimus(Tyyppi.RAHA, kunkku.annaSukujenLKM() * 5, null, null) },
				new Seuraus[] { new Seuraus(Tyyppi.RAHA, -(kunkku.annaSukujenLKM() * 5), null),
						new Seuraus(Tyyppi.SUKUSUHDE, 20, kunkku.suvut) },
				"Voin auttaa teit� korjaust�iss� viidell� kullalla per suku."));
		
		/*paatokset.add(new Paatos(new Vaatimus[] { new Vaatimus(Tyyppi.SUKUSUHDE, 1, kunkku.etsiSuosituinMagia().get(0), null) },
				new Seuraus[] { new Seuraus(Tyyppi.SUKUSUHDE, 10, kunkku.suvut),
						new Seuraus(Tyyppi.SUKUSUHDE, -20, kunkku.etsiSuosituinUskonnollinen()),
						new Seuraus(Tyyppi.SUKUSUHDE, 20, etsisuku(maaginen & uskonnollinen)),
						Puuttuu viel� sukujen v�liset muutokset},
				"Kansa on selv�stikkin k�sitt�nyt jotain v��rin. Annas kun selit�n, mist� t�m� johtui."));

		Random r = new Random();
		Suku x = kunkku.suvut.get(r.nextInt(kunkku.suvut.size()));
		String neitsyenKoti = x.annaNimi();
		ArrayList<Suku> uhri = new ArrayList<Suku>(Arrays.asList(x));
		// Huumori mieless� j�timem mahdolliseksi tapattaa ehdottajan tytt�ren xD
		
		paatokset.add(new Paatos(new Vaatimus[] { new Vaatimus(Tyyppi.SUKUSUHDE, 1, kunkku.etsiSuosituinUskonnollinen().get(0), null) },
				new Seuraus[] { new Seuraus(Tyyppi.SUKUSUHDE, 10, kunkku.suvut),
						new Seuraus(Tyyppi.SUKUSUHDE, -20, uhri),
						new Seuraus(Tyyppi.SUKUSUHDE, 10, kunkku.suvut),
						Puuttuu viel� sukujen v�liset muutokset},
				"Jos jumalat ovat tosiaan niin vihaisia meid�n on uhrattava neitsyt " + neitsyenKoti + " suvulta mit� pikimmiten."));*/
		
		paatokset.add(new Paatos(new Vaatimus[] { new Vaatimus(Tyyppi.NULL, 0, null, null) },
				new Seuraus[] { new Seuraus(Tyyppi.SUKUSUHDE, -20, kunkku.suvut)},
				"En n�e miten t�m� ongelma koskee minua."));
		
		kunkku.ongelmat.add(new Ongelma("Maanj�ristys",
				"Jumalat ovat vihaisia meille. Kaikkia sukuja on kohdannut onnettomuus ja heid�n "
						+ "tilansa ovat kokeneet suurta vahinkoa. Miten toimimme?",
				kunkku.annaAatelisin(), paatokset));
	}
}