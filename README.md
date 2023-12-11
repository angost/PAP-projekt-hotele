# Projekt PAP

## Temat
System obsługi sieci hoteli.
Aplikacja desktopowa w Javie połączona z bazą danych służąca do obsługi sieci hoteli. Będzie umożliwiać wyszukiwanie pokojów według potrzeb, sprawdzenie stanu dostępności pokoju, rezerwację go. Może też umożliwiać opłaty, wysyłanie ofert i promocji, opinie o obiektach.

## Autorzy
    Adrian Murawski
    Angelika Ostrowska
    Jakub Bąba
    Kacper Straszak

## Konfiguracja bazy danych
Aby móc skonfigurować bazę danych, **Docker** musi być zainstalowany.\
W projekcie wykorzystywana jest baza **PostgreSQL**, uruchamiana poprzez kontener w Dockerze. Aby skonfigurować bazę danych wpisz:
```
sudo bash db_init.sh
```
