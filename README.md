# pn-frontend-e2e
Questo repository contiene i test e2e per le webapps di Piattaforma Notifiche

Per eseguire i test portali mittente e persona fisica e persona giuridica su macchina unix like il comando è il seguente:
./mvnw clean test -Dcucumber.filter.tags=@TestSuite -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica -Denvironment=test

Per eseguire i test portali mittente e persona fisica e persona giuridica su macchina Windows il comando è il seguente:
mvnw.cmd clean test -Dcucumber.filter.tags=@TestSuite -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica -Denvironment=test


Per lanciare singolarmente delle funzioni basta modificare il valore del -Dcucumber.filter.tags con il nome della funzionalità che si vuole lanciare.
Es. per lanciare l'invio della notifica:

./mvnw clean test -Dcucumber.filter.tags=@invioNotifiche -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica -Denvironment=test

Per lanciare singolarmente i test di un portale basta modificare il valore del -Dcucumber.filter.tags con: @mittente, @PF, @PG

Per lanciare singolarmente basta andare a vedere il tag nel feature file. es:@TA_inserimentoDatiErratiDestinatario 

E' possibile cambiare l'ambiente di esecuzione test sostituendo il valore test nella variabile -Denvironment con dev.

Per la Login con token exchange è possibile modificare o aggiungere il token nel file tokenLogin.yaml sotto la cartella Resources/dataPopulation



plugin IntellJ: Cucumber, Gherking