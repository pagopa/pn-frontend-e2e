package it.pn.frontend.e2e.steps;

import io.cucumber.java.en.Given;
import it.pn.frontend.e2e.enums.User;
import it.pn.frontend.e2e.model.PageInfo;
import it.pn.frontend.e2e.model.SharedContext;
import it.pn.frontend.e2e.presentation.IPresentationHandler;
import it.pn.frontend.e2e.presentation.models.LoginPage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginSteps {

    private final IPresentationHandler presentation;
    private final SharedContext sharedContext;

    @Given("l'utente tenta di navigare alla pagina {page}")
    public void loadPage(PageInfo page) {
        sharedContext.setCurrentPage(page);
        presentation.navigateTo(page.url());
    }

    @Given("l'utente {user} tenta la login")
    public void compileForm(User user) {
        LoginPage loginPage = (LoginPage) presentation.parse(sharedContext.getCurrentPage().pageClass());
        loginPage.setUsername(user.getUsername());
        loginPage.setPassword(user.getPassword());
        loginPage.setComune(user.getComune());
        loginPage.login();
    }
}
