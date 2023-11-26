***4.5/5 ( 90% )***
# Comment from Teacher:
```
Tak vseobecne, komentare maju byt na vysvetlenie tazsie pochopitelneho kodu. Komentovat v podstate kazdy riadkok nema velmi vyznam ak je absolutne jasne co sa robi, napr
28 // Begin drawing a polygon
29 this.openPolygon();
36 // Finish the polygon
37 this.closePolygon();
112 // Calculate the square of 'q'
113 double a = Math.pow(q, 2);
108 // Check if 'p' is a prime number using the isPrime method
109 if (this.isPrime(p) == true) {
...

Štvorce (1b)

67 newSize = newSize * 0.70;
0.7 != sqrt(2)/2
Pouzivanie takychto bulharskych konstant urcite neodporucam, priklad
100×0.7^10 = 2.824 752 49
100×(sqrt(2)/2)^10 = 3.125
celkom rozdiel pri desiatom stvorci

0.5b

inak ok

Goldbachova hypotéza (2b)

Ak by sme premennu counter v countDivisors nazvali countOfDivisors potrebovali by sme este komentare?

Sice je v zadani napisane, ze efektivnost netreba riesit, ale neda sa zistit, ci je cislo prvocislo aj lahsie ako zistenim, ci ma prave 2 delitelov? Spytam sa inak, co tak zistit, ci nema tretieho?

107 for (int p = 0; p <= n; p++) {
Preco ideme p od 0 ak mame hladat prvocislo? nestaci ist od 2?

113 double a = Math.pow(q, 2);
email od Viktora:
Počas prezerania Vašich riešení domácich zadaní som si spomenul na e-mail, ktorý sme dostali aj my ako prváci na predmete PAZ1a od nášho prednášajúceho a cvičiaceho, Františka Galčíka. Keďže by som tento e-mail nevedel lepšie sformulovať, dovoľte mi, aby som Vám preposlal jeho úryvok.
„Po minulé roky som už videl všeličo, ale také posadnutie Math.pow tu ešte nebolo. Paradoxne aj napriek tomu, že súčasťou úloh cvičení tretieho týždňa bol informačný text o Math.pow (prosím prečítajte si ho s porozumením). Na doplnenie: celočíselná aritmetika (hocijaké matematické operácie) je obmedzená rozsahom prípustných hodnôt pre jednotlivé typy, no aritmetika s číslami s desatinnou čiarkou je okrem rozsahu obmedzená aj presnosťou. V uvedenom texte je ukážka, kedy celočíselná aritmetika vyráta korektný výsledok, no aritmetika s číslami s desatinnou čiarkou nie. Ak si pozriete na parametre a návratový typ metódy Math.pow, jasne vidieť, že Math.pow patrí do sveta aritmetiky s číslami s desatinnou čiarkou. A toto je svet plný ilúzie, nepresností a brutálnych matematických aproximačných fínt (často vychádzajúcich z Taylorových radov a rozvojov). Áno, môžete namietať, že vaše riešenia 3. sady domácich zadaní boli korektné. A máte pravdu, lebo v tomto drobnom svete domáceho zadania a použitých testovacích vstupov k nepresnostiam nedošlo. No ak sa naučíte používať aritmetiku s desatinnými číslami na problémy zo sveta celočíselnej aritmetiky, riskujete zlozvyk, na ktorý raz môže niekto poriadne doplatiť. Totiž nepresnosť sa môže neukázať pri testovaní, ale prejaví sa až pri nasadení - a vtedy už môže ísť o obrovské peniaze (bankové, biznis aplikácie, vesmírne misie) alebo život (riadenie lietadiel, letovej prevádzky, či medicínskych zariadení). Verím, že zvrhlosti ako nahradenie výpočtu druhej mocniny intovskej premennej x (teda x*x) výrazom (int)(Math.pow(x, 2)) vo vašich riešeniach už neuvidíme.“

Este by sa dal jeden komentar k zlozitosti a to ten, ze nebolo by rychlejsie hladat najprv mozne q a potom overovat, ci vyratane p je prvocislo?

Okrem math pow ok

Kombinované číslo (2b)

132 // Store the first number
133 int a = n;
134 // Store the second number
135 int b = m;
136 // Store the combined result
137 int ans = 0;
138 // Counter for digit position
139 int counter = 0;
Ok ale co tak pomenovat premenne tak ako ich pomenuvavame v komentaroch? Napr
int firstNumber = n;
int secondNumber = m;
int combinedNumberToReturn = 0;
int digitPosition = 0;

(int) Math.pow(10, counter)
- zase email

Na zamyslenie, vo while riesis, kym su obe cisla > 0. Co tak tam dat a zaroven a teda skoncit hned ked je jedno 0 a za while cyklus napisat:
ans = ans + (a * (int) Math.pow(10, counter));
ans = ans + (b * (int) Math.pow(10, counter));
(Math.pow nechavam len koli rychlosti a mojej lenivosti :D ale inak by som ho nahradil pomocnou metodou s nazvom addZerosToNumber)

inak ok 
```
