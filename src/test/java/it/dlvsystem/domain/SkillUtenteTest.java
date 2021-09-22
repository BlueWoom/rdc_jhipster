package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class SkillUtenteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SkillUtente.class);
        SkillUtente skillUtente1 = new SkillUtente();
        skillUtente1.setId(1L);
        SkillUtente skillUtente2 = new SkillUtente();
        skillUtente2.setId(skillUtente1.getId());
        assertThat(skillUtente1).isEqualTo(skillUtente2);
        skillUtente2.setId(2L);
        assertThat(skillUtente1).isNotEqualTo(skillUtente2);
        skillUtente1.setId(null);
        assertThat(skillUtente1).isNotEqualTo(skillUtente2);
    }
}
