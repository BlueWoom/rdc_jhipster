package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class AziendaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Azienda.class);
        Azienda azienda1 = new Azienda();
        azienda1.setId(1L);
        Azienda azienda2 = new Azienda();
        azienda2.setId(azienda1.getId());
        assertThat(azienda1).isEqualTo(azienda2);
        azienda2.setId(2L);
        assertThat(azienda1).isNotEqualTo(azienda2);
        azienda1.setId(null);
        assertThat(azienda1).isNotEqualTo(azienda2);
    }
}
