package com.development.simplestockmanager.web.view.search;

import com.development.simplestockmanager.business.object.controller.specific.BrandSpecificController;
import com.development.simplestockmanager.business.persistence.Brand;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Brand object
 *
 * @author foxtrot
 */
@ManagedBean(name = "brandSearch")
@ViewScoped
public class BrandSearchView extends BaseSearchView {

    private final BrandSpecificController specificController;

    private Brand browser;
    private Brand view;
    private List<Brand> list;

    public BrandSearchView() {
        specificController = new BrandSpecificController();

        clear();
    }

    @Override
    public void find() {
        list = specificController.findAll();
    }

    @Override
    public final void clear() {
        browser = new Brand();
        view = new Brand();
        list = new ArrayList<>();
    }

    public void initView(Brand brand) {
        System.out.println(brand);
    }

    public void initEdit(Brand brand) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Brand> getList() {
        return list;
    }

}
