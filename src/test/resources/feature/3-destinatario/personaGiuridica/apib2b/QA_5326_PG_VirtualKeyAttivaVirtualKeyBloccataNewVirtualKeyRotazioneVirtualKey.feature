Feature: PG - Utente Amministratore Persona Giuridica con virtual key attiva, blocca la virtual key e registra una nuova virtual key che verrà a sua volta ruotata e ne verrà registrata una nuova.

  @TestSuite
  @TA_PG_VirtualKeyAttivaVirtualKeyBloccataNewVirtualKeyRotazioneVirtualKey_QA_5325
  @integrazioneApi
  @apiKey
  #@bilinguismo
  @NRT_Blocco_2
  Scenario:PN-QA-5325  PG - Utente Amministratore Persona Giuridica con virtual key attiva, blocca la virtual key e registra una nuova virtual key che verrà a sua volta ruotata e ne verrà registrata una nuova.
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
# Inserire una chiave Attiva
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Verifica stato Chiave Personale "Attiva"

    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma

    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma

    Then Verifica stato Chiave Personale "Attiva"
    And Verifica stato Chiave Personale "Bloccata"
    And Verifica stato Chiave Personale "Ruotata"













