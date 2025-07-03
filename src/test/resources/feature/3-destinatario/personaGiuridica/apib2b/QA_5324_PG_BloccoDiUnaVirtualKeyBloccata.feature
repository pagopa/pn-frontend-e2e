Feature: PG - Blocco di una virtual key per un utente Amministratore Persona Giuridica con già una virtual key bloccata

  @TestSuite
  @TA_PG_BloccoDiUnaVirtualKeyBloccata_QA_5324
  @integrazioneApi
  @integrazioneApiDelegato
  #@bilinguismo
  @NRT_Blocco_2
  Scenario:PN-QA-5324  PG - Blocco di una virtual key per un utente Amministratore Persona Giuridica con già una virtual key bloccata
    Given PG - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente public keys
# tasto Registra chiave pubblica
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra

    And Pulisci ambiente virtual keys

    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
#  verifica stati
    And Verifica stato Chiave Personale "Attiva"

    And Cliccare sui tre puntini Virtual key con stato "Attiva"

    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And  Verifica stato Chiave Personale "Bloccata"

    Then Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Verifica stato Chiave Personale "Attiva"

    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota | Ruota             |
      | view  | Visualizza codice |











