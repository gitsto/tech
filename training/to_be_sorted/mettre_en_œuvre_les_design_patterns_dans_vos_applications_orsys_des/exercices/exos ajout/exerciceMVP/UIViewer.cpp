//
//
//  Generated by StarUML(tm) C++ Add-In
//
//  @ Project : Untitled
//  @ File Name : UIViewer.cpp
//  @ Date : 23/08/2009
//  @ Author : 
//
//
#include <iostream>
#include <string>
using namespace std;

#include "UIViewer.h"

void UIViewer::UIViewer() {
    presenter = new Presenter(this);
}

void UIViewer::start() {
    presenter->start();
}

void UIViewer::callRequestUserCode() {
    loadListTypes(presenter->LoadListType());
    cout <<"Introduisez le code utilisateur:"<<endl;
    string rep;
    cin >> rep;
    presenter->set_code(rep) ;
    if (presenter->Validate())
    {
        presenter->CodeInputEnded();
    }
    else
    {
       cout <<"donnees non valides"<<endl;
       char c;
       cin >> c;
    }

}

void UIViewer::callDisplayUserProfile() {
    cout << "Utilisateur: " << presenter->get_code()<<endl;
    cout << "  Nom: "<< presenter->get_nom() <<endl;
    cout << "  Pr�nom: "<< presenter->get_prenom() <<endl;
    cout << "Appuyez sur ENTER pour quitter"<<endl;
    char c;
    cin >> c;

}

void UIViewer::displayListTypes(list<string> t) {
    cout <<"Tapez le num�ro du type choisi:"<<endl;
    for (int i=0; i<types.Count; i++)
    {
        cout <<i<<" "<< types[i]<<endl;
    }
    int rep;
    cin >> rep;
    presenter->set_type( types[rep]);

}

