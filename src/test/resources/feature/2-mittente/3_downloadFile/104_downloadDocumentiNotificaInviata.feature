Feature: il mittente download attestazione notifica presa in carico

  @TestSuite
  @TA_MittenteDownloadDocumentiNotificaInviata
  @mittente
  @DownloadFileMittente

  Scenario: PN-11678 - il mittente download documenti di una notifica inviata da oltre 120 giorni
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    Then Si visualizza correttamente la pagina dello 'stato della piattaforma' di mittente
    And Si visualizza correttamente la tabella dei disservizi
    And Nella pagina stato della piattaforma si cambia il numero elementi visualizzati attraverso il filtro
    And Nella pagina stato della piattaforma si cambia pagina utilizzando una freccetta 8
    And Download file attestazione disservizio
    And Si controlla che esista pop up scadenza
    And Nella pagina Piattaforma Notifiche selezionare la voce 'Notifiche'
    And Nella pagina Piattaforma Notifiche mittente inserire un arco temporale di maggiore di 120 giorni
    #And Nella pagina Piattaforma Notifiche mittente inserire un arco temporale
    #  | annoDa   | 2024 |
    #  | meseDa   | 3  |
    #  | giornoDa | 1    |
    #  | annoA    | 2024 |
    #  | meseA    | 3   |
    #  | giornoA  |3    |
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita
    And Salva codice IUN
    And Logout da portale mittente
    And Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk
    When Nella Home di helpdesk utente clicca su sezione ricerca ed estrazione dati
    And visualizzazione corretta pagina ricerca ed estrazione dati
    And Selezione ottieni notifica
    And viene inserito codice IUN salvato
    And controllo messaggio di successo
    And controllo password
    And controllo link per scaricare zip e scarico file
    And Aspetta 5 secondi
    And Inserisco la password ed estraggo il file zip
    And Controllo sia presente documento "Riepilogo fallimenti.txt"
    And Si elimina file estratto
