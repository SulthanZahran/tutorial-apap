package apap.tutorial.haidokter.service;
import apap.tutorial.haidokter.model.ObatModel;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ObatRestService {

    ObatModel createObat(ObatModel obat);

    ObatModel updateObat(Long id, ObatModel obat);

    ObatModel getObatById(Long id);

    List<ObatModel> retrieveListObat();

    void deleteObatByID(Long id);
}
