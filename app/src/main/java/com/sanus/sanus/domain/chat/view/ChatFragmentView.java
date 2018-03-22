package com.sanus.sanus.domain.chat.view;

import com.sanus.sanus.domain.chat.data.ContactUser;

import java.util.List;

public interface ChatFragmentView {

    void setDataAdapter(List<ContactUser> busquedaDoctors);
}
