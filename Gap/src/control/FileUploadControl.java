package control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import model.CompostoBean;
import model.CompostoModel;
import model.ProdottoBean;
import model.ProdottoModel;

@WebServlet(name = "/FileUploadControl", urlPatterns = { "/fileupload" }, initParams = {
		@WebInitParam(name = "file-upload", value = "Elementi") })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
														// temporarily stored on disk
		maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
		maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
public class FileUploadControl extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String SAVE_DIR = "";

	public void init() {
		// Get the file location where it would be stored
		SAVE_DIR = getServletConfig().getInitParameter("file-upload"); //piglia il parametro inizializzato prima quindi Elementi
	}

	public FileUploadControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		out.write("Error: GET method is used but POST method is required");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource"); 
		String savePath = request.getServletContext().getRealPath("") + File.separator + SAVE_DIR;
		ProdottoBean prodotto = new ProdottoBean();
		//prodotto.setCodice((int)(Math.random()*10000000));
		prodotto.setNome(request.getParameter("nome"));
		prodotto.setAltezza(Integer.parseInt(request.getParameter("altezza")));
		prodotto.setPrezzo(Integer.parseInt(request.getParameter("profondita")));
		prodotto.setLarghezza(Integer.parseInt(request.getParameter("larghezza")));
		prodotto.setTipologia(request.getParameter("tipologia"));
		prodotto.setQuantita(Integer.parseInt(request.getParameter("quantita")));
		prodotto.setPrezzo(Integer.parseInt(request.getParameter("prezzo")));
		prodotto.setSconto(Integer.parseInt(request.getParameter("sconto")));
		ProdottoModel model = new ProdottoModel(ds);
		try {
			model.doSave(prodotto);
		} catch (SQLException e) {
			System.out.println("Eccezione salvataggio prodotto");
			e.printStackTrace();
		}
		
		
		if(request.getParameter("abelia_monocromo") != null && (request.getParameter("abelia_monocromo") != ""))
		{
			String idMateriale = request.getParameter("abelia_monocromo");
			int id = Integer.parseInt(idMateriale);
			ProdottoBean bean= null;
			try {
				bean = model.doRetrieveByKey(prodotto.getNome());
			} catch (SQLException e) {
				System.out.println("Eccezione recupero prodotto dal database");
				e.printStackTrace();
			}
			CompostoModel modelComposto = new CompostoModel(ds);
			CompostoBean composto = new CompostoBean();
			composto.setCodiceProdotto(bean.getCodice());
			composto.setIdMateriale(id);
			try {
				modelComposto.doSave(composto);
			} catch (SQLException e) {
				System.out.println("Errore salvataggio composto di prodotto e materiale");
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("bergonia_marrone") != null && (request.getParameter("bergonia_marrone") != ""))
		{
			String idMateriale = request.getParameter("bergonia_marrone");
			int id = Integer.parseInt(idMateriale);
			ProdottoBean bean= null;
			try {
				bean = model.doRetrieveByKey(prodotto.getNome());
			} catch (SQLException e) {
				System.out.println("Eccezione recupero prodotto dal database");
				e.printStackTrace();
			}
			CompostoModel modelComposto = new CompostoModel(ds);
			CompostoBean composto = new CompostoBean();
			composto.setCodiceProdotto(bean.getCodice());
			composto.setIdMateriale(id);
			try {
				modelComposto.doSave(composto);
			} catch (SQLException e) {
				System.out.println("Errore salvataggio composto di prodotto e materiale");
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("lilum_blu") != null && (request.getParameter("lilum_blu") != ""))
		{
			String idMateriale = request.getParameter("lilum_blu");
			int id = Integer.parseInt(idMateriale);
			ProdottoBean bean= null;
			try {
				bean = model.doRetrieveByKey(prodotto.getNome());
			} catch (SQLException e) {
				System.out.println("Eccezione recupero prodotto dal database");
				e.printStackTrace();
			}
			CompostoModel modelComposto = new CompostoModel(ds);
			CompostoBean composto = new CompostoBean();
			composto.setCodiceProdotto(bean.getCodice());
			composto.setIdMateriale(id);
			try {
				modelComposto.doSave(composto);
			} catch (SQLException e) {
				System.out.println("Errore salvataggio composto di prodotto e materiale");
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("liroe_bianco") != null && (request.getParameter("liroe_bianco") != ""))
		{
			String idMateriale = request.getParameter("liroe_bianco");
			int id = Integer.parseInt(idMateriale);
			ProdottoBean bean= null;
			try {
				bean = model.doRetrieveByKey(prodotto.getNome());
			} catch (SQLException e) {
				System.out.println("Eccezione recupero prodotto dal database");
				e.printStackTrace();
			}
			CompostoModel modelComposto = new CompostoModel(ds);
			CompostoBean composto = new CompostoBean();
			composto.setCodiceProdotto(bean.getCodice());
			composto.setIdMateriale(id);
			try {
				modelComposto.doSave(composto);
			} catch (SQLException e) {
				System.out.println("Errore salvataggio composto di prodotto e materiale");
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("pelle_beige") != null && (request.getParameter("pelle_beige") != ""))
		{
			String idMateriale = request.getParameter("pelle_beige");
			int id = Integer.parseInt(idMateriale);
			ProdottoBean bean= null;
			try {
				bean = model.doRetrieveByKey(prodotto.getNome());
			} catch (SQLException e) {
				System.out.println("Eccezione recupero prodotto dal database");
				e.printStackTrace();
			}
			CompostoModel modelComposto = new CompostoModel(ds);
			CompostoBean composto = new CompostoBean();
			composto.setCodiceProdotto(bean.getCodice());
			composto.setIdMateriale(id);
			try {
				modelComposto.doSave(composto);
			} catch (SQLException e) {
				System.out.println("Errore salvataggio composto di prodotto e materiale");
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("pelle_nero") != null && (request.getParameter("pelle_nero") != ""))
		{
			String idMateriale = request.getParameter("pelle_nero");
			int id = Integer.parseInt(idMateriale);
			ProdottoBean bean= null;
			try {
				bean = model.doRetrieveByKey(prodotto.getNome());
			} catch (SQLException e) {
				System.out.println("Eccezione recupero prodotto dal database");
				e.printStackTrace();
			}
			CompostoModel modelComposto = new CompostoModel(ds);
			CompostoBean composto = new CompostoBean();
			composto.setCodiceProdotto(bean.getCodice());
			composto.setIdMateriale(id);
			try {
				modelComposto.doSave(composto);
			} catch (SQLException e) {
				System.out.println("Errore salvataggio composto di prodotto e materiale");
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("pelle_ombra") != null && (request.getParameter("pelle_ombra") != ""))
		{
			String idMateriale = request.getParameter("pelle_ombra");
			int id = Integer.parseInt(idMateriale);
			ProdottoBean bean= null;
			try {
				bean = model.doRetrieveByKey(prodotto.getNome());
			} catch (SQLException e) {
				System.out.println("Eccezione recupero prodotto dal database");
				e.printStackTrace();
			}
			CompostoModel modelComposto = new CompostoModel(ds);
			CompostoBean composto = new CompostoBean();
			composto.setCodiceProdotto(bean.getCodice());
			composto.setIdMateriale(id);
			try {
				modelComposto.doSave(composto);
			} catch (SQLException e) {
				System.out.println("Errore salvataggio composto di prodotto e materiale");
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("pelle_rosso") != null && (request.getParameter("pelle_rosso") != ""))
		{
			String idMateriale = request.getParameter("pelle_rosso");
			int id = Integer.parseInt(idMateriale);
			ProdottoBean bean= null;
			try {
				bean = model.doRetrieveByKey(prodotto.getNome());
			} catch (SQLException e) {
				System.out.println("Eccezione recupero prodotto dal database");
				e.printStackTrace();
			}
			CompostoModel modelComposto = new CompostoModel(ds);
			CompostoBean composto = new CompostoBean();
			composto.setCodiceProdotto(bean.getCodice());
			composto.setIdMateriale(id);
			try {
				modelComposto.doSave(composto);
			} catch (SQLException e) {
				System.out.println("Errore salvataggio composto di prodotto e materiale");
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("santolina_bordeaux") != null && (request.getParameter("santolina_bordeaux") != ""))
		{
			String idMateriale = request.getParameter("santolina_bordeaux");
			int id = Integer.parseInt(idMateriale);
			ProdottoBean bean= null;
			try {
				bean = model.doRetrieveByKey(prodotto.getNome());
			} catch (SQLException e) {
				System.out.println("Eccezione recupero prodotto dal database");
				e.printStackTrace();
			}
			CompostoModel modelComposto = new CompostoModel(ds);
			CompostoBean composto = new CompostoBean();
			composto.setCodiceProdotto(bean.getCodice());
			composto.setIdMateriale(id);
			try {
				modelComposto.doSave(composto);
			} catch (SQLException e) {
				System.out.println("Errore salvataggio composto di prodotto e materiale");
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("solidago_monocromo") != null && (request.getParameter("solidago_monocromo") != ""))
		{
			String idMateriale = request.getParameter("solidago_monocromo");
			int id = Integer.parseInt(idMateriale);
			ProdottoBean bean= null;
			try {
				bean = model.doRetrieveByKey(prodotto.getNome());
			} catch (SQLException e) {
				System.out.println("Eccezione recupero prodotto dal database");
				e.printStackTrace();
			}
			CompostoModel modelComposto = new CompostoModel(ds);
			CompostoBean composto = new CompostoBean();
			composto.setCodiceProdotto(bean.getCodice());
			composto.setIdMateriale(id);
			try {
				modelComposto.doSave(composto);
			} catch (SQLException e) {
				System.out.println("Errore salvataggio composto di prodotto e materiale");
				e.printStackTrace();
			}
		}
		
		
		
		
		File fileSaveDir = new File(savePath); //path dove salvare il file
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir(); //se non trova il path lo crea
		}

		String message = "upload =\n";
		if (request.getParts() != null && request.getParts().size() > 0) {
			for (Part part : request.getParts()) {
				String fileName = prodotto.getNome() + ".jpg";
				//System.out.println(fileName);//estrae nome file 
				if (fileName != null && !fileName.equals("")) { //se l'info è valida
					part.write(savePath + File.separator + fileName); //salva il file nel path
		//System.out.println(savePath + File.separator + fileName);
					message = message + fileName + "\n";
				} else {
					request.setAttribute("error", "Errore: Bisogna selezionare almeno un file");

				}
			}
		}

		request.setAttribute("message", message);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(response.encodeURL("/Index.jsp"));
		dispatcher.forward(request, response);

	}
}
