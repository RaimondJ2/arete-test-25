# Book API

moodul: `LX/LX06BookAPI`

pakk: `ee.taltech.iti0202.bookapi`

**Ülesandes tuleb ise kirjutada automaattestid (_coverage_ > 90%).**

# Ülesanne
Selles ülesandes loote REST api. Andmed api jaoks tulevad eelmisest ülesandest (LX05BookScraper). Kui eelmises ülesandes raamatuid
ei salvestanud või ülesannet üldse ei teinud, siis ülesande juurde on lisatud [link](https://iti0202-2021.pages.taltech.ee/public/files/books.json) raamatutele, mida
võib ülesande lahendamisel kasutada.

Siin ülesandes keskendume api endpointidele, api hostimist pole nõutud. Kasutaja suhtleb apiga läbi mingi Kontroller klassi.


# Nõuded

## Get requestid

Rest kontrolleris peaks olema public meetod `get(String path)`, mis tegeleb get requestidega. `path` sisaldab infot
selle kohta, mida programmilt küsiti.

### Nõutav funktsionaalsus get requestide puhul

- mingi päring tagastab kõik andmebaasis olevad raamatud
- mingi päring tagastab kindla id-ga raamatu. (id ei pea olema ainult number, aga peab olema unikaalne)
- mingi päring tagastab raamatu(d), alates mingist indeksist (offset)
- mingi päring tagastab päringus ette antud arvu raamatu(id) (limit)
- mingi päring tagastab raamatu(d), ette antud vahemikus (kahte eelmist kombineerides)
- mingi päring tagastab raamatu(d), mis vastavad kindlale pealkirjale
- mingi päring tagastab raamatu(d), mis vastavad kindlale autorile
- mingi päring tagastab raamatu(d), mis vastavad kindlale väljaandmisaastale
- mingi päring tagastab raamatu(d), mis on sorteeritud tähestikuilises järjekorras
- mingi päring tagastab ramaatu(d), mis on sorteeritud lehekülgede arvu järgi
- mingi päring tagastab raamatu(d), mis on sorteeritud väljaandmisaasta järgi

Päring võib sisaldada mitut eelnevalt kirjeldatud funktsionaalsust. Ehk siis lubatud on ka päringud, mis näiteks küsivad
raamatut kindla autori ja sorteeritult lehekülgede arvu järgi.


## Post requestid

Rest kontrolleris peaks olema public meetod `post(String path, Book book)`, mis tegeleb post requestidega.

### Nõutav funktsionaalsus post requestide puhul

- mingi päring võimaldab lisada uue raamatu andmebaasi


## Put requestid

Rest kontrolleris peaks olema public meetod `put(String path, Book book)`, mis tegeleb put requestidega.

Put meetod ei salvesta andmebaasi uut raamatut, vaid otsib andmebaasist ette antud id-ga raamatu (path peaks sisaldama raamatu id-d)
ning muudab selle raamatu vastavalt ette antud raamatu andmetele.

### Nõutav funktsionaalsus put requestide puhul

- mingi päring võimaldab muuta raamatu andmeid andmebaasis

## Delete requestid

Rest kontrolleris peaks olema public meetod `delete(String path)`.

### Nõutav funktsionaalsus delete dequestide puhul

- mingi päring võimaldab kustutada andmebaasist mingi kindla raamatu

# Punktid
- funktsionaalsuse eest: 10p
- Rest api disaini eest (kas on rest api häid tavu järgitud, vaata linke ülesande all): 10p

# Abiks

## Mõned näited `path` väärtuse kohta
- näita kõiki raamatuid (`get()` meetod): `/books`
- näita kindla id-ga ramat: `/books/{id}`
- tagastame raamatud ette antud vahemikus: `/books?offset=0&limit=5`
- lisa raamat: `/books`


## Kasulikud lingid

- [Best Practices for Naming REST API Endpoints](https://nordicapis.com/10-best-practices-for-naming-api-endpoints/)
- [Query parameters](https://rapidapi.com/blog/api-glossary/parameters/query/)
- [json fail raamatutega](https://iti0202-2021.pages.taltech.ee/public/files/books.json)

