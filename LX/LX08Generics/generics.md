# Generics

Selles ülesandes tutvume Java geneerikutega (*generics*). Sellest on natuke juttu javadocis: https://javadoc.pages.taltech.ee/oop/oop-generics.html

Samuti võib vaadata Orace'i enda dokumentatsiooni: https://docs.oracle.com/javase/tutorial/java/generics/index.html

Te olete kindlasti oma koodis neid juba kasutanud. Proovime siinse ülesandega natuke avada tausta, mismoodi need asjad toimivad.

Alustame näitest. Me tahame luua objekti, mille sees saab hoida mingid väärtust (objekti). Meil tekib kohe küsimus, et milliseid väärtusi (objekte) me seal hoida soovime. Kui oleme aru saanud, et Javas kõik klassid laiendavad üldist klassi `Object`, siis võime otsustada lahendada olukorra nii:

```java
public class Box {
    private Object object;

    public Box(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
```

Kui meil on näiteks looma klass (`Animal`):
```java
public class Animal {
    public void eat() {
        System.out.println("eat");
    }
}
```

saaksime luua uue "kasti" järgnevalt:

```java
Animal animal = new Animal();
Box box = new Box(animal);
```
Meil on kast, selle sisse panime looma objekti.

Aga nüüd tahaks sealt kastist objekti võtta ja sellega midagi teha. Kuna `getObject()` meetod tagastab `Object` tüüpi objekti, peame teisendama:

```java
((Animal)box.getObject()).eat();
```

See pole väga mugav ning võib tekitada erinevaid vigu (mida tugeva tüübi korral kompileerimine saaks juba vältida).

Üks variant, et me teeme eraldi `AnimalBox`, ja seal oleks meetod `public Animal getAnimal()`. Aga mis siis, kui me tahaksime kastis hoida autot või arve või sõne? Me peaks selliselt iga erineva andmetüübi jaoks erineva kasti tegema. Aga õnneks Javas on selle jaoks lahendus olema - geneerikud.

Toome nüüd sama näite, kus loome kasti, aga geneerilise andmetüübiga:

```java
public class GenericsBox<T> {
    private T t;

    public GenericsBox(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}
```

Klassi deklaratsioonis on klassi nime järgi "nokkade" (`<` ja `>`) vahel `T`. See tähistab andmetüüpi. Aga kuna seda andmetüüpi ei ole teada, siis tähistatakse see tähega `T` (võib ka muu täht olla). See tüüp tuleb arendajal endal määrata, kui ta hakkab kasutama `GenericsBox` klassi. Äkki tuleb tuttav ette `List<Integer>`? Selles näites T = `Integer`.

Nüüd kogu klassi ulatuses `T` tähistab seda sama tüüpi (klassi). Justkui `T` asemele võib mõelda konkreetse tüübi, näiteks `String`. 

Selle geneerilise kasti kasutamise koodinäide:

```java
GenericsBox<Animal> animalBox = new GenericsBox<>(animal);
animalBox.get().eat();
```
Nagu näha, kuna me lõime andmetüübi juba `Animal` tüübiga, siis `get()` meetod tagastab täpselt sama andmetüübi ning me ei pea enam teisendama hakkama, vaid saame kohe `eat()` meetodi välja kutsuda. Samamoodi tekib sellise koodi kasutamisel vähem vigu jooksutamise ajal (osa vigu selgub juba kompileerimisel).

## Ülesanne

Kaust `LX/LX08Generics`

Selles ülesandes on tegelikult kaks osa. Mõlemad annavad punkte. Õppejõud võib pisteliselt koodi üle vaadata, et lahendus oleks mõistlik.

Mõlemad lahendused tuleb panna pakki `ee.taltech.iti0202.generics`

### Ülesanne A - `Hash2Map`

Luua klass `Hash2Map`, mille mõte on see, et saaks n-ö kahemõõtmelist map'i hoida. Kasutades java sisseehitatud Map-e.

Vaata allolevat näidet:

```java
Hash2Map<String, String, Integer> nameCountByCountry = new Hash2Map<>();
System.out.println(nameCountByCountry.getKeys());  // []
nameCountByCountry.put("Estonia", "Paul", 12);
nameCountByCountry.put("Estonia", "Mari", 17);
nameCountByCountry.put("Finland", "Matti", 16);
nameCountByCountry.put("Finland", "Itti", 13);
nameCountByCountry.put("Latvia", "Reins", 10);
nameCountByCountry.put("Latvia", "Kaira", 17);

System.out.println(nameCountByCountry.getKeys()); // [Latvia, Finland, Estonia]
System.out.println(nameCountByCountry.getKeys("Estonia")); // [Paul, Mari]
System.out.println(nameCountByCountry.get("Finland", "Matti"));  //16
System.out.println(nameCountByCountry.get("Finland", "Reins"));  //null
System.out.println(nameCountByCountry.getKeys("Sweden"));  // []
System.out.println(nameCountByCountry.getAllValues());  // [10, 17, 13, 16, 12, 17]
```

Nõuded meetoditele:

- `void put(x, y, z)` - lisab võtmed ja väärtuse.
- `getKeys()` - tagastab kõige esimese taseme võtmed `Set`-ina (järjekord pole tähtis); Kui ühtegi elementi pole, tagastab tühja Seti.
- `getKeys(key)` - tagastab kõik teise taseme võtmed `Set`-ina vastavalt ette antud võtmele. Ehk näites esimese taseme väärtus määrab ära riigi, siis see meetod tagastab kõik selle riigi nimed. Kui sellist võtit ei leidu, tagastab tühja `Set`-i.
- `get(x, y)` - tagastab väärtuse vastavalt esimese ja teise taseme võtmele. Näites riigi nime ja inimese nime järgi nende koguse. Kui sellist võtit pole (kas x või y), tagastab `null`.
- `getMap(x)` - tagastab teise taseme mapi. Näites tagastab vastavalt riigi nimele nimede-koguste mapi. Kui sellist võtit pole, tagastab tühja mapi.
- `getAllValues()` - tagastab listi kõikidest väärtustest (kordused jäävad sisse), järjekord pole oluline. Kui ühtegi elementi pole, tagastab tühja listi.

### Ülesanne B - `HashList`

Selles ülesandes tuleb realiseerida uus andmestruktuur `HashList`. Kuigi tegelikult uut Listi tehes oleks õige laiendada `List` liidest, siis me seda siin ei hakka tegema (sealt tekib kohe väga palju meetodeid). 

`ArrayList`-i puhul töötab `contains()` meetod nii, et käib kõik elemendid läbi ja otsib sedasi otsitavat elementi. Umbes nii:

```java
for (int i = start; i < end; i++) {
    if (objectToLookFor.equals(elements[i])) {
        return true;
    }
}
return false;
```

Meie aga tahaks, et `contains()` töötaks `HashMap`-ile sarnaselt.
Üldiselt ongi tegemist `HashMap`-i implementatsiooniga, aga see ei tohi olla tehtud java sisseehitatud andmetüüpidega (tuleks kasutada näiteks massiivi `[]` mitte List-i).

Kui massiiviga map'i realiseerimine tundub tülikas ja keeruline, siis võib siin kasutada ka mingit muud lahendust (kasvõi sisseehitatud Map'i/Set'i), aga siis saab selle osa eest vähem punkte.

Arraylist on sisult isesuurenev massiiv. Arraylisti töötamisest võib lugeda siit: https://www.geeksforgeeks.org/internal-working-of-arraylist-in-java/

HashMap töötamisest võib lugeda siit: https://www.geeksforgeeks.org/internal-working-of-hashmap-java/

`contains()` jaoks oleks mõistlik teha nii:

- teha wrapper klass, kus hoitakse andmeid. Täpsemalt, selles võiks hoida väärtust ennast ja viidet järgmisele objektile (`next`). Algselt `next = null`.
- need wrapper objektid tuleks hoida massiivis
- massiivi pikkus tuleks algselt ära määrata.
- kui lisatakse uus objekt, siis tuleb see panna wrapperisse
- objekti hashCode() väärtuse järgi arvutatakse massiivi indeks. Kuna hashCode annab suure väärtuse, siis tuleks sellest võtta moodul vastavalt massiivi suurusele (näiteks hashCode võib olla 1787, aga kui massiivi suurus on 10, siis tulebki võtta 1787 % 10 ja lõpuks läheb see wrapper objekt positsioonile 7)
- kui sellel positsioonil juba on wrapper objekt, siis tuleb panna uus wrapper objekt selle objekti `next` väärtuse alla (ehk siis next viitab järgmisele objektile, sarnaselt nagu LinkedList töötab). 
- selliselt võib next-ahel päris pikk olla. Kui näiteks 10 objekti järjest annavad samas hashCode'i, aga pole päris sama väärtusega, tulebki sama indeksi peale 10 wrapper objekti, mis igaüks viitab järgmisele (tekib n-ö LinkedList nendest elementidest)
- contains() otsib hashCode'i järgi üles positsiooni ja käib selle potsisiooni taga olevad kõik wrapperid läbi (next-ahelaga), kun leitakse objekt, mille `equals()` võrdlus otsitava objektiga annab `true`.
- arvestada tuleb ka elemendi eemaldamisega (next-ahelast tuleb objekt välja võtta)

On vaja realiseerida meetodid:

- `void add(element)` - lisab elemendi listi
- `public get(int index)` - tagastab elemendi vastavalt positsioonile
- `public int size()` - tagastab listi pikkuse
- `public remove(int index)` - eemaldab elemendi vastavalt indeksile ja tagastab eemaldatud elemendi. Kui indeks ei ole sobiv, annab `IndexOutOfBoundsException` erindi
- `public void addAll(list)` - aktsepteerib listi kas sama tüüpi või sama tüübi alamtüüpi elementidega ja lisab need listi lõppu
- `public boolean contains(element)` - tagastab true/false vastavalt, kas element on listis või mitte. Eeldab, et elementidel on `hashCode` ja `equals()` korrektselt realiseeritud. Ehk siis element leidub, kui listi on element, mille puhul `element.equals(listElement[i])`.

**Vihje**, indeksi kontrollimiseks võib kasutada järgmist meetodit: `Objects.checkIndex(index, numberOfElements)`. See tõstatab vajaliku erindi ise. Ehk siis näiteks get(int index) meetodi esimene rida ongi selline.

Siin üks võimalik kood katsetamiseks:

```java
class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}
HashList<Animal> animalHashList = new HashList<>();
Animal animal = new Animal();
Dog dog1 = new Dog();
Dog dog2 = new Dog();
Cat cat1 = new Cat();
Cat cat2 = new Cat();
List<Dog> dogs = new ArrayList<>();
dogs.add(dog1);
dogs.add(dog2);
List<Cat> cats = new ArrayList<>(List.of(cat1, cat2));
animalHashList.add(animal);
System.out.println(animalHashList.size()); // 1
animalHashList.addAll(dogs);
animalHashList.addAll(cats);
System.out.println(animalHashList.size()); // 5
System.out.println(animalHashList.get(2));  // Dog@b4c966a
animalHashList.remove(2);
System.out.println(animalHashList.get(2));  // Cat@2f4d3709
System.out.println(animalHashList.contains(dog1)); // true
System.out.println(animalHashList.contains(dog2)); // false
```

Kuidas testida, kas `contains()` on efektiivne?

- Loo `ArrayList` ja lisa sinna näiteks 100000 arvu (0 - 99999)
- Loo `HashList` ja lisa sinna näiteks 100000 arvu (0 - 99999)
- Otsi `ArrayList`-ist näiteks viimast elementi 1000 korda (list.contains(99999))
- Otsi `HashList`-ist näiteks viimast elementi 1000 korda (list.contains(99999))
- Võrdle neid otsinguid näiteks `System.nanoTime()`-iga (enne tsüklit märgid aja muutujasse, pärast tsüklit võrdled salvestatud aega hetkeajaga - vahe on kulunud aeg).
- `HashList`-i tulemus peaks olema vähemalt 2-4 korda kiirem.
