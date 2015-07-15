
import com.development.simplestockmanager.business.persistence.Language;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.object.component.selector.type.EmployeeTypeSelector;
import com.development.simplestockmanager.web.object.component.selector.type.LanguageSelector;
import com.development.simplestockmanager.web.object.component.selector.type.SexTypeSelector;



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
        LanguageSelector languageSelector = new LanguageSelector(WebConstant.SELECTOR.MODE.NONE, "en_US");
        languageSelector.setSelection(languageSelector.getList().get(2));
        Language selectedValue = languageSelector.getSelectedValue();
        
        LanguageSelector languageSelector1 = new LanguageSelector(WebConstant.SELECTOR.MODE.NONE, "ca_ES", selectedValue);
        System.out.println(languageSelector1.getSelection());
        System.out.println(languageSelector1.getSelectedValue());
//        EmployeeTypeSelector employeeTypeSelector = new EmployeeTypeSelector(WebConstant.SELECTOR.MODE.ALL, "es_ES");
//        System.out.println(employeeTypeSelector.getList());
////        System.out.println(selector.getDisplayName(sexType));
//        System.out.println(selector.getList());
//        selector.setSelection("");
//        System.out.println(selector.getSelectedValue());
    }
}
