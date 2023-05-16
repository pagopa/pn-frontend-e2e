# pn-frontend-e2e
Questo repository contiene i test e2e per le webapps di Piattaforma Notifiche

Per eseguire i test su macchina unix like il comando è il seguente:

portali mittente e destinatario:

    ./mvnw clean test -Dcucumber.filter.tags=@firstCommitRun -Dbrowser=chrome -Dheadless=false -DlogFileName=pagoPA
Portale RADD

    ./mvnw clean test -Dcucumber.filter.tags=@RADD -Dbrowser=chrome -Dheadless=false -DlogFileName=RADD

Per eseguire i test su macchina Windows il comando è il seguente:

portali mittente e destinatario:

    mvnw.cmd clean test -Dcucumber.filter.tags=@firstCommitRun -Dbrowser=chrome -Dheadless=false -DlogFileName=pagoPA

Portale RADD

    mvnw.cmd clean test -Dcucumber.filter.tags=@RADD -Dbrowser=chrome -Dheadless=false -DlogFileName=RADD

plugin IntellJ: Cucumber, Gherking

Per lanciare i test singolarmente eseguire il comando a riga 4 modificando il -Dcucumber.filter.tags con: @test<iddeltestbook>

