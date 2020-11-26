package apap.tutorial.haidokter.service;

import apap.tutorial.haidokter.model.ObatModel;
import apap.tutorial.haidokter.model.ResepModel;
import apap.tutorial.haidokter.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ObatRestServiceImpl implements ObatRestService{
    @Autowired
    private ObatDb obatDb;

    @Override
    public ObatModel createObat(ObatModel obat){return obatDb.save(obat);}

    @Override
    public ObatModel updateObat(Long id, ObatModel obat){
        ObatModel obat1 = getObatById(id);
        obat1.setNama(obat.getNama());
        obat1.setBentuk(obat.getBentuk());
        obat1.setKuantitas(obat.getKuantitas());
        return obatDb.save(obat1);
    }

    @Override
    public ObatModel getObatById(Long id){
        Optional<ObatModel> obat =  obatDb.findById(id);
        if(obat.isPresent()){
            return obat.get();
        } else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<ObatModel> retrieveListObat(){return obatDb.findAll();}

    @Override
    public void deleteObatByID(Long id){
        ObatModel obat = getObatById(id);
        obatDb.delete(obat);
    }
}
