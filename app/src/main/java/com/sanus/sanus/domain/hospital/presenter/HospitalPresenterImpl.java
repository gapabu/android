package com.sanus.sanus.domain.hospital.presenter;

import com.sanus.sanus.domain.hospital.data.Hospital;
import com.sanus.sanus.domain.hospital.interactor.HospitalInteractor;
import com.sanus.sanus.domain.hospital.interactor.HospitalInteractorImpl;
import com.sanus.sanus.domain.hospital.view.HospitalView;

import java.util.List;

public class HospitalPresenterImpl implements HospitalPresenter{
	private HospitalView view;
	private HospitalInteractor interactor;

	public HospitalPresenterImpl(HospitalView view){
		this.view = view;
		interactor = new HospitalInteractorImpl(this);
	}



	@Override
	public void setDataAdapter(List<Hospital> commentsDoctorList) {
		view.setDataAdapter(commentsDoctorList);
	}

	@Override
	public void enableButton() {
		view.enableButton();
	}

	@Override
	public void disableButton() {
		view.disableButton();
	}

	@Override
	public void selectDoctor(String value) {
		view.selectDoctor(value);
	}

	@Override
	public void viewHospital() {
		interactor.viewHospital();
	}

	@Override
	public void buscador(String texto) {
		interactor.buscador(texto);
	}

	@Override
	public void validateButtonEnable() {
		interactor.validateButtonEnable();
	}
}
