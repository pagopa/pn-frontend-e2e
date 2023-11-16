# pn-frontend-e2e
Questo repository contiene i test e2e per le webapps di Piattaforma Notifiche

Per eseguire i test portali mittente e persona fisica su macchina unix like il comando è il seguente:
./mvnw clean test -Dcucumber.filter.tags=@TestSuite -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica -Denvironment=test

Per eseguire i test portali mittente e persona fisica su macchina Windows il comando è il seguente:
mvnw.cmd clean test -Dcucumber.filter.tags=@TestSuite -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica -Denvironment=test

plugin IntellJ: Cucumber, Gherking

Per lanciare singolarmente delle funzioni basta modificare il valore del -Dcucumber.filter.tags con il nome della funzionalità che si vuole lanciare. Es. per lanciare l'invio della notifica:
./mvnw clean test -Dcucumber.filter.tags=@invioNotifiche -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica -Denvironment=test

Per lanciare singolarmente i test di un portale basta modificare il valore del -Dcucumber.filter.tags con: @mittente, @PF,

E' possibile cambiare l'ambiente di esecuzione test sostituendo il valore test nella variabile -Denvironment con gli ambienti dev.
