/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Database.DBController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.time.LocalDateTime;
import javax.faces.model.SelectItem;
import javax.annotation.PostConstruct;

/*
* gui_TicketUebersicht
* Funktionalität für Ticketsuche und Übersicht
*
* <Klassenvariablen>
    String Priority = Ticketpriorität
    String Category = Kategorie
    String Course = Ausgewählter Kurs
    String Title = Titel des Ticket
    String Comment = Notizen
    String NewComment = Neue Notizen
    String Username = Aktueller Benutzername
    String Status = Status des Ticket
    Date creationDate = Erstellungsdatum des Tickets
    Integer ReportedTime = Berechnete Zeit am Ticket
    List CourseList = Kursliste aus DB
    List CategoryList = Kategorielist aus DB
    List PriorityList = Prioritätenliste aus DB
    List StatusList = Statusliste aus DB
*
* <Sichtbarkeit>
* public
*/ 

// Managed Bean 
@ManagedBean
@SessionScoped

public class gui_TicketUebersicht 
{
    String Priority;
    String Category;
    String Course;
    String Title;
    //String Username = "Admin";
    String Status;
    String CreationDate;
    String TicketID;
    String SortOrder;
    String TotalResults;
    List<SelectItem> CourseList;
    List<SelectItem> CategoryList;
    List<SelectItem> PriorityList;
    List<SelectItem> StatusList;
    
   String Username = "Admin";
   
    // Initialisieren und laden der Select Einträge
    @PostConstruct
    public void Init()        
    {
        
        String Result;
        Integer i;
           
        // Prioritäten laden
        PriorityList = new ArrayList<SelectItem>();
        i = 1;
        Result = "Start";
        while (Result != null ) 
        {   
          Result = DBController.GetData("priority", "name", "where id= '" + i + "'");
          PriorityList.add(new SelectItem(Result, Result));
          i++;
        }
        
        // Kategorie laden
        CategoryList = new ArrayList<SelectItem>();
        i = 1;
        Result = "Start";
        while (Result != null ) 
        {   
          Result = DBController.GetData("category", "name", "where id= '" + i + "'");
          CategoryList.add(new SelectItem(Result, Result));
          i++;
        }
                
        // Kurse laden
        CourseList = new ArrayList<SelectItem>();
        i = 1;
        Result = "Start";
        while (Result != null ) 
        {   
          Result = DBController.GetData("courses", "name", "where id= '" + i + "'");
          CourseList.add(new SelectItem(Result, Result));
        i++;
        }
        
        // Status laden
        StatusList = new ArrayList<SelectItem>();
        i = 1;
        Result = "Start";
        while (Result != null ) 
        {   
          Result = DBController.GetData("state", "name", "where id= '" + i + "'");
          StatusList.add(new SelectItem(Result, Result));
          i++;
        }
    }
    
    public String Search()
        {
            
            return null;
        }
    
    public String Reset()
    {
        TicketID = "";
        Category = "";
        Priority = "";
        Course = "";
        Status = "";
        CreationDate = "";
        return null;
    }    
    
     // Getter Methoden
    public List<SelectItem> getCourseList()
    {
    return CourseList;
    }
    public List<SelectItem> getPriorityList()
    {
    return PriorityList;
    }
    public List<SelectItem> getCategoryList()
    {
    return CategoryList;
    }
    public List<SelectItem> getStatusList()
    {
    return StatusList;
    }
     public String getPriority() 
    {
	return Priority;
    }
    public String getCategory() 
    {
	return Category;
    }
    public String getCourse() 
    {
	return Course;
    }
    public String getTitle() 
    {
	return Title;
    }
    public String getUsername() 
    {
	return Username;
    }
    public String getStatus() 
    {
	return Status;
    }
    public String getCreationDate() 
    {
	return CreationDate;
    }
    public String getTicketID() 
    {
	return TicketID;
    }
    public String getSortOrder() 
    {
	return SortOrder;
    }
    public String getTotalResults() 
    {
	return TotalResults;
    }
    
    // Setter Methoden
    public void setPriority(String Priority) 
    {
        this.Priority = Priority;
    }
    public void setCategory(String Category) 
    {
        this.Category = Category;
    }
    public void setCourse(String Course) 
    {
        this.Course = Course;
    }
    public void setTitle(String Title) 
    {
        this.Title = Title;
    }
    public void setUsername(String Username) 
    {
        this.Username = Username;
    }
    public void setStatus(String Status) 
    {
        this.Status = Status;
    }
        public void setCreationDate(String CreationDate) 
    {
        this.CreationDate = CreationDate;
    }
    public void setTicketID(String TicketID) 
    {
        this.TicketID = TicketID;
    }
    public void setSortOrder(String SortOrder) 
    {
        this.SortOrder = SortOrder;
    }

}

    
