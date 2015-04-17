package edu.bcm.dldcc.big.nursa.util.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Util to calculate URLs
 * @author mcowiti
 *
 */
@Named("urlUtil")
@ApplicationScoped
public class DatasourceUrlUtil {

	
	String patternString = "(\\$\\{ID\\}|\\$\\{GENE.OFFICIAL.NAME\\})";
	Pattern pattern = Pattern.compile(patternString);
	
	
	//TODO move these to DB on Ref object?
	private String pubmedBaseUrl="http://www.ncbi.nlm.nih.gov/pubmed/";
	private String pubmedAbstractBaseUrl="http://www.ncbi.nlm.nih.gov/pubmed/";
	
	
	@Produces
	public String getPubmedBaseUrl() {
		return pubmedBaseUrl;
	}

	@Produces
	public String getPubmedAbstractBaseUrl() {
		return pubmedAbstractBaseUrl;
	}

	public  String calculatedUrl(String baseUrl,String id,String name){
		Map<String,String> tokens = new HashMap<String,String>();
		tokens.put("${ID}", id);
		tokens.put("${GENE.OFFICIAL.NAME}", name);

		Matcher matcher = pattern.matcher(baseUrl);

		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
		    matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	public String calcPubmedUrl(String baseUrl,String pubmedId){
		return new StringBuffer(baseUrl).append(pubmedId).toString();
	}
	
}
