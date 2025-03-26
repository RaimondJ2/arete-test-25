Module: `LX/LX03Pokemon`

Package: `ee.taltech.iti0202.pokemon`

## Vihjed

Vihjevideo selle ülesande kohta (request, gson, deserialiseerimine): https://www.youtube.com/watch?v=wT03IUN_ODQ

Gson: [tutorialspoint](https://www.tutorialspoint.com/gson/gson_quick_guide.htm), [howtodoinjava](https://howtodoinjava.com/gson/gson/), [baeldung](https://www.baeldung.com/gson-deserialization-guide)

HTTP-request võimalusi: [HttpUrlConnection](https://www.baeldung.com/java-http-request), [OkHttp](https://www.baeldung.com/guide-to-okhttp), [HttpClient](https://www.baeldung.com/java-9-http-client)

Tegemist on GET request-iga. Antud juhul on teil vaja URL-ile lisada *request* või *query* parameetrid `offset` ja `limit`
ning soovitatav on request-i *header*-is täpsustada ka `Content-type`, mis on `application/json`.

IntelliJ-s moodulisse *dependency* lisamine: [jetbrains](https://www.jetbrains.com/help/idea/working-with-module-dependencies.html)

![](https://iti0202.pages.taltech.ee/public/files/ex13_IntelliJ_Dependency.png)

Otsingusse kirjutad soovitud *dependency* viite, leiad need [Maven repository](https://mvnrepository.com/)-st.
Gson'i puhul oleks see `com.google.code.gson:gson` ning valid mõne versiooni.

## Ülesanne

**Sellel ülesandel automaatteste ei ole ning testid tuleb ise kirjutada.**

Kirjuta programm, mis teeb API päringu pokeapi.co pihta, korraldab pokemonide vahelisi võitlusi ning kirjutab võitluste tulemused faili.

Url, kust kõik pokemonid kätte saab: https://pokeapi.co/api/v2/pokemon?offset=0&limit=100000

Kasuta näiteks Gsonit, et Pokemonid Java objektideks teha.

Selleks, et vähendada serveri koormust, tuleks veebipäringud puhverdada. See tähendab, et päritud veebilehe tulemus tuleks faili kirjutada. Kui selline fail on juba olemas, siis loetakse andmed (json) failist. Kui sellist faili pole, loetakse andmed veebist ja kirjutatakse faili.

Näide ühest Pokemonist:
```json
{
    "name": "bulbasaur",
    "speed": 45,
    "attack": 49,
    "defense": 49,
    "special-attack": 65,
    "special-defense": 65,
    "hp": 45,
    "types": [
        "poison",
        "grass"
    ],
    "abilities": [
        "chlorophyll",
        "overgrow"
    ],
    "forms": [
        "bulbasaur"
    ],
    "moves": [
        "razor-wind","swords-dance","cut","bind","vine-whip","headbutt","tackle","body-slam","take-down","double-edge","growl",
        "strength","mega-drain","leech-seed","growth","razor-leaf","solar-beam","poison-powder","sleep-powder","petal-dance",
        "string-shot","toxic","rage","mimic","double-team","defense-curl","light-screen","reflect","bide","sludge","skull-bash",
        "amnesia","flash","rest","substitute","snore","curse","protect","sludge-bomb","mud-slap","giga-drain","endure","charm",
        "swagger","fury-cutter","attract","sleep-talk","return","frustration","safeguard","sweet-scent","synthesis","hidden-power",
        "sunny-day","rock-smash","facade","nature-power","ingrain","knock-off","secret-power","grass-whistle","bullet-seed",
        "magical-leaf","natural-gift","worry-seed","seed-bomb","energy-ball","leaf-storm","power-whip","captivate","grass-knot",
        "venoshock","round","echoed-voice","grass-pledge","work-up","grassy-terrain","confide"
    ],
    "height": 7,
    "weight": 69,
    "base_experience": 64
}
```

Ründamiskordajad
----

All tabelis on ründamise kordajad. Iga rida tähistab ründaja tüüpi, veerud tähistavad kaitsja tüüpi. Seega kui `fighting` tüüpi pokemon ründab näiteks `normal` tüüpi pokemoni, on kordajaks teise rea esimeses veerus olev arv `2.0`.

Tabeli sisselugmiseks võib kasutada `Scanner` objekti. Sellel on olemas meetodid `next()` ja `nextDouble()`, mis automaatselt jätavad tühikud ja tabid vahele ning loevad vastavalt sõne või reaalarvulise väärtuse. Tabel tasuks endale näiteks maatriksiks teha.

```
           normal fighting flying  poison  ground   rock    bug    ghost   steel   fire    water   grass electric psychic   ice   dragon   dark    fairy
normal      1.0     1.0     1.0     1.0     1.0     0.5     1.0     0.0     0.5     1.0     1.0     1.0     1.0     1.0     1.0     1.0     1.0     1.0
fighting    2.0     1.0     0.5     0.5     1.0     2.0     0.5     0.0     2.0     1.0     1.0     1.0     1.0     0.5     2.0     1.0     2.0     0.5
flying      1.0     2.0     1.0     1.0     1.0     0.5     2.0     1.0     0.5     1.0     1.0     2.0     0.5     1.0     1.0     1.0     1.0     1.0
poison      1.0     1.0     1.0     0.5     0.5     0.5     1.0     0.5     0.0     1.0     1.0     2.0     1.0     1.0     1.0     1.0     1.0     2.0
ground      1.0     1.0     0.0     2.0     1.0     2.0     0.5     1.0     2.0     2.0     1.0     0.5     2.0     1.0     1.0     1.0     1.0     1.0
rock        1.0     0.5     2.0     1.0     0.5     1.0     2.0     1.0     0.5     2.0     1.0     1.0     1.0     1.0     2.0     1.0     1.0     1.0
bug         1.0     0.5     0.5     0.5     1.0     1.0     1.0     0.5     0.5     0.5     1.0     2.0     1.0     2.0     1.0     1.0     2.0     0.5
ghost       0.0     1.0     1.0     1.0     1.0     1.0     1.0     2.0     1.0     1.0     1.0     1.0     1.0     2.0     1.0     1.0     0.5     1.0
steel       1.0     1.0     1.0     1.0     1.0     2.0     1.0     1.0     0.5     0.5     0.5     1.0     0.5     1.0     2.0     1.0     1.0     2.0
fire        1.0     1.0     1.0     1.0     1.0     0.5     2.0     1.0     2.0     0.5     0.5     2.0     1.0     1.0     2.0     0.5     1.0     1.0
water       1.0     1.0     1.0     1.0     2.0     2.0     1.0     1.0     1.0     2.0     0.5     0.5     1.0     1.0     1.0     0.5     1.0     1.0
grass       1.0     1.0     0.5     0.5     2.0     2.0     0.5     1.0     0.5     0.5     2.0     0.5     1.0     1.0     1.0     0.5     1.0     1.0
electric    1.0     1.0     2.0     1.0     0.0     1.0     1.0     1.0     1.0     1.0     2.0     0.5     0.5     1.0     1.0     0.5     1.0     1.0
psychic     1.0     2.0     1.0     2.0     1.0     1.0     1.0     1.0     0.5     1.0     1.0     1.0     1.0     0.5     1.0     1.0     0.0     1.0
ice         1.0     1.0     2.0     1.0     2.0     1.0     1.0     1.0     0.5     0.5     0.5     2.0     1.0     1.0     0.5     2.0     1.0     1.0
dragon      1.0     1.0     1.0     1.0     1.0     1.0     1.0     1.0     0.5     1.0     1.0     1.0     1.0     1.0     1.0     2.0     1.0     0.0
dark        1.0     0.5     1.0     1.0     1.0     1.0     1.0     2.0     1.0     1.0     1.0     1.0     1.0     2.0     1.0     1.0     0.5     0.5
fairy       1.0     2.0     1.0     0.5     1.0     1.0     1.0     1.0     0.5     0.5     1.0     1.0     1.0     1.0     1.0     2.0     2.0     1.0
```

Pokemonide võitlused
----

Kaks pokemoni saavad omavahel pidada **duelli**. Pokemonid ründavad kordamööda - üks pokemon alustab oma käiguga siis, kui teine on oma käigu täielikult lõpetanud. Käigu võitja leidmine käib järgneva valemi järgi (eeldusel, et meil võitlevad pokemon1 ja pokemon2 ning Pokemon1 käib esimesena):
```
totalAttack = pokemon1.getAttack(turnCounter) * pokemon1.getAttackMultiplier(pokemon2.getTypes()) - pokemon2.getDefense(turnCounter)
```
Esimene käik on number 1.

Käikude lugemine on oluline sellepärast, et pokemoni iga kolmas rünnak on *special attack* (vt üleval pokemoni näites `special-attack` atribuuti). Samuti on iga teine kaitsmine *special defense* (vt üleval näites `special-defense`). 

NB: igasugune *defense* väärtus (vahet pole, kas *special* või tavaline), peaks arvutusse jõudmisel olema jagatud kahega.

Kui kaitsval pokemonil on mitu tüüpi, siis tuleb vastavad kordajad korrutada. Kui ründaval pokemonil on mitu tüüpi, siis tuleb valida selline tüüp, mis annab parima tulemuse. Käigu lõpus tuleb `totalAttack` lahutada kaitsva pokemoni eludest. Pokemonid ei saa võitluse ajal elusid tagasi. Kui `totalAttack` peaks mingil põhjusel olema negatiivne, siis rünnatud pokemon viga ei saa.

Kui kahe pokemoni vaheline duell kestab rohkem kui 100 käigupaari (st kumbki pokemon on 100 korda rünnanud ja 100 korda kaitsnud), siis on tegemist viigiga ja kumbki pokemon punkte ei saa.
Kui aga ühe pokemoni elud otsa saavad, siis duell lõpeb ning võitja saab oma skoorile ühe punkti juurde. Peale igat võitlust taastatakse pokemonide elud.

Pokemonid saavad ka pidada **turniiri**, kus nad **kõik paarikaupa võitlevad**. Turniiril võitleb iga pokemon iga teisega ainult ühe korra. Siin saab kasutada varem loodud duelli funktsionaalsust, kuid enne tuleb välja selgitada, kumb pokemon esimesena ründab. Selleks on järgmine loogika: 
* Pokemon, kelle *speed* on kõrgem, ründab esimesena.
* Kui mõlema pokemoni *speed* on sama, siis ründab esimesena pokemon, kelle *weight* on väiksem.
* Kui pokemonid kaaluvad sama palju, siis ründab madalama *height*'iga pokemon enne.
* Kui pokemonid on sama pikad, siis ründab rohkemate *ability*'tega pokemon enne.
* Kui pokemonidel on sama hulk *ability*'sid, siis ründab esimesena see, kellel on rohkem *moves* elemente.
* Kui *moves* elementide hulk on sama, siis ründab kõrgema *base_experience* väärtusega pokemon enne.
* Kui ka see on sama, siis järelikult on tegemist sama pokemoniga ja võitlust ei toimu. 

Kui kõik pokemonid on omavahel võidelnud, kirjuta pokemonide edetabel faili. Failis peaks pokemonid olema sorteeritud võitude arvu järgi, sama skooriga pokemonid on tähestiku järjekorras.

All on toodud mõned tulemused erinevate sisenditega. Sisendi puhul esimene arv näitab `offset` väärtust, teine `limit` väärtust URL-is.

**Sisend:** 0, 4

<details>
<summary><strong>Tulemused</strong></summary>

```
venusaur 3
charmander 2
ivysaur 1
bulbasaur 0
```

</details>


**Sisend:** 0, 10:

<details>
<summary><strong>Tulemused</strong></summary>

```
charizard 7
venusaur 7
blastoise 6
charmeleon 5
ivysaur 5
bulbasaur 4
wartortle 4
charmander 3
squirtle 3
caterpie 0
```

</details>


**Sisend:** 0, 100:

<details>
<summary><strong>Tulemused</strong></summary>

```
slowbro 89
tentacruel 88
dodrio 87
kingler 86
nidoking 83
cloyster 82
nidoqueen 82
pidgeot 82
muk 80
blastoise 79
charizard 79
fearow 79
dewgong 77
hypno 77
venusaur 77
arcanine 75
golbat 75
magneton 75
victreebel 75
poliwrath 74
golduck 72
raichu 72
slowpoke 72
sandslash 71
gengar 70
golem 69
dugtrio 68
rapidash 67
arbok 66
ninetales 65
machamp 64
beedrill 63
vileplume 63
wigglytuff 63
farfetchd 58
graveler 58
venomoth 58
persian 57
haunter 56
raticate 56
weepinbell 56
poliwhirl 55
grimer 53
primeape 53
alakazam 52
doduo 52
ivysaur 52
krabby 52
pidgeotto 52
geodude 51
wartortle 51
machoke 50
ponyta 50
clefable 49
onix 49
charmeleon 48
gloom 48
parasect 47
nidorino 46
magnemite 45
nidorina 45
butterfree 44
sandshrew 43
bellsprout 40
growlithe 40
tentacool 40
drowzee 37
spearow 37
venonat 37
bulbasaur 34
zubat 34
psyduck 33
seel 33
oddish 31
gastly 30
paras 30
shellder 30
machop 29
charmander 28
pikachu 28
squirtle 28
mankey 27
ekans 26
poliwag 24
diglett 23
nidoran-m 23
clefairy 22
kadabra 22
nidoran-f 21
pidgey 21
voltorb 20
vulpix 18
jigglypuff 17
meowth 14
rattata 13
weedle 10
kakuna 7
caterpie 3
metapod 3
abra 0
```

</details>


**Sisend:** 69, 100:

<details>
<summary><strong>Tulemused</strong></summary>

```
mewtwo 93
gyarados 91
mew 89
kabutops 88
dragonite 86
snorlax 85
aerodactyl 82
lapras 80
zapdos 80
articuno 78
tauros 78
kangaskhan 77
starmie 77
kingler 76
magneton 76
tentacruel 76
slowbro 75
feraligatr 74
scyther 73
cloyster 70
exeggutor 69
moltres 69
omastar 69
dodrio 68
pinsir 68
electabuzz 67
muk 67
victreebel 67
crobat 66
dewgong 65
rhydon 64
golem 63
vaporeon 63
hypno 62
jolteon 62
seaking 62
dragonair 61
gengar 58
kabuto 57
meganium 56
flareon 55
hitmonlee 55
rapidash 54
slowpoke 54
weepinbell 54
hitmonchan 52
typhlosion 52
weezing 52
croconaw 51
graveler 51
rhyhorn 51
magmar 50
electrode 48
furret 47
seadra 47
ariados 45
noctowl 45
haunter 44
krabby 44
marowak 44
farfetchd 43
geodude 43
onix 43
bayleef 41
jynx 41
tangela 41
exeggcute 40
ponyta 39
mr-mime 37
omanyte 37
doduo 36
goldeen 36
grimer 35
totodile 35
magnemite 34
chikorita 31
cubone 30
dratini 30
quilava 30
lickitung 29
porygon 29
tentacool 29
shellder 26
ledian 25
seel 25
koffing 24
gastly 22
staryu 22
cyndaquil 19
drowzee 19
eevee 19
horsea 16
voltorb 13
spinarak 11
ditto 10
hoothoot 9
ledyba 9
chansey 5
sentret 2
magikarp 0
```

</details>


**Sisend:** 666, 33:

<details>
<summary><strong>Tulemused</strong></summary>

```
barbaracle 28
malamar 27
doublade 26
gogoat 26
tyrantrum 25
pangoro 24
aegislash-shield 23
slurpuff 23
pyroar 22
florges 21
aurorus 20
clawitzer 20
honedge 20
heliolisk 19
aromatisse 18
dragalge 18
furfrou 18
tyrunt 15
meowstic-male 14
litleo 13
skiddo 13
skrelp 13
spritzee 11
espurr 10
pancham 10
floette 9
inkay 9
amaura 8
swirlix 8
binacle 5
clauncher 5
helioptile 5
flabebe 2
```

</details>


