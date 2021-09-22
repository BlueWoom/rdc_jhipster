package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class OffertaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Offerta.class);
        Offerta offerta1 = new Offerta();
        offerta1.setId(1L);
        Offerta offerta2 = new Offerta();
        offerta2.setId(offerta1.getId());
        assertThat(offerta1).isEqualTo(offerta2);
        offerta2.setId(2L);
        assertThat(offerta1).isNotEqualTo(offerta2);
        offerta1.setId(null);
        assertThat(offerta1).isNotEqualTo(offerta2);
    }
}
