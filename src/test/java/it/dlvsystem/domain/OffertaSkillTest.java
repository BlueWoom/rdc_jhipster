package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class OffertaSkillTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OffertaSkill.class);
        OffertaSkill offertaSkill1 = new OffertaSkill();
        offertaSkill1.setId(1L);
        OffertaSkill offertaSkill2 = new OffertaSkill();
        offertaSkill2.setId(offertaSkill1.getId());
        assertThat(offertaSkill1).isEqualTo(offertaSkill2);
        offertaSkill2.setId(2L);
        assertThat(offertaSkill1).isNotEqualTo(offertaSkill2);
        offertaSkill1.setId(null);
        assertThat(offertaSkill1).isNotEqualTo(offertaSkill2);
    }
}
