/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leuville.jeanluc.jva.essaidatetime;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahChronology;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jldeleage
 */
public class Main {
    
    public static void main(String[] args) {

        //====================================================================//
        //                        L O C A L   D A T E                         //
        //====================================================================//
        System.out.println("L O C A L   D A T E");
        LocalDate       localDateMaintenant = LocalDate.now();
        System.out.println("localDateMaintenant : " + localDateMaintenant);
        LocalDate       localDateDemain = localDateMaintenant.plusDays(1);
        System.out.println("localDateDemain : " + localDateDemain);
        System.out.println();

        //====================================================================//
        //                   L O C A L   D A T E   T I M E                    //
        //====================================================================//
        System.out.println("L O C A L   D A T E   T I M E");
        LocalDateTime   localDateTimeAujourdhuiDebutJournee = localDateMaintenant.atStartOfDay();
        System.out.println("localDateTimeDebutJournee : " + localDateTimeAujourdhuiDebutJournee);
        LocalDateTime   localDateTimeMaintenant = LocalDateTime.now();
        System.out.println("localDateTimeMaintenant : " + localDateTimeMaintenant);
        LocalDateTime   localDateTimeAujourdhuiMidi = localDateMaintenant.atTime(12, 0, 0, 0);
        System.out.println("localDateTimeAujourdhuiMidi : " + localDateTimeAujourdhuiMidi);
        System.out.println();

        //====================================================================//
        //                             Z O N E S                              //
        //====================================================================//
        System.out.println("Z O N E");
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        List<String> availableZoneIdsAsList = new LinkedList<>(availableZoneIds);
        Collections.sort(availableZoneIdsAsList);
        for (String uneZone : availableZoneIdsAsList)
            System.out.println(uneZone);
        ZoneId zoneIdCayenne = ZoneId.of("America/Cayenne");
        System.out.println("ZoneId de Cayenne : " + zoneIdCayenne);
        System.out.println();

        //====================================================================//
        //                   Z O N E D   D A T E   T I M E                    //
        //====================================================================//
        System.out.println("Z O N E S   D A T E   T I M E");
        ZonedDateTime   zonedDateTimeMaintenant = ZonedDateTime.now();
        System.out.println("zonedDateMaintenant : " + zonedDateTimeMaintenant);
        ZonedDateTime   zonedDateTimeMidiAParis = zonedDateTimeMaintenant.withHour(12).withMinute(0).withSecond(0);
        System.out.println("zonedDateTimeMidi : " + zonedDateTimeMidiAParis);
        ZonedDateTime   zonedDateTimeCayenneAujourdhuiMidiAParis = zonedDateTimeMidiAParis.withZoneSameInstant(zoneIdCayenne);
        System.out.println("zonedDateTimeAujourdhuiMidiCayenne : " + zonedDateTimeCayenneAujourdhuiMidiAParis);
        LocalDateTime localDateTimeCayenneMidiAParis = zonedDateTimeCayenneAujourdhuiMidiAParis.toLocalDateTime();
        System.out.println("localDateTimeCayenneMidiAParis : " + localDateTimeCayenneMidiAParis);
        System.out.println();

        //====================================================================//
        //                           I N S T A N T                            //
        //====================================================================//
        System.out.println("I N S T A N T");
        Instant         instantMaintenant = Instant.now();
        System.out.println("instantMaintenant : " + instantMaintenant);
        Instant         instantAujourdhuiMidiUTC = Instant.from(zonedDateTimeCayenneAujourdhuiMidiAParis);
        System.out.println("intantAujourdhuiMidi : " + instantAujourdhuiMidiUTC);
        System.out.println();
    
        //====================================================================//
        //                          A D J U S T E R                           //
        //====================================================================//
        System.out.println("Adjuster");
        LocalDate   localDateProchainMardi = localDateMaintenant.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        System.out.println("localDateProchainMardi : " + localDateProchainMardi);
        LocalDate   localDateFinDeCeMois = localDateMaintenant.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("localDateFinDeCeMois : " + localDateFinDeCeMois);
        // Attention, ceci peut ne pas fonctionner si la date initiale est
        // dans un mois de 30 jours ou moins
        // LocalDate   localDateSaintSylvestre = localDateMaintenant.withDayOfMonth(31).withMonth(12);
        LocalDate   localDateSaintSylvestre = localDateMaintenant.withMonth(12).withDayOfMonth(31);
        System.out.println("localDateSaintSylvestre " + localDateSaintSylvestre);
        System.out.println();

        //====================================================================//
        //                   T E M P O R A L    A M O U N T                   //
        //====================================================================//
        System.out.println("D U R A T I O N   A N D   P E R I O D");
        Period deuxMoisEtVingtJours = Period.of(0, 2, 20);
        LocalDate localDateDansDeuxMoisEtVingtJours = localDateMaintenant.plus(deuxMoisEtVingtJours);
        System.out.println("localDateDansDeuxMoisEtVingtJours : " + localDateDansDeuxMoisEtVingtJours);
        Period      periodJusquALaFinDuMois = Period.between(localDateFinDeCeMois, localDateMaintenant);
        System.out.println("periodJusquALaFinDuMois : " + periodJusquALaFinDuMois);
        Period      periodJusquALaFinDeLAnnee = Period.between(localDateMaintenant, localDateSaintSylvestre);
        System.out.println("periodJusquALaFinDeLAnnee : " + periodJusquALaFinDeLAnnee);
        System.out.println();

        //====================================================================//
        //                      C H R O N O L O G I E S                       //
        //====================================================================//
        System.out.println("C H R O N O L O G I E");
        Set<Chronology> availableChronologies = Chronology.getAvailableChronologies();
        for (Chronology chrono : availableChronologies) System.out.println(chrono);
        long epochDayAujourdhui = localDateMaintenant.toEpochDay();
        System.out.println("epochDayAujourdhui : " + epochDayAujourdhui);
        Chronology chronologie = HijrahChronology.INSTANCE;
        System.out.println("chronologie : " + chronologie);
        ChronoLocalDate dateEpochDayHegire = chronologie.dateEpochDay(epochDayAujourdhui);
        System.out.println("dateEpochDayHegire : " + dateEpochDayHegire);
        System.out.println();

    }       // main

}       // Main
