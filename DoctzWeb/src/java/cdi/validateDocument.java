/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@FacesValidator(value="validateDocumentBean")
public class validateDocument implements Validator{

    /**
     * Creates a new instance of validateDocument
     */
    public validateDocument() {
    }
    
     @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
        Part file = (Part) value;
        FacesMessage message=null;
        
        
          try {
 
            if (file==null || file.getSize()<=0 || file.getContentType().isEmpty() )
                message=new FacesMessage("Must not be empty");
            else if (!file.getContentType().endsWith("pdf"))
                message=new FacesMessage("Select valid document(pdf format)");
            
            if (message!=null && !message.getDetail().isEmpty())
                {
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(message );
                }
 
        } catch (Exception ex) {
               throw new ValidatorException(new FacesMessage(ex.getMessage()));
        }
    }
    
    
}
