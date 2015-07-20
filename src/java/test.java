
import com.development.simplestockmanager.business.persistence.Language;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.object.selector.type.EmployeeTypeSelector;
import com.development.simplestockmanager.web.object.selector.type.LanguageSelector;
import com.development.simplestockmanager.web.object.selector.type.SexTypeSelector;
import com.development.simplestockmanager.web.service.general.UrlService;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author foxtrot
 */
public class test {
    public static void main(String[] args) {
        UrlService url = new UrlService();
        System.out.println(url.add("asd"));
    }
}
