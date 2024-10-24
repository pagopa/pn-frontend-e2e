Feature: La persona giuridica elimina l'indirizzo email

  @TestSuite
  @TA_eliminaEmailPG
  @PG
  @recapitiPG

  Scenario: PN-9157 - La persona giuridica elimina l'indirizzo email
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email
    And Si clicca su elimina email
    And Si annulla eliminazione email
    And Si controlla presenza email precedentemente salvata "prova@test.it"
    And Si clicca su elimina email
    And Si conferma "Rimuovi email" nel pop up
    Then Nella pagina I Tuoi Recapiti si controlla che l'indirizzo Email non sia presente
    And Logout da portale persona giuridica
