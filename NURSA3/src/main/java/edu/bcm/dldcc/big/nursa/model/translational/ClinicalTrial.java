package edu.bcm.dldcc.big.nursa.model.translational;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import edu.bcm.dldcc.big.nursa.data.common.DateUnit;
import edu.bcm.dldcc.big.nursa.model.common.Reference;
import edu.bcm.dldcc.big.nursa.model.common.TranslationalSynonym;

@Entity
public class ClinicalTrial extends Translational {

	private static final long serialVersionUID = -6539873159916956194L;
	
	@OneToOne(cascade=CascadeType.ALL)
	private TranslationalSynonym nctID;
	
	@OneToOne(cascade=CascadeType.ALL)
	private TranslationalSynonym officialTitle;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private Map<String, TranslationalSynonym> studyId = new HashMap<String, TranslationalSynonym>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CT_CTSponsor")
	private List<ClinicalTrialSponsor> sponsors = new ArrayList<ClinicalTrialSponsor>();
	
	@ManyToOne(cascade=CascadeType.ALL)
	private ClinicalTrialSource ctSource;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CT_CTOversight")
	private List<ClinicalTrialOversight> oversightAuthorities = new ArrayList<ClinicalTrialOversight>();
	
	private Boolean oversightHasDMC;
	
	@Lob
	private String detailedDescription;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private ClinicalTrialStatus status;
	
	private Date startDate;
	
	private Date completionDate;
	private String completionDateType;
	
	private Date primaryCompletionDate;
	private String primaryCompletionDateType;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private ClinicalTrialPhase phase;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private ClinicalTrialStudyType studyType;
	
	@ElementCollection
	private Map<String, String> studyDesign = new HashMap<String, String>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CT_CTOutcome")
	private List<ClinicalTrialOutcome> primaryOutcome = new ArrayList<ClinicalTrialOutcome>();
	
	private Integer numberOfArms;
	
	private Integer enrollmentNumber;
	private String enrollmentType;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CT_CONDITION")
	private List<Disease> condition = new ArrayList<Disease>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CT_DRUG")
	private List<Drug> drugs = new ArrayList<Drug>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CT_CTIntervention")
	private List<ClinicalTrialIntervention> interventions = new ArrayList<ClinicalTrialIntervention>();
	
	@Column(length=3500)
	private String eligibilityTextBlock;
	@ManyToOne(cascade=CascadeType.ALL)
	private Gender eligibilityGender;
	private Integer eligibilityMinimumAgeValue;
	@ManyToOne(cascade=CascadeType.ALL)
	private DateUnit eligibilityMinimumAgeUnit;
	private Integer eligibilityMaximumAgeValue;
	@ManyToOne(cascade=CascadeType.ALL)
	private DateUnit eligibilityMaximumAgeUnit;
	private Boolean eligibilityHealthVolunteers;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Official overallOfficial;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Location> location = new ArrayList<Location>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Country> countries = new ArrayList<Country>();
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Reference resultsReference;
	
	private Date verificationDate;
	private Date lastChangedDate;
	private Date firstReceivedDate;
	
	private Boolean fdaRegulated;
	private Boolean section801;
	private Boolean expandedAccess;
	
	
	/**
	 * @return the nctID
	 */
	public TranslationalSynonym getNctID() {
		return nctID;
	}


	/**
	 * @param nctID the nctID to set
	 */
	public void setNctID(TranslationalSynonym nctID) {
		this.nctID = nctID;
	}


	public ClinicalTrial() {
		setType("Clinical Trial");
	}


	/**
	 * @return the officialTitle
	 */
	public TranslationalSynonym getOfficialTitle() {
		return officialTitle;
	}


	/**
	 * @param officialTitle the officialTitle to set
	 */
	public void setOfficialTitle(TranslationalSynonym officialTitle) {
		this.officialTitle = officialTitle;
	}


	/**
	 * @return the studyId
	 */
	public Map<String, TranslationalSynonym> getStudyId() {
		return studyId;
	}


	/**
	 * @param studyId the studyId to set
	 */
	public void setStudyId(Map<String, TranslationalSynonym> studyId) {
		this.studyId = studyId;
	}


	/**
	 * @return the sponsors
	 */
	public List<ClinicalTrialSponsor> getSponsors() {
		return sponsors;
	}


	/**
	 * @param sponsors the sponsors to set
	 */
	public void setSponsors(List<ClinicalTrialSponsor> sponsors) {
		this.sponsors = sponsors;
	}


	/**
	 * @return the ctSource
	 */
	public ClinicalTrialSource getCtSource() {
		return ctSource;
	}


	/**
	 * @param ctSource the source to set
	 */
	public void setCtSource(ClinicalTrialSource ctSource) {
		this.ctSource = ctSource;
	}


	/**
	 * @return the oversightAuthorities
	 */
	public List<ClinicalTrialOversight> getOversightAuthorities() {
		return oversightAuthorities;
	}


	/**
	 * @param oversightAuthorities the oversightAuthorities to set
	 */
	public void setOversightAuthorities(
			List<ClinicalTrialOversight> oversightAuthorities) {
		this.oversightAuthorities = oversightAuthorities;
	}


	/**
	 * @return the oversightHasDMC
	 */
	public Boolean getOversightHasDMC() {
		return oversightHasDMC;
	}


	/**
	 * @param oversightHasDMC the oversightHasDMC to set
	 */
	public void setOversightHasDMC(Boolean oversightHasDMC) {
		this.oversightHasDMC = oversightHasDMC;
	}

	/**
	 * @return the detailedDescription
	 */
	public String getDetailedDescription() {
		return detailedDescription;
	}


	/**
	 * @param detailedDescription the detailedDescription to set
	 */
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}


	/**
	 * @return the status
	 */
	public ClinicalTrialStatus getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(ClinicalTrialStatus status) {
		this.status = status;
	}


	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}


	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	/**
	 * @return the completionDate
	 */
	public Date getCompletionDate() {
		return completionDate;
	}


	/**
	 * @param completionDate the completionDate to set
	 */
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}


	/**
	 * @return the completionDateType
	 */
	public String getCompletionDateType() {
		return completionDateType;
	}


	/**
	 * @param completionDateType the completionDateType to set
	 */
	public void setCompletionDateType(String completionDateType) {
		this.completionDateType = completionDateType;
	}


	/**
	 * @return the primaryCompletionDate
	 */
	public Date getPrimaryCompletionDate() {
		return primaryCompletionDate;
	}


	/**
	 * @param primaryCompletionDate the primaryCompletionDate to set
	 */
	public void setPrimaryCompletionDate(Date primaryCompletionDate) {
		this.primaryCompletionDate = primaryCompletionDate;
	}


	/**
	 * @return the primaryCompletionDateType
	 */
	public String getPrimaryCompletionDateType() {
		return primaryCompletionDateType;
	}


	/**
	 * @param primaryCompletionDateType the primaryCompletionDateType to set
	 */
	public void setPrimaryCompletionDateType(String primaryCompletionDateType) {
		this.primaryCompletionDateType = primaryCompletionDateType;
	}


	/**
	 * @return the phase
	 */
	public ClinicalTrialPhase getPhase() {
		return phase;
	}


	/**
	 * @param phase the phase to set
	 */
	public void setPhase(ClinicalTrialPhase phase) {
		this.phase = phase;
	}


	/**
	 * @return the studyType
	 */
	public ClinicalTrialStudyType getStudyType() {
		return studyType;
	}


	/**
	 * @param studyType the studyType to set
	 */
	public void setStudyType(ClinicalTrialStudyType studyType) {
		this.studyType = studyType;
	}


	/**
	 * @return the studyDesign
	 */
	public Map<String, String> getStudyDesign() {
		return studyDesign;
	}


	/**
	 * @param studyDesign the studyDesign to set
	 */
	public void setStudyDesign(Map<String, String> studyDesign) {
		this.studyDesign = studyDesign;
	}


	/**
	 * @return the primaryOutcome
	 */
	public List<ClinicalTrialOutcome> getPrimaryOutcome() {
		return primaryOutcome;
	}


	/**
	 * @param primaryOutcome the primaryOutcome to set
	 */
	public void setPrimaryOutcome(List<ClinicalTrialOutcome> primaryOutcome) {
		this.primaryOutcome = primaryOutcome;
	}


	/**
	 * @return the numberOfArms
	 */
	public Integer getNumberOfArms() {
		return numberOfArms;
	}


	/**
	 * @param numberOfArms the numberOfArms to set
	 */
	public void setNumberOfArms(Integer numberOfArms) {
		this.numberOfArms = numberOfArms;
	}


	/**
	 * @return the enrollmentNumber
	 */
	public Integer getEnrollmentNumber() {
		return enrollmentNumber;
	}


	/**
	 * @param enrollmentNumber the enrollmentNumber to set
	 */
	public void setEnrollmentNumber(Integer enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}


	/**
	 * @return the enrollmentType
	 */
	public String getEnrollmentType() {
		return enrollmentType;
	}


	/**
	 * @param enrollmentType the enrollmentType to set
	 */
	public void setEnrollmentType(String enrollmentType) {
		this.enrollmentType = enrollmentType;
	}


	/**
	 * @return the condition
	 */
	public List<Disease> getCondition() {
		return condition;
	}


	/**
	 * @param condition the condition to set
	 */
	public void setCondition(List<Disease> condition) {
		this.condition = condition;
	}


	/**
	 * @return the drugs
	 */
	public List<Drug> getDrugs() {
		return drugs;
	}


	/**
	 * @param drugs the drugs to set
	 */
	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}


	/**
	 * @return the interventions
	 */
	public List<ClinicalTrialIntervention> getInterventions() {
		return interventions;
	}


	/**
	 * @param interventions the interventions to set
	 */
	public void setInterventions(List<ClinicalTrialIntervention> interventions) {
		this.interventions = interventions;
	}


	/**
	 * @return the eligibilityTextBlock
	 */
	public String getEligibilityTextBlock() {
		return eligibilityTextBlock;
	}


	/**
	 * @param eligibilityTextBlock the eligibilityTextBlock to set
	 */
	public void setEligibilityTextBlock(String eligibilityTextBlock) {
		this.eligibilityTextBlock = eligibilityTextBlock;
	}


	/**
	 * @return the eligibilityGender
	 */
	public Gender getEligibilityGender() {
		return eligibilityGender;
	}


	/**
	 * @param eligibilityGender the eligibilityGender to set
	 */
	public void setEligibilityGender(Gender eligibilityGender) {
		this.eligibilityGender = eligibilityGender;
	}




	/**
	 * @return the eligibilityMinimumAgeValue
	 */
	public Integer getEligibilityMinimumAgeValue() {
		return eligibilityMinimumAgeValue;
	}


	/**
	 * @param eligibilityMinimumAgeValue the eligibilityMinimumAgeValue to set
	 */
	public void setEligibilityMinimumAgeValue(Integer eligibilityMinimumAgeValue) {
		this.eligibilityMinimumAgeValue = eligibilityMinimumAgeValue;
	}


	/**
	 * @return the eligibilityMinimumAgeUnit
	 */
	public DateUnit getEligibilityMinimumAgeUnit() {
		return eligibilityMinimumAgeUnit;
	}


	/**
	 * @param eligibilityMinimumAgeUnit the eligibilityMinimumAgeUnit to set
	 */
	public void setEligibilityMinimumAgeUnit(DateUnit eligibilityMinimumAgeUnit) {
		this.eligibilityMinimumAgeUnit = eligibilityMinimumAgeUnit;
	}


	/**
	 * @return the eligibilityMaximumAgeValue
	 */
	public Integer getEligibilityMaximumAgeValue() {
		return eligibilityMaximumAgeValue;
	}


	/**
	 * @param eligibilityMaximumAgeValue the eligibilityMaximumAgeValue to set
	 */
	public void setEligibilityMaximumAgeValue(Integer eligibilityMaximumAgeValue) {
		this.eligibilityMaximumAgeValue = eligibilityMaximumAgeValue;
	}


	/**
	 * @return the eligibilityMaximumAgeUnit
	 */
	public DateUnit getEligibilityMaximumAgeUnit() {
		return eligibilityMaximumAgeUnit;
	}


	/**
	 * @param eligibilityMaximumAgeUnit the eligibilityMaximumAgeUnit to set
	 */
	public void setEligibilityMaximumAgeUnit(DateUnit eligibilityMaximumAgeUnit) {
		this.eligibilityMaximumAgeUnit = eligibilityMaximumAgeUnit;
	}


	/**
	 * @return the eligibilityHealthVolunteers
	 */
	public Boolean getEligibilityHealthVolunteers() {
		return eligibilityHealthVolunteers;
	}


	/**
	 * @param eligibilityHealthVolunteers the eligibilityHealthVolunteers to set
	 */
	public void setEligibilityHealthVolunteers(Boolean eligibilityHealthVolunteers) {
		this.eligibilityHealthVolunteers = eligibilityHealthVolunteers;
	}


	/**
	 * @return the overallOfficial
	 */
	public Official getOverallOfficial() {
		return overallOfficial;
	}


	/**
	 * @param overallOfficial the overallOfficial to set
	 */
	public void setOverallOfficial(Official overallOfficial) {
		this.overallOfficial = overallOfficial;
	}


	/**
	 * @return the location
	 */
	public List<Location> getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(List<Location> location) {
		this.location = location;
	}


	/**
	 * @return the countries
	 */
	public List<Country> getCountries() {
		return countries;
	}


	/**
	 * @param countries the countries to set
	 */
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}


	/**
	 * @return the resultsReference
	 */
	public Reference getResultsReference() {
		return resultsReference;
	}


	/**
	 * @param resultsReference the resultsReference to set
	 */
	public void setResultsReference(Reference resultsReference) {
		this.resultsReference = resultsReference;
	}


	/**
	 * @return the verificationDate
	 */
	public Date getVerificationDate() {
		return verificationDate;
	}


	/**
	 * @param verificationDate the verificationDate to set
	 */
	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}


	/**
	 * @return the lastChangedDate
	 */
	public Date getLastChangedDate() {
		return lastChangedDate;
	}


	/**
	 * @param lastChangedDate the lastChangedDate to set
	 */
	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}


	/**
	 * @return the firstReceivedDate
	 */
	public Date getFirstReceivedDate() {
		return firstReceivedDate;
	}


	/**
	 * @param firstReceivedDate the firstReceivedDate to set
	 */
	public void setFirstReceivedDate(Date firstReceivedDate) {
		this.firstReceivedDate = firstReceivedDate;
	}


	/**
	 * @return the fdaRegulated
	 */
	public Boolean getFdaRegulated() {
		return fdaRegulated;
	}


	/**
	 * @param fdaRegulated the fdaRegulated to set
	 */
	public void setFdaRegulated(Boolean fdaRegulated) {
		this.fdaRegulated = fdaRegulated;
	}


	/**
	 * @return the section801
	 */
	public Boolean getSection801() {
		return section801;
	}


	/**
	 * @param section801 the section801 to set
	 */
	public void setSection801(Boolean section801) {
		this.section801 = section801;
	}


	/**
	 * @return the expandedAccess
	 */
	public Boolean getExpandedAccess() {
		return expandedAccess;
	}


	/**
	 * @param expandedAccess the expandedAccess to set
	 */
	public void setExpandedAccess(Boolean expandedAccess) {
		this.expandedAccess = expandedAccess;
	}
	
	

}
