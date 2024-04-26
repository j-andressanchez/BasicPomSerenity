package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.pages.PageObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;


public class Servicios extends PageObject {		
	
	private static Map<String, String> header = new HashMap<>();
	private static String strRequestBody;
	private static Response response;
	private static HttpResponse responseHttp;
	private static RequestConfig requestConfig = RequestConfig.custom().build();
	private static List<NameValuePair> urlParameters = new ArrayList<>();
	
	
	/**
	 * Metodo que dispara metodos POST de servicios REST
	 * 
	 * @param strBody String que contiene el cuerpo del request
	 * @param strUrlServicio String que contiene la url del servicio a consumir
	 * @param header Mapa con los datos a configurar como cabeceras del servicio
	 */
	public Response ejecutarPOSTServicioREST(String strBody,  String strUrlServicio, Map<String, String> header) {	
		try {
			return SerenityRest.givenWithNoReporting()
					.headers(header)
			        .and()	
			        .body(strBody)
			        .when()
			        .post(strUrlServicio);
		}catch(Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
		
		return null;		
	}	
	
	/**
	 * Metodo que agrega valores al header a utilizar en el request
	 * 
	 * @param strKey String de key a agregar al header
	 * @param strValor String de valor a agregar al header
	 */
	public void agregarHeader(String strKey, String strValor) {
		header.put(strKey, strValor);
	}
	
	/**
	 * Metodo que retorna header con valores cargados previamente
	 * 
	 */
	public Map<String, String> getHeader() {
		return header;
	}
	
	/**
	 * Metodo que asigna valor de body a variable para reutilizar
	 * 
	 */
	public static void setBodyRequest(String strBody) {
		strRequestBody = strBody;
	}
	
	/**
	 * Metodo que retorna body request guardado previamente
	 * 
	 */
	public String getBodyRequest() {
		return strRequestBody;
	}
	
	/**
	 * Metodo que obtiene token de autorizacion para servicio a partir del ambiente indicado
	 * 
	 * @param strClave String con valor de key a reemplazar en el body
	 * @param strValue String con valor de dato a reemplazar en el body
	 */	
	public static void modificarBodyRequest(String strClave, String strValor) {
		try {
			strRequestBody = strRequestBody.replace(strClave, strValor);
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}
	
	/**
	 * Metodo que asigna valor de reponse a variable para reutilizar
	 * 
	 */
	public static void setResponse(Response resp) {
		response = resp;		
	}
	
	/**
	 * Metodo que retorna reponse guardado previamente
	 * 
	 */
	public Response getResponse() {
		return response;
	}	
	
	/**
	 * Metodo que asigna valor de parametros de request form-data
	 * 
	 */
	public void setParametrosFormData(String strKey, String strValor) {			
		urlParameters.add(new BasicNameValuePair(strKey, strValor));		
	}
	
	/**
	 * Metodo que retorna valor de parametros de request form-data
	 * 
	 */
	public List<NameValuePair> getParametrosFormData(){
		return urlParameters; 
	}
	
	/**
	 * Metodo que dispara metodos POST de servicios form-data
	 * 
	 * @param strUrlServicio String que contiene la url del servicio a consumir
	 * @param params Lista con los datos a configurar como parametros del servicio
	 */
	public HttpResponse ejecutarPOSTServicioFormData(String strUrlServicio, List<NameValuePair> params){
		
		try {			
			
			HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();        
	        HttpPost request = new HttpPost(strUrlServicio);	        
	        List<Map<String, String>> data = new ArrayList<>();
	        data.add(getHeader());
	        
	        for(int i = 0; i < data.size(); i++) {
	        	
	        	for (Entry<String, String> mapHeader : data.get(i).entrySet()) {
	        		request.setHeader(mapHeader.getKey(), mapHeader.getValue());					
				}
	        }
	        
	        request.setEntity(new UrlEncodedFormEntity(params));
	        setResponseHttp(httpClient.execute(request));	        
	        return responseHttp;
			
		}catch(Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
		
		return null;      
    }
	
	/**
	 * Metodo que asigna valor de reponse http a variable para reutilizar
	 * 
	 */
	public static void setResponseHttp(HttpResponse resp) {
		responseHttp = resp;		
	}
	
	/**
	 * Metodo que retorna reponse http guardado previamente
	 * 
	 */
	public HttpResponse getResponseHttp() {
		return responseHttp;
	}
}
