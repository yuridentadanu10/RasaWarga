package com.project.ateam.a_team321.model.jenis;

import com.project.ateam.a_team321.R;
import com.project.ateam.a_team321.model.jenis.Jenis;

import java.util.ArrayList;

public class JenisData {
    private static String[] jenis = {
            "Alam",
            "Budaya",
            "Historis",
            "Religi",
            "Arsitektur"
    };

    private static int[] gambarJenis = {
            R.drawable.gambar_alam,
            R.drawable.batik,
            R.drawable.historis,
            R.drawable.religi,
            R.drawable.arsi
    };

    public static ArrayList<Jenis> getListData() {
        ArrayList<Jenis> list = new ArrayList<>();
        for (int position = 0; position < jenis.length; position++){
            Jenis jenisList = new Jenis();
            jenisList.setJenis(jenis[position]);
            jenisList.setGambar(gambarJenis[position]);
            list.add(jenisList);
        }
        return list;
    }
}
