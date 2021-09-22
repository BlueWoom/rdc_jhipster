package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class IstruzioneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Istruzione.class);
        Istruzione istruzione1 = new Istruzione();
        istruzione1.setId(1L);
        Istruzione istruzione2 = new Istruzione();
        istruzione2.setId(istruzione1.getId());
        assertThat(istruzione1).isEqualTo(istruzione2);
        istruzione2.setId(2L);
        assertThat(istruzione1).isNotEqualTo(istruzione2);
        istruzione1.setId(null);
        assertThat(istruzione1).isNotEqualTo(istruzione2);
    }
}
