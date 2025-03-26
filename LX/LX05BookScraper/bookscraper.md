# Book club

moodul: `LX/LX05BookScraper`

pakk: `ee.taltech.iti0202.bookscraper`

Selle ülesande eesmärk on koostada [webscraper](https://en.wikipedia.org/wiki/Web_scraping), mille abil saame koguda
infot raamatupoodides olevate raamatute kohta.

**Ülesandes tuleb ise kirjutada automaattestid (_coverage_ > 90%).**

## Nõuded

- Programm kogub infot vähemalt ühest raamatupoest - 10p
- Programm kogub infot veel ühest raamatupoest - 5p
- On implementeeritud cache, ehk kui tehakse mingi korduv päring, siis küsitakse juba andmeid lokaalselt kuskilt failist - 3p
- Programm on gradle projekt ning kõik välised teegid on `build.gradle` failis defineeritud - 2p

## Täpsustused

Vaja on luua programm, mis küsiks veebilehelt html-i sisu, ning siis suudaks seda html-i parsida ning raamatud ja
autorid sealt kätte saada.

Raamatupoed, mida töödelda:
- [Apollo](https://www.apollo.ee)
- [Rahva Raamat](https://www.rahvaraamat.ee/et)
- Kui on soov mõnda teist ramaatupoodi töödelda, peate sellest discordis teada andma ning loa saama

Saadud info peab olema ilusti klassidesse jaotatud. Iga raamat peab olema eraldi objekt.
Iga autor peab olema eraldi objekt.

Vajalikud andmed raamatu jaoks:
- keel
- teemad (topics)
- pealkiri
- kirjastus (publisher)
- autor
- link raamatule veebipoes
- väljaandmisaasta
- lehekülgede arv
- hind

Vajalikud andmed autori jaoks:
- eesnimi
- perenimi
- autori raamatud

Andmeid võite juurde lisada ja muuta enda jaoks loogilisemaks. Näiteks raamatu hinna võib jagada püsikliendi hinnaks
ja tavahinnaks.

Meetodid, mis peaksid olemas olema:
````
 List<Book> getBooksByTopic(String topic);

 List<Book> getBooksByAuthor(Author author);

 List<Book> getAllBooks();
````
Raamatuid on veebipoes palju ning kõiki ei ole vaja küsida ega salvestada. Erinevaid raamatute teemasid võiks olla vähemalt 10
ning iga teema kohta raamatuid 110.

Cache tähendab siin ülesandes seda, et kui olete juba välja kutsunud `getBooksByTopic(history)`, siis uuel väljakutsumisel
ei küsita veebipoest uuesti andmeid, vaid need tulevad kuskilt lokaalselt salvestatud failist.
Meetodile saab kaasa anda mingi boolean väärtuse, kas cache'i kasutada või mitte.
Raamatute salvestamiseks võib kasutada [gson](https://github.com/google/gson) teeki.


## Abiks
Vaatame näidetena Apollo e-poodi ja Rahva Raamatu e-poodi

### Apollo

Apollo e-pood asub lingil: https://www.apollo.ee/raamatud.

Meil on huvi näiteks ajaloo raamatute kohta.
siis vaatame linki: https://www.apollo.ee/raamatud/eestikeelsed-raamatud/ajalugu.

Näeme, et lehele ilmusid ajaloo teemalised raamatud. Samamoodi nagu veebipoes läheb kasutaja, kes tunneb huvi ajaloo raamatutest
sellele lingile, küsib ka meie programm ajaloo raamatuid sellelt lingilt.

Nüüd on vaja luua mingi lihtne meetod, mis teeb päringu sellele lehele ning saab tagasi html-i.

Kui html on käes, on vaja seda htmli parsida. Seda saame teha näiteks [Jsoupi](https://jsoup.org)
või [HtmlUniti](https://htmlunit.sourceforge.io) abil.

Näide Jsoupiga
````
public Document parseHtml(String html){
       return Jsoup.parse(html)
}
````
`parseHtml` tagastab Jsoupi `Document` tüüpi objekti. Document objektist saame valida huvipakkuvad elemendid `select`
meetodi abil. Näiteks kui html struktuur on järgmine:
````
<div class="books">
    <div class="book">
        ...
    </div>
    <div class="book">
        ...
    </div>
    
    ...
    
    <div class="book">
            ...
    </div>

</div>
````
Valime elemendi, mis sisaldab raamatuid:
````
public Document parseHtml(String html){
       return Jsoup.parse(html)
}

public void viewBooks(String html){
    Document document = parseHtml(html)
    Element books = document.select(".books").first()
}
````
Valime elemendid, mis sisaldavad infot raamatu kohta:
````
public Document parseHtml(String html){
       return Jsoup.parse(html)
}

public void viewBooks(String html){
    Document document = parseHtml(html)
    Elements books = document.select(".book")
}
````
Apollo html-i struktuuriga tutvu ise ning vali sealt vajalik info.

Ainuüksi sellelt lehelt saadud infost ei piisa ning täpsema info saamiseks, on vaja igat raamatu lehte külastada veel
eraldi. Seda teeb inimene veebilehte külastades raamatu peale klõpsates. Mis Apollo veebipoe puhul tähendab `<a>` tagile
vajutamist. Uuri, kuidas Jsoupi või mõne muu teegi abil saaks selle lingi sealt kätte.

### Rahva Raamat

Rahva Raamatu veebipood on natukene teist moodi üles ehitatud ja scraperile peab tegema mõned muudatused.

Ilukirjanduse leiame näiteks lingilt: https://www.rahvaraamat.ee/c/ilukirjandus/1/1/29/et#!/activeTab=tab02. Näeme seal
veebilehitseja abil raamatuid. Kui nüüd programm küsib selle urli html-i, siis avastame, et raamatuid seal html-is ei ole.

Rahva Raamatu veebipoe puhul ilmuvad raamatud veebilehele dünaamiliselt. Aeglasema internetiühenduse puhul näeme, et
seda linki külastades saame alguses lehekülje ilma raamatuteta ning teate 'laen tooteid'. Kui teeme päringu selle urli
pihta, siis see ongi see html, mille me sealt saame (ilma toodeteta html).

Tuleb välja selgitada, kust kohast raamatud lehele ilmuvad. Uurime brauseri konsooli abil
xhr päringuid, mis veebipood teeb ja tuvastame, kust raamatud tulevad. Uurige kuidas link muutub veebilehel alla kerides.

## Kasulikud lingid

- Urlist hmtli saamine
    - https://docs.oracle.com/javase/tutorial/networking/urls/creatingUrls.html
    - https://www.baeldung.com/java-url
- Web scraping libs:
    - [JSoup](https://jsoup.org)
    - [HTMLUnit](http://htmlunit.sourceforge.net)
    - midagi muud, mida ise leiad.
- Gradle:
    - [Gradle get started](https://docs.gradle.org/current/samples/sample_building_java_applications.html)
    - [gradle ja Intellij](https://www.jetbrains.com/help/idea/gradle.html)

