Feature:Il delegato persona giuridica accede ad una delega

  @TestSuite
  @TA_PGdelegatoPagaNotifica
  @DeleghePG
  @PG
  @deleghe2
  @DeleghePFPG1
  @NRT_Blocco_1
  @NRT_PN13213
  Scenario: PN-10389 - Il delegato persona giuridica paga una notifica
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #    And Si verifica che visualizzato lo stato Pagato
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    # Seconda PG per TA non disponibile, si crea una delega per PF
    #And Si controlla che non sia presente una delega con stesso nome persona giuridica "Le Epistolae srl"
    #And Nella sezione Deleghe si verifica sia presente una delega accettata per PG
    And Si controlla che non sia presente una delega con stesso nome
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
    And Nella sezione Deleghe si crea una delega accettata per PG
    And Logout da portale persona giuridica
    And PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella sezione Deleghe si accetta la delega accettata per PG
    And Click Notifiche
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sulle notifiche di "(Convivio Spa)"
    And Si controlla la pagina delle notifiche delegati di "Convivio Spa"
    And Refresh pagina
