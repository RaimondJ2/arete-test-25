# HashCode

### Moodul: `LX/LX07HashCode`

Selles ülesandes tutvume `equals` ja `hashCode` meetoditega.

Lisainfot leiad Javadoc'ist: https://javadoc.pages.taltech.ee/oop/oop-special-methods.html#equals-ja-hashcode

`equals` meetodit olete juba kasutanud (kasvõi sõnede võrdlemiseks). 
Nagu nimigi ütleb, kontrollitakse sellega kahe objekti võrdsust. 
Eeldades, et loodud klass ei kirjuta `equals` meetodit üle, on objektid võrdsed vaid juhul, 
kui nad viitavad samasse kohta mälus. Kui tahta kontrollida ka objekti sisu (väljade väärtust), 
tuleb see meetod ise realiseerida.

Lisaks eelnevalt mainitud `equals`ile realiseerime ka `hashCode` meetodi. 
See tagastab täisarvulise väärtuse ning on kasutusel räsi arvutamisel. Räsi kasutatakse näiteks `HashMap` ja `HashSet` andmehulkades.


### Meetodite realiseerimisel hoia meeles:

| definitsioon   | seletus                                                                                                              |
|----------------|----------------------------------------------------------------------------------------------------------------------|
| reflektiivsus  | `equals()` puhul peab objekt iseendaga olema võrdne (`obj.equals(obj) == true`)                                      |
| sümmeetrilisus | `x.equals(y) == y.equals(x)`                                                                                         |
| transitiivsus  | if `x.equals(y)` and `y.equals(z)` then `x.equals(z)`                                                                |
| järjepidevus   | `equals()` ja `hashCode()` väärtused tohivad muutuda ainult juhul, kui üks neis kontrollitavatest väärtustest muutub |
|                | objektid, mis on võrdsed (`equals()` meetodi järgi), peavad tagastama sama räsikoodi.                                |
| kollisioonid   | mittevõrdsed objektid võivad tagastada sama räsikoodi.                                                               |

Erilist tähelepanu tuleks pöörata `hashCode()` meetodile, sest selle realiseerimisel rikutakse sageli ülaltoodud reegleid.
<br/><br/>
## Näited

Loome klassi `Point`, mis kujutab punkti tasandil (x- ja y-koordinaadiga). Klassi konstruktor on `Point(int x, int y)`. 
Oletame, et meil on list punktidega, kuhu me ei taha lisada korduvaid elemente. 
Punkt `(1, 2)` on sama sõltumata sellest, kas tegemist on sama või erineva objektiga (kus x ja y väärtused on samad).

#### Seega, kui meil on järgnev kood:
```java
List<Point> selectedPoints = new ArrayList<>();
for (Point p : points) {
    if (!selectedPoints.contains(p)) {
        selectedPoints.add(p);
    }
}
```
<br/><br/>
#### `contains()` meetodi seletus Javadoc'is
```
Returns true if this list contains the specified element. 
More formally, returns true if and only if this list contains 
at least one element e such that Objects.equals(o, e).
```
Definitsioonist lähtuvalt teame, et `contains()` meetod kasutab `equals()` meetodit.
<br/><br/>

#### Punktide kattuvuse kontrolliks (punktide koordinaadid on võrdsed), kirjutame järgmise koodi:
```java
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        //  kas parameetriga kaasa antud objekt, on täpselt sama (viitab mälus samma kohta), mis esimene objekt.
        if (this == o) return true;
        // kas etteantud parameeter eksisteerib või saadud objekti klass ei ole sama mis antud objektil (`Point`).
        if (o == null || getClass() != o.getClass()) return false;
        // etteantud objekt teisendatakse (cast) `Point` objektiks.
        Point point = (Point) o;
        // kontroll, kas x ja y väärtused on võrdsed.
        return x == point.x && y == point.y; 
    }
}
```
<br/><br/>
#### Meie eelnev koodinäide toimib samade väärtustega punktide välja filtreerimiseks.*

```java
Point p1 = new Point(1, 2);
Point p2 = new Point(1, 3);
Point p3 = new Point(1, 2);
List<Point> points = List.of(p1, p2, p3);

List<Point> selectedPoints = new ArrayList<>();
for (Point p : points) {
    if (!selectedPoints.contains(p)) {
        selectedPoints.add(p);
    }
}
System.out.println(selectedPoints);
```
<br/><br/>
#### Annab tulemuseks (toString() meetod on realiseeritud vastavalt):
```
[Point{x=1, y=2}, Point{x=1, y=3}]
```
<br/><br/>
#### Lisades järgmise koodi:
```java
Set<Point> pointSet = new HashSet<>();
for (Point p : points) {
    pointSet.add(p);
}
System.out.println(pointSet);
```
<br/><br/>
#### Saame tulemuseks:
```
[Point{x=1, y=3}, Point{x=1, y=2}, Point{x=1, y=2}]
```

Saadud tulemus ei ole meile sobival kujul, selleks et seda õigele kujule saada, realiseerime `hashCode()` meetodi. 
IntelliJ oskab seda ise genereerida. _Alt_ + _insert_ klahvikombinatsioon avab menüü, kus on võimalik valida vajalikud väljad ning nendega `equals` ja `hashCode` realiseerida.
<br/><br/>
## Ülesanne

### Point

Realiseeri `Point` klassi `hashCode` nii, et viimane näide (`HashSet`-iga) töötaks korrektselt (tulemusse peaks jääma kaks punkti).

### Person

Tuleb luua klass `Person`, mida saab kasutada telefoniraamatu jaoks (`Map<Person, Integer> numbers = new HashMap<>();`)

Selleks, et tagada HashMap'i korrektne toimimine, peavad nii `equals()` kui ka `hashCode()` olema implementeeritud.
Antud ülesandes lihtsustame telefoniraamatu ülesehitust (alati ei pea täpselt 100% kattuv vaste olema), seega täpsustame siinkohal reeglid:

- Keskmise nime puudumine ei mõjuta objektide võrdsust, ühel objektil on nimeks `Ago CoolGuy Luberg` ja teisel `Ago Luberg` (keskmine nimi on tühi `""`)
- Eesnimi võib esineda ka eesnime tähena, ühel objektil on eesnimeks `Ago` ja teisel vaid selle esimene täht `A`
- Vanust arvestatakse kümneaastase sammuna, vanused 20, 21, .. 29 on kõik võrdsed, 29 ja 30 on aga erinevad.

Sisendi puhul on garanteeritud:
- eesnimi ja perenimi on alati sõned, millel on vähemalt 1 täht
- keskmine nimi on alati sõne, mis võib olla ka tühisõne
- vanus on alati täisarv vahemikus 0 kuni 99 (kaasa arvatud)

## Boonusharjutus (väike)

Realiseeri `hashCode()` selliselt, et erineva vanusevahemiku puhul tagastuks erinev räsikood.
Kuigi `hashCode()` reegel ütleb, et erineva võrdsusega objektide puhul võib räsikood olla ka sama, 
siis `HashMap` efektiivsuse seisukohast oleks hea, kui selliseid kollisioone esineks võimalikult vähe. 

Räsi eeliseks on, et `Map` objektist oleks võtmele vastava väärtuse leidmine võimalikult kiire. 
Selleks võetakse võtme objekti räsi ja otsitakse sellele vastet. 
Otsing on kõige efektiivsem siis kui igal objektil on erinev räsi. 
Kui aga kõikide objektide puhul on räsi sama, siis lõpuks tuleb `containsKey()` või `get()` meetodi puhul kõik võtmeobjektid läbi käia 
ning otsingu efektiivsus on sama mis listi puhul (keerukus muutub O(1) pealt O(n) peale).

All on ka mõned koodinäited.
<br/><br/>
## Mall

Klass: `ee.taltech.iti0202.hashcode.Point`
```java
package ee.taltech.iti0202.hashcode;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```
<br/><br/>
Klass: `ee.taltech.iti0202.hashcode.Person`
```java
package ee.taltech.iti0202.hashcode;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private int age;

    public Person(String firstName, String middleName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
    }
}
```
<br/><br/>
Klass: `ee.taltech.iti0202.hashcode.HashCodeMain`
```java
package ee.taltech.iti0202.hashcode;

import java.util.HashMap;
import java.util.Map;

class HashCodeMain {
    public static void main(String[] args) {

        //
        Map<Person, Integer> numbers = new HashMap<>();
        Person person1 = new Person("Ago", "von", "Luberg", 20);
        Person person2 = new Person("Ago", "von", "Luberg", 20);
        Person person3 = new Person("Ago", "", "Luberg", 20);
        Person person4 = new Person("Ago", "blah", "Luberg", 20);
        Person person5 = new Person("Ago", "von", "Luberg", 25);
        Person person6 = new Person("Ago", "von", "Luberg", 35);
        numbers.put(person1, 123);
        numbers.put(person2, 345);
        numbers.put(person3, 3);
        numbers.put(person4, 6);
        numbers.put(person5, 7);
        numbers.put(person6, 8);
        System.out.println(person1.equals(person3));  // true
        System.out.println(person1.equals(person4));  // false
        System.out.println(person1.equals(person5));  // true
        System.out.println(person1.equals(person6));  // false
        System.out.println(numbers);
        // {Person{firstName='Ago', lastName='Luberg', middleName='von', age=35}=8, Person{firstName='Ago', lastName='Luberg', middleName='von', age=20}=7, Person{firstName='Ago', lastName='Luberg', middleName='blah', age=20}=6}
    }
}
```