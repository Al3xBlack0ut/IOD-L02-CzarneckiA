2.1 Aby zweryfikować poprawność interakcji metody loadExpenses z obiektem zastępczym, należy rozszerzyć klasę MyDatabase o mechanizm śledzenia wywołań metod.

5.1 Tak, kolejność ma znaczenie.
Mockito bierze ostatnią pasującą regułę. Jak dałem anyString() na końcu, to nadpisało wszystko - dlatego dla "Home" i "Car" też zwracało pustą listę zamiast konkretnych wydatków.