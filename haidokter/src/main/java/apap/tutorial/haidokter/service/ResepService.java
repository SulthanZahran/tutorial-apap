package apap.tutorial.haidokter.service;

import apap.tutorial.haidokter.model.ResepModel;

import java.util.ArrayList;
import java.util.List;

public interface ResepService{
    //Method untuk menambah resep
    void addResep(ResepModel resep);

    //Method untuk mendapatkan semua data Resep yaang telah tersimpan
    List<ResepModel> getResepList();

    //Method untuk mendapatkan data sebuah resep berdasarkan nomor resep

    ResepModel getResepByNomorResep(Long noResep);

//    void updateResepByNomorResep(String noResep, String catatan);

    ResepModel updateResep(ResepModel resepModel);

//    int deleteResep(String noResep);

    void deleteResep(ResepModel resep);
}