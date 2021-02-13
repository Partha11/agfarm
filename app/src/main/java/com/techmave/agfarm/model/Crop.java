package com.techmave.agfarm.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crop {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_bangla")
    @Expose
    private String nameBangla;
    @SerializedName("seedlings")
    @Expose
    private Seedlings seedlings;
    @SerializedName("cultivation")
    @Expose
    private Cultivation cultivation;
    @SerializedName("fertilizer")
    @Expose
    private List<Integer> fertilizer = null;
    @SerializedName("irrigation")
    @Expose
    private List<Irrigation> irrigation = null;
    @SerializedName("seed_amount")
    @Expose
    private String seedAmount;
    @SerializedName("nutrition")
    @Expose
    private String nutrition;
    @SerializedName("medicine_properties")
    @Expose
    private String medicineProperties;
    @SerializedName("use_case")
    @Expose
    private String useCase;
    @SerializedName("requirements")
    @Expose
    private String requirements;
    @SerializedName("cultivation_details")
    @Expose
    private String cultivationDetails;
    @SerializedName("fertilization_process")
    @Expose
    private String fertilizationProcess;
    @SerializedName("diseases")
    @Expose
    private List<Disease> diseases = null;
    @SerializedName("breeds")
    @Expose
    private List<Breed> breeds = null;
    @SerializedName("fertilizer_amount")
    @Expose
    private List<FertilizerAmount> fertilizerAmount = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameBangla() {
        return nameBangla;
    }

    public void setNameBangla(String nameBangla) {
        this.nameBangla = nameBangla;
    }

    public Seedlings getSeedlings() {
        return seedlings;
    }

    public void setSeedlings(Seedlings seedlings) {
        this.seedlings = seedlings;
    }

    public Cultivation getCultivation() {
        return cultivation;
    }

    public void setCultivation(Cultivation cultivation) {
        this.cultivation = cultivation;
    }

    public List<Integer> getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(List<Integer> fertilizer) {
        this.fertilizer = fertilizer;
    }

    public List<Irrigation> getIrrigation() {
        return irrigation;
    }

    public void setIrrigation(List<Irrigation> irrigation) {
        this.irrigation = irrigation;
    }

    public String getSeedAmount() {
        return seedAmount;
    }

    public void setSeedAmount(String seedAmount) {
        this.seedAmount = seedAmount;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public String getMedicineProperties() {
        return medicineProperties;
    }

    public void setMedicineProperties(String medicineProperties) {
        this.medicineProperties = medicineProperties;
    }

    public String getUseCase() {
        return useCase;
    }

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getCultivationDetails() {
        return cultivationDetails;
    }

    public void setCultivationDetails(String cultivationDetails) {
        this.cultivationDetails = cultivationDetails;
    }

    public String getFertilizationProcess() {
        return fertilizationProcess;
    }

    public void setFertilizationProcess(String fertilizationProcess) {
        this.fertilizationProcess = fertilizationProcess;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public List<Breed> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<Breed> breeds) {
        this.breeds = breeds;
    }

    public List<FertilizerAmount> getFertilizerAmount() {
        return fertilizerAmount;
    }

    public void setFertilizerAmount(List<FertilizerAmount> fertilizerAmount) {
        this.fertilizerAmount = fertilizerAmount;
    }

}