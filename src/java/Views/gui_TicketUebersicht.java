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
//import java.util.Collection;
import java.util.List;
import java.time.LocalDateTime;
import java.util.TreeMap;
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
    String Tutor;
    List<SelectItem> CourseList;
    List<SelectItem> CategoryList;
    List<SelectItem> PriorityList;
    List<SelectItem> StatusList;
    List<SelectItem> TutorList;
    
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
        
        // Tutoren laden
        TutorList = new ArrayList<SelectItem>();
        i = 1;
        Result = "Start";
        Integer Max = Integer.parseInt(DBController.GetData("user", "MAX(id)", ""));
        TutorList.add(new SelectItem("", ""));
        while ( i <= Max ) 
        {   
           Result = DBController.GetData("user", "username", "where id = '" + i + "' and id_usergroup in (1,3)");
           if (Result != null)
          {
            TutorList.add(new SelectItem(Result, Result));
          }
            i++;
        }
    }
    
    public String Search()
        {
            // Variablen
            Integer ID_Cat;
            Integer ID_Cou;
            Integer ID_Pri;
            Integer ID_User;
            Integer ID_Admin;
            Integer ID_Sta;
            String Clause;
            String MaxValues;
            String OrderBy;
            
            //Auslesen der ID's
            ID_Cat = Integer.parseInt(DBController.GetData ("CATEGORY", "id", "WHERE name='" + Category + "'"));
            ID_Cou = Integer.parseInt(DBController.GetData ("COURSES", "id", "WHERE name='" + Course + "'"));
            ID_Sta = Integer.parseInt(DBController.GetData ("STATE", "id", "WHERE name='New'"));
            ID_Pri = Integer.parseInt(DBController.GetData ("PRIORITY", "id", "WHERE name='" + Priority + "'"));
            ID_Admin = Integer.parseInt(DBController.GetData ("USER", "id", "WHERE username = '" + Tutor + "'"));
            ID_User = Integer.parseInt(DBController.GetData ("USER", "id", "WHERE username = '" + Username + "'"));
            
            // SQL Statement vorbereiten
            Clause = "id_user = '" + ID_User + "'";
            
            // ID
            if (TicketID.isEmpty() == false )
            {
                Clause = "id = " + TicketID;
            }
            
            // Priorität
            if (Priority.isEmpty() == false )
            {
                Clause = Clause + " and id_priority = '" + ID_Pri + "'";
            }
            
            // Kategorie
            if (Category.isEmpty() == false )
            {
                Clause = Clause + " and id_category = '" + ID_Cat + "'";
            }
            
            // Kurs
            if (Course.isEmpty() == false )
            {
                Clause = Clause + " and id_course = '" + ID_Cou + "'";
            }
            
            // Erstellungsdatum
            if (CreationDate.isEmpty() == false )
            {
                Clause = Clause + " and creationDate = '" + CreationDate + "'";
            }
            
            // Status
            if (Status.isEmpty() == false )
            {
                Clause = Clause + " and state = '" + ID_Sta + "'";
            }
            
            // Zuordnung
            if (Tutor.isEmpty() == false )
            {
                Clause = Clause + " and id_user2 = '" + ID_Admin + "'";
            }
            
            // MaxValues festlegen
           if (SortOrder.equalsIgnoreCase("Alle"))
           {
               MaxValues = "";
           }
           else
           {
               MaxValues = "TOP " + SortOrder;
           }
           
           
            // Map für ID
            TreeMap<String, String> Map_ID = new TreeMap<String, String>();
            
            // Map für Kurs
            TreeMap<String, String> Map_Course = new TreeMap<String, String>();
            
            // Map für Titel
            TreeMap<String, String> Map_Title = new TreeMap<String, String>();
            
            // Map für Status
            TreeMap<String, String> Map_State = new TreeMap<String, String>();
            
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
    public List<SelectItem> getTutorList()
    {
    return TutorList;
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
    public String getTutor() 
    {
	return Tutor;
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
    public void setTutor(String Tutor) 
    {
        this.Tutor = Tutor;
    }

}

    

