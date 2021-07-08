package co.com.company.util;

import io.cucumber.datatable.DataTable;

public class TransposeDataTable {

    private TransposeDataTable() {
    }

    public static <T> T transposeDataTable(Class<T> c, DataTable datatable) {
        return datatable.convert(c, true);
    }

}