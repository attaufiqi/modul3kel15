package modul3kel07.notebook.example.com.modul3kel15.ui.detailCar;

import java.util.List;

import modul3kel07.notebook.example.com.modul3kel15.data.model.DataCar;

public interface DetailView {

    void showErrorCarById(String message);

    void showSuccessCarById(List<DataCar> dataCar);
}
