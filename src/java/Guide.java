/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elias
 */
public class Guide {
    
    private String id;
    private String name;
   
   // private String languages; 
 
    
    public Guide(String pid, String pname)
    {
        this.id = pid;
        this.name = pname;
       
      
    }
/*
    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
    */
    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
}

