package ua.com.alevel.web.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

import java.util.List;

@Getter
@Setter
@ToString
public class PageData<REQ extends ResponseDto> {

    private int currentPage;
    private int pageSize;
    private int totalPageSize;
    private long itemsSize;
    private List<REQ> items;
    private final int[] pageSizeItems;
    private boolean showFirst;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showLast;
    private String sort;
    private String order;
    private long currentShowFromEntries;
    private long currentShowToEntries;

    public PageData() {
        this.currentPage = 0;
        this.pageSize = 10;
        this.totalPageSize = 0;
        this.itemsSize = 0;
        this.items = new ArrayList<>();
        this.pageSizeItems = new int[]{10, 25, 50, 100};
        this.showFirst = false;
        this.showPrevious = false;
        this.showNext = false;
        this.showLast = false;
    }

    public void initPaginationState() {
        if (pageSize < itemsSize) {
            this.totalPageSize = (int) itemsSize / pageSize;
            this.showFirst = currentPage != 1;
            this.showPrevious = currentPage - 1 != 0;
            this.showLast = currentPage - 1 != totalPageSize;
            this.showNext = currentPage - 1 != totalPageSize;
        }
        currentShowFromEntries = ((long) (currentPage - 1) * pageSize) + 1;
        currentShowToEntries = ((long) (currentPage - 1) * pageSize) + items.size();
    }
}

