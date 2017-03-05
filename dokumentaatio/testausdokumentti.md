En testannut automaattisesti peliolion periviä luokkia, jotka piirtävät itsensä. En myöskään testannut Pongbreaker-luokan sisältämää actionPerformed-metodia enkä
Laatikko-luokan sisältämää kohtaa, jossa käytetään Random-oliota. Testasin näitä käsin siten, että varmistin peliolioiden oikein piirtämisen, ja että oikeat asiat tapahtuvat kun actionPerformed-metodia kutsutaan. Testailin Random-oliota käyttävää kohtaa myös eri todennäköisyyksillä varmistaakseni toimivuuden.
Bugit: 
1. Pallon tullessaan vasemmalta ja osuu täsmällisesti laatikon oikeaan yläkulmaan, niin pallo kääntää x- ja y-suuntaa, vaikka pitäisi vain kääntää y-suuntaa. Sama bugi pätee myös toisipäin.
2. Jos pallo tulee esim. ylhäältä alas mailaa kohti, ja mailaa liikutetaan palloa päin siten, että mailan yläosa osuu palloon "vauhdissa", niin on mahdollista, että pallo menee hetkellisesti mailan sisälle. Tällöin tosin peli yleensä samalla myös loppuu. 
3. Jar-tiedostossa äänet eivätkä highscoren tallentaminen toimi.
