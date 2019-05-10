package com.ninety_nine_filter;

import com.ninety_nine_filter.FilterList.IImageFilter;

/**
 * 作者    chin_style
 */
public class FilterBean {
    private int id;
    private int imageId;
    private IImageFilter filter;

    public FilterBean(int imageId, IImageFilter filter, int id) {
        this.filter = filter;
        this.imageId = imageId;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public IImageFilter getFilter() {
        return filter;
    }

    public void setFilter(IImageFilter filter) {
        this.filter = filter;
    }
}
