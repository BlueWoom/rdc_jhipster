package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class CvIstruzioneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CvIstruzione.class);
        CvIstruzione cvIstruzione1 = new CvIstruzione();
        cvIstruzione1.setId(1L);
        CvIstruzione cvIstruzione2 = new CvIstruzione();
        cvIstruzione2.setId(cvIstruzione1.getId());
        assertThat(cvIstruzione1).isEqualTo(cvIstruzione2);
        cvIstruzione2.setId(2L);
        assertThat(cvIstruzione1).isNotEqualTo(cvIstruzione2);
        cvIstruzione1.setId(null);
        assertThat(cvIstruzione1).isNotEqualTo(cvIstruzione2);
    }
}
