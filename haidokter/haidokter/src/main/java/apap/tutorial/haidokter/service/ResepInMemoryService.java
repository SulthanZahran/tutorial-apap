package apap.tutorial.haidokter.service;

import apap.tutorial.haidokter.model.ResepModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResepInMemoryService implements ResepService {
    private List<ResepModel> listResep;

    // Constructor
    public ResepInMemoryService() {
        listResep = new ArrayList<>();
    }

    @Override
    public void addResep(ResepModel resep) {
        listResep.add(resep);
    }

    @Override
    public List<ResepModel> getResepList() {
        return listResep;
    }

    @Override
    public ResepModel getResepByNomorResep(String noResep) {
        ResepModel resepDicari = null;
        for (ResepModel i : listResep) {
            if (noResep.equals(i.getNoResep())) {
                resepDicari = i;
            }
        }
        return resepDicari;
    }

    @Override
    public void updateResepByNomorResep(String noResep, String catatan) {
        for (ResepModel i : listResep) {
            if (noResep.equals(i.getNoResep())) {
                i.setCatatan(catatan);
            }
        }
    }

    @Override
    public int deleteResep(String noResep) {
        for (ResepModel i : listResep) {
            if (noResep.equals(i.getNoResep())) {
                listResep.remove(i);
                return 0;
            }
        }
        return 1;
    }

    @Override
    public void deleteAllResep() {
        listResep.clear();
    }
}
