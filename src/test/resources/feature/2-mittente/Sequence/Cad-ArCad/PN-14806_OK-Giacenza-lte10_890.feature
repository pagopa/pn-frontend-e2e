Feature: invio notifica con sequence

  @Tag_PN_14806
  @TestSuite

  Scenario: [PN-14806 CAD/ARCAD SEQUENCE OK-Giacenza-lte10_890]
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | @OK-Giacenza-lte10_890 |
      | descrizione       | @OK-Giacenza-lte10_890 |
      | gruppo            | test-TA-FE             |
      | codiceTassonomico | 100105P                |
      | modalitaInvio     | A/R                    |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF                       |
      | nomeCognomeDestinatario | Giuseppe Maria Garibaldi |
      | codiceFiscale           | GRBGPP87L04L741X         |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via @OK-Giacenza-lte10_890 |
      | civico    | 20                         |
      | localita  | Milano                     |
      | comune    | Milano                     |
      | provincia | MI                         |
      | cap       | 20147                      |
      | stato     | Italia                     |
#    And Nella section Destinatario cliccare su Aggiungi destinatario
#    And Nella section Destinatario inserire i dati del secondo destinatario come persona giuridica
#      | soggettoGiuridico | PG                    |
#      | ragioneSociale    | Convivio Spa          |
#      | codiceFiscale     | 27957814470           |
#      | indirizzo         | Via @FAIL_DECEDUTO_AR |
#      | civico            | 20                    |
#      | localita          | Milano                |
#      | comune            | Milano                |
#      | provincia         | MI                    |
#      | cap               | 20147                 |
#      | stato             | Italia                |
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
#    And Seleziona Nessun Pagamento 2
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
#    And Si verifica che la notifica è stata creata correttamente
#    And Aspetta 10 secondi
#    And Si seleziona la notifica mittente
#    And Si attende completamento notifica "Invio in corso"