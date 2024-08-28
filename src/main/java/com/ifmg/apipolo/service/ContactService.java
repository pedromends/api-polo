package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Campus;
import com.ifmg.apipolo.entity.Contact;
import com.ifmg.apipolo.repository.ContactRepository;
import com.ifmg.apipolo.vo.CampusVO;
import com.ifmg.apipolo.vo.ContactVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void createContact(ContactVO contactVO) {
        Contact contact = new Contact();

        contact.setName(contactVO.getName());
        contact.setEmail(contactVO.getEmail());
        contact.setPosition(contactVO.getPosition());
        contact.setPhoneNumber(contactVO.getPhoneNumber());
        contact.setMessage(contactVO.getMessage());
        contact.setArea(contactVO.getArea());
        contact.setExternalCompany(contactVO.getExternalCompany());

        contactRepository.save(contact);
    }

    public List<ContactVO> listContacts() {

        List<ContactVO> listVO = new ArrayList<>();
        List<Contact> list = contactRepository.findUnreaded();

        for(Contact contact : list) {
            listVO.add(new ContactVO(contact));
        }
        return listVO;
    }

    public List<ContactVO> getAll() {

        List<ContactVO> listVO = new ArrayList<>();
        List<Contact> list = contactRepository.findAllCustom();

        for(Contact contact : list) {
            listVO.add(new ContactVO(contact));
        }
        return listVO;
    }

    public void readNotification(Long notificationId) {

        var contact = contactRepository.findById(notificationId);

        contact.get().setReaded(true);
        contactRepository.save(contact.get());
    }
}
