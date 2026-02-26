package it.frontend.e2e.framework.core.fixtures.capabilities;

import it.frontend.e2e.framework.core.capability.Capability;
import it.frontend.e2e.framework.core.fixtures.TestElement;
import it.frontend.e2e.framework.core.fixtures.TestLocation;
import it.frontend.e2e.framework.core.fixtures.TestSelector;

/**
 * Capability di base per test di ereditarietà.
 */
public interface BaseCapability extends Capability<TestSelector, TestLocation, TestElement> {

    /**
     * Metodo base presente in tutte le capability che estendono questa.
     */
    String baseMethod();

    /**
     * Metodo con parametri.
     */
    String methodWithParams(String param1, int param2);
}

