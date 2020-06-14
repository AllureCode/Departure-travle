package www.gnawTravle.com.travel.entity.page;

import java.io.Serializable;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 分页类
 * @author: wang_sir
 * @create: 2020-06-13 19:56
 **/
public class PageBean<T> implements Serializable {

    /**
     * 当前页 默认第一页
     */
    private Integer currentPage = 1;
    /**
     * 每页显示的条数 默认10条
     */
    private Integer pageSize = 10;
    /**
     * 总条数
     */
    private Integer totalNum;
    /**
     * 是否有下一页
     */
    private Integer isMore;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 开始索引
     */
    private Integer startIndex;
    /**
     * 分页后的数据
     */
    private List<T> data;

    public PageBean() {
    }

    public PageBean(Integer currentPage, Integer pageSize, Integer totalNum) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        //计算总页数
        this.totalPage = (this.totalNum % this.pageSize) == 0 ? (this.totalNum / this.pageSize) : (this.totalNum / this.pageSize + 1);
        //开始索引
        this.startIndex = (this.currentPage - 1) * this.pageSize;
        //是否有下一页
        this.isMore = this.currentPage >= this.totalPage?0:1;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalNum=" + totalNum +
                ", isMore=" + isMore +
                ", totalPage=" + totalPage +
                ", startIndex=" + startIndex +
                ", data=" + data +
                '}';
    }
}
