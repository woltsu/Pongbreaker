
**Aihe:** Pongbreaker on eräänlainen yhdistelmä perinteisistä Pong ja Brick Breaker peleistä. Peli on reaaliaikainen ja sen  perusrakenne on Pong-pelin tyyppinen, mutta pelikentän keskelle voi ilmestyä palikoita, jotka vaikuttavat pelin kulkuun.

Peliin olisi tarkoitus myös luoda erilaisia
"power-uppeja", highscore-järjestelmä ja äänet. Jos aikaa jää, niin tavoitteena on myös luoda human vs.human pelimuoto.

**Käyttäjät:** Pelaaja

**Pelaajan toiminnot:** 
- Pelin aloittaminen
- Oman mailansa liikuttaminen
- Power upp:ien aktivoiminen
- Pelin jälkeen mahdollisuus lisätä oma suorituksensa highscore-listaan
- Highscore-listan avaaminen  

**Luokkakaavio:**  
![Luokkakaavio](/dokumentaatio/Luokkakaavio2.png)


**Pelaaja käynnistää pelin**  
![Sekvenssikaavio1](/dokumentaatio/sekvenssikaavio1.png)  


**Pelaaja liikkuu ylös ja alas**  
![Sekvenssikaavio2](/dokumentaatio/sekvenssikaavio2.png)  

**Rakennekuvaus:**  
Käyttöliittymä sisältää pelin, jonka se samalla myös antaa piirtoalustalle, näppäimistönkuuntelijalle ja pisteidenkäsittelijälle. Piirtoalusta hakee pelistä piirrettävät pelioliot-perivät luokat, joita ovat laatikot, mailat ja pallo. Piirtoalusta kutsuu niiden piirra-metodia, eli ne piirtävät itsensä. Peliolioilla on myös muita yhdistäviä ominaisuuksia, kuten törmäykseen reagoiminen. Näppäimistönkuuntelijaa käytetään pelin aloittamiseen ja mailan liikuttamiseen, ja pisteidenkäsittelijä mahdollisesti tallentaa pelin erän päätyttyä pisteet tiedostoon ja näyttää top6-listan. Piirtoalusta ja pisteidenkäsittelijä implementoivat paivitettava-luokan, joiden metodia paivita pelin while-looppii kutsuu.

Peli sisältää peliolioita, rajojentarkkailijan ja tormayksienhavaitsijan. Pelaaja- ja vastustaja-luokat sisältävät mailat ja ne helpottavat power-uppeihin reagoimista. Rajojentarkkailija pitää huolen siitä, ettei pelioliot karkaa näytöltä, ja tormayksienhavaitsija tarkistaa mikäli pelissä tapahtuu yhteentörmäyksiä.
