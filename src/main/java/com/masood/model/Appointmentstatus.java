package com.masood.model;

public enum Appointmentstatus
{
	PENDING,        // Appointment is booked but not yet confirmed
    CONFIRMED,      // Doctor has accepted or confirmed the appointment
    UNDER_PROCESS,  // Appointment is currently happening
    DONE,           // Appointment completed
    POSTPONED,      // Rescheduled for a later date
    PREPONED,       // Rescheduled for an earlier date
    CANCELLED
}
