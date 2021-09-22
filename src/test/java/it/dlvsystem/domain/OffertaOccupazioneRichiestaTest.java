package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class OffertaOccupazioneRichiestaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OffertaOccupazioneRichiesta.class);
        OffertaOccupazioneRichiesta offertaOccupazioneRichiesta1 = new OffertaOccupazioneRichiesta();
        offertaOccupazioneRichiesta1.setId(1L);
        OffertaOccupazioneRichiesta offertaOccupazioneRichiesta2 = new OffertaOccupazioneRichiesta();
        offertaOccupazioneRichiesta2.setId(offertaOccupazioneRichiesta1.getId());
        assertThat(offertaOccupazioneRichiesta1).isEqualTo(offertaOccupazioneRichiesta2);
        offertaOccupazioneRichiesta2.setId(2L);
        assertThat(offertaOccupazioneRichiesta1).isNotEqualTo(offertaOccupazioneRichiesta2);
        offertaOccupazioneRichiesta1.setId(null);
        assertThat(offertaOccupazioneRichiesta1).isNotEqualTo(offertaOccupazioneRichiesta2);
    }
}
