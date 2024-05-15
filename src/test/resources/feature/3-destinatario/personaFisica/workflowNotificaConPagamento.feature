Feature:Controllo dati notifica con pagamento

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA-ANNULLATA CON PAGAMENTO AFFETTUATO] Verifica testo rimborso su notifica pagata e successivamente annullata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "QWHG-KYNJ-GHMZ-202405-Q-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "JEGU-QMTG-VAVR-202405-K-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    ##
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento1
  Scenario:[NOTIFICA- AVVISO PAGO-PA COSTI INCLUSI] Verifica testo avviso pago pa e notifica con costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "TLRP-MGWN-MUXG-202405-J-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "TLRP-MGWN-MUXG-202405-J-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    # il parametro isTrue é utilizzato per verificare se un elemento deve esistere o no
    And Si controlla testo in box pagamento
      | xpath    | //p[@data-testid='apply-costs-caption']|
      | isTrue    | true         |
    And Si controlla testo in box pagamento
      | xpath    | //button[@data-testid='download-pagoPA-notice-button']|
      | isTrue    | true         |
    And Logout da portale persona fisica



