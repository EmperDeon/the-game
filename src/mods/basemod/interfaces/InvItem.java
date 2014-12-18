/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mods.basemod.interfaces;

import mods.basemod.containers.Mid;

/**
 *
 * @author ilya
 */
public interface InvItem extends Base {

 public String getParam ( String k );

 public String getAllP();
 
 public void addParam(String k, String v);
 
 public void addAllP(String[] p);
 
 @Override
 public Mid getId ();
 
 
}
