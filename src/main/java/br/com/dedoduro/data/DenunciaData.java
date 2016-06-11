package br.com.dedoduro.data;

import br.com.dedoduro.model.Denuncia;
import br.com.dedoduro.model.TipoDenuncia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by gasparbarancelli on 11/06/16.
 */
public interface DenunciaData extends MongoRepository<Denuncia, String> {

    List<Denuncia> findByTipoDenuncia(@Param("tipoDenuncia") TipoDenuncia tipoDenuncia);

}
