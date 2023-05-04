# pn-frontend-e2e
Questo repository contiene i test e2e per le webapps di Piattaforma Notifiche

mvn clean test -Dcucumber.filter.tags=@TestSuite -Dbrowser=chrome -Dheadless=false -DlogFileName=inserimentoNotifica

plugin IntellJ: Cucumber, Gherking

Per lanciare i test singolarmente eseguire il comando a riga 4 modificando il -Dcucumber.filter.tags con: @test<iddeltestbook>