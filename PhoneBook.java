package Phonebook;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    private Set<Contact> contacts;
    private Map<String,Set<Contact>> byName;

    public PhoneBook(){
        //contacts = new TreeSet<>((contact1, contact2) -> contact1.getName().compareTo(contact2.getName()));
        contacts = new TreeSet<>(Comparator.comparing(Contact::getName).thenComparing(Contact::getNumber));
        byName = new HashMap<>();
    }

    void addContact(String name, String number) throws DuplicateNumberException {
        Contact contact = new Contact(name, number);
        if(!contacts.add(contact))
            throw new DuplicateNumberException(number);
        if(!byName.containsKey(name)){
            byName.put(name, new HashSet<>());
        }
        byName.get(name).add(contact);
    }

    void contactsByNumber(String number) {
        if (number.length() < 3)
            return;
        List<Contact> temp = contacts.stream()
                                    .filter(contact -> contact.getNumber().contains(number))
                                    .collect(Collectors.toList());
        if(temp.size() == 0){
            System.out.println("NOT FOUND");
        } else {
            temp.stream()
                    .forEach(contact -> System.out.println(contact));
            //.forEach(System.out::println);
        }
    }

    void contactsByName(String name){
        byName.get(name).stream()
                        .forEach(contact -> System.out.println(contact));
    }
}
