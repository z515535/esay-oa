package org.gzccc.oa.paging;

import java.util.List;

import com.github.pagehelper.Page;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月6日 下午11:24:39<p> 
* 类说明 分页对象<p><b>分页规则:<p>若只需分页查询结果则使用分页方法的返回值，若需要分页查询结果
* 及分页信息，使用Pages 参数
*
*/
public class Pages<T> {
	
	private int pageNum;		//页码，默认为1
	private int pageSize = 20;		//默认每页显示20条
	private long total;		    //总记录条数
	private int pages;		        //总页数
	private List<T> list;			//查询结果集
	private int size;				//查询结果集的真实行数
	
	public Pages(){}
	
	public Pages(List<T> list){
		   Page<T> page = (Page<T>) list;
           this.pageNum = page.getPageNum();
           this.pageSize = page.getPageSize();
           this.total = page.getTotal();
           this.pages = page.getPages();
           this.list = list;
           this.size = page.size();
	}

	public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
 