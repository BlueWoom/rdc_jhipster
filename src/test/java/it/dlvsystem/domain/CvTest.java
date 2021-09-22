package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class CvTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cv.class);
        Cv cv1 = new Cv();
        cv1.setId(1L);
        Cv cv2 = new Cv();
        cv2.setId(cv1.getId());
        assertThat(cv1).isEqualTo(cv2);
        cv2.setId(2L);
        assertThat(cv1).isNotEqualTo(cv2);
        cv1.setId(null);
        assertThat(cv1).isNotEqualTo(cv2);
    }
}
