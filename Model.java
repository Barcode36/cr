package rapports.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Alert;

public class Model {
    private String user;
    private String passwd;
    private String url;
    private String matricule;
    private static Model model; //Singleton
    private Connection connect; 
    private DoctorsModel doctorsModel;
    private ConnexionModel connexionModel;
    private MedicineModel medicineModel;
    private VisitorsModel visitorsModel;
    private ReportModel reportModel;
    private NewReportModel newReportModel;
    // Connexion à la base de donnée
    
    private Model(String user, String passwd){
        this.user = user;
        this.passwd = passwd;
        url="jdbc:mysql://mysql-vaalrhona.alwaysdata.net/vaalrhona_gsb_cr";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver OK");
            
            //Connexion à la db et instanciation du modèle de la fenêtre de connexion
            this.connect = DriverManager.getConnection(url, user, passwd);
            this.connexionModel = new ConnexionModel(connect,url);
        }
        catch (Exception e){
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setTitle("Erreur");
            error.setHeaderText(null);
            error.setContentText("Une erreur a été rencontrée, veuillez réessayer plus tard.");
            error.showAndWait();
            System.exit(0);
            e.printStackTrace();
        }
    }
    
    //Retourne l'unique instance du modèle
    public static Model getInstance()
    {
        if (model==null)
        {
            model = new Model("vaalrhona_view","view");
        }
        return model;
    }
    
    public ConnexionModel getConnexionModel()
    {
        return connexionModel;
    }
    
    public DoctorsModel getDoctorsModel()
    {
        return doctorsModel;
    }
    
    public MedicineModel getMedicineModel()
    {
        return medicineModel;
    }
    
    public VisitorsModel getVisitorsModel()
    {
        return visitorsModel;
    }
    
    public ReportModel getReportModel()
    {
        return reportModel;
    }
    
    public NewReportModel getNewReportModel()
    {
        return newReportModel;
    }
    
    public void setUser(String user)
    {
        this.user = user;
    }
    
    public void setPasswd(String passwd)
    {
        this.passwd = passwd;
    }
    
  //Refait la connexion avec les nouveaux user et mdp récupérés grâce au modèle de connexion
    public void setConnection()
    {
        try
        {
            this.connect = DriverManager.getConnection(url,user,passwd);
            this.doctorsModel = new DoctorsModel(connect);
            this.medicineModel = new MedicineModel(connect);
            this.visitorsModel = new VisitorsModel(connect);
            this.reportModel = new ReportModel(connect);
            this.newReportModel = new NewReportModel(connect,matricule);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void setMatricule(String matricule)
    {
        this.matricule = matricule;
    }
    
    public String getMatricule(String matricule)
    {
        return matricule;
    }
}
    
    