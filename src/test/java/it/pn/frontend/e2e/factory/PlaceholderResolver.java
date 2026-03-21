package it.pn.frontend.e2e.factory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/*
E.g.
protocolNumber: "PROT-{timestamp}"   # PROT-20260321130045
protocolNumber: "PROT-{counter}"     # PROT-1, PROT-2, PROT-3
protocolNumber: "PROT-{uuid}"        # PROT-a3f2b1c4
// Factory
data.setSubject(resolve(n.get("subject")));
data.setProtocolNumber(resolve(n.get("protocolNumber")));
data.setTaxonomyCode(resolve(n.get("taxonomyCode")));
data.setDocumentTitle(resolve(d.get("title")));
 */
public class PlaceholderResolver {
    // thread-safe 
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String resolve(String value) {
        if (value == null) return null;
        return value
            .replace("{timestamp}", LocalDateTime.now().format(FORMATTER))
            .replace("{counter}",   String.valueOf(COUNTER.incrementAndGet()))
            .replace("{uuid}",      java.util.UUID.randomUUID().toString().substring(0, 8));
    }
}