# pn-frontend-e2e
Questo repository contiene i test e2e per le webapps di Piattaforma Notifiche

Per eseguire i test portali mittente e persona fisica su macchina unix like il comando è il seguente:
./mvnw clean test -Dcucumber.filter.tags=@TestSuite -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica -Denvironment=test

Per eseguire i test RADD su macchina unix like il comando è il seguente:
./mvnw clean test -Dcucumber.filter.tags=@RADD -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica -Denvironment=test

Per eseguire i test portali mittente e persona fisica su macchina Windows il comando è il seguente:
mvnw.cmd clean test -Dcucumber.filter.tags=@TestSuite -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica -Denvironment=test

Per eseguire i test RADD su macchina Windows il comando è il seguente:
mvnw.cmd clean test -Dcucumber.filter.tags=@RADD -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica -Denvironment=test

plugin IntellJ: Cucumber, Gherking

Per lanciare i test singolarmente, della prima fase, eseguire il comando modificando il -Dcucumber.filter.tags = @TestSuite con: -Dcucumber.filter.tags = @test<iddeltestbook>.

Mentre per quelli della seconda fase eseguire il comando modificando il  -Dcucumber.filter.tags = @TestSuite con: -Dcucumber.filter.tags = @fase2Test<iddeltestbook>.

E' possibile cambiare l'ambiente di esecuzione test sostituendo il valore test nella variabile -Denvironment con gli ambienti dev o uat