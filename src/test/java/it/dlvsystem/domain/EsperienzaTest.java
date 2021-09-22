package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class EsperienzaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Esperienza.class);
        Esperienza esperienza1 = new Esperienza();
        esperienza1.setId(1L);
        Esperienza esperienza2 = new Esperienza();
        esperienza2.setId(esperienza1.getId());
        assertThat(esperienza1).isEqualTo(esperienza2);
        esperienza2.setId(2L);
        assertThat(esperienza1).isNotEqualTo(esperienza2);
        esperienza1.setId(null);
        assertThat(esperienza1).isNotEqualTo(esperienza2);
    }
}
