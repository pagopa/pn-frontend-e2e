Feature: PG - Utente Amministratore di gruppo non abilitato prova a censire una public key

  @TestSuite
  @TA_PG_UtenteAmministratoreDiGruppoNonAbilitatoProvaCensirePublicKey_QA_5328
  @integrazioneApi
  @integrazioneApiPg2
  @NRT_Blocco_2
  Scenario:PN-QA-5328  PG - Utente Amministratore di gruppo non abilitato prova a censire una public key
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Vita Nova Sas  |
    And Si clicca su prodotto
    And Clicca tasto Accedi OneTrust PG e PF
#  Censire una chiave pubblica per un Operatore
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente public keys
    And Logout da portale persona giuridica delegante
#  Entro come operatore
    And Attesa 2 secondi
    And Login con persona giuridica
      | user           | m.montessori  |
      | pwd            | test          |
      | ragioneSociale | Vita Nova Sas |
    And Si clicca su prodotto
    And Clicca tasto Accedi OneTrust PG e PF
#    Cliccando sulla CTA “Genera chiave personale”
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    Then Nella sezione Integrazione API non si visualizza alcuna chiave "Per poter creare una chiave personale, un amministratore deve prima abilitare l’integrazione."
