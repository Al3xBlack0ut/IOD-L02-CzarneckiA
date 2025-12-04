3.1 
TAK, testy przestałyby działać poprawnie.

Uzasadnienie:
@BeforeEach uruchamia się przed każdym testem, tworząc za każdym razem nową instancję Calculator
@BeforeAll uruchamia się tylko raz przed wszystkimi testami i wymaga, by metoda była static
To spowodowałoby błąd kompilacji (metoda i pole musiałyby być static). Nawet gdyby zmienić na static, wszystkie testy współdzieliłyby ten sam obiekt, co łamie zasadę izolacji testów - każdy test powinien być niezależny

4.1
test1 - FAILURE
test2 - ERROR

Uzasadnienie:
- FAILURE - występuje gdy asercja nie jest spełniona (assertTrue(false) rzuca AssertionFailedError)
- ERROR - występuje gdy w teście zostaje rzucony nieoczekiwany wyjątek

4.2
org.opentest4j.AssertionFailedError

Gdy przechwyciliśmy ten wyjątek w try-catch, test przeszedł - JUnit wykrywa Failure tylko gdy AssertionFailedError propaguje się poza metodę testową.

5.1
Whitebox

Uzasadnienie:
Analiza ścieżek działania programu wymaga znajomości wewnętrznej struktury kodu (instrukcje warunkowe, pętle, rozgałęzienia). Testowanie whitebox polega na projektowaniu testów na podstawie implementacji i struktury kodu, w przeciwieństwie do blackbox, gdzie testy są projektowane tylko na podstawie specyfikacji.

5.2
4 ścieżki

1. isSubscriber() == true → return 0.0
2. isSubscriber() == false AND loyaltyLevel == SILVER -> return 0.9 * price
3. isSubscriber() == false AND loyaltyLevel == GOLD -> return 0.8 * price
4. isSubscriber() == false AND loyaltyLevel == STANDARD -> return price
