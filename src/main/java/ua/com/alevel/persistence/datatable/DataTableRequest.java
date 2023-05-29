package ua.com.alevel.persistence.datatable;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;

@Getter
@Setter
public class DataTableRequest {

    private int page;
    private int size;
    private String order;
    private String sort;
    private Map<String, String[]> requestParamMap;

    public DataTableRequest() {
        requestParamMap = Collections.emptyMap();
    }
}