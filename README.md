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


Per lanciare i test dei recapiti in locale bisogna prima stabilire una connessione ssm al exchanel da cmd (per la configurazione guardare il manuale) 
Esempio; il comando per l'ambiente di test:
aws --profile pn-test-core ssm start-session --target "i-0c7b5a5e1e47dcdff" --document-name AWS-StartPortForwardingSessionToRemoteHost --parameters "{\"portNumber\":[\"8080\"],\"localPortNumber\":[\"8887\"],\"host\":[\"internal-EcsA-20230504103152508600000011-1839177861.eu-south-1.elb.amazonaws.com\"]}"


plugin IntellJ: Cucumber, Gherking