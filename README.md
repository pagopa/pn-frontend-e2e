# pn-frontend-e2e

Questo repository contiene i test e2e per le webapps di Piattaforma Notifiche

## Setup del progetto

### Requisiti

- **Java 7** o superiore
- **IntelliJ IDEA** (consigliata) o qualsiasi IDE preferito

Per poter eseguire i test bisogna seguire in ordine i seguenti passaggi:

1. Una volta clonato la repo bisogna installare le dipendenze con il comando:
    - `mvn clean install -DskipTests` per macchina windows
    - `./mvnw clean install -DskipTests` per macchina unix like (linux, macos)

_N.B: il parametro `-DskipTests` serve per non eseguire i test durante la fase di installazione delle dipendenze_

2. Una volta installate le dipendenze bisogna installare i plugin di intellij per poter visualizzare i file .feature
   correttamente:
    - Cucumber for Java
    - Gherkin

### Esecuzione dei test

Per eseguire tutti i test bisogna lanciare il comando:

- `mvn clean test -Dcucumber.filter.tags=@TestSuite` per macchina windows
- `./mvnw clean test -Dcucumber.filter.tags=@TestSuite` per macchina unix like (linux, macos)

Se si vuole eseguire un test specifico bisogna lanciare il comando:

- `mvn clean test -Dcucumber.filter.tags=@nomeTest` per macchina windows
- `./mvnw clean test -Dcucumber.filter.tags=@nomeTest` per macchina unix like (linux, macos)

Per lanciare singolarmente i test di un portale basta modificare il valore del `-Dcucumber.filter.tags` con: @mittente,
@PF, @PG

Inoltre è possibile cambiare il browser di esecuzione e l'ambiente di esecuzione test sostituendo i valori di default
che trovi
all'interno del file `test-config.properties` con i seguenti parametri:

```
browser=chrome
environment=test
```

### Opzionale

Se si vuole far partire dall'ide di intellij i test basta creare una nuova run configuration cambiando il template di
default con `Cucumber Java`.

1. Andare in `Run -> Edit Configurations`
2. Cliccare in basso a sinistra su `Edit configuration templates`
3. Cliccare su `Cucumber Java`
4. Inserire nel campo `VM options` il seguente valore: `-Dbrowser=chrome -Denvironment=test` e configurarli a
   piacimento.
5. Applicare e premere OK

_N.B: Se si ha delle run configuration passate bisogna cancellarle e crearne una nuova seguendo i passaggi sopra,
altrimenti
non verranno eseguiti i test._


