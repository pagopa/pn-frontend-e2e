# pn-frontend-e2e

Questo repository contiene i test e2e per le webapps di Piattaforma Notifiche

#

## Setup del progetto

### Requisiti

- **Java 7** o superiore
- **IntelliJ IDEA** (consigliata) o qualsiasi IDE preferito

Per poter eseguire i test bisogna seguire in ordine i seguenti passaggi:

1. Una volta clonato la repo bisogna installare le dipendenze con il comando:
    - `mvn clean install -DskipTests` per macchina windows
    - `./mvnw clean install -DskipTests` per macchina unix like (linux, macos)

_N.B: il parametro `-DskipTests` serve per non eseguire i test durante la fase di installazione delle dipendenze_

2. Una volta installate le dipendenze è consigliato installare i plugin di **IntelliJ** per poter visualizzare i file
   .feature
   correttamente:
    - Cucumber for Java
    - Gherkin

> [!NOTE]
> Se si vuole eseguire i test da riga di comando non è obbligatorio installare i plugin.
> I plugin servono solo ad aggiungere alcune utility come: evidenziare le keywords di Cucumber e poter testare
> direttamente con IntelliJ.

## Esecuzione dei test

### Tramite riga di comando

Per eseguire tutti i test bisogna lanciare il comando:

- `mvn clean test -Dcucumber.filter.tags=@TestSuite` per macchina windows
- `./mvnw clean test -Dcucumber.filter.tags=@TestSuite` per macchina unix like (linux, macos)

Se si vuole eseguire un test specifico bisogna lanciare il comando:

- `mvn clean test -Dcucumber.filter.tags=@nomeTest` per macchina windows
- `./mvnw clean test -Dcucumber.filter.tags=@nomeTest` per macchina unix like (linux, macos)

Per lanciare singolarmente i test di un portale basta modificare il valore del `-Dcucumber.filter.tags` con: @mittente,
@PF, @PG

Inoltre è possibile cambiare il browser di esecuzione e l'ambiente dei test sostituendo i valori di default
che trovi
all'interno del file `test-config.properties` con i seguenti parametri:

```
browser=chrome
environment=test
```

### Tramite IntelliJ con plugin Cucumber

Se si vuole far partire dall'ide di intellij i test usando il **plugin di Cucumber**, quindi i triangolini verdi
contrassegnati accanto ai test, basta creare una nuova run
configuration cambiando il template di
default di `Cucumber Java`.

1. Andare in `Run -> Edit Configurations`
2. Cliccare in basso a sinistra su `Edit configuration templates`
3. Cliccare su `Cucumber Java`
4. Inserire nel campo `VM options` il seguente valore: `-Dbrowser=chrome -Denvironment=test` e configurarli a
   piacimento.
5. Applicare e premere OK

> [!WARNING]
> Se si hanno delle run configuration passate bisogna cancellarle e modificare il template di default seguendo i
> passaggi sopra,
> altrimenti non verranno eseguiti i test con i parametri aggiornati.

