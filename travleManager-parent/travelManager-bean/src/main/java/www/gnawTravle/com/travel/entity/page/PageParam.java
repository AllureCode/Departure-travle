package www.gnawTravle.com.travel.entity.page;

import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 简单分页封装
 * @author: wang_sir
 * @create: 2020-06-13 20:48
 **/
public class PageParam<T> implements Serializable {
    /**当前页面*/
    private int pageNumber;
    /**一页多少条数据*/
    private int pageSize;
    /**多少页*/
    private long size;
    /**多少条数据*/
    private long count;

    private int offset;

    private Sort sort;

    private List<T> result;

    public PageParam() {
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", size=" + size +
                ", count=" + count +
                ", offset=" + offset +
                ", sort=" + sort +
                ", result=" + result +
                '}';
    }
}
