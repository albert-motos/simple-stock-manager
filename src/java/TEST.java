
import com.development.simplestockmanager.business.object.controller.general.BrandGeneralController;
import com.development.simplestockmanager.business.persistence.Brand;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author foxtrot
 */
public class TEST {
    public static void main(String[] args) {
        Brand b = new Brand("asd", true);
        
        BrandGeneralController controller = new BrandGeneralController();
        Object read = controller.read(b);
    }
}
