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
import it.dlvsystem.domain.Candidato;
import it.dlvsystem.domain.Offerta;
import it.dlvsystem.repository.AziendaRepository;
import it.dlvsystem.repository.CandidatoRepository;
import it.dlvsystem.repository.OffertaRepository;
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

    @Autowired
    private OffertaRepository offertaRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @PutMapping("/azienda/updateGeneral")
    public ResponseEntity<Azienda> updateAziendaGeneral(@Valid @RequestBody final Azienda azienda) throws URISyntaxException
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
        final Azienda result = aziendaRepository.save(currentAzienda);

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, azienda.getId().toString()))
                .body(result);
    }

    @PutMapping("/candidato/updateGeneral")
    public ResponseEntity<Candidato> updateCandidatoGeneral(@Valid @RequestBody final Candidato candidato)
            throws URISyntaxException
    {
        log.debug("REST request to update Candidato General Info : {}", candidato); //$NON-NLS-1$
        if (candidato.getId() == null)
        {
            throw new BadRequestAlertException("Invalid id for Azienda", ENTITY_NAME, "idnull"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        Candidato currentCandidato = null;
        currentCandidato = candidatoRepository.findById(candidato.getId())
                .orElseThrow(() -> new BadRequestAlertException("Invalid id for Candidato", ENTITY_NAME, "idnull")); //$NON-NLS-1$ //$NON-NLS-2$

        currentCandidato.setCf(candidato.getCf());
        currentCandidato.setNome(candidato.getNome());
        currentCandidato.setCognome(candidato.getCognome());
        currentCandidato.setDataNascita(candidato.getDataNascita());
        currentCandidato.setLuogoNascita(candidato.getLuogoNascita());
        currentCandidato.setSesso(candidato.getSesso());
        currentCandidato.setTelefono(candidato.getTelefono());
        currentCandidato.setEmail(candidato.getEmail());
        currentCandidato.setCitta(candidato.getCitta());
        currentCandidato.setIndirizzo(candidato.getIndirizzo());
        currentCandidato.setCap(candidato.getCap());
        currentCandidato.setProvincia(candidato.getProvincia());
        currentCandidato.setRegione(candidato.getRegione());
        final Candidato result = candidatoRepository.save(currentCandidato);

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, candidato.getId().toString()))
                .body(result);
    }

    @PutMapping("/offerta/updateGeneral")
    public ResponseEntity<Offerta> updateOffertaGeneral(@Valid @RequestBody final Offerta offerta) throws URISyntaxException
    {
        log.debug("REST request to update Azienda General Info : {}", offerta); //$NON-NLS-1$
        if (offerta.getId() == null)
        {
            throw new BadRequestAlertException("Invalid id for Candidato", ENTITY_NAME, "idnull"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        Offerta currentOfferta = null;
        currentOfferta = offertaRepository.findById(offerta.getId())
                .orElseThrow(() -> new BadRequestAlertException("Invalid id for Offerta", ENTITY_NAME, "idnull")); //$NON-NLS-1$ //$NON-NLS-2$

        currentOfferta.setData(offerta.getData());
        currentOfferta.setIndirizzoSede(offerta.getIndirizzoSede());
        currentOfferta.setCittaSede(offerta.getCittaSede());
        currentOfferta.setCapSede(offerta.getCapSede());
        currentOfferta.setProvinciaSede(offerta.getProvinciaSede());
        final Offerta result = offertaRepository.save(currentOfferta);

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerta.getId().toString()))
                .body(result);
    }
}
