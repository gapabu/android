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
	public void viewComents() {
		interactor.viewComents();
	}
}
