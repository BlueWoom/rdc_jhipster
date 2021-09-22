package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class NavigatorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Navigator.class);
        Navigator navigator1 = new Navigator();
        navigator1.setId(1L);
        Navigator navigator2 = new Navigator();
        navigator2.setId(navigator1.getId());
        assertThat(navigator1).isEqualTo(navigator2);
        navigator2.setId(2L);
        assertThat(navigator1).isNotEqualTo(navigator2);
        navigator1.setId(null);
        assertThat(navigator1).isNotEqualTo(navigator2);
    }
}
