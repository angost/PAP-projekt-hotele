# Projekt PAP

## Temat
System obsługi sieci hoteli.
Aplikacja desktopowa w Javie połączona z bazą danych służąca do obsługi sieci hoteli. Będzie umożliwiać wyszukiwanie pokojów według potrzeb, sprawdzenie stanu dostępności pokoju, rezerwację go. Może też umożliwiać opłaty, wysyłanie ofert i promocji, opinie o obiektach.

## Uruchomienie aplikacji
### Wymagania wstępne
Program uruchamiany jest na systemie **Linux**.\
Aby móc uruchomić aplikację, należy zainstalować:
- **OpenJDK 21**
- **Maven**
- **Docker**

### Uruchomienie
Aby uruchomić aplikację, należy wpisać:
```
sudo bash run.sh
```
Skrypt tworzy i konfiguruje bazę danych, buduje aplikację i uruchamia ją.

## Konfiguracja deweloperska
Baza wykorzystywana w projekcie to **PostgreSQL**.
Dane do logowania do bazy danych:
- host: localhost
- port: 5432
- user: postgres
- password: root
- database: postgres

## Autorzy
    Adrian Murawski
    Angelika Ostrowska
    Jakub Bąba
    Kacper Straszak