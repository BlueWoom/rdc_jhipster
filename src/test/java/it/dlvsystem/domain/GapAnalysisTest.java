package it.dlvsystem.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.dlvsystem.web.rest.TestUtil;

public class GapAnalysisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GapAnalysis.class);
        GapAnalysis gapAnalysis1 = new GapAnalysis();
        gapAnalysis1.setId(1L);
        GapAnalysis gapAnalysis2 = new GapAnalysis();
        gapAnalysis2.setId(gapAnalysis1.getId());
        assertThat(gapAnalysis1).isEqualTo(gapAnalysis2);
        gapAnalysis2.setId(2L);
        assertThat(gapAnalysis1).isNotEqualTo(gapAnalysis2);
        gapAnalysis1.setId(null);
        assertThat(gapAnalysis1).isNotEqualTo(gapAnalysis2);
    }
}
