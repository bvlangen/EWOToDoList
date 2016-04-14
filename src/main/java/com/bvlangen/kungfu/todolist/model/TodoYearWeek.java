package com.bvlangen.kungfu.todolist.model;

import com.bvlangen.kungfu.todolist.util.DateUtil;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;

public class TodoYearWeek {

    public static final Locale NL = Locale.forLanguageTag("nl");

    private IntegerProperty year;
    private IntegerProperty weekNr;
    private StringProperty weekDateFromTo;

    private BooleanProperty maBedOpmaken, maPersoonlijkeDingenOpruimen, maVuileKlerenInDeWasDoen, maPersoonlijkeHygiene,
            maSchoneKledingEnSchoenen, maGezondEten, maHuiswerkMaken, maPeInzetGebruiken, maPeRespectTonenLerarenEnVrienden,
            maToegewezenTakenAfmaken, maPeRespectTonenHeleGezin, maJongereBroerOfZusHelpen, ma15MinWingTcEzcrimaTrainen,
            maVoorKorteTermijnWaardevolDoelStellen, maBlijfLettenOpDeGeschrevenDoelen;

    private BooleanProperty diBedOpmaken, diPersoonlijkeDingenOpruimen, diVuileKlerenInDeWasDoen, diPersoonlijkeHygiene,
            diSchoneKledingEnSchoenen, diGezondEten, diHuiswerkMaken, diPeInzetGebruiken, diPeRespectTonenLerarenEnVrienden,
            diToegewezenTakenAfmaken, diPeRespectTonenHeleGezin, diJongereBroerOfZusHelpen, di15MinWingTcEzcrimaTrainen,
            diVoorKorteTermijnWaardevolDoelStellen, diBlijfLettenOpDeGeschrevenDoelen;

    private BooleanProperty woBedOpmaken, woPersoonlijkeDingenOpruimen, woVuileKlerenInDeWasDoen, woPersoonlijkeHygiene,
            woSchoneKledingEnSchoenen, woGezondEten, woHuiswerkMaken, woPeInzetGebruiken, woPeRespectTonenLerarenEnVrienden,
            woToegewezenTakenAfmaken, woPeRespectTonenHeleGezin, woJongereBroerOfZusHelpen, wo15MinWingTcEzcrimaTrainen,
            woVoorKorteTermijnWaardevolDoelStellen, woBlijfLettenOpDeGeschrevenDoelen;

    private BooleanProperty doBedOpmaken, doPersoonlijkeDingenOpruimen, doVuileKlerenInDeWasDoen, doPersoonlijkeHygiene,
            doSchoneKledingEnSchoenen, doGezondEten, doHuiswerkMaken, doPeInzetGebruiken, doPeRespectTonenLerarenEnVrienden,
            doToegewezenTakenAfmaken, doPeRespectTonenHeleGezin, doJongereBroerOfZusHelpen, do15MinWingTcEzcrimaTrainen,
            doVoorKorteTermijnWaardevolDoelStellen, doBlijfLettenOpDeGeschrevenDoelen;

    private BooleanProperty vrBedOpmaken, vrPersoonlijkeDingenOpruimen, vrVuileKlerenInDeWasDoen, vrPersoonlijkeHygiene,
            vrSchoneKledingEnSchoenen, vrGezondEten, vrHuiswerkMaken, vrPeInzetGebruiken, vrPeRespectTonenLerarenEnVrienden,
            vrToegewezenTakenAfmaken, vrPeRespectTonenHeleGezin, vrJongereBroerOfZusHelpen, vr15MinWingTcEzcrimaTrainen,
            vrVoorKorteTermijnWaardevolDoelStellen, vrBlijfLettenOpDeGeschrevenDoelen;

    private BooleanProperty zaBedOpmaken, zaPersoonlijkeDingenOpruimen, zaVuileKlerenInDeWasDoen, zaPersoonlijkeHygiene,
            zaSchoneKledingEnSchoenen, zaGezondEten, zaHuiswerkMaken, zaPeInzetGebruiken, zaPeRespectTonenLerarenEnVrienden,
            zaToegewezenTakenAfmaken, zaPeRespectTonenHeleGezin, zaJongereBroerOfZusHelpen, za15MinWingTcEzcrimaTrainen,
            zaVoorKorteTermijnWaardevolDoelStellen, zaBlijfLettenOpDeGeschrevenDoelen;

    private BooleanProperty zoBedOpmaken, zoPersoonlijkeDingenOpruimen, zoVuileKlerenInDeWasDoen, zoPersoonlijkeHygiene,
            zoSchoneKledingEnSchoenen, zoGezondEten, zoHuiswerkMaken, zoPeInzetGebruiken, zoPeRespectTonenLerarenEnVrienden,
            zoToegewezenTakenAfmaken, zoPeRespectTonenHeleGezin, zoJongereBroerOfZusHelpen, zo15MinWingTcEzcrimaTrainen,
            zoVoorKorteTermijnWaardevolDoelStellen, zoBlijfLettenOpDeGeschrevenDoelen;

    // Default constructor needed for JAXB
    public TodoYearWeek() {
        this(LocalDate.now());
    }

    public TodoYearWeek(final LocalDate now) {
        this(DateUtil.getYear(now), DateUtil.getWeekOfYear(now));
    }

    public TodoYearWeek(final Integer year, final Integer weekNr) {
        this.year = new SimpleIntegerProperty(year);
        this.weekNr = new SimpleIntegerProperty(weekNr);
        this.weekDateFromTo = new SimpleStringProperty(DateUtil.formatFromToDate(year, weekNr));

        this.maBedOpmaken = new SimpleBooleanProperty(false);
        this.maPersoonlijkeDingenOpruimen = new SimpleBooleanProperty(false);
        this.maVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.maVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.maPersoonlijkeHygiene = new SimpleBooleanProperty(false);
        this.maSchoneKledingEnSchoenen = new SimpleBooleanProperty(false);
        this.maGezondEten = new SimpleBooleanProperty(false);
        this.maHuiswerkMaken = new SimpleBooleanProperty(false);
        this.maPeInzetGebruiken = new SimpleBooleanProperty(false);
        this.maPeRespectTonenLerarenEnVrienden = new SimpleBooleanProperty(false);
        this.maToegewezenTakenAfmaken = new SimpleBooleanProperty(false);
        this.maPeRespectTonenHeleGezin = new SimpleBooleanProperty(false);
        this.maJongereBroerOfZusHelpen = new SimpleBooleanProperty(false);
        this.ma15MinWingTcEzcrimaTrainen = new SimpleBooleanProperty(false);
        this.maVoorKorteTermijnWaardevolDoelStellen = new SimpleBooleanProperty(false);
        this.maBlijfLettenOpDeGeschrevenDoelen = new SimpleBooleanProperty(false);

        this.diBedOpmaken = new SimpleBooleanProperty(false);
        this.diPersoonlijkeDingenOpruimen = new SimpleBooleanProperty(false);
        this.diVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.diVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.diPersoonlijkeHygiene = new SimpleBooleanProperty(false);
        this.diSchoneKledingEnSchoenen = new SimpleBooleanProperty(false);
        this.diGezondEten = new SimpleBooleanProperty(false);
        this.diHuiswerkMaken = new SimpleBooleanProperty(false);
        this.diPeInzetGebruiken = new SimpleBooleanProperty(false);
        this.diPeRespectTonenLerarenEnVrienden = new SimpleBooleanProperty(false);
        this.diToegewezenTakenAfmaken = new SimpleBooleanProperty(false);
        this.diPeRespectTonenHeleGezin = new SimpleBooleanProperty(false);
        this.diJongereBroerOfZusHelpen = new SimpleBooleanProperty(false);
        this.di15MinWingTcEzcrimaTrainen = new SimpleBooleanProperty(false);
        this.diVoorKorteTermijnWaardevolDoelStellen = new SimpleBooleanProperty(false);
        this.diBlijfLettenOpDeGeschrevenDoelen = new SimpleBooleanProperty(false);

        this.woBedOpmaken = new SimpleBooleanProperty(false);
        this.woPersoonlijkeDingenOpruimen = new SimpleBooleanProperty(false);
        this.woVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.woVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.woPersoonlijkeHygiene = new SimpleBooleanProperty(false);
        this.woSchoneKledingEnSchoenen = new SimpleBooleanProperty(false);
        this.woGezondEten = new SimpleBooleanProperty(false);
        this.woHuiswerkMaken = new SimpleBooleanProperty(false);
        this.woPeInzetGebruiken = new SimpleBooleanProperty(false);
        this.woPeRespectTonenLerarenEnVrienden = new SimpleBooleanProperty(false);
        this.woToegewezenTakenAfmaken = new SimpleBooleanProperty(false);
        this.woPeRespectTonenHeleGezin = new SimpleBooleanProperty(false);
        this.woJongereBroerOfZusHelpen = new SimpleBooleanProperty(false);
        this.wo15MinWingTcEzcrimaTrainen = new SimpleBooleanProperty(false);
        this.woVoorKorteTermijnWaardevolDoelStellen = new SimpleBooleanProperty(false);
        this.woBlijfLettenOpDeGeschrevenDoelen = new SimpleBooleanProperty(false);

        this.doBedOpmaken = new SimpleBooleanProperty(false);
        this.doPersoonlijkeDingenOpruimen = new SimpleBooleanProperty(false);
        this.doVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.doVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.doPersoonlijkeHygiene = new SimpleBooleanProperty(false);
        this.doSchoneKledingEnSchoenen = new SimpleBooleanProperty(false);
        this.doGezondEten = new SimpleBooleanProperty(false);
        this.doHuiswerkMaken = new SimpleBooleanProperty(false);
        this.doPeInzetGebruiken = new SimpleBooleanProperty(false);
        this.doPeRespectTonenLerarenEnVrienden = new SimpleBooleanProperty(false);
        this.doToegewezenTakenAfmaken = new SimpleBooleanProperty(false);
        this.doPeRespectTonenHeleGezin = new SimpleBooleanProperty(false);
        this.doJongereBroerOfZusHelpen = new SimpleBooleanProperty(false);
        this.do15MinWingTcEzcrimaTrainen = new SimpleBooleanProperty(false);
        this.doVoorKorteTermijnWaardevolDoelStellen = new SimpleBooleanProperty(false);
        this.doBlijfLettenOpDeGeschrevenDoelen = new SimpleBooleanProperty(false);

        this.vrBedOpmaken = new SimpleBooleanProperty(false);
        this.vrPersoonlijkeDingenOpruimen = new SimpleBooleanProperty(false);
        this.vrVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.vrVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.vrPersoonlijkeHygiene = new SimpleBooleanProperty(false);
        this.vrSchoneKledingEnSchoenen = new SimpleBooleanProperty(false);
        this.vrGezondEten = new SimpleBooleanProperty(false);
        this.vrHuiswerkMaken = new SimpleBooleanProperty(false);
        this.vrPeInzetGebruiken = new SimpleBooleanProperty(false);
        this.vrPeRespectTonenLerarenEnVrienden = new SimpleBooleanProperty(false);
        this.vrToegewezenTakenAfmaken = new SimpleBooleanProperty(false);
        this.vrPeRespectTonenHeleGezin = new SimpleBooleanProperty(false);
        this.vrJongereBroerOfZusHelpen = new SimpleBooleanProperty(false);
        this.vr15MinWingTcEzcrimaTrainen = new SimpleBooleanProperty(false);
        this.vrVoorKorteTermijnWaardevolDoelStellen = new SimpleBooleanProperty(false);
        this.vrBlijfLettenOpDeGeschrevenDoelen = new SimpleBooleanProperty(false);

        this.zaBedOpmaken = new SimpleBooleanProperty(false);
        this.zaPersoonlijkeDingenOpruimen = new SimpleBooleanProperty(false);
        this.zaVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.zaVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.zaPersoonlijkeHygiene = new SimpleBooleanProperty(false);
        this.zaSchoneKledingEnSchoenen = new SimpleBooleanProperty(false);
        this.zaGezondEten = new SimpleBooleanProperty(false);
        this.zaHuiswerkMaken = new SimpleBooleanProperty(false);
        this.zaPeInzetGebruiken = new SimpleBooleanProperty(false);
        this.zaPeRespectTonenLerarenEnVrienden = new SimpleBooleanProperty(false);
        this.zaToegewezenTakenAfmaken = new SimpleBooleanProperty(false);
        this.zaPeRespectTonenHeleGezin = new SimpleBooleanProperty(false);
        this.zaJongereBroerOfZusHelpen = new SimpleBooleanProperty(false);
        this.za15MinWingTcEzcrimaTrainen = new SimpleBooleanProperty(false);
        this.zaVoorKorteTermijnWaardevolDoelStellen = new SimpleBooleanProperty(false);
        this.zaBlijfLettenOpDeGeschrevenDoelen = new SimpleBooleanProperty(false);

        this.zoBedOpmaken = new SimpleBooleanProperty(false);
        this.zoPersoonlijkeDingenOpruimen = new SimpleBooleanProperty(false);
        this.zoVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.zoVuileKlerenInDeWasDoen = new SimpleBooleanProperty(false);
        this.zoPersoonlijkeHygiene = new SimpleBooleanProperty(false);
        this.zoSchoneKledingEnSchoenen = new SimpleBooleanProperty(false);
        this.zoGezondEten = new SimpleBooleanProperty(false);
        this.zoHuiswerkMaken = new SimpleBooleanProperty(false);
        this.zoPeInzetGebruiken = new SimpleBooleanProperty(false);
        this.zoPeRespectTonenLerarenEnVrienden = new SimpleBooleanProperty(false);
        this.zoToegewezenTakenAfmaken = new SimpleBooleanProperty(false);
        this.zoPeRespectTonenHeleGezin = new SimpleBooleanProperty(false);
        this.zoJongereBroerOfZusHelpen = new SimpleBooleanProperty(false);
        this.zo15MinWingTcEzcrimaTrainen = new SimpleBooleanProperty(false);
        this.zoVoorKorteTermijnWaardevolDoelStellen = new SimpleBooleanProperty(false);
        this.zoBlijfLettenOpDeGeschrevenDoelen = new SimpleBooleanProperty(false);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(final int year) {
        this.year.set(year);
    }

    public int getWeekNr() {
        return weekNr.get();
    }

    public IntegerProperty weekNrProperty() {
        return weekNr;
    }

    public void setWeekNr(final int weekNr) {
        this.weekNr.set(weekNr);
    }

    public String getWeekDateFromTo() {
        return DateUtil.formatFromToDate(getYear(), getWeekNr());
    }

    public StringProperty weekDateFromToProperty() {
        return weekDateFromTo;
    }

    public void setWeekDateFromTo(final String weekDateFromTo) {
        this.weekDateFromTo.set(weekDateFromTo);
    }

    public boolean getMaBedOpmaken() {
        return maBedOpmaken.get();
    }

    public BooleanProperty maBedOpmakenProperty() {
        return maBedOpmaken;
    }

    public void setMaBedOpmaken(final boolean maBedOpmaken) {
        this.maBedOpmaken.set(maBedOpmaken);
    }

    public boolean getMaPersoonlijkeDingenOpruimen() {
        return maPersoonlijkeDingenOpruimen.get();
    }

    public BooleanProperty maPersoonlijkeDingenOpruimenProperty() {
        return maPersoonlijkeDingenOpruimen;
    }

    public void setMaPersoonlijkeDingenOpruimen(final boolean maPersoonlijkeDingenOpruimen) {
        this.maPersoonlijkeDingenOpruimen.set(maPersoonlijkeDingenOpruimen);
    }

    public boolean getMaVuileKlerenInDeWasDoen() {
        return maVuileKlerenInDeWasDoen.get();
    }

    public BooleanProperty maVuileKlerenInDeWasDoenProperty() {
        return maVuileKlerenInDeWasDoen;
    }

    public void setMaVuileKlerenInDeWasDoen(final boolean maVuileKlerenInDeWasDoen) {
        this.maVuileKlerenInDeWasDoen.set(maVuileKlerenInDeWasDoen);
    }

    public boolean getMaPersoonlijkeHygiene() {
        return maPersoonlijkeHygiene.get();
    }

    public BooleanProperty maPersoonlijkeHygieneProperty() {
        return maPersoonlijkeHygiene;
    }

    public void setMaPersoonlijkeHygiene(final boolean maPersoonlijkeHygiene) {
        this.maPersoonlijkeHygiene.set(maPersoonlijkeHygiene);
    }

    public boolean getMaSchoneKledingEnSchoenen() {
        return maSchoneKledingEnSchoenen.get();
    }

    public BooleanProperty maSchoneKledingEnSchoenenProperty() {
        return maSchoneKledingEnSchoenen;
    }

    public void setMaSchoneKledingEnSchoenen(final boolean maSchoneKledingEnSchoenen) {
        this.maSchoneKledingEnSchoenen.set(maSchoneKledingEnSchoenen);
    }

    public boolean getMaGezondEten() {
        return maGezondEten.get();
    }

    public BooleanProperty maGezondEtenProperty() {
        return maGezondEten;
    }

    public void setMaGezondEten(final boolean maGezondEten) {
        this.maGezondEten.set(maGezondEten);
    }

    public boolean getMaHuiswerkMaken() {
        return maHuiswerkMaken.get();
    }

    public BooleanProperty maHuiswerkMakenProperty() {
        return maHuiswerkMaken;
    }

    public void setMaHuiswerkMaken(final boolean maHuiswerkMaken) {
        this.maHuiswerkMaken.set(maHuiswerkMaken);
    }

    public boolean getMaPeInzetGebruiken() {
        return maPeInzetGebruiken.get();
    }

    public BooleanProperty maPeInzetGebruikenProperty() {
        return maPeInzetGebruiken;
    }

    public void setMaPeInzetGebruiken(final boolean maPeInzetGebruiken) {
        this.maPeInzetGebruiken.set(maPeInzetGebruiken);
    }

    public boolean getMaPeRespectTonenLerarenEnVrienden() {
        return maPeRespectTonenLerarenEnVrienden.get();
    }

    public BooleanProperty maPeRespectTonenLerarenEnVriendenProperty() {
        return maPeRespectTonenLerarenEnVrienden;
    }

    public void setMaPeRespectTonenLerarenEnVrienden(final boolean maPeRespectTonenLerarenEnVrienden) {
        this.maPeRespectTonenLerarenEnVrienden.set(maPeRespectTonenLerarenEnVrienden);
    }

    public boolean getMaToegewezenTakenAfmaken() {
        return maToegewezenTakenAfmaken.get();
    }

    public BooleanProperty maToegewezenTakenAfmakenProperty() {
        return maToegewezenTakenAfmaken;
    }

    public void setMaToegewezenTakenAfmaken(final boolean maToegewezenTakenAfmaken) {
        this.maToegewezenTakenAfmaken.set(maToegewezenTakenAfmaken);
    }

    public boolean getMaPeRespectTonenHeleGezin() {
        return maPeRespectTonenHeleGezin.get();
    }

    public BooleanProperty maPeRespectTonenHeleGezinProperty() {
        return maPeRespectTonenHeleGezin;
    }

    public void setMaPeRespectTonenHeleGezin(final boolean maPeRespectTonenHeleGezin) {
        this.maPeRespectTonenHeleGezin.set(maPeRespectTonenHeleGezin);
    }

    public boolean getMaJongereBroerOfZusHelpen() {
        return maJongereBroerOfZusHelpen.get();
    }

    public BooleanProperty maJongereBroerOfZusHelpenProperty() {
        return maJongereBroerOfZusHelpen;
    }

    public void setMaJongereBroerOfZusHelpen(final boolean maJongereBroerOfZusHelpen) {
        this.maJongereBroerOfZusHelpen.set(maJongereBroerOfZusHelpen);
    }

    public boolean getMa15MinWingTcEzcrimaTrainen() {
        return ma15MinWingTcEzcrimaTrainen.get();
    }

    public BooleanProperty ma15MinWingTcEzcrimaTrainenProperty() {
        return ma15MinWingTcEzcrimaTrainen;
    }

    public void setMa15MinWingTcEzcrimaTrainen(final boolean ma15MinWingTcEzcrimaTrainen) {
        this.ma15MinWingTcEzcrimaTrainen.set(ma15MinWingTcEzcrimaTrainen);
    }

    public boolean getMaVoorKorteTermijnWaardevolDoelStellen() {
        return maVoorKorteTermijnWaardevolDoelStellen.get();
    }

    public BooleanProperty maVoorKorteTermijnWaardevolDoelStellenProperty() {
        return maVoorKorteTermijnWaardevolDoelStellen;
    }

    public void setMaVoorKorteTermijnWaardevolDoelStellen(final boolean maVoorKorteTermijnWaardevolDoelStellen) {
        this.maVoorKorteTermijnWaardevolDoelStellen.set(maVoorKorteTermijnWaardevolDoelStellen);
    }

    public boolean getDiBedOpmaken() {
        return diBedOpmaken.get();
    }

    public BooleanProperty diBedOpmakenProperty() {
        return diBedOpmaken;
    }

    public void setDiBedOpmaken(final boolean diBedOpmaken) {
        this.diBedOpmaken.set(diBedOpmaken);
    }

    public boolean getDiPersoonlijkeDingenOpruimen() {
        return diPersoonlijkeDingenOpruimen.get();
    }

    public BooleanProperty diPersoonlijkeDingenOpruimenProperty() {
        return diPersoonlijkeDingenOpruimen;
    }

    public void setDiPersoonlijkeDingenOpruimen(final boolean diPersoonlijkeDingenOpruimen) {
        this.diPersoonlijkeDingenOpruimen.set(diPersoonlijkeDingenOpruimen);
    }

    public boolean getDiVuileKlerenInDeWasDoen() {
        return diVuileKlerenInDeWasDoen.get();
    }

    public BooleanProperty diVuileKlerenInDeWasDoenProperty() {
        return diVuileKlerenInDeWasDoen;
    }

    public void setDiVuileKlerenInDeWasDoen(final boolean diVuileKlerenInDeWasDoen) {
        this.diVuileKlerenInDeWasDoen.set(diVuileKlerenInDeWasDoen);
    }

    public boolean getDiPersoonlijkeHygiene() {
        return diPersoonlijkeHygiene.get();
    }

    public BooleanProperty diPersoonlijkeHygieneProperty() {
        return diPersoonlijkeHygiene;
    }

    public void setDiPersoonlijkeHygiene(final boolean diPersoonlijkeHygiene) {
        this.diPersoonlijkeHygiene.set(diPersoonlijkeHygiene);
    }

    public boolean getDiSchoneKledingEnSchoenen() {
        return diSchoneKledingEnSchoenen.get();
    }

    public BooleanProperty diSchoneKledingEnSchoenenProperty() {
        return diSchoneKledingEnSchoenen;
    }

    public void setDiSchoneKledingEnSchoenen(final boolean diSchoneKledingEnSchoenen) {
        this.diSchoneKledingEnSchoenen.set(diSchoneKledingEnSchoenen);
    }

    public boolean getDiGezondEten() {
        return diGezondEten.get();
    }

    public BooleanProperty diGezondEtenProperty() {
        return diGezondEten;
    }

    public void setDiGezondEten(final boolean diGezondEten) {
        this.diGezondEten.set(diGezondEten);
    }

    public boolean getDiHuiswerkMaken() {
        return diHuiswerkMaken.get();
    }

    public BooleanProperty diHuiswerkMakenProperty() {
        return diHuiswerkMaken;
    }

    public void setDiHuiswerkMaken(final boolean diHuiswerkMaken) {
        this.diHuiswerkMaken.set(diHuiswerkMaken);
    }

    public boolean getDiPeInzetGebruiken() {
        return diPeInzetGebruiken.get();
    }

    public BooleanProperty diPeInzetGebruikenProperty() {
        return diPeInzetGebruiken;
    }

    public void setDiPeInzetGebruiken(final boolean diPeInzetGebruiken) {
        this.diPeInzetGebruiken.set(diPeInzetGebruiken);
    }

    public boolean getDiPeRespectTonenLerarenEnVrienden() {
        return diPeRespectTonenLerarenEnVrienden.get();
    }

    public BooleanProperty diPeRespectTonenLerarenEnVriendenProperty() {
        return diPeRespectTonenLerarenEnVrienden;
    }

    public void setDiPeRespectTonenLerarenEnVrienden(final boolean diPeRespectTonenLerarenEnVrienden) {
        this.diPeRespectTonenLerarenEnVrienden.set(diPeRespectTonenLerarenEnVrienden);
    }

    public boolean getDiToegewezenTakenAfmaken() {
        return diToegewezenTakenAfmaken.get();
    }

    public BooleanProperty diToegewezenTakenAfmakenProperty() {
        return diToegewezenTakenAfmaken;
    }

    public void setDiToegewezenTakenAfmaken(final boolean diToegewezenTakenAfmaken) {
        this.diToegewezenTakenAfmaken.set(diToegewezenTakenAfmaken);
    }

    public boolean getDiPeRespectTonenHeleGezin() {
        return diPeRespectTonenHeleGezin.get();
    }

    public BooleanProperty diPeRespectTonenHeleGezinProperty() {
        return diPeRespectTonenHeleGezin;
    }

    public void setDiPeRespectTonenHeleGezin(final boolean diPeRespectTonenHeleGezin) {
        this.diPeRespectTonenHeleGezin.set(diPeRespectTonenHeleGezin);
    }

    public boolean getDiJongereBroerOfZusHelpen() {
        return diJongereBroerOfZusHelpen.get();
    }

    public BooleanProperty diJongereBroerOfZusHelpenProperty() {
        return diJongereBroerOfZusHelpen;
    }

    public void setDiJongereBroerOfZusHelpen(final boolean diJongereBroerOfZusHelpen) {
        this.diJongereBroerOfZusHelpen.set(diJongereBroerOfZusHelpen);
    }

    public boolean getDi15MinWingTcEzcrimaTrainen() {
        return di15MinWingTcEzcrimaTrainen.get();
    }

    public BooleanProperty di15MinWingTcEzcrimaTrainenProperty() {
        return di15MinWingTcEzcrimaTrainen;
    }

    public void setDi15MinWingTcEzcrimaTrainen(final boolean di15MinWingTcEzcrimaTrainen) {
        this.di15MinWingTcEzcrimaTrainen.set(di15MinWingTcEzcrimaTrainen);
    }

    public boolean getDiVoorKorteTermijnWaardevolDoelStellen() {
        return diVoorKorteTermijnWaardevolDoelStellen.get();
    }

    public BooleanProperty diVoorKorteTermijnWaardevolDoelStellenProperty() {
        return diVoorKorteTermijnWaardevolDoelStellen;
    }

    public void setDiVoorKorteTermijnWaardevolDoelStellen(final boolean diVoorKorteTermijnWaardevolDoelStellen) {
        this.diVoorKorteTermijnWaardevolDoelStellen.set(diVoorKorteTermijnWaardevolDoelStellen);
    }

    public boolean getWoBedOpmaken() {
        return woBedOpmaken.get();
    }

    public BooleanProperty woBedOpmakenProperty() {
        return woBedOpmaken;
    }

    public void setWoBedOpmaken(final boolean woBedOpmaken) {
        this.woBedOpmaken.set(woBedOpmaken);
    }

    public boolean getWoPersoonlijkeDingenOpruimen() {
        return woPersoonlijkeDingenOpruimen.get();
    }

    public BooleanProperty woPersoonlijkeDingenOpruimenProperty() {
        return woPersoonlijkeDingenOpruimen;
    }

    public void setWoPersoonlijkeDingenOpruimen(final boolean woPersoonlijkeDingenOpruimen) {
        this.woPersoonlijkeDingenOpruimen.set(woPersoonlijkeDingenOpruimen);
    }

    public boolean getWoVuileKlerenInDeWasDoen() {
        return woVuileKlerenInDeWasDoen.get();
    }

    public BooleanProperty woVuileKlerenInDeWasDoenProperty() {
        return woVuileKlerenInDeWasDoen;
    }

    public void setWoVuileKlerenInDeWasDoen(final boolean woVuileKlerenInDeWasDoen) {
        this.woVuileKlerenInDeWasDoen.set(woVuileKlerenInDeWasDoen);
    }

    public boolean getWoPersoonlijkeHygiene() {
        return woPersoonlijkeHygiene.get();
    }

    public BooleanProperty woPersoonlijkeHygieneProperty() {
        return woPersoonlijkeHygiene;
    }

    public void setWoPersoonlijkeHygiene(final boolean woPersoonlijkeHygiene) {
        this.woPersoonlijkeHygiene.set(woPersoonlijkeHygiene);
    }

    public boolean getWoSchoneKledingEnSchoenen() {
        return woSchoneKledingEnSchoenen.get();
    }

    public BooleanProperty woSchoneKledingEnSchoenenProperty() {
        return woSchoneKledingEnSchoenen;
    }

    public void setWoSchoneKledingEnSchoenen(final boolean woSchoneKledingEnSchoenen) {
        this.woSchoneKledingEnSchoenen.set(woSchoneKledingEnSchoenen);
    }

    public boolean getWoGezondEten() {
        return woGezondEten.get();
    }

    public BooleanProperty woGezondEtenProperty() {
        return woGezondEten;
    }

    public void setWoGezondEten(final boolean woGezondEten) {
        this.woGezondEten.set(woGezondEten);
    }

    public boolean getWoHuiswerkMaken() {
        return woHuiswerkMaken.get();
    }

    public BooleanProperty woHuiswerkMakenProperty() {
        return woHuiswerkMaken;
    }

    public void setWoHuiswerkMaken(final boolean woHuiswerkMaken) {
        this.woHuiswerkMaken.set(woHuiswerkMaken);
    }

    public boolean getWoPeInzetGebruiken() {
        return woPeInzetGebruiken.get();
    }

    public BooleanProperty woPeInzetGebruikenProperty() {
        return woPeInzetGebruiken;
    }

    public void setWoPeInzetGebruiken(final boolean woPeInzetGebruiken) {
        this.woPeInzetGebruiken.set(woPeInzetGebruiken);
    }

    public boolean getWoPeRespectTonenLerarenEnVrienden() {
        return woPeRespectTonenLerarenEnVrienden.get();
    }

    public BooleanProperty woPeRespectTonenLerarenEnVriendenProperty() {
        return woPeRespectTonenLerarenEnVrienden;
    }

    public void setWoPeRespectTonenLerarenEnVrienden(final boolean woPeRespectTonenLerarenEnVrienden) {
        this.woPeRespectTonenLerarenEnVrienden.set(woPeRespectTonenLerarenEnVrienden);
    }

    public boolean getWoToegewezenTakenAfmaken() {
        return woToegewezenTakenAfmaken.get();
    }

    public BooleanProperty woToegewezenTakenAfmakenProperty() {
        return woToegewezenTakenAfmaken;
    }

    public void setWoToegewezenTakenAfmaken(final boolean woToegewezenTakenAfmaken) {
        this.woToegewezenTakenAfmaken.set(woToegewezenTakenAfmaken);
    }

    public boolean getWoPeRespectTonenHeleGezin() {
        return woPeRespectTonenHeleGezin.get();
    }

    public BooleanProperty woPeRespectTonenHeleGezinProperty() {
        return woPeRespectTonenHeleGezin;
    }

    public void setWoPeRespectTonenHeleGezin(final boolean woPeRespectTonenHeleGezin) {
        this.woPeRespectTonenHeleGezin.set(woPeRespectTonenHeleGezin);
    }

    public boolean getWoJongereBroerOfZusHelpen() {
        return woJongereBroerOfZusHelpen.get();
    }

    public BooleanProperty woJongereBroerOfZusHelpenProperty() {
        return woJongereBroerOfZusHelpen;
    }

    public void setWoJongereBroerOfZusHelpen(final boolean woJongereBroerOfZusHelpen) {
        this.woJongereBroerOfZusHelpen.set(woJongereBroerOfZusHelpen);
    }

    public boolean getWo15MinWingTcEzcrimaTrainen() {
        return wo15MinWingTcEzcrimaTrainen.get();
    }

    public BooleanProperty wo15MinWingTcEzcrimaTrainenProperty() {
        return wo15MinWingTcEzcrimaTrainen;
    }

    public void setWo15MinWingTcEzcrimaTrainen(final boolean wo15MinWingTcEzcrimaTrainen) {
        this.wo15MinWingTcEzcrimaTrainen.set(wo15MinWingTcEzcrimaTrainen);
    }

    public boolean getWoVoorKorteTermijnWaardevolDoelStellen() {
        return woVoorKorteTermijnWaardevolDoelStellen.get();
    }

    public BooleanProperty woVoorKorteTermijnWaardevolDoelStellenProperty() {
        return woVoorKorteTermijnWaardevolDoelStellen;
    }

    public void setWoVoorKorteTermijnWaardevolDoelStellen(final boolean woVoorKorteTermijnWaardevolDoelStellen) {
        this.woVoorKorteTermijnWaardevolDoelStellen.set(woVoorKorteTermijnWaardevolDoelStellen);
    }

    public boolean getDoBedOpmaken() {
        return doBedOpmaken.get();
    }

    public BooleanProperty doBedOpmakenProperty() {
        return doBedOpmaken;
    }

    public void setDoBedOpmaken(final boolean doBedOpmaken) {
        this.doBedOpmaken.set(doBedOpmaken);
    }

    public boolean getDoPersoonlijkeDingenOpruimen() {
        return doPersoonlijkeDingenOpruimen.get();
    }

    public BooleanProperty doPersoonlijkeDingenOpruimenProperty() {
        return doPersoonlijkeDingenOpruimen;
    }

    public void setDoPersoonlijkeDingenOpruimen(final boolean doPersoonlijkeDingenOpruimen) {
        this.doPersoonlijkeDingenOpruimen.set(doPersoonlijkeDingenOpruimen);
    }

    public boolean getDoVuileKlerenInDeWasDoen() {
        return doVuileKlerenInDeWasDoen.get();
    }

    public BooleanProperty doVuileKlerenInDeWasDoenProperty() {
        return doVuileKlerenInDeWasDoen;
    }

    public void setDoVuileKlerenInDeWasDoen(final boolean doVuileKlerenInDeWasDoen) {
        this.doVuileKlerenInDeWasDoen.set(doVuileKlerenInDeWasDoen);
    }

    public boolean getDoPersoonlijkeHygiene() {
        return doPersoonlijkeHygiene.get();
    }

    public BooleanProperty doPersoonlijkeHygieneProperty() {
        return doPersoonlijkeHygiene;
    }

    public void setDoPersoonlijkeHygiene(final boolean doPersoonlijkeHygiene) {
        this.doPersoonlijkeHygiene.set(doPersoonlijkeHygiene);
    }

    public boolean getDoSchoneKledingEnSchoenen() {
        return doSchoneKledingEnSchoenen.get();
    }

    public BooleanProperty doSchoneKledingEnSchoenenProperty() {
        return doSchoneKledingEnSchoenen;
    }

    public void setDoSchoneKledingEnSchoenen(final boolean doSchoneKledingEnSchoenen) {
        this.doSchoneKledingEnSchoenen.set(doSchoneKledingEnSchoenen);
    }

    public boolean getDoGezondEten() {
        return doGezondEten.get();
    }

    public BooleanProperty doGezondEtenProperty() {
        return doGezondEten;
    }

    public void setDoGezondEten(final boolean doGezondEten) {
        this.doGezondEten.set(doGezondEten);
    }

    public boolean getDoHuiswerkMaken() {
        return doHuiswerkMaken.get();
    }

    public BooleanProperty doHuiswerkMakenProperty() {
        return doHuiswerkMaken;
    }

    public void setDoHuiswerkMaken(final boolean doHuiswerkMaken) {
        this.doHuiswerkMaken.set(doHuiswerkMaken);
    }

    public boolean getDoPeInzetGebruiken() {
        return doPeInzetGebruiken.get();
    }

    public BooleanProperty doPeInzetGebruikenProperty() {
        return doPeInzetGebruiken;
    }

    public void setDoPeInzetGebruiken(final boolean doPeInzetGebruiken) {
        this.doPeInzetGebruiken.set(doPeInzetGebruiken);
    }

    public boolean getDoPeRespectTonenLerarenEnVrienden() {
        return doPeRespectTonenLerarenEnVrienden.get();
    }

    public BooleanProperty doPeRespectTonenLerarenEnVriendenProperty() {
        return doPeRespectTonenLerarenEnVrienden;
    }

    public void setDoPeRespectTonenLerarenEnVrienden(final boolean doPeRespectTonenLerarenEnVrienden) {
        this.doPeRespectTonenLerarenEnVrienden.set(doPeRespectTonenLerarenEnVrienden);
    }

    public boolean getDoToegewezenTakenAfmaken() {
        return doToegewezenTakenAfmaken.get();
    }

    public BooleanProperty doToegewezenTakenAfmakenProperty() {
        return doToegewezenTakenAfmaken;
    }

    public void setDoToegewezenTakenAfmaken(final boolean doToegewezenTakenAfmaken) {
        this.doToegewezenTakenAfmaken.set(doToegewezenTakenAfmaken);
    }

    public boolean getDoPeRespectTonenHeleGezin() {
        return doPeRespectTonenHeleGezin.get();
    }

    public BooleanProperty doPeRespectTonenHeleGezinProperty() {
        return doPeRespectTonenHeleGezin;
    }

    public void setDoPeRespectTonenHeleGezin(final boolean doPeRespectTonenHeleGezin) {
        this.doPeRespectTonenHeleGezin.set(doPeRespectTonenHeleGezin);
    }

    public boolean getDoJongereBroerOfZusHelpen() {
        return doJongereBroerOfZusHelpen.get();
    }

    public BooleanProperty doJongereBroerOfZusHelpenProperty() {
        return doJongereBroerOfZusHelpen;
    }

    public void setDoJongereBroerOfZusHelpen(final boolean doJongereBroerOfZusHelpen) {
        this.doJongereBroerOfZusHelpen.set(doJongereBroerOfZusHelpen);
    }

    public boolean getDo15MinWingTcEzcrimaTrainen() {
        return do15MinWingTcEzcrimaTrainen.get();
    }

    public BooleanProperty do15MinWingTcEzcrimaTrainenProperty() {
        return do15MinWingTcEzcrimaTrainen;
    }

    public void setDo15MinWingTcEzcrimaTrainen(final boolean do15MinWingTcEzcrimaTrainen) {
        this.do15MinWingTcEzcrimaTrainen.set(do15MinWingTcEzcrimaTrainen);
    }

    public boolean getDoVoorKorteTermijnWaardevolDoelStellen() {
        return doVoorKorteTermijnWaardevolDoelStellen.get();
    }

    public BooleanProperty doVoorKorteTermijnWaardevolDoelStellenProperty() {
        return doVoorKorteTermijnWaardevolDoelStellen;
    }

    public void setDoVoorKorteTermijnWaardevolDoelStellen(final boolean doVoorKorteTermijnWaardevolDoelStellen) {
        this.doVoorKorteTermijnWaardevolDoelStellen.set(doVoorKorteTermijnWaardevolDoelStellen);
    }

    public boolean getVrBedOpmaken() {
        return vrBedOpmaken.get();
    }

    public BooleanProperty vrBedOpmakenProperty() {
        return vrBedOpmaken;
    }

    public void setVrBedOpmaken(final boolean vrBedOpmaken) {
        this.vrBedOpmaken.set(vrBedOpmaken);
    }

    public boolean getVrPersoonlijkeDingenOpruimen() {
        return vrPersoonlijkeDingenOpruimen.get();
    }

    public BooleanProperty vrPersoonlijkeDingenOpruimenProperty() {
        return vrPersoonlijkeDingenOpruimen;
    }

    public void setVrPersoonlijkeDingenOpruimen(final boolean vrPersoonlijkeDingenOpruimen) {
        this.vrPersoonlijkeDingenOpruimen.set(vrPersoonlijkeDingenOpruimen);
    }

    public boolean getVrVuileKlerenInDeWasDoen() {
        return vrVuileKlerenInDeWasDoen.get();
    }

    public BooleanProperty vrVuileKlerenInDeWasDoenProperty() {
        return vrVuileKlerenInDeWasDoen;
    }

    public void setVrVuileKlerenInDeWasDoen(final boolean vrVuileKlerenInDeWasDoen) {
        this.vrVuileKlerenInDeWasDoen.set(vrVuileKlerenInDeWasDoen);
    }

    public boolean getVrPersoonlijkeHygiene() {
        return vrPersoonlijkeHygiene.get();
    }

    public BooleanProperty vrPersoonlijkeHygieneProperty() {
        return vrPersoonlijkeHygiene;
    }

    public void setVrPersoonlijkeHygiene(final boolean vrPersoonlijkeHygiene) {
        this.vrPersoonlijkeHygiene.set(vrPersoonlijkeHygiene);
    }

    public boolean getVrSchoneKledingEnSchoenen() {
        return vrSchoneKledingEnSchoenen.get();
    }

    public BooleanProperty vrSchoneKledingEnSchoenenProperty() {
        return vrSchoneKledingEnSchoenen;
    }

    public void setVrSchoneKledingEnSchoenen(final boolean vrSchoneKledingEnSchoenen) {
        this.vrSchoneKledingEnSchoenen.set(vrSchoneKledingEnSchoenen);
    }

    public boolean getVrGezondEten() {
        return vrGezondEten.get();
    }

    public BooleanProperty vrGezondEtenProperty() {
        return vrGezondEten;
    }

    public void setVrGezondEten(final boolean vrGezondEten) {
        this.vrGezondEten.set(vrGezondEten);
    }

    public boolean getVrHuiswerkMaken() {
        return vrHuiswerkMaken.get();
    }

    public BooleanProperty vrHuiswerkMakenProperty() {
        return vrHuiswerkMaken;
    }

    public void setVrHuiswerkMaken(final boolean vrHuiswerkMaken) {
        this.vrHuiswerkMaken.set(vrHuiswerkMaken);
    }

    public boolean getVrPeInzetGebruiken() {
        return vrPeInzetGebruiken.get();
    }

    public BooleanProperty vrPeInzetGebruikenProperty() {
        return vrPeInzetGebruiken;
    }

    public void setVrPeInzetGebruiken(final boolean vrPeInzetGebruiken) {
        this.vrPeInzetGebruiken.set(vrPeInzetGebruiken);
    }

    public boolean getVrPeRespectTonenLerarenEnVrienden() {
        return vrPeRespectTonenLerarenEnVrienden.get();
    }

    public BooleanProperty vrPeRespectTonenLerarenEnVriendenProperty() {
        return vrPeRespectTonenLerarenEnVrienden;
    }

    public void setVrPeRespectTonenLerarenEnVrienden(final boolean vrPeRespectTonenLerarenEnVrienden) {
        this.vrPeRespectTonenLerarenEnVrienden.set(vrPeRespectTonenLerarenEnVrienden);
    }

    public boolean getVrToegewezenTakenAfmaken() {
        return vrToegewezenTakenAfmaken.get();
    }

    public BooleanProperty vrToegewezenTakenAfmakenProperty() {
        return vrToegewezenTakenAfmaken;
    }

    public void setVrToegewezenTakenAfmaken(final boolean vrToegewezenTakenAfmaken) {
        this.vrToegewezenTakenAfmaken.set(vrToegewezenTakenAfmaken);
    }

    public boolean getVrPeRespectTonenHeleGezin() {
        return vrPeRespectTonenHeleGezin.get();
    }

    public BooleanProperty vrPeRespectTonenHeleGezinProperty() {
        return vrPeRespectTonenHeleGezin;
    }

    public void setVrPeRespectTonenHeleGezin(final boolean vrPeRespectTonenHeleGezin) {
        this.vrPeRespectTonenHeleGezin.set(vrPeRespectTonenHeleGezin);
    }

    public boolean getVrJongereBroerOfZusHelpen() {
        return vrJongereBroerOfZusHelpen.get();
    }

    public BooleanProperty vrJongereBroerOfZusHelpenProperty() {
        return vrJongereBroerOfZusHelpen;
    }

    public void setVrJongereBroerOfZusHelpen(final boolean vrJongereBroerOfZusHelpen) {
        this.vrJongereBroerOfZusHelpen.set(vrJongereBroerOfZusHelpen);
    }

    public boolean getVr15MinWingTcEzcrimaTrainen() {
        return vr15MinWingTcEzcrimaTrainen.get();
    }

    public BooleanProperty vr15MinWingTcEzcrimaTrainenProperty() {
        return vr15MinWingTcEzcrimaTrainen;
    }

    public void setVr15MinWingTcEzcrimaTrainen(final boolean vr15MinWingTcEzcrimaTrainen) {
        this.vr15MinWingTcEzcrimaTrainen.set(vr15MinWingTcEzcrimaTrainen);
    }

    public boolean getVrVoorKorteTermijnWaardevolDoelStellen() {
        return vrVoorKorteTermijnWaardevolDoelStellen.get();
    }

    public BooleanProperty vrVoorKorteTermijnWaardevolDoelStellenProperty() {
        return vrVoorKorteTermijnWaardevolDoelStellen;
    }

    public void setVrVoorKorteTermijnWaardevolDoelStellen(final boolean vrVoorKorteTermijnWaardevolDoelStellen) {
        this.vrVoorKorteTermijnWaardevolDoelStellen.set(vrVoorKorteTermijnWaardevolDoelStellen);
    }

    public boolean getZaBedOpmaken() {
        return zaBedOpmaken.get();
    }

    public BooleanProperty zaBedOpmakenProperty() {
        return zaBedOpmaken;
    }

    public void setZaBedOpmaken(final boolean zaBedOpmaken) {
        this.zaBedOpmaken.set(zaBedOpmaken);
    }

    public boolean getZaPersoonlijkeDingenOpruimen() {
        return zaPersoonlijkeDingenOpruimen.get();
    }

    public BooleanProperty zaPersoonlijkeDingenOpruimenProperty() {
        return zaPersoonlijkeDingenOpruimen;
    }

    public void setZaPersoonlijkeDingenOpruimen(final boolean zaPersoonlijkeDingenOpruimen) {
        this.zaPersoonlijkeDingenOpruimen.set(zaPersoonlijkeDingenOpruimen);
    }

    public boolean getZaVuileKlerenInDeWasDoen() {
        return zaVuileKlerenInDeWasDoen.get();
    }

    public BooleanProperty zaVuileKlerenInDeWasDoenProperty() {
        return zaVuileKlerenInDeWasDoen;
    }

    public void setZaVuileKlerenInDeWasDoen(final boolean zaVuileKlerenInDeWasDoen) {
        this.zaVuileKlerenInDeWasDoen.set(zaVuileKlerenInDeWasDoen);
    }

    public boolean getZaPersoonlijkeHygiene() {
        return zaPersoonlijkeHygiene.get();
    }

    public BooleanProperty zaPersoonlijkeHygieneProperty() {
        return zaPersoonlijkeHygiene;
    }

    public void setZaPersoonlijkeHygiene(final boolean zaPersoonlijkeHygiene) {
        this.zaPersoonlijkeHygiene.set(zaPersoonlijkeHygiene);
    }

    public boolean getZaSchoneKledingEnSchoenen() {
        return zaSchoneKledingEnSchoenen.get();
    }

    public BooleanProperty zaSchoneKledingEnSchoenenProperty() {
        return zaSchoneKledingEnSchoenen;
    }

    public void setZaSchoneKledingEnSchoenen(final boolean zaSchoneKledingEnSchoenen) {
        this.zaSchoneKledingEnSchoenen.set(zaSchoneKledingEnSchoenen);
    }

    public boolean getZaGezondEten() {
        return zaGezondEten.get();
    }

    public BooleanProperty zaGezondEtenProperty() {
        return zaGezondEten;
    }

    public void setZaGezondEten(final boolean zaGezondEten) {
        this.zaGezondEten.set(zaGezondEten);
    }

    public boolean getZaHuiswerkMaken() {
        return zaHuiswerkMaken.get();
    }

    public BooleanProperty zaHuiswerkMakenProperty() {
        return zaHuiswerkMaken;
    }

    public void setZaHuiswerkMaken(final boolean zaHuiswerkMaken) {
        this.zaHuiswerkMaken.set(zaHuiswerkMaken);
    }

    public boolean getZaPeInzetGebruiken() {
        return zaPeInzetGebruiken.get();
    }

    public BooleanProperty zaPeInzetGebruikenProperty() {
        return zaPeInzetGebruiken;
    }

    public void setZaPeInzetGebruiken(final boolean zaPeInzetGebruiken) {
        this.zaPeInzetGebruiken.set(zaPeInzetGebruiken);
    }

    public boolean getZaPeRespectTonenLerarenEnVrienden() {
        return zaPeRespectTonenLerarenEnVrienden.get();
    }

    public BooleanProperty zaPeRespectTonenLerarenEnVriendenProperty() {
        return zaPeRespectTonenLerarenEnVrienden;
    }

    public void setZaPeRespectTonenLerarenEnVrienden(final boolean zaPeRespectTonenLerarenEnVrienden) {
        this.zaPeRespectTonenLerarenEnVrienden.set(zaPeRespectTonenLerarenEnVrienden);
    }

    public boolean getZaToegewezenTakenAfmaken() {
        return zaToegewezenTakenAfmaken.get();
    }

    public BooleanProperty zaToegewezenTakenAfmakenProperty() {
        return zaToegewezenTakenAfmaken;
    }

    public void setZaToegewezenTakenAfmaken(final boolean zaToegewezenTakenAfmaken) {
        this.zaToegewezenTakenAfmaken.set(zaToegewezenTakenAfmaken);
    }

    public boolean getZaPeRespectTonenHeleGezin() {
        return zaPeRespectTonenHeleGezin.get();
    }

    public BooleanProperty zaPeRespectTonenHeleGezinProperty() {
        return zaPeRespectTonenHeleGezin;
    }

    public void setZaPeRespectTonenHeleGezin(final boolean zaPeRespectTonenHeleGezin) {
        this.zaPeRespectTonenHeleGezin.set(zaPeRespectTonenHeleGezin);
    }

    public boolean getZaJongereBroerOfZusHelpen() {
        return zaJongereBroerOfZusHelpen.get();
    }

    public BooleanProperty zaJongereBroerOfZusHelpenProperty() {
        return zaJongereBroerOfZusHelpen;
    }

    public void setZaJongereBroerOfZusHelpen(final boolean zaJongereBroerOfZusHelpen) {
        this.zaJongereBroerOfZusHelpen.set(zaJongereBroerOfZusHelpen);
    }

    public boolean getZa15MinWingTcEzcrimaTrainen() {
        return za15MinWingTcEzcrimaTrainen.get();
    }

    public BooleanProperty za15MinWingTcEzcrimaTrainenProperty() {
        return za15MinWingTcEzcrimaTrainen;
    }

    public void setZa15MinWingTcEzcrimaTrainen(final boolean za15MinWingTcEzcrimaTrainen) {
        this.za15MinWingTcEzcrimaTrainen.set(za15MinWingTcEzcrimaTrainen);
    }

    public boolean getZaVoorKorteTermijnWaardevolDoelStellen() {
        return zaVoorKorteTermijnWaardevolDoelStellen.get();
    }

    public BooleanProperty zaVoorKorteTermijnWaardevolDoelStellenProperty() {
        return zaVoorKorteTermijnWaardevolDoelStellen;
    }

    public void setZaVoorKorteTermijnWaardevolDoelStellen(final boolean zaVoorKorteTermijnWaardevolDoelStellen) {
        this.zaVoorKorteTermijnWaardevolDoelStellen.set(zaVoorKorteTermijnWaardevolDoelStellen);
    }

    public boolean getZoBedOpmaken() {
        return zoBedOpmaken.get();
    }

    public BooleanProperty zoBedOpmakenProperty() {
        return zoBedOpmaken;
    }

    public void setZoBedOpmaken(final boolean zoBedOpmaken) {
        this.zoBedOpmaken.set(zoBedOpmaken);
    }

    public boolean getZoPersoonlijkeDingenOpruimen() {
        return zoPersoonlijkeDingenOpruimen.get();
    }

    public BooleanProperty zoPersoonlijkeDingenOpruimenProperty() {
        return zoPersoonlijkeDingenOpruimen;
    }

    public void setZoPersoonlijkeDingenOpruimen(final boolean zoPersoonlijkeDingenOpruimen) {
        this.zoPersoonlijkeDingenOpruimen.set(zoPersoonlijkeDingenOpruimen);
    }

    public boolean getZoVuileKlerenInDeWasDoen() {
        return zoVuileKlerenInDeWasDoen.get();
    }

    public BooleanProperty zoVuileKlerenInDeWasDoenProperty() {
        return zoVuileKlerenInDeWasDoen;
    }

    public void setZoVuileKlerenInDeWasDoen(final boolean zoVuileKlerenInDeWasDoen) {
        this.zoVuileKlerenInDeWasDoen.set(zoVuileKlerenInDeWasDoen);
    }

    public boolean getZoPersoonlijkeHygiene() {
        return zoPersoonlijkeHygiene.get();
    }

    public BooleanProperty zoPersoonlijkeHygieneProperty() {
        return zoPersoonlijkeHygiene;
    }

    public void setZoPersoonlijkeHygiene(final boolean zoPersoonlijkeHygiene) {
        this.zoPersoonlijkeHygiene.set(zoPersoonlijkeHygiene);
    }

    public boolean getZoSchoneKledingEnSchoenen() {
        return zoSchoneKledingEnSchoenen.get();
    }

    public BooleanProperty zoSchoneKledingEnSchoenenProperty() {
        return zoSchoneKledingEnSchoenen;
    }

    public void setZoSchoneKledingEnSchoenen(final boolean zoSchoneKledingEnSchoenen) {
        this.zoSchoneKledingEnSchoenen.set(zoSchoneKledingEnSchoenen);
    }

    public boolean getZoGezondEten() {
        return zoGezondEten.get();
    }

    public BooleanProperty zoGezondEtenProperty() {
        return zoGezondEten;
    }

    public void setZoGezondEten(final boolean zoGezondEten) {
        this.zoGezondEten.set(zoGezondEten);
    }

    public boolean getZoHuiswerkMaken() {
        return zoHuiswerkMaken.get();
    }

    public BooleanProperty zoHuiswerkMakenProperty() {
        return zoHuiswerkMaken;
    }

    public void setZoHuiswerkMaken(final boolean zoHuiswerkMaken) {
        this.zoHuiswerkMaken.set(zoHuiswerkMaken);
    }

    public boolean getZoPeInzetGebruiken() {
        return zoPeInzetGebruiken.get();
    }

    public BooleanProperty zoPeInzetGebruikenProperty() {
        return zoPeInzetGebruiken;
    }

    public void setZoPeInzetGebruiken(final boolean zoPeInzetGebruiken) {
        this.zoPeInzetGebruiken.set(zoPeInzetGebruiken);
    }

    public boolean getZoPeRespectTonenLerarenEnVrienden() {
        return zoPeRespectTonenLerarenEnVrienden.get();
    }

    public BooleanProperty zoPeRespectTonenLerarenEnVriendenProperty() {
        return zoPeRespectTonenLerarenEnVrienden;
    }

    public void setZoPeRespectTonenLerarenEnVrienden(final boolean zoPeRespectTonenLerarenEnVrienden) {
        this.zoPeRespectTonenLerarenEnVrienden.set(zoPeRespectTonenLerarenEnVrienden);
    }

    public boolean getZoToegewezenTakenAfmaken() {
        return zoToegewezenTakenAfmaken.get();
    }

    public BooleanProperty zoToegewezenTakenAfmakenProperty() {
        return zoToegewezenTakenAfmaken;
    }

    public void setZoToegewezenTakenAfmaken(final boolean zoToegewezenTakenAfmaken) {
        this.zoToegewezenTakenAfmaken.set(zoToegewezenTakenAfmaken);
    }

    public boolean getZoPeRespectTonenHeleGezin() {
        return zoPeRespectTonenHeleGezin.get();
    }

    public BooleanProperty zoPeRespectTonenHeleGezinProperty() {
        return zoPeRespectTonenHeleGezin;
    }

    public void setZoPeRespectTonenHeleGezin(final boolean zoPeRespectTonenHeleGezin) {
        this.zoPeRespectTonenHeleGezin.set(zoPeRespectTonenHeleGezin);
    }

    public boolean getZoJongereBroerOfZusHelpen() {
        return zoJongereBroerOfZusHelpen.get();
    }

    public BooleanProperty zoJongereBroerOfZusHelpenProperty() {
        return zoJongereBroerOfZusHelpen;
    }

    public void setZoJongereBroerOfZusHelpen(final boolean zoJongereBroerOfZusHelpen) {
        this.zoJongereBroerOfZusHelpen.set(zoJongereBroerOfZusHelpen);
    }

    public boolean getZo15MinWingTcEzcrimaTrainen() {
        return zo15MinWingTcEzcrimaTrainen.get();
    }

    public BooleanProperty zo15MinWingTcEzcrimaTrainenProperty() {
        return zo15MinWingTcEzcrimaTrainen;
    }

    public void setZo15MinWingTcEzcrimaTrainen(final boolean zo15MinWingTcEzcrimaTrainen) {
        this.zo15MinWingTcEzcrimaTrainen.set(zo15MinWingTcEzcrimaTrainen);
    }

    public boolean getZoVoorKorteTermijnWaardevolDoelStellen() {
        return zoVoorKorteTermijnWaardevolDoelStellen.get();
    }

    public BooleanProperty zoVoorKorteTermijnWaardevolDoelStellenProperty() {
        return zoVoorKorteTermijnWaardevolDoelStellen;
    }

    public void setZoVoorKorteTermijnWaardevolDoelStellen(final boolean zoVoorKorteTermijnWaardevolDoelStellen) {
        this.zoVoorKorteTermijnWaardevolDoelStellen.set(zoVoorKorteTermijnWaardevolDoelStellen);
    }

    public boolean getMaBlijfLettenOpDeGeschrevenDoelen() {
        return maBlijfLettenOpDeGeschrevenDoelen.get();
    }

    public BooleanProperty maBlijfLettenOpDeGeschrevenDoelenProperty() {
        return maBlijfLettenOpDeGeschrevenDoelen;
    }

    public void setMaBlijfLettenOpDeGeschrevenDoelen(final boolean maBlijfLettenOpDeGeschrevenDoelen) {
        this.maBlijfLettenOpDeGeschrevenDoelen.set(maBlijfLettenOpDeGeschrevenDoelen);
    }

    public boolean getDiBlijfLettenOpDeGeschrevenDoelen() {
        return diBlijfLettenOpDeGeschrevenDoelen.get();
    }

    public BooleanProperty diBlijfLettenOpDeGeschrevenDoelenProperty() {
        return diBlijfLettenOpDeGeschrevenDoelen;
    }

    public void setDiBlijfLettenOpDeGeschrevenDoelen(final boolean diBlijfLettenOpDeGeschrevenDoelen) {
        this.diBlijfLettenOpDeGeschrevenDoelen.set(diBlijfLettenOpDeGeschrevenDoelen);
    }

    public boolean getWoBlijfLettenOpDeGeschrevenDoelen() {
        return woBlijfLettenOpDeGeschrevenDoelen.get();
    }

    public BooleanProperty woBlijfLettenOpDeGeschrevenDoelenProperty() {
        return woBlijfLettenOpDeGeschrevenDoelen;
    }

    public void setWoBlijfLettenOpDeGeschrevenDoelen(final boolean woBlijfLettenOpDeGeschrevenDoelen) {
        this.woBlijfLettenOpDeGeschrevenDoelen.set(woBlijfLettenOpDeGeschrevenDoelen);
    }

    public boolean getDoBlijfLettenOpDeGeschrevenDoelen() {
        return doBlijfLettenOpDeGeschrevenDoelen.get();
    }

    public BooleanProperty doBlijfLettenOpDeGeschrevenDoelenProperty() {
        return doBlijfLettenOpDeGeschrevenDoelen;
    }

    public void setDoBlijfLettenOpDeGeschrevenDoelen(final boolean doBlijfLettenOpDeGeschrevenDoelen) {
        this.doBlijfLettenOpDeGeschrevenDoelen.set(doBlijfLettenOpDeGeschrevenDoelen);
    }

    public boolean getVrBlijfLettenOpDeGeschrevenDoelen() {
        return vrBlijfLettenOpDeGeschrevenDoelen.get();
    }

    public BooleanProperty vrBlijfLettenOpDeGeschrevenDoelenProperty() {
        return vrBlijfLettenOpDeGeschrevenDoelen;
    }

    public void setVrBlijfLettenOpDeGeschrevenDoelen(final boolean vrBlijfLettenOpDeGeschrevenDoelen) {
        this.vrBlijfLettenOpDeGeschrevenDoelen.set(vrBlijfLettenOpDeGeschrevenDoelen);
    }

    public boolean getZaBlijfLettenOpDeGeschrevenDoelen() {
        return zaBlijfLettenOpDeGeschrevenDoelen.get();
    }

    public BooleanProperty zaBlijfLettenOpDeGeschrevenDoelenProperty() {
        return zaBlijfLettenOpDeGeschrevenDoelen;
    }

    public void setZaBlijfLettenOpDeGeschrevenDoelen(final boolean zaBlijfLettenOpDeGeschrevenDoelen) {
        this.zaBlijfLettenOpDeGeschrevenDoelen.set(zaBlijfLettenOpDeGeschrevenDoelen);
    }

    public boolean getZoBlijfLettenOpDeGeschrevenDoelen() {
        return zoBlijfLettenOpDeGeschrevenDoelen.get();
    }

    public BooleanProperty zoBlijfLettenOpDeGeschrevenDoelenProperty() {
        return zoBlijfLettenOpDeGeschrevenDoelen;
    }

    public void setZoBlijfLettenOpDeGeschrevenDoelen(final boolean zoBlijfLettenOpDeGeschrevenDoelen) {
        this.zoBlijfLettenOpDeGeschrevenDoelen.set(zoBlijfLettenOpDeGeschrevenDoelen);
    }

    public static Comparator<TodoYearWeek> getTodoYearWeekComparator() {
        return (TodoYearWeek todoYearWeek1, TodoYearWeek todoYearWeek2) -> {
            if (todoYearWeek1.getYear() < todoYearWeek2.getYear()) {
                return -1;
            } else if (todoYearWeek1.getYear() > todoYearWeek2.getYear()) {
                return 1;
            } else if (todoYearWeek1.getYear() == todoYearWeek2.getYear()){
                if (todoYearWeek1.getWeekNr() < todoYearWeek2.getWeekNr()) {
                    return -1;
                } else if (todoYearWeek1.getWeekNr() > todoYearWeek2.getWeekNr()) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        };
    }
}
