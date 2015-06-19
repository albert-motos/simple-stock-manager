package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.BrandNull;
import com.development.simplestockmanager.business.object.helper.BrandHelper;
import com.development.simplestockmanager.business.persistence.old.Brand;
import com.development.simplestockmanager.business.persistence.controller.BrandJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class BrandGeneralController {

    public long create(Brand brand) {
        Query query = BrandHelper.getFindByNameQuery(brand.getName());

        if (query.getResultList().isEmpty()) {
            try {
                BrandJpaController brandJpaController = BrandHelper.getJpaController();
                brandJpaController.create(brand);
            } catch (Exception e) {
                brand = new BrandNull();
            }
        } else {
            brand = new BrandNull();
        }

        return brand.getId();
    }

    public Brand read(Brand brand) {
        try {
            Query query = BrandHelper.getFindByIdQuery(brand.getId());
            brand = (Brand) query.getSingleResult();
        } catch (Exception e) {
            brand = new BrandNull();
        }

        return brand;
    }

    public long update(Brand brand) {
        long status = Constant.UPDATE.FAILURE;

        if (read(brand).getId() != Constant.IDENTIFIER.INVALID) {
            Query query = BrandHelper.getFindByNameQuery(brand.getName());
            boolean uniqueName = true;

            if (!query.getResultList().isEmpty()) {
                Brand otherBrand = (Brand) query.getSingleResult();
                uniqueName = brand.getId().equals(otherBrand.getId());
            }

            if (uniqueName) {
                try {
                    BrandJpaController brandJpaController = BrandHelper.getJpaController();
                    brandJpaController.edit(brand);
                    status = Constant.UPDATE.SUCCESS;
                } catch (Exception e) {

                }
            }
        }

        return status;
    }

    public long delete(Brand brand) {
        long status = Constant.DELETE.FAILURE;

        if (read(brand).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                BrandJpaController brandJpaController = BrandHelper.getJpaController();
                brandJpaController.destroy(brand.getId());
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

}
