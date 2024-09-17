package it.pn.frontend.e2e.model.address;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DigitalAddressResponse {
    private DigitalAddress digitalAddress;
}
