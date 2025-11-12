# System aukcyjny

## Wprowadzenie

Specyfikacja wymagań funkcjonalnych w ramach informatyzacji procesu sprzedaży produktów w oparciu o mechanizm aukcyjny.

## Procesy biznesowe

---
<a id="bc1"></a>

### BC1: Sprzedaż aukcyjna

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Opis:** Proces biznesowy opisujący sprzedaż za pomocą mechanizmu aukcyjnego.

**Scenariusz główny:**
1. [Sprzedający](#ac1) wystawia produkt na aukcję. ([UC1](#uc1))
2. [Kupujący](#ac2) oferuje kwotę za produkt wyższą od aktualnie najwyższej oferty. ([UC2](#uc2), [BR1](#br1))
3. [Kupujący](#ac2) wygrywa aukcję. ([BR2](#br2))
4. [Kupujący](#ac2) przekazuje należność Sprzedającemu. ([UC3](#uc3))
5. [Sprzedający](#ac1) przekazuje produkt Kupującemu. ([UC4](#uc4))

**Scenariusze alternatywne:**

2.A. Oferta Kupującego została przebita, a [Kupujący](#ac2) pragnie przebić aktualnie najwyższą ofertę.

* 2.A.1. Przejdź do kroku 2.

3.A. Czas aukcji upłynął i [Kupujący](#ac2) przegrał aukcję. ([BR2](#br2))

* 3.A.1. Koniec przypadku użycia.

4.A. [Kupujący](#ac2) nie opłacił wygranej aukcji w terminie. ([BR3](#br3))

* 4.A.1. System oznacza transakcję jako nieopłaconą i informuje [Sprzedającego](#ac1) o braku płatności.
* 4.A.2. Koniec procesu sprzedaży tej aukcji.

---

## Aktorzy

<a id="ac1"></a>

### AC1: Sprzedający

Osoba oferująca towar na aukcji.

<a id="ac2"></a>

### AC2: Kupujący

Osoba chcąca zakupić produkt na aukcji.

## Przypadki użycia poziomu użytkownika

### Aktorzy i ich cele

[Sprzedający](#ac1):
* [UC1](#uc1): Wystawienie produktu na aukcję
* [UC4](#uc4): Potwierdzenie przekazania produktu

[Kupujący](#ac2):
* [UC2](#uc2): Złożenie oferty w aukcji
* [UC3](#uc3): Opłacenie wygranej aukcji

#### Dodatkowe cele aktorów

[Sprzedający](#ac1):
* UC5: Edycja aukcji przed pierwszą ofertą
* UC6: Anulowanie aukcji przed pierwszą ofertą

[Kupujący](#ac2):
* UC7: Przeglądanie i wyszukiwanie aukcji
* UC8: Wyświetlenie szczegółów aukcji
* UC9: Ustawienie automatycznej oferty (maksymalnej)

---
<a id="uc1"></a>

### UC1: Wystawienie produktu na aukcję

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) zgłasza do systemu chęć wystawienia produktu na aukcję.
2. System prosi o podanie danych produktu i ceny wywoławczej.
3. [Sprzedający](#ac1) podaje dane produktu oraz cenę wywoławczą.
4. System weryfikuje poprawność danych.
5. System informuje o pomyślnym wystawieniu produktu na aukcję.

**Scenariusze alternatywne:**

4.A. Podano niepoprawne lub niekompletne dane produktu.
* 4.A.1. System informuje o błędnie podanych danych.
* 4.A.2. Przejdź do kroku 2.

---
<a id="uc2"></a>

### UC2: Złożenie oferty w aukcji

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) wybiera trwającą aukcję, w której chce złożyć ofertę.
2. System prezentuje szczegóły aukcji, w tym aktualnie najwyższą ofertę, minimalną dopuszczalną ofertę i czas do zakończenia. ([BR1](#br1))
3. [Kupujący](#ac2) wprowadza proponowaną kwotę zakupu.
4. System weryfikuje poprawność kwoty względem wymagań licytacji. ([BR1](#br1))
5. System rejestruje ofertę i aktualizuje najwyższą ofertę w aukcji.
6. System informuje [Kupującego](#ac2) o przyjęciu oferty oraz o tym, czy jest ona w tym momencie najwyższa.

**Scenariusze alternatywne:**

4.A. Kwota oferty jest niższa niż dozwolona minimalna wartość przebicia.
* 4.A.1. System odrzuca ofertę i informuje o minimalnej dopuszczalnej kwocie.
* 4.A.2. Przejdź do kroku 3.

5.A. W trakcie składania oferty inny [Kupujący](#ac2) złożył wyższą ofertę.
* 5.A.1. System zapisuje ofertę, oznacza ją jako nie-najwyższą i informuje o aktualnym statusie.
* 5.A.2. [Kupujący](#ac2) może ponowić próbę (przejdź do kroku 3).

1.A. Aukcja zakończyła się przed złożeniem oferty.
* 1.A.1. System informuje o zakończeniu aukcji. Koniec przypadku użycia.

---
<a id="uc3"></a>

### UC3: Opłacenie wygranej aukcji

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. Po zakończeniu aukcji system informuje [Kupującego](#ac2) o wygranej oraz kwocie do zapłaty. ([BR2](#br2))
2. [Kupujący](#ac2) inicjuje płatność za wygraną aukcję.
3. System przekierowuje do operatora płatności i przekazuje dane transakcji.
4. [Kupujący](#ac2) autoryzuje płatność u operatora płatności.
5. System otrzymuje potwierdzenie powodzenia płatności od operatora i rejestruje płatność.
6. System informuje [Sprzedającego](#ac1) o opłaceniu transakcji oraz udostępnia dane do realizacji przekazania produktu.

**Scenariusze alternatywne:**

4.A. Płatność odrzucona przez operatora.
* 4.A.1. System informuje o niepowodzeniu płatności i umożliwia ponowienie.
* 4.A.2. Przejdź do kroku 2.

5.A. Brak potwierdzenia płatności w oczekiwanym czasie.
* 5.A.1. System oznacza płatność jako oczekującą i wyświetla informację o opóźnieniu.

2.A. [Kupujący](#ac2) przekroczył maksymalny czas na opłacenie.
* 2.A.1. System blokuje możliwość opłacenia i oznacza transakcję zgodnie z polityką. ([BR3](#br3))

---
<a id="uc4"></a>

### UC4: Potwierdzenie przekazania produktu

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) po otrzymaniu informacji o płatności wprowadza w systemie potwierdzenie przekazania produktu (np. dane przesyłki/odbioru osobistego).
2. System zapisuje potwierdzenie i udostępnia [Kupującemu](#ac2) dane przekazania/śledzenia.

**Scenariusze alternatywne:**

1.A. Brak odnotowanej płatności za aukcję.
* 1.A.1. System uniemożliwia potwierdzenie przekazania i informuje o braku płatności. ([UC3](#uc3))

---

## Obiekty biznesowe (inaczej obiekty dziedzinowe lub informatyczne)

### BO1: Aukcja

Aukcja jest formą zawierania transakcji kupna-sprzedaży, w której Sprzedający określa cenę wywoławczą produktu, natomiast Kupujący mogą oferować własną ofertę zakupu każdorazowo proponując kwotę wyższą od aktualnie oferowanej kwoty. Aukcja kończy się po upływie określonego czasu. Jeśli złożona została co najmniej jedna oferta zakupu, produkt nabywa ten Kupujący, który zaproponował najwyższą kwotę.

### BO2: Produkt

Fizyczny lub cyfrowy obiekt, który ma zostać sprzedany w ramach aukcji.

### BO3: Oferta

Deklaracja ceny złożona przez [Kupującego](#ac2) w ramach konkretnej aukcji. Zawiera m.in. kwotę i znacznik czasu.

### BO4: Płatność

Rekord odzwierciedlający opłacenie wygranej aukcji przez [Kupującego](#ac2), zawierający status i identyfikator transakcji operatora płatności.

### BO5: Przesyłka

Dane potwierdzenia przekazania produktu przez [Sprzedającego](#ac1), np. numer nadania, przewoźnik, forma przekazania.

## Reguły biznesowe

<a id="br1"></a>

### BR1: Złożenie oferty

Złożenie oferty wymaga zaproponowania kwoty wyższej niż aktualnie oferowana o minimum 1,00 PLN.

<a id="br2"></a>

### BR2: Rozstrzygnięcie aukcji

Aukcję wygrywa ten z [Kupujący](#ac2)ch, który w momencie jej zakończenia (upłynięcia czasu) złożył najwyższą ofertę.

<a id="br3"></a>

### BR3: Termin opłacenia wygranej aukcji

[Kupujący](#ac2) powinien opłacić wygraną aukcję w określonym terminie (np. 48 godzin) od momentu zakończenia aukcji; po przekroczeniu terminu transakcja może zostać oznaczona jako nieopłacona.

## Macierz CRUD(L)

| Przypadek użycia                              | Aukcja | Produkt | Oferta | Płatność | Przesyłka |
| --------------------------------------------- | :----: | :-----: | :----: | :------: | :-------: |
| UC1: Wystawienie produktu na aukcję           |   C    |   C     |   R    |    -     |     -     |
| UC2: Złożenie oferty w aukcji                 |   R    |   R     |   C    |    -     |     -     |
| UC3: Opłacenie wygranej aukcji                |   R    |   R     |   R    |    C     |     -     |
| UC4: Potwierdzenie przekazania produktu       |   R    |   R     |   R    |    R     |     C     |

Legenda: C – Create, R – Read, U – Update, D – Delete, L – List (brak wpisu = nie dotyczy)
