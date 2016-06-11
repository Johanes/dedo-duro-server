package br.com.dedoduro.controller;

import br.com.dedoduro.data.DenunciaData;
import br.com.dedoduro.model.Denuncia;
import br.com.dedoduro.model.DenunciaSimples;
import br.com.dedoduro.model.TipoDenuncia;
import br.com.dedoduro.validation.ValidationErrorBuilder;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasparbarancelli on 11/06/16.
 */
@CrossOrigin
@RestController
@RequestMapping("/denuncia")
public class DenunciaController {

    @Autowired private DenunciaData denunciaData;

    @Cacheable("denuncia-getAll")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Denuncia> getAll() {
        return denunciaData.findAll();
    }

    @Cacheable("denuncia-getAll")
    @RequestMapping(value = "/simple", method = RequestMethod.GET, produces = "application/json")
    public List<DenunciaSimples> getAllSimple() {
        List<DenunciaSimples> denunciaSimplesList = new ArrayList<>();
        List<Denuncia> denunciaList = denunciaData.findAll();
        for (Denuncia denuncia : denunciaList) {
            denunciaSimplesList.add(new DenunciaSimples(denuncia));
        }
        return denunciaSimplesList;
    }

    @Cacheable(value = "denuncia-get")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Denuncia get(@PathVariable("id") String id) {
        return denunciaData.findOne(id);
    }

    @Cacheable("denuncia-findByTipoDenuncia")
    @RequestMapping(value = "/findByTipoDenuncia", method = RequestMethod.GET, produces = "application/json")
    public List<Denuncia> findByTipoDenuncia(@RequestParam("tipoDenuncia") TipoDenuncia tipoDenuncia) {
        return denunciaData.findByTipoDenuncia(tipoDenuncia);
    }


    @CacheEvict(cacheNames = {"denuncia-getAll", "denuncia-get", "denuncia-findByTipoDenuncia"}, allEntries = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id")String id) {
        denunciaData.delete(id);
    }

    @CacheEvict(cacheNames = {"denuncia-getAll", "denuncia-get", "denuncia-findByTipoDenuncia"}, allEntries = true)
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Denuncia.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity insert(@RequestBody @Valid Denuncia denuncia, BindingResult result) {
        return save(denuncia, result);
    }

    @CacheEvict(cacheNames = {"denuncia-getAll", "denuncia-get", "denuncia-findByTipoDenuncia"}, allEntries = true)
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Denuncia.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity update(@RequestBody @Valid Denuncia denuncia, BindingResult result) {
        return save(denuncia, result);
    }

    private ResponseEntity save(@RequestBody @Valid Denuncia denuncia, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(result));
        }
        return ResponseEntity.ok(denunciaData.save(denuncia));
    }

}
