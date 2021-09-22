package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class OccupazioneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Occupazione.class);
        Occupazione occupazione1 = new Occupazione();
        occupazione1.setId(1L);
        Occupazione occupazione2 = new Occupazione();
        occupazione2.setId(occupazione1.getId());
        assertThat(occupazione1).isEqualTo(occupazione2);
        occupazione2.setId(2L);
        assertThat(occupazione1).isNotEqualTo(occupazione2);
        occupazione1.setId(null);
        assertThat(occupazione1).isNotEqualTo(occupazione2);
    }
}
