package apap.tutorial.haidokter.service;

import apap.tutorial.haidokter.model.ObatModel;
import apap.tutorial.haidokter.model.ResepModel;
import apap.tutorial.haidokter.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ObatServiceImpl implements ObatService {
    @Autowired
    ObatDb obatDb;

    @Override
    public void addObat(ObatModel obat){
        obatDb.save(obat);
    }

    @Override
    public ObatModel getObatById(Long id){
        return obatDb.findById(id).get();
    }

    @Override
    public ObatModel updateObat(ObatModel obat){
        ObatModel obatUpdate = obatDb.findById(obat.getId()).get();

        try{
            obatUpdate.setNama(obat.getNama());
            obatUpdate.setBentuk(obat.getBentuk());
            obatUpdate.setKuantitas(obat.getKuantitas());
            obatDb.save(obatUpdate);
            return obatUpdate;
        }
        catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deleteObat(ObatModel obat){
        obatDb.delete(obat);
    }

    @Override
    public List<ObatModel> getObatByBentukKuantitas(String bentuk, Integer kuantitas){
        private List<ObatModel> listObat;
        for(ObatModel i: obatDb.findAll()){
            if(bentuk == "Kapsul"){
               ObatModel obat = obatDb.findByBentukdanKuantitas(0, kuantitas).get();
               listObat.add(obat);
            }
            else if(bentuk == "Tablet"){
                ObatModel obat = obatDb.findByBentukdanKuantitas(1, kuantitas).get();
                listObat.add(obat);
            }
            else if(bentuk == "Sirup"){
                ObatModel obat = obatDb.findByBentukdanKuantitas(2, kuantitas).get();
                listObat.add(obat);
            }
        }
        return listObat;
    }
}
