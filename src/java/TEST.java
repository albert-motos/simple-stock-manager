
import com.development.simplestockmanager.business.object.controller.general.BrandGeneralController;
import com.development.simplestockmanager.business.object.controller.general.EmployeeGeneralController;
import com.development.simplestockmanager.business.object.controller.general.ProductGeneralController;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.internationalization.InternationalizationController;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.object.validator.BrandValidator;
import com.development.simplestockmanager.web.object.validator.EmployeeValidator;
import com.development.simplestockmanager.web.object.validator.ProductValidator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        InternationalizationController ic = new InternationalizationController("es_ES");
        BrandGeneralController controller = new BrandGeneralController();
        BrandValidator validator = new BrandValidator(WebConstant.VALIDATOR.MODE.EDIT, ic);
        Brand brand = controller.read(new Brand((long) 1));
        brand.setId((long) 100);
        validator.setObject(brand);
        System.out.println(validator.validate());
        
        
        
//        Brand b = new Brand("asd", true);
//        
//        BrandGeneralController controller = new BrandGeneralController();
//        Object read = controller.read(b);
//        try {
//            String input_date = "03/06/2015";
//            
//System.out.println("#" + dt1);
//            DateFormat format2 = new SimpleDateFormat("EEEE");
//            String finalDay = format2.format(dt1);
//            System.out.println("#" + finalDay);
//        } catch (ParseException ex) {
//            Logger.getLogger(TEST.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
