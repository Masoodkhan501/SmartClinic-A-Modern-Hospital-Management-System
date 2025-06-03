package com.masood.DTO;

import java.util.List;

import com.masood.model.Appointment;

public class AppointmentDetailsDTO {
	private List<Appointment> withoutDoc;
	private List<Appointment> withoutOperation;
	private List<Appointment> withOperation;

	public AppointmentDetailsDTO(List<Appointment> withoutDoc, List<Appointment> withoutOperation,
			List<Appointment> withOperation) {
		this.withoutDoc = withoutDoc;
		this.withoutOperation = withoutOperation;
		this.withOperation = withOperation;
	}

	public AppointmentDetailsDTO() {
	}

	public List<Appointment> getWithoutDoc() {
		return withoutDoc;
	}

	public void setWithoutDoc(List<Appointment> withoutDoc) {
		this.withoutDoc = withoutDoc;
	}

	public List<Appointment> getWithoutOperation() {
		return withoutOperation;
	}

	public void setWithoutOperation(List<Appointment> withoutOperation) {
		this.withoutOperation = withoutOperation;
	}

	public List<Appointment> getWithOperation() {
		return withOperation;
	}

	public void setWithOperation(List<Appointment> withOperation) {
		this.withOperation = withOperation;
	}

	public String toString() {
		return "AppointmentDetailsDTO [" + (withoutDoc != null ? "withoutDoc=" + withoutDoc + ", " : "")
				+ (withoutOperation != null ? "withoutOperation=" + withoutOperation + ", " : "")
				+ (withOperation != null ? "withOperation=" + withOperation : "") + "]";
	}

}
