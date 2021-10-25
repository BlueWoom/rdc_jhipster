package it.dlvsystem.web.rest;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.HeaderUtil;
import it.dlvsystem.domain.Azienda;
import it.dlvsystem.repository.AziendaRepository;
import it.dlvsystem.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link it.dlvsystem.domain.Azienda}.
 */
@RestController
@RequestMapping("/api/registers")
@Transactional
public class RegistersResource
{

    private static final String ENTITY_NAME = "registers"; //$NON-NLS-1$

    private final Logger log = LoggerFactory.getLogger(RegistersResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private AziendaRepository aziendaRepository;

    @PutMapping("/azienda/updateGeneral")
    public ResponseEntity<Azienda> updateGeneral(@Valid @RequestBody final Azienda azienda) throws URISyntaxException
    {
        log.debug("REST request to update Azienda General Info : {}", azienda); //$NON-NLS-1$
        if (azienda.getId() == null)
        {
            throw new BadRequestAlertException("Invalid id for Azienda", ENTITY_NAME, "idnull"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        Azienda currentAzienda = null;
        currentAzienda = aziendaRepository.findById(azienda.getId())
                .orElseThrow(() -> new BadRequestAlertException("Invalid id for Azienda", ENTITY_NAME, "idnull")); //$NON-NLS-1$ //$NON-NLS-2$

        currentAzienda.setCf(azienda.getCf());
        currentAzienda.setRagioneSociale(azienda.getRagioneSociale());
        currentAzienda.setIndirizzoSede(azienda.getIndirizzoSede());
        currentAzienda.setProvinciaSede(azienda.getProvinciaSede());
        currentAzienda.setRegioneSede(azienda.getRegioneSede());
        currentAzienda.setCapSede(azienda.getCapSede());
        final Azienda result = aziendaRepository.save(azienda);

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, azienda.getId().toString()))
                .body(result);
    }
}
